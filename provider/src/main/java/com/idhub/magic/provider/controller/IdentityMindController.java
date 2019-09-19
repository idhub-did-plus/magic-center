package com.idhub.magic.provider.controller;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.common.parameter.MagicResponse;
import com.idhub.magic.provider.kyc.idmind.Interaction;
import com.idhub.magic.provider.service.IdentityMindService;


@RestController
@RequestMapping("/idm")
public class IdentityMindController {
	@Autowired
	IdentityMindService service;
	@Autowired
	Datastore ds;

	@GetMapping("/evaluate")
	public MagicResponse<Interaction> evaluate(String orderId) {
		Interaction inter = service.evaluate(orderId);
			
		return new MagicResponse(inter);
	}
}
