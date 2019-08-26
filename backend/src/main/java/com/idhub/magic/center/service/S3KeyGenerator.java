package com.idhub.magic.center.service;

public class S3KeyGenerator {
	static public String forMaterial(String identity, String type, String name) {
		return identity + type + name;
	}
}
