package com.idhub.magic.infra.model;

import java.util.List;

import org.mongodb.morphia.annotations.Id;

public class TokenConfig {
	String name;
	String symbol;
	String decimals ;
	List<Partition> partitions;
	public List<Partition> getPartitions() {
		return partitions;
	}
	public void setPartitions(List<Partition> partitions) {
		this.partitions = partitions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getDecimals() {
		return decimals;
	}
	public void setDecimals(String decimals) {
		this.decimals = decimals;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	String tokenType;
}
