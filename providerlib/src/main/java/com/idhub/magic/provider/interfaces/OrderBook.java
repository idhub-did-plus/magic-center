package com.idhub.magic.provider.interfaces;

import java.util.List;

import com.idhub.magic.provider.Order;

public interface OrderBook {
	List<Order> tome(String identity);
	boolean receive(String identity, String orderId);
	List<Order> listAll();
}
