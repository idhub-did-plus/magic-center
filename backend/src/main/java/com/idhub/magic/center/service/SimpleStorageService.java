package com.idhub.magic.center.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
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

@Service
public class SimpleStorageService {

	AmazonS3Client s3;


	@Value("${amazonProperties.endpointUrl}")
	private String endpointUrl;
	@Value("${amazonProperties.bucketName}")
	private String bucketName;
	@Value("${amazonProperties.accessKey}")
	private String accessKey;
	@Value("${amazonProperties.secretKey}")
	private String secretKey;

	@PostConstruct
	private void initializeAmazon() {

		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
		
		//ap-northeast-1
		this.s3 = new AmazonS3Client(credentials);
		s3.setRegion(Region.getRegion(Regions.AP_SOUTHEAST_1));
	//	List<Bucket> bs = s3.listBuckets();
	//	System.out.println(bs);

	}

	public String uploadToS3(MultipartFile file, String key) {
		try {
			byte[] data = file.getBytes();
			ByteArrayInputStream in = new ByteArrayInputStream(data);
		
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(data.length);
			s3.putObject(new PutObjectRequest(bucketName, key, in, meta).withCannedAcl(CannedAccessControlList.PublicRead));
			GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, key);
			URL url = s3.generatePresignedUrl(urlRequest);

			return url.toString();
		} catch (Exception ase) {
			ase.printStackTrace();
			throw new RuntimeException(ase);
		} 
	}

	public String store(byte[] data, String key) {
		try {

			ByteArrayInputStream in = new ByteArrayInputStream(data);
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(data.length);
			s3.putObject(
					new PutObjectRequest(bucketName, key, in, meta).withCannedAcl(CannedAccessControlList.PublicRead));
			GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, key);
			URL url = s3.generatePresignedUrl(urlRequest);

			return url.toString();
		} catch (Exception ase) {
			ase.printStackTrace();
			throw new RuntimeException(ase);
		}
	}

	public byte[] get(String key) {
		try {
			S3Object object = s3.getObject(new GetObjectRequest(bucketName, key));

			byte[] data = IOUtils.toByteArray(object.getObjectContent());

			return data;
		} catch (Exception ase) {
			ase.printStackTrace();
			throw new RuntimeException(ase);
		}
	}
}
