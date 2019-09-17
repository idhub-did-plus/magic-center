package com.idhub.magic.provider.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.idhub.magic.provider.Order;
@Entity
public class ProviderOrder {
	@Id
	String id;
	String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	Order order;
	public ProviderOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProviderOrder(Order order) {
		super();
		this.order = order;
		id = order.id;
	}
	public String getId() {
		return id;
	}
	public Order getOrder() {
		return order;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

}
