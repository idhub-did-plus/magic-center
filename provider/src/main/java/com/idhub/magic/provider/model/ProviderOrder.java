package com.idhub.magic.provider.model;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.idhub.magic.provider.Order;
@Entity
public class ProviderOrder {
	@Id
	String id;
	String state;
	Date createTime;
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
		createTime = new Date();
		state = ProviderOrderState.unreceived.name();
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
