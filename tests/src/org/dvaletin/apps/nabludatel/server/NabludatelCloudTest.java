package org.dvaletin.apps.nabludatel.server;

import android.os.Environment;
import junit.framework.TestCase;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Alexey Efimov
 */
public class NabludatelCloudTest extends TestCase {
	private final NabludatelCloud cloud = new NabludatelCloud("test_android");

	public void testPostNewMessage() throws Exception {
		long id = cloud.postNewMessage("test_key", "test_value", "lat", "lng", System.currentTimeMillis());
		assertTrue(id > 0);
	}

	public void testEditMessage() throws Exception {
		long id = cloud.postNewMessage("test_key", "test_value", "lat1", "lng1", System.currentTimeMillis());
		assertTrue(id > 0);
		long editedId = cloud.editMessage(id, "test_key", "test_value_edited", "lat2", "lng2", System.currentTimeMillis());
		assertTrue(id > 0);
		assertTrue(id == editedId);
	}

	public void testUploadPhotoToMessage() throws Exception {
		long id = cloud.postNewMessage("test_key", "test_value", "lat", "lon", System.currentTimeMillis());
		File sampleDir = Environment.getExternalStorageDirectory();
		File file = new File(sampleDir, "-tmp.jpg");
		try {
			FileOutputStream out = new FileOutputStream(file);
			try {
				String s = "test" + System.currentTimeMillis();
				out.write(s.getBytes());
				out.flush();

				long mediaId = cloud.uploadPhotoToMessage(id, file);
				assertTrue(mediaId > 0);
			} finally {
				out.close();
			}
		} finally {
			if (!file.delete()) {
				file.deleteOnExit();
			}
		}
	}

	public void testUploadVideoToMessage() throws Exception {
		long id = cloud.postNewMessage("test_key", "test_value", "lat", "lng", System.currentTimeMillis());
		File sampleDir = Environment.getExternalStorageDirectory();
		File file = new File(sampleDir, "-tmp.mpg");
		try {
			FileOutputStream out = new FileOutputStream(file);
			try {
				String s = "test" + System.currentTimeMillis();
				out.write(s.getBytes());
				out.flush();

				long mediaId = cloud.uploadVideoToMessage(id, file);
				assertTrue(mediaId > 0);
			} finally {
				out.close();
			}
		} finally {
			if (!file.delete()) {
				file.deleteOnExit();
			}
		}
	}
}
