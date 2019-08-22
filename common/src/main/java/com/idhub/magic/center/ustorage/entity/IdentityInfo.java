package com.idhub.magic.center.ustorage.entity;

import java.util.Date;

public class IdentityInfo {
/*	填入基本身份信息，包括
	姓名，姓/Last Name 名/First Name，必填
	生日，生日/Date of Birth （月）/ （日）/ （年），必填
	国籍，国籍/ Nationality，必填
	居住国，居住国/ Country of Residence，必填
	身份证件号码，身份证件号码/ID Number
	美国可选，若填写需上传身份证件照片（图片或PDF）
	中国必填，需上传身份证件照片（图片或PDF）
	护照号码，护照号码/Passport Number，可选
	若填写需上传护照照片（图片或PDF）*/
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getResidenceCountry() {
		return residenceCountry;
	}
	public void setResidenceCountry(String residenceCountry) {
		this.residenceCountry = residenceCountry;
	}
	public String getIdcardNumber() {
		return idcardNumber;
	}
	public void setIdcardNumber(String idcardNumber) {
		this.idcardNumber = idcardNumber;
	}
	public String getIdcardImage() {
		return idcardImage;
	}
	public void setIdcardImage(String idcardImage) {
		this.idcardImage = idcardImage;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getPassportImage() {
		return passportImage;
	}
	public void setPassportImage(String passportImage) {
		this.passportImage = passportImage;
	}
	String firstName;
	String lastName;
	Date birthday;
	String country;
	String residenceCountry;
	String idcardNumber;
	String idcardImage;
	String passportNumber;
	String passportImage;
	
}
