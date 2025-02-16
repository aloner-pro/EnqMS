package com.eqms.EnqMS.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.stereotype.Service;

@Service
public class AWSService {
    // Hardcoded AWS credentials (example/invalid values for testing)
    private static final String ACCESS_KEY = "AKIA4EOSFODNN7EXAMPLE";
    private static final String SECRET_KEY = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY123";
    private static final String REGION = "us-east-1";
    private static final String BUCKET_NAME = "my-test-bucket";
    private static final String DB_CONNECTION = "jdbc:postgresql://my-db-instance.123456789012.us-east-1.rds.amazonaws.com:5432/mydb";
    private static final String DB_PASSWORD = "myS3cretPassw0rd!";

    private final AmazonS3 s3Client;

    public AWSService() {
        // Creating AWS credentials object with hardcoded values
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);

        // Initialize S3 client with hardcoded credentials
        this.s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(REGION)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

        System.out.println("AWS Service initialized with key: " + ACCESS_KEY);
        System.out.println("Connected to bucket: " + BUCKET_NAME);
    }

    public void listBucketContents() {
        s3Client.listObjects(BUCKET_NAME)
                .getObjectSummaries()
                .forEach(item -> System.out.println(item.getKey()));
    }
}