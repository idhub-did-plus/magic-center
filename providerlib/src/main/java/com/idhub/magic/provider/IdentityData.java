package com.idhub.magic.provider;

import java.util.List;

import com.idhub.magic.common.ustorage.entity.IdentityArchive;
import com.idhub.magic.common.ustorage.entity.Material;

public class IdentityData {

	public IdentityData(String identity, IdentityArchive archive, List<Material> materials) {
		super();
		this.identity = identity;
		this.archive = archive;
		this.materials = materials;
	}
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
	public IdentityData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Material> getMaterials() {
		return materials;
	}
	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	} 

}
