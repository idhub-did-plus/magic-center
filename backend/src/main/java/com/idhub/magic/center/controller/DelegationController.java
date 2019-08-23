package com.idhub.magic.center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.idhub.magic.center.parameter.CreateIdentityDelegatedParam;
import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.center.service.DelegationService;

@RestController
@RequestMapping("/delegation")

public class DelegationController {
	@Autowired
	DelegationService delegation;
    @PostMapping("/create_identity")
	public MagicResponse createIdentity(@RequestBody String json) {
    	CreateIdentityDelegatedParam param = JSON.parseObject(json, CreateIdentityDelegatedParam.class);
    	this.delegation.createEntityDelegated(param);
		return new MagicResponse();
	}

	
}
