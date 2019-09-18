package com.idhub.magic.provider.controller;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.provider.kyc.idmind.Interaction;
import com.idhub.magic.provider.model.IdentityEntity;
import com.idhub.magic.provider.model.ProviderOrder;
import com.idhub.magic.provider.model.ProviderOrderState;
import com.idhub.magic.provider.service.OrderRepository;


@RestController
@RequestMapping("/order")
public class ClaimOrderController {
	@Autowired
	OrderRepository rep;
	@Autowired
	Datastore ds;
	@GetMapping("/list")
	public MagicResponse<List<ProviderOrder>> list(ProviderOrderState state, int startPage, int pageSize) {
		List<ProviderOrder> orders = rep.list(state, startPage, pageSize);
		return new MagicResponse<List<ProviderOrder>>(orders);
		
	}
	@GetMapping("/identity_archive")
	public MagicResponse<IdentityEntity> identityArchive(String identity) {
		IdentityEntity iden = ds.find(IdentityEntity.class, "id", identity).get();
		return new MagicResponse<IdentityEntity>(iden);
		
	}
	@GetMapping("/interactions")
	public MagicResponse<List<Interaction>> interactions(String orderId) {
		 List<Interaction> iden = ds.find(Interaction.class, "orderId", orderId).asList();
		return new MagicResponse<List<Interaction>>(iden);
		
	}
}
