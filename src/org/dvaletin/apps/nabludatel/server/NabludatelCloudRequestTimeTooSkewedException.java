package org.dvaletin.apps.nabludatel.server;

/**
 * Throw to ask user adjust date/time to fix S3 upload.
 *
 * @author Alexey Efimov
 */
public class NabludatelCloudRequestTimeTooSkewedException extends Exception {
	public NabludatelCloudRequestTimeTooSkewedException(Throwable throwable) {
		super(throwable);
	}
}
