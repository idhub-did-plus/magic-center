package com.idhub.magic.infra.model;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class IssueAgent {
	@Id
	String id;
	public AgentAdvancedInformation getAdvancedInformation() {
		return advancedInformation;
	}

	public void setAdvancedInformation(AgentAdvancedInformation advancedInformation) {
		this.advancedInformation = advancedInformation;
	}

	String name;
	String symbol;
	String identity;
	String status;
	Date createTime;
	AgentAdvancedInformation advancedInformation;
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public String getIdentity() {
		return identity;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
