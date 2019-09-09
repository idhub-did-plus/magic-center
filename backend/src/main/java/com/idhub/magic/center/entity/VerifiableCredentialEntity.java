package com.idhub.magic.center.entity;

import org.mongodb.morphia.annotations.Entity;

import com.idhub.magic.verifiablecredentials.VerifiableCredential;
@Entity
public class VerifiableCredentialEntity {
	String id;
	
	String type;
	
	VerifiableCredential credential;

}
