package com.idhub.magic.center.ustorage;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.idhub.magic.common.ustorage.entity.IdentityArchive;
@Entity
public class IdentityStorage {
	public long getEin() {
		return ein;
	}
	public void setEin(long ein) {
		this.ein = ein;
	}
	@Id
	String id;
	long ein;
	IdentityArchive identityArchive;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public IdentityArchive getIdentityArchive() {
		return identityArchive;
	}
	public void setIdentityArchive(IdentityArchive identityArchive) {
		this.identityArchive = identityArchive;
	}

}
