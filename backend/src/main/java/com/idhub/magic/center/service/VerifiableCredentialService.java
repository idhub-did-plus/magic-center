package com.idhub.magic.center.service;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idhub.magic.verifiablecredentials.VerifiableCredential;
@Service
public class VerifiableCredentialService {
	@Autowired Datastore ds;
	public void store(VerifiableCredential vc) {
		
	}

}
