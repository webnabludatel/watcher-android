package org.dvaletin.apps.nabludatel.server;

import android.os.Environment;
import junit.framework.TestCase;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Alexey Efimov
 */
public class NabludatelCloudTest extends TestCase {
	private final NabludatelCloud cloud = new NabludatelCloud("test_android");

	public void testAuthentication() {
		JSONObject authentication = cloud.authentication();
		assertNotNull(authentication);

		assertTrue(cloud.isAuthenticated());
		assertTrue(cloud.getAuthenticatedUserId() != -1L);
	}

	public void testPostNewMessage() throws Exception {
		long id = cloud.postNewMessage("test_polling_id", "test_key", "test_value", 0.0, 0.1, System.currentTimeMillis());
		assertTrue(id > 0);

		id = cloud.postNewMessage("test_key", "test_value", 0.0, 0.1, System.currentTimeMillis());
		assertTrue(id > 0);
	}

	public void testEditMessage() throws Exception {
		long id = cloud.postNewMessage("test_polling_id", "test_key", "test_value", 0.0, 0.1, System.currentTimeMillis());
		assertTrue(id > 0);
		long editedId = cloud.editMessage(id, "test_polling_id", "test_key", "test_value_edited", 0.2, 0.3, System.currentTimeMillis());
		assertTrue(id > 0);
		assertTrue(id == editedId);
	}

	public void testUploadMediaToMessage() throws Exception {
		long id = cloud.postNewMessage("test_polling_id", "test_key", "test_value", 0.0, 0.1, System.currentTimeMillis());
		File sampleDir = Environment.getExternalStorageDirectory();
		File file = new File(sampleDir, "-tmp.jpg");
		try {
			FileOutputStream out = new FileOutputStream(file);
			try {
				String s = "test" + System.currentTimeMillis();
				out.write(s.getBytes());
				out.flush();

				long mediaId = cloud.uploadMediaToMessage(id, "tests", file, "photo");
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
