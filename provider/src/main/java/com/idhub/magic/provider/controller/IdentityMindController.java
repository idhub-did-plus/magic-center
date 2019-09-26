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
	@Autowired SimpleStorageService simpleStorageService;
	@GetMapping("/evaluate")
	public MagicResponse<Interaction> evaluate(String orderId) {
		Interaction inter = service.evaluate(orderId);
			
		return new MagicResponse(inter);
	}
	@GetMapping("/document_verification")
	public MagicResponse documentVerification(String orderId, String materialId,String description) throws Exception {
		ProviderOrder order = ds.find(ProviderOrder.class, "id", orderId).get();
		IdentityEntity iden = ds.find(IdentityEntity.class, "id", order.getOrder().identity).get();
		String tid = iden.getTransactionId();
		if(tid == null)
			return new MagicResponse(false, "please call evaluate first!");
		Material mat = find(iden, materialId);
		byte[] data = this.simpleStorageService.get(materialId);
		MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", materialId + "." + mat.getExt(), RequestBody.create(MediaType.parse("image/*"), data));
		idm.getCustomerService().uploadDodumentVerification(tid, description,  filePart).execute().body();
		return new MagicResponse();
	}
	Material find(IdentityEntity iden, String id) {
		for(Material m : iden.getData().getMaterials()) {
			if(m.getId().equals(id))
				return m;
			
		}
		return null;
	}
}
