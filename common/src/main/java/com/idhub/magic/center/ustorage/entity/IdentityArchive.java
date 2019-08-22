package com.idhub.magic.center.ustorage.entity;

public class IdentityArchive {

	public IdentityInfo getIdentityInfo() {
		return identityInfo;
	}
	public void setIdentityInfo(IdentityInfo identityInfo) {
		this.identityInfo = identityInfo;
	}
	public BasicInfo getBasicInfo() {
		return basicInfo;
	}
	public void setBasicInfo(BasicInfo basicInfo) {
		this.basicInfo = basicInfo;
	}
	IdentityInfo identityInfo;
	BasicInfo basicInfo;
}
