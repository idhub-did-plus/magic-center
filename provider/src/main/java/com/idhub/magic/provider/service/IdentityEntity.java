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
}
