package com.idhub.magic.center.ustorage;

import com.idhub.magic.center.ustorage.entity.Material;

public class MaterialWrapper {
	String id;
	Material material;
	public MaterialWrapper() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MaterialWrapper(Material material) {
		super();
		this.material = material;
		id = material.getIdentity() + material.getType() + material.getName();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}

}
