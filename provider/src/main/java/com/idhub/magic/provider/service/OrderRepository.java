package com.idhub.magic.provider.service;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idhub.magic.provider.Order;

@Service
public class OrderRepository {
	@Autowired Datastore ds;

	public void store(List<Order> my) {
		for(Order o : my) {
			ProviderOrder po = new ProviderOrder(o);
			ds.save(po);
		}
		
	}
	
}
