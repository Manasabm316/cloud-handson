package com.test.S3;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

public class s3upload 
{
    public static void main( String[] args )
    {
	    try {
			// SECTION 1 OPTION 1: Create a S3 client with in-program credential
			//
			BasicAWSCredentials awsCreds = new BasicAWSCredentials("", "");
			// us-west-2 is AWS Oregon
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(" ").withCredentials(new AWSStaticCredentialsProvider(awsCreds))
					.build();

			// SECTION 1 OPTION 2: Create a S3 client with the aws credentials set in OS (require config and crendentials in .aws folder.) Demonstrate at the end of this video.
			//
//			AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();

			// SECTION 2: Put file in S3 bucket
			//
			String bucketName = " ";
			String folderName = " ";
			String fileNameInS3 = " ";
			String fileNameInLocalPC = " ";

			PutObjectRequest request = new PutObjectRequest(bucketName, folderName + "/" + fileNameInS3, new File(fileNameInLocalPC));
			s3Client.putObject(request);
			System.out.println("File Uploaded to s3 Bucket Successfully");
			
			// SECTION 3: Get file from S3 bucket
			//
			S3Object fullObject;
			fullObject = s3Client.getObject(new GetObjectRequest(bucketName, folderName + "/" + fileNameInS3));
			System.out.println("File Downloaded from s3 Bucket Successfully");
			// Print file content line by line
			InputStream is = fullObject.getObjectContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			System.out.println("File Content");
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

		} catch (IOException | AmazonS3Exception e) {

			e.printStackTrace();
		}
    }
}