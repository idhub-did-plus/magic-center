package com.idhub.magic.provider.access;

public class MyCredential {
	String text;
	public MyCredential(String text, String signature) {
		super();
		this.text = text;
		this.signature = signature;
	}
	String signature;
	@Override
	public boolean equals(Object obj) {
		MyCredential other = (MyCredential)obj;
		if(obj instanceof MyCredential) {
			return text.equals(other.text) && signature.equals(other.signature);
		}
		return super.equals(obj);
	}
}
