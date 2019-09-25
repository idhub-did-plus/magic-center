package com.idhub.magic.common.ustorage.entity;

import java.util.List;

public class IdentityEverything{
	public IdentityArchive archive;
	public List<Material> materials;
	public IdentityEverything(IdentityArchive archive, List<Material> materials, List<String> claims) {
		super();
		this.archive = archive;
		this.materials = materials;
		this.claims = claims;
	}
	public List<String> claims;
}