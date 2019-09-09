package com.idhub.magic.provider.service;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.idhub.magic.provider.Order;
@Entity
public class ProviderOrder {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public ProviderOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	String id;
	Order order;
	public ProviderOrder(Order order) {
		super();
		this.order = order;
		id = order.id;
	}

}
