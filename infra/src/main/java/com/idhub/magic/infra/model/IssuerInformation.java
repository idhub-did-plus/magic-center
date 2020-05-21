package com.idhub.magic.infra.model;

public class IssuerInformation {
	String legalEntityName;
	String registrationNumber;
	String vatRegistrationNumber;
	String website;
	Address address = new Address();
	ContactPerson contactPerson = new ContactPerson();
	public String getLegalEntityName() {
		return legalEntityName;
	}
	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}
	public String getVatRegistrationNumber() {
		return vatRegistrationNumber;
	}
	public void setVatRegistrationNumber(String vatRegistrationNumber) {
		this.vatRegistrationNumber = vatRegistrationNumber;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public ContactPerson getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
}
