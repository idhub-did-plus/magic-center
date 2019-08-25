package com.idhub.magic.center.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.center.event.MagicEvent;
import com.idhub.magic.center.kvc.entity.KycOrder;
import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.center.service.ChainEventStore;
import com.idhub.magic.center.ustorage.entity.IdentityArchive;
import com.idhub.magic.center.ustorage.entity.Material;

@RestController
@RequestMapping("/kyc")

public class KycController {
	@Autowired ChainEventStore store;
    @GetMapping("/order")
	public MagicResponse order(KycOrder order, String identity) {
    	
		return new MagicResponse();
	}

	
}
