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
import com.idhub.magic.infra.model.IssuranceRecord;
import com.idhub.magic.infra.model.ProjectMaterial;
import com.idhub.magic.infra.model.ProjectStatus;
import com.idhub.magic.infra.model.TokenConfig;
import com.idhub.magic.infra.service.SimpleStorageService;

@CrossOrigin
@RestController
@RequestMapping("/issurance")
public class IssuranceController {

	@Autowired
	Datastore ds;
	private SimpleStorageService simpleStorageService;
	@GetMapping("/record")
	public MagicResponse record(IssuranceRecord rec) {
		Subject sub = SecurityUtils.getSubject();
		String identity = sub == null ? null : (String) sub.getPrincipal();
		ds.save(rec);
		return new MagicResponse(rec);
	}
	@GetMapping("/list_page")
	public MagicResponse<List<IssuranceRecord>> listPage(String pid, int startPage, int pageSize) {
		Subject sub = SecurityUtils.getSubject();
		String identity = sub == null ? null : (String) sub.getPrincipal();
		Query<IssuranceRecord> query = ds.createQuery(IssuranceRecord.class).field("projectId").equal(pid)
				.offset(startPage * pageSize).limit(pageSize).order("time");
		List<IssuranceRecord> rst = query.asList();
		return new MagicResponse<List<IssuranceRecord>>(rst);
	}

	@GetMapping("/list")
	public MagicResponse<List<IssuranceRecord>> list(String pid) {
		Subject sub = SecurityUtils.getSubject();
		String identity = sub == null ? null : (String) sub.getPrincipal();
		List<IssuranceRecord> rst = listAll(pid);
		return new MagicResponse<List<IssuranceRecord>>(rst);
	}

	
	public List<IssuranceRecord> listAll(String pid) {
		Query<IssuranceRecord> query = ds.createQuery(IssuranceRecord.class).field("projectId").equal(pid)
				.order("time");

		return query.asList();
	}

	@GetMapping("/size")
	public MagicResponse<Long> size(String pid) {
		Subject sub = SecurityUtils.getSubject();
		String identity = sub == null ? null : (String) sub.getPrincipal();

		Query<IssuranceRecord> query = ds.createQuery(IssuranceRecord.class).field("projectId").equal(pid);
		return new MagicResponse<Long>(query.countAll());
	}

	
}
