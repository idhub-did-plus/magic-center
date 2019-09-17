package com.idhub.magic.provider.model;

import org.mongodb.morphia.annotations.Id;

import com.idhub.magic.provider.IdentityData;

public class IdentityEntity{
	@Id
	String id;
	IdentityData data;
	public IdentityEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public IdentityEntity(IdentityData data) {
		super();
		this.data = data;
		id = data.getIdentity();
	}
	public IdentityData getData() {
		return data;
	}
	public String getId() {
		return id;
	}
	public void setData(IdentityData data) {
		this.data = data;
	}
	public void setId(String id) {
		this.id = id;
	}
}
