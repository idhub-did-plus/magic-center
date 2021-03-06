package com.idhub.magic.infra.model;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
@Entity
public class ProjectMaterial {
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	@Id
	String id;
	String ext;
	String name;
	String type;
	String projectId;
	String contentDescription;
	Date uploadTime;
	Date onchainTime;
	String hash;
	public Date getOnchainTime() {
		return onchainTime;
	}

	public void setOnchainTime(Date onchainTime) {
		this.onchainTime = onchainTime;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public boolean isOnchain() {
		return onchain;
	}

	public void setOnchain(boolean onchain) {
		this.onchain = onchain;
	}
	boolean onchain = false;

	public String getContentDescription() {
		return contentDescription;
	}

	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}

	public ProjectMaterial() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public ProjectMaterial(String pid, String type, String name, String ext,String content) {
		this.uploadTime = new Date();
		this.projectId = pid;
		this.name = name;
		this.type = type;
		this.ext = ext;
		this.id = pid + type + name;
		this.contentDescription = content;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
}
