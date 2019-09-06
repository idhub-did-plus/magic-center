package com.idhub.magic.provider.agent;

import java.util.List;

import com.idhub.magic.provider.Order;

public interface OrderBookAgent{
	
	public List<Order> tome(String providerIdentity) ;

	public boolean receive(String orderId);

	public List<Order> listAll();
}
