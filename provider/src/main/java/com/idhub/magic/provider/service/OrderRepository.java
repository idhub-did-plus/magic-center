package com.idhub.magic.provider.service;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idhub.magic.provider.Order;
import com.idhub.magic.provider.model.ProviderOrder;
import com.idhub.magic.provider.model.ProviderOrderState;
@Service
public class OrderRepository {
	@Autowired Datastore ds;

	public void store(List<Order> my) {
		for(Order o : my) {
			ProviderOrder po = new ProviderOrder(o);
			ds.save(po);
		}
	}
		

	public List<ProviderOrder> list(ProviderOrderState state, int startPage, int pageSize){
		Query<ProviderOrder> query = ds.createQuery(ProviderOrder.class).field("state").equal(state.name()).offset(startPage * pageSize).limit(pageSize).order("createTime");
		return query.asList();
	}
	
}
