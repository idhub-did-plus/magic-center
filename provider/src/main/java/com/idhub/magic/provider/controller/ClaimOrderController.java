package com.idhub.magic.provider.controller;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.provider.model.ProviderOrder;


@RestController
@RequestMapping("/order")
public class ClaimOrderController {
	@Autowired
	Datastore ds;

	@GetMapping("/list")
	public MagicResponse<List<ProviderOrder>> list() {
		return new MagicResponse<List<ProviderOrder>>();
		
	}
}
