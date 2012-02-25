package org.dvaletin.apps.nabludatel.server;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;

/**
 * @author Alexey Efimov
 */
public class NabludatelServerException extends Exception {
	public NabludatelServerException(String detailMessage) {
		super(detailMessage);
	}

	public NabludatelServerException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public NabludatelServerException(Throwable throwable) {
		super(throwable);
	}

	public boolean isAmazonS3ErrorCode(String errorCode) {
		if (getCause() instanceof AmazonS3Exception) {
			AmazonS3Exception s3Exception = (AmazonS3Exception) getCause();
			return errorCode.equalsIgnoreCase(s3Exception.getErrorCode());
		}
		return false;
	}

	public boolean isRequestTimeTooSkewed() {
		return isAmazonS3ErrorCode("RequestTimeTooSkewed");
	}

	public boolean isUnauthorized() {
		return isHttpResponseCode(HttpStatus.SC_UNAUTHORIZED);
	}

	public boolean isHttpResponseCode(int code) {
		if (getCause() instanceof HttpResponseException) {
			HttpResponseException responseException = (HttpResponseException) getCause();
			return responseException.getStatusCode() == code;
		}
		return false;
	}
}
