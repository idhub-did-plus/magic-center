package com.idhub.magic.provider;

import java.util.List;

import com.idhub.magic.center.ustorage.entity.IdentityArchive;
import com.idhub.magic.center.ustorage.entity.Material;

public class IdentityData {

	String identity;
	IdentityArchive archive;
	List<Material> materials;
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public IdentityArchive getArchive() {
		return archive;
	}
	public void setArchive(IdentityArchive archive) {
		this.archive = archive;
	} 

}
