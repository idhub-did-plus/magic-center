package com.idhub.magic.center.ustorage.entity.component;

import java.util.List;

public class Address {
	List<AddressElement> addressSequence;
	String postalCode;

	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public List<AddressElement> getAddressSequence() {
		return addressSequence;
	}

	public void setAddressSequence(List<AddressElement> addressSequence) {
		this.addressSequence = addressSequence;
	}
	
}
