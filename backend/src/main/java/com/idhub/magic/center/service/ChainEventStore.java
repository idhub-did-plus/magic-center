package com.idhub.magic.center.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idhub.magic.center.entity.EventWrapper;
import com.idhub.magic.center.event.MagicEvent;

@Service
public class ChainEventStore {
	@Autowired Datastore store;
	public void store(MagicEvent event, String identity) {
		EventWrapper wrapper = new EventWrapper(identity, event);
		store.save(wrapper);
	}
	public List<MagicEvent> getEventsByIdentity(String identity){
		Query<EventWrapper> query = store.createQuery(EventWrapper.class).field("identity").equal(identity).field("sent").equal(true);
		List<EventWrapper> data = query.asList();
		UpdateOperations<EventWrapper> operations = store.createUpdateOperations(EventWrapper.class).set("sent", true);
		store.update(query, operations);
		List<MagicEvent> rst = data.stream().map(e->e.getEvent()).collect(Collectors.toList());
		return rst;
	}

}
