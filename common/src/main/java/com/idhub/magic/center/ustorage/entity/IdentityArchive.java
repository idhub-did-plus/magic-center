package com.idhub.magic.center.ustorage.entity;

public class IdentityArchive {

	IdentityInfo identityInfo;
	BasicInfo basicInfo;
	FinancialProfile financialProfile;
	public BasicInfo getBasicInfo() {
		return basicInfo;
	}
	public FinancialProfile getFinancialProfile() {
		return financialProfile;
	}
	public IdentityInfo getIdentityInfo() {
		return identityInfo;
	}
	public void setBasicInfo(BasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}
	public void setFinancialProfile(FinancialProfile financialProfile) {
		this.financialProfile = financialProfile;
	}
	public void setIdentityInfo(IdentityInfo identityInfo) {
		this.identityInfo = identityInfo;
	}
}
