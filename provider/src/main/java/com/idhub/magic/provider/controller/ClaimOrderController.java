package com.idhub.magic.provider.controller;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.common.parameter.MagicResponse;
import com.idhub.magic.provider.kyc.idmind.Interaction;
import com.idhub.magic.provider.model.IdentityEntity;
import com.idhub.magic.provider.model.ProviderOrder;
import com.idhub.magic.provider.model.ProviderOrderState;
import com.idhub.magic.provider.model.VerifiableClaimEntity;
import com.idhub.magic.provider.service.BlockchainService;
import com.idhub.magic.provider.service.OrderRepository;


@RestController
@RequestMapping("/order")
public class ClaimOrderController {
	@Autowired
	OrderRepository rep;
	@Autowired
	Datastore ds;
	@Autowired
	BlockchainService blockchainService;
	@GetMapping("/list")
	public MagicResponse<List<ProviderOrder>> list(ProviderOrderState state, int startPage, int pageSize) {
		List<ProviderOrder> orders = rep.list(state, startPage, pageSize);
		return new MagicResponse<List<ProviderOrder>>(orders);
		
	}
	@GetMapping("/size")
	public MagicResponse<Integer> size(ProviderOrderState state) {
		int size = rep.size(state);
		return new MagicResponse<Integer>(size);
		
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
	@GetMapping("/receive")
	public MagicResponse<IdentityEntity> receive(String orderId) {
		
		
		IdentityEntity id = rep.receive(orderId);
		
		return new MagicResponse<IdentityEntity>(id);
		
	}
	@GetMapping("/drop")
	public MagicResponse drop(String orderId) {
		
		
		rep.drop(orderId);
		
		return new MagicResponse();
		
	}
	@GetMapping("/refuse_claim")
	public MagicResponse refuseClaim(String orderId) {
		
		
		rep.refuseClaim(orderId);
		
		return new MagicResponse();
		
	}
	@GetMapping("/issue_claim")
	public MagicResponse<String> issueClaim(String orderId) {
		
		
		VerifiableClaimEntity claim = rep.issueClaim(orderId);
		blockchainService.publishTo780(claim.getClaim());
		return new MagicResponse(claim.getJsonld());
		
	}
}
