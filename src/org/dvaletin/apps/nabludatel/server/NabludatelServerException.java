package org.dvaletin.apps.nabludatel.server;

/**
 * @author Alexey Efimov
 */
public class NabludatelServerException extends Exception {
	public NabludatelServerException() {
	}

	public NabludatelServerException(String detailMessage) {
		super(detailMessage);
	}

	public NabludatelServerException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public NabludatelServerException(Throwable throwable) {
		super(throwable);
	}
}
