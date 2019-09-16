package com.idhub.magic.center.ustorage.entity.component;

public class Address {
	String state;//province
	String city;
	String neighborhood;
	String detailAddress;
	Street street;
	String postalCode;
	public String getCity() {
		return city;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public String getState() {
		return state;
	}
	public Street getStreet() {
		return street;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setStreet(Street street) {
		this.street = street;
	}
}
