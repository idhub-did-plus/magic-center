package com.idhub.magic.center.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idhub.magic.center.entity.OrderEntity;
import com.idhub.magic.center.entity.OrderState;
import com.idhub.magic.center.ustorage.entity.Material;
import com.idhub.magic.provider.Order;
import com.idhub.magic.provider.interfaces.OrderBook;
import com.idhub.magic.verifiablecredentials.VerifiableCredential;
@Service
public class OrderBookService implements OrderBook{
	@Autowired Datastore ds;
	@Override
	public List<Order> tome(String providerIdentity) {
		List<OrderEntity> es = ds.createQuery(OrderEntity.class).field("order.directTo").equal(providerIdentity).asList();
		List<Order> rst = es.stream().map(OrderEntity::getOrder).collect(toList());
		return rst;
	}

	@Override
	public boolean receive(String identity, String orderId) {
		 Query<OrderEntity> query = ds.createQuery(OrderEntity.class).field("id").equal(orderId).field("provider").doesNotExist();
		 UpdateOperations<OrderEntity> op = ds.createUpdateOperations(OrderEntity.class).set("provider", identity).set("state", OrderState.relayed.name());
		 UpdateResults n = ds.update(query, op);
		 if(n.getUpdatedCount() == 0)
			 return false;
		 return true;
	}

	@Override
	public List<Order> listAll() {
		List<OrderEntity> es = ds.createQuery(OrderEntity.class).asList();
		List<Order> rst = es.stream().map(OrderEntity::getOrder).collect(toList());
		return rst;
	}

	@Override
	public void issueClaim(VerifiableCredential credential) {
		
	}

}
