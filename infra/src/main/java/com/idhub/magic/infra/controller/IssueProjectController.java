package com.idhub.magic.infra.controller;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.idhub.magic.infra.model.DeployedToken;
import com.idhub.magic.infra.model.IssueProject;
import com.idhub.magic.infra.model.IssuerInformation;
import com.idhub.magic.infra.model.ProjectDetail;
import com.idhub.magic.infra.model.ProjectMaterial;
import com.idhub.magic.infra.model.ProjectStatus;
import com.idhub.magic.infra.model.TokenConfig;
import com.idhub.magic.infra.service.SimpleStorageService;

@CrossOrigin
@RestController
@RequestMapping("/issue_project")
public class IssueProjectController {

	@Autowired
	Datastore ds;
	private SimpleStorageService simpleStorageService;

	@GetMapping("/list_page")
	public MagicResponse<List<IssueProject>> listPage(ProjectStatus status, int startPage, int pageSize) {
		Subject sub = SecurityUtils.getSubject();
		String identity = sub == null ? null : (String) sub.getPrincipal();
		List<IssueProject> rst = listPage(identity, status, startPage, pageSize);
		return new MagicResponse<List<IssueProject>>(rst);
	} 

	@GetMapping("/list")
	public MagicResponse<List<IssueProject>> list(ProjectStatus status) {
		Subject sub = SecurityUtils.getSubject();
		String identity = sub == null ? null : (String) sub.getPrincipal();
		boolean isManager = sub.hasRole("complianceManager");
		List<IssueProject> rst = isManager?listAll(status): listMy(identity, status);
		return new MagicResponse<List<IssueProject>>(rst);
	}

	public List<IssueProject> listPage(String identity, ProjectStatus status, int startPage, int pageSize) {
		Query<IssueProject> query = ds.createQuery(IssueProject.class).field("status").equal(status.name())
				.offset(startPage * pageSize).limit(pageSize).order("createTime");
		if (identity != null)
			query.field("agentIdentity").equal(identity);
		return query.asList();
	}
	public List<IssueProject> listAll( ProjectStatus status) {
		Query<IssueProject> query = ds.createQuery(IssueProject.class).field("status").equal(status.name())
				.order("createTime");

		return query.asList();
	}
	public List<IssueProject> listMy(String identity, ProjectStatus status) {
		Query<IssueProject> query = ds.createQuery(IssueProject.class).field("status").equal(status.name())
				.order("createTime");
		if (identity != null)
			query.field("agentIdentity").equal(identity);
		return query.asList();
	}

	@GetMapping("/size")
	public MagicResponse<Long> size( ProjectStatus status) {
		Subject sub = SecurityUtils.getSubject();
		String identity = sub == null ? null : (String) sub.getPrincipal();

		Query<IssueProject> query = ds.createQuery(IssueProject.class).field("status").equal(status.name());
		if (identity != null)
			query.field("agentIdentity").equal(identity);
		return new MagicResponse<Long>(query.countAll());
	}

	@PostMapping("/save_issuer_information")
	public MagicResponse saveIssuerInformation(String pid, @RequestBody IssuerInformation info) {
		Subject sub = SecurityUtils.getSubject();
		String identity = sub == null ? null : (String) sub.getPrincipal();
		pid = trim(pid);
		if(pid == null) {
			IssueProject p = new IssueProject();
			p.setAgentIdentity(identity);
			ds.save(p);
			pid = p.getId();
		}
		ObjectId objectId = new ObjectId(pid);
		Query<IssueProject> query = ds.createQuery(IssueProject.class).field("id").equal(objectId);
		UpdateOperations<IssueProject> operations = ds.createUpdateOperations(IssueProject.class).set("issuerInformation", info);
		ds.update(query, operations);
		return new MagicResponse(pid);
	}
	@PostMapping("/save_project_detail")
	public MagicResponse saveProjectDetail(String pid,@RequestBody ProjectDetail info) {
		pid = trim(pid);
		Subject sub = SecurityUtils.getSubject();
		String identity = sub == null ? null : (String) sub.getPrincipal();
		if(pid == null) {
			IssueProject p = new IssueProject();
			p.setAgentIdentity(identity);
			ds.save(p);
			pid = p.getId();
		}
		ObjectId objectId = new ObjectId(pid);
		Query<IssueProject> query = ds.createQuery(IssueProject.class).field("id").equal(objectId);
		
		UpdateOperations<IssueProject> operations = ds.createUpdateOperations(IssueProject.class).set("projectDetail", info);
		UpdateResults r = ds.update(query, operations);
		int i = r.getUpdatedCount();

		return new MagicResponse(pid);
	}
	String trim(String s) {
		if(s == null)
			return null;
		s = s.trim();
		if(s.isEmpty())
			return null;
		if(s.equals("null"))
			return null;
		return s;
	}
	@PostMapping("/save_token_config")
	public MagicResponse saveTokenConfig(String pid,@RequestBody TokenConfig tokenConfig) {
		ObjectId objectId = new ObjectId(pid);
		Query<IssueProject> query = ds.createQuery(IssueProject.class).field("id").equal(objectId);
		UpdateOperations<IssueProject> operations = ds.createUpdateOperations(IssueProject.class).set("tokenConfig", tokenConfig);
		ds.update(query, operations);
		return new MagicResponse(pid);
	}


	@GetMapping("/audit")
	public MagicResponse audit(String pid, boolean agree, String comment) {
		Subject sub = SecurityUtils.getSubject();
		String identity = sub == null ? null : (String) sub.getPrincipal();
		ObjectId objectId = new ObjectId(pid);
		Query<IssueProject> query = ds.createQuery(IssueProject.class).field("id").equal(objectId);
		ProjectStatus st = agree?ProjectStatus.audit_passed:ProjectStatus.audit_denied;
		UpdateOperations<IssueProject> operations = ds.createUpdateOperations(IssueProject.class).set("status", st);
		ds.update(query, operations);
		return new MagicResponse();
	}
	@PostMapping("/token_deployed")
	public MagicResponse tokenDeployed(String pid, @RequestBody  DeployedToken dt) {
		Subject sub = SecurityUtils.getSubject();
		String identity = sub == null ? null : (String) sub.getPrincipal();
		ObjectId objectId = new ObjectId(pid);
		Query<IssueProject> query = ds.createQuery(IssueProject.class).field("id").equal(objectId);
		UpdateOperations<IssueProject> operations = ds.createUpdateOperations(IssueProject.class).set("deployedToken", dt).set("status", ProjectStatus.deployed);
		ds.update(query, operations);
		return new MagicResponse();
	}
	@GetMapping("/test")
	public MagicResponse test() {
		Subject sub = SecurityUtils.getSubject();
		String identity = sub == null ? null : (String) sub.getPrincipal();
		IssueProject p = new IssueProject();
		p.setAgentIdentity(identity);
		ds.save(p);
		
		return new MagicResponse(p);
	}
}
