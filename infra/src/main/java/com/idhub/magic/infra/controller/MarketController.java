package com.idhub.magic.infra.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.infra.model.IssueProject;
import com.idhub.magic.infra.model.ProjectStatus;

@CrossOrigin
@RestController
@RequestMapping("/market")
public class MarketController {
	@Autowired
	Datastore ds;
	@GetMapping("/list")
	public MagicResponse<List<IssueProject>> search() {
		
		List<IssueProject> rst = null;
		return new MagicResponse<List<IssueProject>>(rst);
	}
}
