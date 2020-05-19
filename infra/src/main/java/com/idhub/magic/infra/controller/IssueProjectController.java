package com.idhub.magic.infra.controller;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.idhub.magic.infra.model.DeployedToken;
import com.idhub.magic.infra.model.IssueProject;
import com.idhub.magic.infra.model.IssuerInformation;
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
		List<IssueProject> rst = listPage(identity, ProjectStatus.deployed, startPage, pageSize);
		return new MagicResponse<List<IssueProject>>(rst);
	}

	@GetMapping("/list")
	public MagicResponse<List<IssueProject>> list(ProjectStatus status) {
		Subject sub = SecurityUtils.getSubject();
		String identity = sub == null ? null : (String) sub.getPrincipal();
		List<IssueProject> rst = listAll(identity, ProjectStatus.deployed);
		return new MagicResponse<List<IssueProject>>(rst);
	}

	public List<IssueProject> listPage(String identity, ProjectStatus state, int startPage, int pageSize) {
		Query<IssueProject> query = ds.createQuery(IssueProject.class).field("state").equal(state.name())
				.offset(startPage * pageSize).limit(pageSize).order("createTime");
		if (identity != null)
			query.field("owner").equal(identity);
		return query.asList();
	}

	public List<IssueProject> listAll(String identity, ProjectStatus state) {
		Query<IssueProject> query = ds.createQuery(IssueProject.class).field("state").equal(state.name())
				.order("createTime");
		if (identity != null)
			query.field("owner").equal(identity);
		return query.asList();
	}

	@GetMapping("/size")
	public MagicResponse<Long> size(String identity, ProjectStatus state) {
		Query<IssueProject> query = ds.createQuery(IssueProject.class).field("state").equal(state.name());
		if (identity != null)
			query.field("owner").equal(identity);
		return new MagicResponse<Long>(query.countAll());
	}

	@PostMapping("/save_issuer_information")
	public MagicResponse saveIssuerInformation(String pid, IssuerInformation info) {
		return new MagicResponse<IssueProject>();
	}

	@PostMapping("/save_token_config")
	public MagicResponse saveTokenConfig(String pid, TokenConfig tokenConfig) {
		return new MagicResponse<IssueProject>();
	}


	@GetMapping("/audit")
	public MagicResponse audit(boolean agree, String comment) {
		Subject sub = SecurityUtils.getSubject();
		String identity = sub == null ? null : (String) sub.getPrincipal();

		return new MagicResponse();
	}
	@GetMapping("/token_deployed")
	public MagicResponse tokenDeployed(String pid, DeployedToken dt) {
		Subject sub = SecurityUtils.getSubject();
		String identity = sub == null ? null : (String) sub.getPrincipal();

		return new MagicResponse();
	}
}
