package com.idhub.magic.provider.controller;

import java.io.IOException;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.center.kvc.entity.ClaimOrder;
import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.provider.Order;
import com.idhub.magic.provider.kyc.idmind.ConsumerService;
import com.idhub.magic.provider.kyc.idmind.Converter;
import com.idhub.magic.provider.kyc.idmind.IdentityMindProvider;
import com.idhub.magic.provider.kyc.idmind.Interaction;
import com.idhub.magic.provider.kyc.idmind.entity.consumer.ConsumerKycRequest;
import com.idhub.magic.provider.kyc.idmind.entity.consumer.ConsumerKycResponse;
import com.idhub.magic.provider.model.IdentityEntity;
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
