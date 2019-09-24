package com.idhub.magic.center.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idhub.magic.center.entity.EventWrapper;
import com.idhub.magic.common.event.MagicEvent;
import com.idhub.magic.common.event.MagicEventType;

@Service
public class MagicEventStore {
	@Autowired Datastore store;
	ObjectMapper mapper = new ObjectMapper();
	public void store(MagicEventType type, String identity, Object eo) {
		MagicEvent event = new MagicEvent();
		event.eventClass = eo.getClass().getName();
		event.eventType = type.name();
		try {
			byte[] json = mapper.writeValueAsBytes(eo);
			String encoded = Base64.getEncoder().encodeToString(json);
			event.event = encoded;
			EventWrapper wrapper = new EventWrapper(identity, event);
			store.save(wrapper);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
			
		}
		
		
	
	}
	public List<MagicEvent> getEventsByIdentity(String identity){
		Query<EventWrapper> query = store.createQuery(EventWrapper.class).field("identity").equal(identity).field("sent").equal(false);
		List<EventWrapper> data = query.asList();
		UpdateOperations<EventWrapper> operations = store.createUpdateOperations(EventWrapper.class).set("sent", true);
		store.update(query, operations);
		List<MagicEvent> rst = data.stream().map(e->e.getEvent()).collect(Collectors.toList());
		return rst;
	}

}
