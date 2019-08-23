package com.idhub.magic.center.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.idhub.magic.center.event.MagicEvent;
@Entity
public class EventWrapper {
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
	MagicEvent event;
	public EventWrapper(String identity, MagicEvent event) {
		super();
		this.identity = identity;
		this.event = event;
	}
	public EventWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MagicEvent getEvent() {
		return event;
	}
	public String getId() {
		return id;
	}
	public String getIdentity() {
		return identity;
	}
	public void setEvent(MagicEvent event) {
		this.event = event;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
}
