package com.idhub.magic.center.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.center.ustorage.entity.IdentityArchive;

@RestController
@RequestMapping("/storage")

public class ChainEventController {

    @PostMapping("/storeArchive")
	public MagicResponse storeArchive(@RequestBody IdentityArchive archive) {
    	
		return new MagicResponse();
	}

	
}
