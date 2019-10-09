package com.idhub.magic.common.claim;

public class ClaimSeed {
	String identity;
	String thirdpartyIdentity;
	String identityArchiveJson;
	String signatureHex;
	public String getIdentity() {
		return identity;
	}
	public String getIdentityArchiveJson() {
		return identityArchiveJson;
	}
	public String getSignatureHex() {
		return signatureHex;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public void setIdentityArchiveJson(String identityArchiveJson) {
		this.identityArchiveJson = identityArchiveJson;
	}
	public void setSignatureHex(String signatureHex) {
		this.signatureHex = signatureHex;
	}
	public String getThirdpartyIdentity() {
		return thirdpartyIdentity;
	}
	public void setThirdpartyIdentity(String thirdpartyIdentity) {
		this.thirdpartyIdentity = thirdpartyIdentity;
	}
	
}
