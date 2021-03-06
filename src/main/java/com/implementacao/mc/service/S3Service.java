package com.implementacao.mc.service;

import java.io.File;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

	private Logger LOG = org.slf4j.LoggerFactory.getLogger(S3Service.class);
	
	@Autowired
	private AmazonS3 S3client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	public void uploadFile(String localFilePach) {
		 try {
			 
			 File file = new File(localFilePach);
			 LOG.info("Iniciando upload");
			 S3client.putObject(new PutObjectRequest(bucketName,"test", file));
			 LOG.info("Finalizando Upload");
			
		} catch (AmazonServiceException e) {
			
			LOG.info("AmazonServiceException" + e.getErrorMessage());
			LOG.info("Status code: " + e.getErrorCode());
		}catch (AmazonClientException e) {
			
			LOG.info("AmazonClientException" + e.getMessage());
			
		}
	}
}
