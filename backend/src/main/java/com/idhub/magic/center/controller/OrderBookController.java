package com.idhub.magic.center.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.center.service.ChainEventStore;
import com.idhub.magic.center.service.OrderBookService;
import com.idhub.magic.center.service.VerifiableCredentialService;
import com.idhub.magic.provider.Order;
import com.idhub.magic.provider.interfaces.OrderBook;
import com.idhub.magic.verifiablecredentials.VerifiableCredential;
@RestController
@RequestMapping("/orderbook")
public class OrderBookController implements OrderBook {
	@Autowired OrderBookService store;
	@Autowired VerifiableCredentialService vcService;
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
    @GetMapping("/list-all")
	@Override
	public List<Order> listAll() {
		// TODO Auto-generated method stub
		return store.listAll();
	}
    @GetMapping("/issue-claim")
	@Override
	public void issueClaim(String identity,VerifiableCredential credential) {
		vcService.store(credential);
		
	}

}
