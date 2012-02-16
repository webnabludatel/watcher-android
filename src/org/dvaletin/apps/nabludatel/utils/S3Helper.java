package org.dvaletin.apps.nabludatel.utils;

import java.io.File;


import android.util.Log;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3Helper {
	private String deviceId;
	AmazonS3Client s3Client = new AmazonS3Client(new BasicAWSCredentials(
			Consts.getS3AccessKeyId(), Consts.getS3AccessKey()));

	public S3Helper(String id) {
		this.deviceId = id;
	}

	public void uploadImageToS3(final File fileToUpload) {

		try {
			// String s3Folder = deviceId;
			String fileName = fileToUpload.getName();
			PutObjectRequest por = new PutObjectRequest(
					Consts.getS3PictureBucket(), "android/" + deviceId + "/"
							+ fileName, fileToUpload); // Content type is
														// determined by file
														// extension.
			this.s3Client.putObject(por
					.withCannedAcl(CannedAccessControlList.PublicRead));
		} catch (Exception exception) {
			Log.d("S3", "Upload Failure " + exception.getMessage());
		}

	}
}
