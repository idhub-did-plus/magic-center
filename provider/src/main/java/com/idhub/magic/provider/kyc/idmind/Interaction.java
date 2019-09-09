package com.idhub.magic.provider.kyc.idmind;

import org.mongodb.morphia.annotations.Entity;

@Entity
public class Interaction {
	String id;
	String orderId;
	String transactionId;
	Object request;
	Object response;
	public String getId() {
		return id;
	}
	public String getOrderId() {
		return orderId;
	}

	public Object getRequest() {
		return request;
	}
	public Object getResponse() {
		return response;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setRequest(Object request) {
		this.request = request;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

}
