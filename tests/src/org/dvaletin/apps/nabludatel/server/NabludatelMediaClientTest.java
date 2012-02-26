package org.dvaletin.apps.nabludatel.server;

import android.os.Environment;
import junit.framework.TestCase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Alexey Efimov
 */
public class NabludatelMediaClientTest extends TestCase {
	private final NabludatelMediaClient client = new NabludatelCloud("test_android").getMediaClient();

	public void testOverwriteUpload() throws Exception {
		assertNotNull(client);

		File sampleDir = Environment.getExternalStorageDirectory();
		if (!sampleDir.exists()) {
			assertTrue(sampleDir.mkdirs());
		}
		File file = new File(sampleDir, "-tmp.txt");
		try {
			if (file.exists()) {
				assertTrue(file.delete());
			}
			FileOutputStream out = new FileOutputStream(file);
			try {
				String s = "test" + System.currentTimeMillis();
				out.write(s.getBytes());
				out.flush();

				String url = client.upload("tests", file);
				assertEquals(client.toUrl("tests", file), url);

				DefaultHttpClient httpClient = new DefaultHttpClient();
				ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				httpClient.execute(new HttpGet(url)).getEntity().writeTo(bytes);
				assertEquals(s, bytes.toString());
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
