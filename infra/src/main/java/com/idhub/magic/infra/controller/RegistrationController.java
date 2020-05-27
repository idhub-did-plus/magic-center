package com.idhub.magic.infra.controller;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.infra.model.AgentAdvancedInformation;
import com.idhub.magic.infra.model.AgentStatus;
import com.idhub.magic.infra.model.IssueAgent;

@CrossOrigin
@RestController
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	Datastore ds;

	@GetMapping("/signup")
	public MagicResponse signup(IssueAgent agent) {
		agent.setStatus(AgentStatus.auditing.name());
		agent.setCreateTime(new Date());
		ds.save(agent);
		return new MagicResponse();
	}
	@GetMapping("/signup")
	public MagicResponse complete(String id, AgentAdvancedInformation agent) {
		Query<IssueAgent> query = ds.createQuery(IssueAgent.class).field("id").equal(id);
		UpdateOperations<IssueAgent> operations = ds.createUpdateOperations(IssueAgent.class).set("advancedInformation", agent);
		ds.update(query, operations);
		ds.save(agent);
		return new MagicResponse();
	}

	@GetMapping("/list_page")
	public MagicResponse<List<IssueAgent>> listPage(AgentStatus status, int startPage, int pageSize) {

		List<IssueAgent> rst = page(status, startPage, pageSize);
		return new MagicResponse<List<IssueAgent>>(rst);
	} 

	@GetMapping("/list")
	public MagicResponse<List<IssueAgent>> list(AgentStatus status) {

		List<IssueAgent> rst = all(status);
		return new MagicResponse<List<IssueAgent>>(rst);
	}
	
	

	public List<IssueAgent> page(AgentStatus status, int startPage, int pageSize) {
		Query<IssueAgent> query = ds.createQuery(IssueAgent.class).field("status").equal(status.name())
				.offset(startPage * pageSize).limit(pageSize).order("createTime");

		return query.asList();
	}

	public List<IssueAgent> all( AgentStatus status) {
		Query<IssueAgent> query = ds.createQuery(IssueAgent.class).field("status").equal(status.name())
				.order("createTime");
	
		return query.asList();
	}
	@GetMapping("/audit")
	public MagicResponse audit(String aid, boolean agree, String comment) {
		Subject sub = SecurityUtils.getSubject();
		String identity = sub == null ? null : (String) sub.getPrincipal();
		Query<IssueAgent> query = ds.createQuery(IssueAgent.class).field("id").equal(aid);
		AgentStatus st = agree?AgentStatus.passed:AgentStatus.denied;
		UpdateOperations<IssueAgent> operations = ds.createUpdateOperations(IssueAgent.class).set("status", st);
		ds.update(query, operations);
		return new MagicResponse();
	}
}
