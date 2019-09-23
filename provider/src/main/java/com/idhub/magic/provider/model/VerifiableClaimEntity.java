package com.idhub.magic.provider.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.idhub.magic.common.claim.VerifiableClaim;
import com.idhub.magic.verifiablecredentials.VerifiableCredential;
@Entity
public class VerifiableClaimEntity {
	public VerifiableClaimEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VerifiableClaimEntity(VerifiableClaim claim, VerifiableCredential credential, String jsonld) {
		super();
		this.claim = claim;
		Credential = credential;
		this.jsonld = jsonld;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public VerifiableClaim getClaim() {
		return claim;
	}
	public void setClaim(VerifiableClaim claim) {
		this.claim = claim;
	}
	public VerifiableCredential getCredential() {
		return Credential;
	}
	public void setCredential(VerifiableCredential credential) {
		Credential = credential;
	}
	public String getJsonld() {
		return jsonld;
	}
	public void setJsonld(String jsonld) {
		this.jsonld = jsonld;
	}
	@Id
	String id;
	VerifiableClaim claim;
	VerifiableCredential Credential;
	String jsonld;

}
