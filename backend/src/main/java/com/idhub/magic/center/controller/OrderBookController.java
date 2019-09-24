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
import com.idhub.magic.provider.IdentityData;
import com.idhub.magic.provider.Order;
import com.idhub.magic.provider.interfaces.OrderBook;
import com.idhub.magic.verifiablecredentials.VerifiableCredential;
@RestController
@RequestMapping("/orderbook")
public class OrderBookController implements OrderBook {
	@Autowired OrderBookService store;

    @GetMapping("/directed")
	@Override
	public List<Order> tome(String identity) {
		return store.tome(identity);
	}
    @GetMapping("/receive")
	@Override
	public boolean receive(String identity, String orderId) {
		// TODO Auto-generated method stub
    	return store.receive(identity,orderId);
	}
    @GetMapping("/list_all")
	@Override
	public List<Order> listAll() {
		// TODO Auto-generated method stub
		return store.listAll();
	}
    @PostMapping("/issue_claim")
	@Override
	public void issueClaim(String identity,String orderId,@RequestBody String credential) {
		store.issueClaim(identity, orderId, credential);
		
	}
    @GetMapping("/get_identity_information")
	@Override
	public IdentityData getIdentityInformation(String identity,String targetIdentity) {
		// TODO Auto-generated method stub
		return store.getIdentityInformation(identity, targetIdentity);
	}
    @GetMapping("/refuse_claim")
   	@Override
	public void refuseClaim(String identity, String orderId) {
		store.refuseClaim(identity, orderId);
		
	}

}
