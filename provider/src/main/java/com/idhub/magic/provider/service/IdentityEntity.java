package com.idhub.magic.provider.service;

import org.mongodb.morphia.annotations.Id;

import com.idhub.magic.provider.IdentityData;

public class IdentityEntity{
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public IdentityData getData() {
		return data;
	}
	public void setData(IdentityData data) {
		this.data = data;
	}
	@Id
	String id;
	IdentityData data;
	public IdentityEntity(IdentityData data) {
		super();
		this.data = data;
		id = data.getIdentity();
	}
	public IdentityEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
}
