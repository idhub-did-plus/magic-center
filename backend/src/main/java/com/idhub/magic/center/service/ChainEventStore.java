package com.idhub.magic.center.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idhub.magic.center.entity.ChainEventWrapper;
import com.idhub.magic.center.event.ChainEvent;

@Service
public class ChainEventStore {
	@Autowired Datastore store;
	public void store(ChainEvent event, String identity) {
		ChainEventWrapper wrapper = new ChainEventWrapper(identity, event);
		store.save(wrapper);
	}
	public List<ChainEvent> getEventsByIdentity(String identity){
		Query<ChainEventWrapper> query = store.createQuery(ChainEventWrapper.class).field("identity").equal(identity).field("sent").equal(true);
		List<ChainEventWrapper> data = query.asList();
		UpdateOperations<ChainEventWrapper> operations = store.createUpdateOperations(ChainEventWrapper.class).set("sent", true);
		store.update(query, operations);
		List<ChainEvent> rst = data.stream().map(e->e.getEvent()).collect(Collectors.toList());
		return rst;
	}

}
