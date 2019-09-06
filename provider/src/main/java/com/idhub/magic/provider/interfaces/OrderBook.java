package com.idhub.magic.provider.interfaces;

import java.util.List;

import com.idhub.magic.provider.Order;

public interface OrderBook {
	List<Order> tome(String providerIdentity);
	boolean receive(String orderId);
	List<Order> listAll();
}
