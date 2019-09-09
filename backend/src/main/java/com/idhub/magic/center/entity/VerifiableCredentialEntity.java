package com.idhub.magic.center.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.idhub.magic.verifiablecredentials.VerifiableCredential;
@Entity
public class VerifiableCredentialEntity {
	@Id
	String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public VerifiableCredential getCredential() {
		return credential;
	}

	public void setCredential(VerifiableCredential credential) {
		this.credential = credential;
	}

	String type;
	
	VerifiableCredential credential;

}
