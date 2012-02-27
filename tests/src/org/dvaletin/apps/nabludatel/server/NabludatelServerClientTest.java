package org.dvaletin.apps.nabludatel.server;

import junit.framework.TestCase;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Alexey Efimov
 */
public class NabludatelServerClientTest extends TestCase {
	private final NabludatelServerClient client = new NabludatelCloud("test_android").getServerClient();

	private static JSONObject createTestMessagePayload() throws JSONException {
		JSONObject payload = new JSONObject();
		payload.put("test", "android");
		return payload;
	}

	public NabludatelServerClientTest() {
	}

	public void testAuthenticationSecret() throws Exception {
		String secret = client.authenticationSecret();
		assertNotNull(secret);
		assertNotSame("", secret);
	}

	public void testPostNewMessage() throws Exception {
		String secret = client.authenticationSecret();
		JSONObject payload = createTestMessagePayload();
		long messageId = client.postNewMessage(secret, payload);
		assertTrue(messageId != -1L);
	}

	public void testEditMessage() throws Exception {
		String secret = client.authenticationSecret();
		JSONObject payload = createTestMessagePayload();
		long messageId = client.postNewMessage(secret, payload);
		messageId = client.editMessage(messageId, secret, payload);
		assertTrue(messageId != -1L);
	}

	public void testAttachMediaToMessage() throws Exception {
		String secret = client.authenticationSecret();
		JSONObject payload = createTestMessagePayload();
		long messageId = client.postNewMessage(secret, payload);
		long mediaItemId = client.attachMediaToMessage(messageId, secret, payload);
		assertTrue(mediaItemId != -1L);
	}

	public void testDeleteMediaFromMessage() throws Exception {
		String secret = client.authenticationSecret();
		JSONObject payload = createTestMessagePayload();
		long messageId = client.postNewMessage(secret, payload);
		long mediaItemId = client.attachMediaToMessage(messageId, secret, payload);
		assertTrue(mediaItemId != -1L);
		payload.put("deleted", true);
		long deletedMediaItemId = client.deleteMediaFromMessage(mediaItemId, secret, payload);
		assertTrue(mediaItemId == deletedMediaItemId);
	}
}
