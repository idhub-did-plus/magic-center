package com.idhub.magic.provider.acl.controller;

import org.apache.shiro.authc.AuthenticationToken;

public class SignatureToken implements AuthenticationToken {
	String address;
	String queryString;
	String signature;
	public SignatureToken(String address, String queryString,String sig) {
		super();
		this.address = address;
		this.queryString = queryString;
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
		return signature;
	}

}
