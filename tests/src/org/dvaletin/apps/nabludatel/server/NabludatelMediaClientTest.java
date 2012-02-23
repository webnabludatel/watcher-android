package org.dvaletin.apps.nabludatel.server;

import android.os.Environment;
import junit.framework.TestCase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Alexey Efimov
 */
public class NabludatelMediaClientTest extends TestCase {
	private static final String accessKey;
	private static final String secretKey;
	static {
		try {
			Properties properties = new Properties();
			properties.load(NabludatelMediaClient.class.getResourceAsStream("aws.properties"));
			accessKey = properties.getProperty("android.access.key");
			secretKey = properties.getProperty("android.secret.key");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private final NabludatelMediaClient client = new NabludatelMediaClient("test_android", accessKey, secretKey);

	public void testOverwriteUpload() throws Exception {
		File sampleDir = Environment.getExternalStorageDirectory();
		File file = new File(sampleDir, "-tmp.txt");
		try {
			FileOutputStream out = new FileOutputStream(file);
			try {
				String s = "test" + System.currentTimeMillis();
				out.write(s.getBytes());
				out.flush();

				String url = client.upload(file);
				assertEquals(client.toUrl(file), url);

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
