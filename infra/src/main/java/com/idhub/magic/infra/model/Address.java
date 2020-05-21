package com.idhub.magic.infra.model;

public class Address {
	String country;
	String city;
	String postalCode;
	String address;
	String countryOfIncorporation;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountryOfIncorporation() {
		return countryOfIncorporation;
	}
	public void setCountryOfIncorporation(String countryOfIncorporation) {
		this.countryOfIncorporation = countryOfIncorporation;
	}
}
