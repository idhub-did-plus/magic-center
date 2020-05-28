package com.idhub.magic.infra.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.idhub.magic.infra.model.FileStorage;

//@Service
public class MongoStorageService implements StorageService{
	@Autowired
	Datastore ds;

	public String store(MultipartFile file, String key) {
		try {
			byte[] data = file.getBytes();
			
			FileStorage st = new FileStorage(key, data);
			ds.save(st);
			return key;
		} catch (Exception ase) {
			ase.printStackTrace();
			throw new RuntimeException(ase);
		} 
	}

	public String store(byte[] data, String key) {
		try {
	
			FileStorage st = new FileStorage(key, data);
			ds.save(st);
			return key;
		} catch (Exception ase) {
			ase.printStackTrace();
			throw new RuntimeException(ase);
		}
	}

	public byte[] get(String key) {

		FileStorage fs = ds.createQuery(FileStorage.class).field("id").equal(key).get();


			return fs.getData();
		
	}
	public void stream(String key, OutputStream out) {
		try {
			FileStorage fs = ds.createQuery(FileStorage.class).field("id").equal(key).get();

			ByteArrayInputStream in = new ByteArrayInputStream(fs.getData());
			IOUtils.copy(in, out);
			out.flush();
		} catch (Exception ase) {
			ase.printStackTrace();
			throw new RuntimeException(ase);
		}
	}
}
