package com.idhub.magic.infra.controller;

public class MagicResponse<T> {
	
	boolean success;
	String message;
	String claim;
	
	public String getClaim() {
		return claim;
	}
	public void setClaim(String claim) {
		this.claim = claim;
	}
	T data;
	public MagicResponse(boolean suc, String msg) {
		success = suc;
		message = msg;
	}
	public MagicResponse(T data) {
		success = true;
		this.data = data;
	}
	public MagicResponse() {
		success = true;
		message = "success!";
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
