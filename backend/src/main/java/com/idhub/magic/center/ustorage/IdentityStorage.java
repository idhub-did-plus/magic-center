package com.idhub.magic.center.ustorage;

import com.idhub.magic.center.ustorage.entity.IdentityArchive;

public class IdentityStorage {
	String identity;
	IdentityArchive identityArchive;
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public IdentityArchive getIdentityArchive() {
		return identityArchive;
	}
	public void setIdentityArchive(IdentityArchive identityArchive) {
		this.identityArchive = identityArchive;
	}

}
