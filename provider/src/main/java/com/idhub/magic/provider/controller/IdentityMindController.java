package com.idhub.magic.provider.controller;

import java.io.IOException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.common.parameter.MagicResponse;
import com.idhub.magic.common.ustorage.entity.Material;
import com.idhub.magic.provider.kyc.idmind.IdentityMindProvider;
import com.idhub.magic.provider.kyc.idmind.Interaction;
import com.idhub.magic.provider.model.IdentityEntity;
import com.idhub.magic.provider.model.ProviderOrder;
import com.idhub.magic.provider.service.IdentityMindService;
import com.idhub.magic.provider.service.SimpleStorageService;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


@RestController
@RequestMapping("/idm")
public class IdentityMindController {
	@Autowired
	IdentityMindService service;
	@Autowired
	Datastore ds;
	@Autowired
	IdentityMindProvider idm;

	@GetMapping("/evaluate")
	public MagicResponse<Interaction> evaluate(String orderId) {
		Interaction inter = service.evaluate(orderId);
			
		return new MagicResponse(inter);
	}
	@GetMapping("/document_verification")
	public MagicResponse documentVerification(String orderId, String materialId,String description) throws Exception {
		service.documentVerification(orderId, materialId, description);
		return new MagicResponse();
	}
	
	
}
