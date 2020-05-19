package com.idhub.magic.infra.model;

public class ProjectMaterial {
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	String id;
	String ext;
	String name;
	String type;
	String projectId;

	public ProjectMaterial(String pid, String type, String name, String ext) {
		this.projectId = pid;
		this.name = name;
		this.type = type;
		this.ext = ext;
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
