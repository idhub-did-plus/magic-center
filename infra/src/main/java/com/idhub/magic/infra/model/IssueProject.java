package com.idhub.magic.infra.model;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class IssueProject{
	@Id
	String id;
	String name   ;
	String agentIdentity ;
	String status;
	Date createTime;
	ProjectMaterial projectMaterial;
	IssuerInformation issuerInformation;
	TokenConfig tokenConfig;
	DeployedToken deployedToken;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAgentIdentity() {
		return agentIdentity;
	}
	public void setAgentIdentity(String agentIdentity) {
		this.agentIdentity = agentIdentity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public ProjectMaterial getProjectMaterial() {
		return projectMaterial;
	}
	public void setProjectMaterial(ProjectMaterial projectMaterial) {
		this.projectMaterial = projectMaterial;
	}
	public IssuerInformation getIssuerInformation() {
		return issuerInformation;
	}
	public void setIssuerInformation(IssuerInformation issuerInformation) {
		this.issuerInformation = issuerInformation;
	}
	public TokenConfig getTokenConfig() {
		return tokenConfig;
	}
	public void setTokenConfig(TokenConfig tokenConfig) {
		this.tokenConfig = tokenConfig;
	}
	public DeployedToken getDeployedToken() {
		return deployedToken;
	}
	public void setDeployedToken(DeployedToken deployedToken) {
		this.deployedToken = deployedToken;
	}
}
