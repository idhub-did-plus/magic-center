package com.idhub.magic.center.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class ClaimEntity {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getClaimJsonld() {
		return claimJsonld;
	}
	public void setClaimJsonld(String claimJsonld) {
		this.claimJsonld = claimJsonld;
	}
	@Id
	String id;
	String identity;
	String orderId;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	String claimJsonld;
	
}
