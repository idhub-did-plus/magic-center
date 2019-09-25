package com.idhub.magic.center.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.center.service.MagicEventStore;
import com.idhub.magic.center.service.OrderBookService;
import com.idhub.magic.center.service.VerifiableCredentialService;
import com.idhub.magic.common.parameter.MagicResponse;
import com.idhub.magic.provider.IdentityData;
import com.idhub.magic.provider.Order;
import com.idhub.magic.provider.interfaces.OrderBook;
import com.idhub.magic.provider.interfaces.OrderBookWrapper;
import com.idhub.magic.verifiablecredentials.VerifiableCredential;
@RestController
@RequestMapping("/orderbook")
public class OrderBookController implements OrderBookWrapper {
	@Autowired OrderBookService store;

    @GetMapping("/directed")
	@Override
	public MagicResponse<List<Order>> tome(String identity) {
		return new MagicResponse<List<Order>>(store.tome(identity));
	}
    @GetMapping("/receive")
	@Override
	public MagicResponse receive(String identity, String orderId) {
		// TODO Auto-generated method stub
    	 store.receive(identity,orderId);
    	 return new MagicResponse();
	}
    @GetMapping("/list_all")
	@Override
	public MagicResponse<List<Order>> listAll() {
		// TODO Auto-generated method stub
		return new  MagicResponse<List<Order>>(store.listAll());
		
	}
    @PostMapping("/issue_claim")
	@Override
	public MagicResponse issueClaim(String identity,String orderId,@RequestBody String credential) {
		store.issueClaim(identity, orderId, credential);
		return new MagicResponse();
		
	}
    @GetMapping("/get_identity_information")
	@Override
	public MagicResponse<IdentityData> getIdentityInformation(String identity,String orderId) {
		// TODO Auto-generated method stub
		return new MagicResponse<IdentityData>(store.getIdentityInformation(identity, orderId));
	}
    @GetMapping("/refuse_claim")
   	@Override
	public MagicResponse refuseClaim(String identity, String orderId) {
		store.refuseClaim(identity, orderId);
		return new MagicResponse();
		
	}

}
