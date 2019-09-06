package com.idhub.magic.center.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idhub.magic.center.entity.OrderEntity;
import com.idhub.magic.center.ustorage.entity.Material;
import com.idhub.magic.provider.Order;
import com.idhub.magic.provider.interfaces.OrderBook;
@Service
public class OrderBookService implements OrderBook{
	@Autowired Datastore ds;
	@Override
	public List<Order> tome(String providerIdentity) {
		List<OrderEntity> es = ds.createQuery(OrderEntity.class).field("directTo").equal(providerIdentity).asList();
		List<Order> rst = es.stream().map(OrderEntity::getOrder).collect(toList());
		return rst;
	}

	@Override
	public boolean receive(String orderId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Order> listAll() {
		List<OrderEntity> es = ds.createQuery(OrderEntity.class).asList();
		List<Order> rst = es.stream().map(OrderEntity::getOrder).collect(toList());
		return rst;
	}

}
