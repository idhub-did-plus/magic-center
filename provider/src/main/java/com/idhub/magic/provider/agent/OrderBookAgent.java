package com.idhub.magic.provider.agent;

import java.util.List;

import com.idhub.magic.provider.Order;
import com.idhub.magic.provider.interfaces.OrderBook;

public class OrderBookAgent implements OrderBook{

	@Override
	public List<Order> tome(String providerIdentity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean receive(String orderId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Order> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
