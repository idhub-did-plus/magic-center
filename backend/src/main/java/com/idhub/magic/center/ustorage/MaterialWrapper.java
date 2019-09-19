package com.idhub.magic.center.ustorage;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import com.idhub.magic.common.ustorage.entity.Material;
@Entity
public class MaterialWrapper {
	@Id
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
