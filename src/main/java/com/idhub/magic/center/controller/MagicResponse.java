package com.idhub.magic.center.controller;

public class MagicResponse {
	
	boolean success;
	String message;
	
	Object data;
	public MagicResponse(boolean suc, String msg) {
		success = suc;
		message = msg;
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
