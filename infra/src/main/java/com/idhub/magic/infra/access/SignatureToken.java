package com.idhub.magic.infra.access;

import org.apache.shiro.authc.AuthenticationToken;

public class SignatureToken implements AuthenticationToken {
	public String getClaim() {
		return claim;
	}

	String address;

	String signature;
	String claim;
	String timestamp;
	public SignatureToken(String address, String claim, String timestamp,String sig) {
		super();
		this.address = address;
		this.claim = claim;
		this.timestamp = timestamp;
		this.signature = sig;
	}

	@Override
	public Object getPrincipal() {
		// TODO Auto-generated method stub
		return address;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return new MyCredential(address + claim + timestamp, signature);
	}

}
