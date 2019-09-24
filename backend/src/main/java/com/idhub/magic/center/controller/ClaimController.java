package com.idhub.magic.center.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.center.service.MagicEventStore;
import com.idhub.magic.center.service.ClaimOrderService;
import com.idhub.magic.common.claim.entity.ClaimOrder;
import com.idhub.magic.common.event.MagicEvent;
import com.idhub.magic.common.parameter.MagicResponse;
import com.idhub.magic.common.ustorage.entity.IdentityArchive;
import com.idhub.magic.common.ustorage.entity.Material;

@RestController
@RequestMapping("/claim")
public class ClaimController {
	@Autowired ClaimOrderService store;
	
    @PostMapping("/order")
	public MagicResponse order(@RequestBody ClaimOrder order, String identity) {
    	store.relay(order);
		return new MagicResponse();
	}

	
}
