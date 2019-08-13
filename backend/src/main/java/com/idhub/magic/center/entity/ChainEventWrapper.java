package com.idhub.magic.center.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.idhub.magic.center.event.ChainEvent;
@Entity
public class ChainEventWrapper {
	@Id
	String id;
	String identity;
	boolean sent = false;
	public boolean isSent() {
		return sent;
	}
	public void setSent(boolean sent) {
		this.sent = sent;
	}
	ChainEvent event;
	public ChainEventWrapper(String identity, ChainEvent event) {
		super();
		this.id = event.threadId;
		this.identity = identity;
		this.event = event;
	}
	public ChainEventWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChainEvent getEvent() {
		return event;
	}
	public String getId() {
		return id;
	}
	public String getIdentity() {
		return identity;
	}
	public void setEvent(ChainEvent event) {
		this.event = event;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
}
