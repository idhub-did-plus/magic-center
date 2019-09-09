package com.idhub.magic.provider.service;

import org.mongodb.morphia.annotations.Id;

import com.idhub.magic.provider.IdentityData;

public class IdentityEntity{
	@Id
	String id;
	IdentityData data;
}
