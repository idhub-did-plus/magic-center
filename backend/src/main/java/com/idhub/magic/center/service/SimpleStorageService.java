package com.idhub.magic.center.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.annotation.PostConstruct;

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
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;

@Service
public class SimpleStorageService {
	// @Autowired
	AmazonS3Client s3;
	// String bucketName = "xxxxxxx"; // 【你 bucket 的名字】 # 首先需要保证 s3 上已经存在该存储桶
	/*
	 * void init() { s3 = new AmazonS3Client(new BasicAWSCredentials(AWS_ACCESS_KEY,
	 * AWS_SECRET_KEY)); s3.setRegion(Region.getRegion(Regions.US_EAST_1)); //
	 * 此处根据自己的 s3 地区位置改变 }
	 */

	private AmazonS3 s3client;

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
		this.s3client = new AmazonS3Client(credentials);
	}

	public String uploadToS3(MultipartFile file, String key) throws IOException {
		try {
			byte[] data = file.getBytes();
			ByteArrayInputStream in = new ByteArrayInputStream(data);
			String bucketPath = bucketName + "/upload";

			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(data.length);
			s3.putObject(
					new PutObjectRequest(bucketPath, key, in, meta).withCannedAcl(CannedAccessControlList.PublicRead));
			GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(bucketName, key);
			URL url = s3.generatePresignedUrl(urlRequest);

			return url.toString();
		} catch (AmazonServiceException ase) {
			ase.printStackTrace();
		} catch (AmazonClientException ace) {
			ace.printStackTrace();
		}
		return null;
	}

}
