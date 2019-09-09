package com.idhub.magic.center.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.idhub.magic.center.kvc.entity.ClaimOrder;
import com.idhub.magic.provider.Order;

@Entity
public class OrderEntity {
	@Id
	String id;
	String state;
	String provider;
	Order order;
	String directTo;
	public String getDirectTo() {
		return directTo;
	}
	public void setDirectTo(String directTo) {
		this.directTo = directTo;
	}
	public OrderEntity(Order o) {
		this.state = OrderState.waiting.name();
		
	}
	public OrderEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getId() {
		return id;
	}
	public Order getOrder() {
		return order;
	}
	public String getProvider() {
		return provider;
	}
	public String getState() {
		return state;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public void setState(String state) {
		this.state = state;
	}
}
