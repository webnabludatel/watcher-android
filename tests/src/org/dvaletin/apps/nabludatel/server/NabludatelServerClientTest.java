package org.dvaletin.apps.nabludatel.server;

import android.test.AndroidTestCase;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Alexey Efimov
 */
public class NabludatelServerClientTest extends AndroidTestCase {
	private final NabludatelServerClient client =
			new NabludatelServerClient("http://webnabludatel.org/api/v1", "test_android");

	private static JSONObject createTestMessagePayload() throws JSONException {
		JSONObject payload = new JSONObject();
		payload.put("test", "android");
		return payload;
	}

	public NabludatelServerClientTest() {
	}

	public void testAuthentication() throws Exception {
		String secret = client.authentication();
		assertNotNull(secret);
		assertNotSame("", secret);
	}

	public void testPostNewMessage() throws Exception {
		String secret = client.authentication();
		JSONObject payload = createTestMessagePayload();
		long messageId = client.postNewMessage(secret, payload);
		assertTrue(messageId != -1L);
	}

	public void testEditMessage() throws Exception {
		String secret = client.authentication();
		JSONObject payload = createTestMessagePayload();
		long messageId = client.postNewMessage(secret, payload);
		messageId = client.editMessage(messageId, secret, payload);
		assertTrue(messageId != -1L);
	}

	public void testAttachMediaToMessage() throws Exception {
		String secret = client.authentication();
		JSONObject payload = createTestMessagePayload();
		long messageId = client.postNewMessage(secret, payload);
		messageId = client.attachMediaToMessage(messageId, secret, payload);
		assertTrue(messageId != -1L);
	}
}
