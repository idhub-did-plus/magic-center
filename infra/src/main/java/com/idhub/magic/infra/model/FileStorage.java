package com.idhub.magic.infra.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity
public class FileStorage {
	@Id
	String id;
	public FileStorage(String key, byte[] data2) {
		id = key;
		data = data2;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	byte[] data;

}
