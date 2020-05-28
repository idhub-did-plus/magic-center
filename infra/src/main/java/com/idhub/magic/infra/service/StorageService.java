package com.idhub.magic.infra.service;

import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

	String store(MultipartFile file, String key);

	String store(byte[] data, String key);

	byte[] get(String key);

	void stream(String key, OutputStream out);

}