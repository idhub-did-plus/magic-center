package com.idhub.magic.infra.model;

public class ProjectDetail {
	String name;
	String assetType;
	String jurisdiction;
	boolean hasFinancialRegulator;
	boolean everRaseedFunds;
	String stepsCompleted;
	int plannedRasedCapital;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	public boolean isHasFinancialRegulator() {
		return hasFinancialRegulator;
	}
	public void setHasFinancialRegulator(boolean hasFinancialRegulator) {
		this.hasFinancialRegulator = hasFinancialRegulator;
	}
	public boolean isEverRaseedFunds() {
		return everRaseedFunds;
	}
	public void setEverRaseedFunds(boolean everRaseedFunds) {
		this.everRaseedFunds = everRaseedFunds;
	}
	public String getStepsCompleted() {
		return stepsCompleted;
	}
	public void setStepsCompleted(String stepsCompleted) {
		this.stepsCompleted = stepsCompleted;
	}
	public int getPlannedRasedCapital() {
		return plannedRasedCapital;
	}
	public void setPlannedRasedCapital(int plannedRasedCapital) {
		this.plannedRasedCapital = plannedRasedCapital;
	}
	
	
	

}
