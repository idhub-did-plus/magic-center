package com.idhub.magic.common.claim;

public class ClaimSeed {
	String identity;
	String thirpartyIdentity;
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
	public String getThirpartyIdentity() {
		return thirpartyIdentity;
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
	public void setThirpartyIdentity(String thirpartyIdentity) {
		this.thirpartyIdentity = thirpartyIdentity;
	}
}
