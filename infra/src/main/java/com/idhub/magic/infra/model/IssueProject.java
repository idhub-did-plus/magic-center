package com.idhub.magic.infra.model;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class IssueProject{
	@Id
	String id;
	String agentIdentity ;
	String status = ProjectStatus.editing.name();
	Date createTime = new Date();
	ProjectDetail projectDetail = new ProjectDetail();
	IssuerInformation issuerInformation = new IssuerInformation();
	TokenConfig tokenConfig;
	DeployedToken deployedToken;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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

	public ProjectDetail getProjectDetail() {
		return projectDetail;
	}
	public void setProjectDetail(ProjectDetail projectDetail) {
		this.projectDetail = projectDetail;
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
