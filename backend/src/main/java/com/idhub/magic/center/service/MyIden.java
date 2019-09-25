package com.idhub.magic.center.service;

import java.util.List;

public class MyIden{
	public List<String> getAssociates() {
		return associates;
	}
	public void setAssociates(List<String> associates) {
		this.associates = associates;
	}
	public long getEin() {
		return ein;
	}
	public void setEin(long ein) {
		this.ein = ein;
	}
	List<String> associates;
	long ein;
	public MyIden(List<String> associates, long ein) {
		super();
		this.associates = associates;
		this.ein = ein;
	}
}