package com.idhub.magic.center.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.abi.Utils;
import com.alibaba.fastjson.JSON;
import com.idhub.magic.center.controller.parameter.CreateIdentityDelegatedParam;
import com.idhub.magic.center.service.DelegationService;

@RestController
@RequestMapping("/delegation")

public class DelegationController {
	@Autowired
	DelegationService delegation;
    @PostMapping("/createIdentity")
	public MagicResponse createIdentity(@RequestBody String json) {
    	CreateIdentityDelegatedParam param = JSON.parseObject(json, CreateIdentityDelegatedParam.class);
    	this.delegation.createEntityDelegated(param);
		return new MagicResponse();
	}

	
}
