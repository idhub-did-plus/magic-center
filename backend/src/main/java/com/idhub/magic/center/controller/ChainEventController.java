package com.idhub.magic.center.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.center.event.ChainEvent;
import com.idhub.magic.center.parameter.MagicResponse;
import com.idhub.magic.center.service.ChainEventStore;
import com.idhub.magic.center.ustorage.entity.IdentityArchive;

@RestController
@RequestMapping("/event")

public class ChainEventController {
	@Autowired ChainEventStore store;
    @GetMapping("/query_events")
	public MagicResponse getChainEvent(String identity) {
    	List<ChainEvent> data = store.getEventsByIdentity(identity);
		MagicResponse<List<ChainEvent>> resp = new MagicResponse<List<ChainEvent>>(data);
		return resp;
	}

	
}
