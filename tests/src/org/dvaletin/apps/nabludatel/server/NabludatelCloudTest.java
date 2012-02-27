package org.dvaletin.apps.nabludatel.server;

import android.os.Environment;
import junit.framework.TestCase;
import org.dvaletin.apps.nabludatel.utils.Consts;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
		long id = cloud.postNewMessage("test_key", "test_value", 0.0, 0.1, System.currentTimeMillis(), 1L, 1L);
		assertTrue(id > 0);
	}

	public void testEditMessage() throws Exception {
		long id = cloud.postNewMessage("test_key", "test_value", 0.0, 0.1, System.currentTimeMillis(), 1L, 1L);
		assertTrue(id > 0);
		long editedId = cloud.editMessage(id, "test_key", "test_value_edited", 0.2, 0.3, System.currentTimeMillis(), 1L, 1L);
		assertTrue(id > 0);
		assertTrue(id == editedId);
	}

	public void testUploadMediaToMessage() throws Exception {
		long id = cloud.postNewMessage("test_key", "test_value", 0.0, 0.1, System.currentTimeMillis(), 1L, 1L);
		long mediaId = uploadTestFile(id);
		assertTrue(mediaId > 0);
	}

	public void testSetDeletedMediaForMessage() throws Exception {
		long id = cloud.postNewMessage("test_key", "test_value", 0.0, 0.1, System.currentTimeMillis(), 1L, 1L);
		long mediaId = uploadTestFile(id);
		long deletedMediaId = cloud.setMediaDeletedForMessage(id, mediaId, System.currentTimeMillis(), 1L);
		assertTrue(mediaId == deletedMediaId);
	}

	private long uploadTestFile(long id) throws IOException, NabludatelCloudRequestTimeTooSkewedException {
		File sampleDir = Environment.getExternalStorageDirectory();
		File file = new File(sampleDir, "-tmp.jpg");
		try {
			FileOutputStream out = new FileOutputStream(file);
			try {
				String s = "test" + System.currentTimeMillis();
				out.write(s.getBytes());
				out.flush();

				return cloud.uploadMediaToMessage(id, 0L, "tests", file, Consts.PHOTO_FILE, 1L, 1L);
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
