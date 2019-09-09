package com.idhub.magic.center.service;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idhub.magic.center.entity.OrderEntity;
import com.idhub.magic.center.kvc.entity.ClaimOrder;
import com.idhub.magic.provider.Order;

@Service
public class ClaimOrderService {
	@Autowired Datastore ds;
	public void relay(ClaimOrder order) {
		Order o = new Order();
		o.identity = order.identity;
		o.claimType = order.requestedClaimType;
		OrderEntity oe = new OrderEntity(o);
		oe.setDirectTo(ClaimProviderService.defaultProvider);
		ds.save(oe);
	}

}
