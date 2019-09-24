package com.idhub.magic.center.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idhub.magic.center.annotation.DoNotAuth;
import com.idhub.magic.center.service.MagicEventStore;
import com.idhub.magic.common.contracts.IdentityRegistryInterface.IdentityCreatedEventResponse;
import com.idhub.magic.common.event.MagicEvent;
import com.idhub.magic.common.event.MagicEventType;
import com.idhub.magic.common.parameter.MagicResponse;
import com.idhub.magic.common.ustorage.entity.IdentityArchive;
import com.idhub.magic.common.ustorage.entity.Material;

@RestController
@RequestMapping("/event")

public class EventController {
	@Autowired MagicEventStore store;
    @GetMapping("/query_events")
    @DoNotAuth
	public MagicResponse getChainEvent(String identity) {
    	List<MagicEvent> data = store.getEventsByIdentity(identity);
		MagicResponse<List<MagicEvent>> resp = new MagicResponse<List<MagicEvent>>(data);
		return resp;
	}

    @GetMapping("/dummy_event")
	public MagicResponse dummyEvent(String identity) {
    	
    	IdentityCreatedEventResponse e = new IdentityCreatedEventResponse();
    	store.store(MagicEventType.chain_event, identity, e);
		return new MagicResponse();
	}

}
