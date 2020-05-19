package com.idhub.magic.infra.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class DeployedToken  {
	String name;
	String symbol;
	String decimals ;
	@Id
	String contractAddress;
	String ownerAccount;
	String deployAccount;
	String tokenType;
	String[] controllers;
	String registryAddress;
	public String getContractAddress() {
		return contractAddress;
	}
	public String[] getControllers() {
		return controllers;
	}
	public String getDecimals() {
		return decimals;
	}
	public String getDeployAccount() {
		return deployAccount;
	}
	public String getName() {
		return name;
	}
	public String getOwnerAccount() {
		return ownerAccount;
	}
	public String getRegistryAddress() {
		return registryAddress;
	}
	public String getSymbol() {
		return symbol;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}
	public void setControllers(String[] controllers) {
		this.controllers = controllers;
	}
	public void setDecimals(String decimals) {
		this.decimals = decimals;
	}
	public void setDeployAccount(String deployAccount) {
		this.deployAccount = deployAccount;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOwnerAccount(String ownerAccount) {
		this.ownerAccount = ownerAccount;
	}
	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
}
