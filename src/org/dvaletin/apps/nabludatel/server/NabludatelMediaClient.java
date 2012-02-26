package org.dvaletin.apps.nabludatel.server;

import android.util.Log;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.dvaletin.apps.nabludatel.utils.Encodings;

import java.io.File;
import java.security.NoSuchAlgorithmException;

/**
 * S3 client.
 *
 * @author dvaletin
 * @author Alexey Efimov
 */
public class NabludatelMediaClient {
	private static final String T = NabludatelMediaClient.class.getSimpleName();

	private static final String BUCKET = "webnabludatel-media";

	private final String deviceId;
	private final AmazonS3Client s3Client;

	public NabludatelMediaClient(String deviceId, AWSCredentials awsCredentials) {
		this.deviceId = deviceId;
		this.s3Client = new AmazonS3Client(awsCredentials);
	}

	public String toUrl(String folderName, File file) throws NoSuchAlgorithmException {
		return "https://s3-eu-west-1.amazonaws.com/" + BUCKET + "/" + toS3FileName(folderName, file);
	}

	private String toS3FileName(String folderName, File file) throws NoSuchAlgorithmException {
		return (folderName != null ? folderName + "/" : "") + Encodings.md5(deviceId) + '/' + file.getName();
	}

	/**
	 * Upload file and return URL to it.
	 *
	 * @param folderName Folder name
	 * @param file Local file
	 * @return Access URL to file on storage.
	 * @throws NabludatelServerException It upload has error.
	 */
	public String upload(String folderName, File file) throws NabludatelServerException {
		try {
			long time = System.currentTimeMillis();
			PutObjectRequest putObjectRequest = putRequest(folderName, file)
					.withCannedAcl(CannedAccessControlList.PublicRead);
			PutObjectResult result = s3Client.putObject(putObjectRequest);
			time = System.currentTimeMillis() - time;
			Log.i(T, "Uploaded " + file.getName() + " to " + toUrl(null, file) +
					", etag (md5) " + result.getETag() + " " + time + " ms");
			return toUrl(null, file);
		} catch (Exception e) {
			throw new NabludatelServerException("Failed to upload " + file.getName() + " to S3 storage", e);
		}
	}

	private PutObjectRequest putRequest(String folderName, File file) throws NoSuchAlgorithmException {
		return new PutObjectRequest(BUCKET, toS3FileName(folderName, file), file);
	}
}
