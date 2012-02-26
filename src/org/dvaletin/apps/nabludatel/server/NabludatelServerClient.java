package org.dvaletin.apps.nabludatel.server;

import android.util.Log;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.dvaletin.apps.nabludatel.utils.Encodings;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.security.NoSuchAlgorithmException;

/**
 * Implementation of API
 * https://progress-engine.basecamphq.com/W4390792
 *
 * @author Alexey Efimov
 */
public class NabludatelServerClient {
	public static final String T = NabludatelServerClient.class.getSimpleName();

	private final JsonHttpClient client = new GZIPJsonHttpClient();
	private final String serverApiRoot;
	private final String deviceId;

	public NabludatelServerClient(String serverApiRoot, String deviceId) {
		this.serverApiRoot = serverApiRoot;
		this.deviceId = deviceId;
	}

	public String authenticationSecret() throws NabludatelServerException, JSONException {
		return authentication().getString("secret");
	}

	public JSONObject authentication() throws NabludatelServerException, JSONException {
		return post(deviceIdToRequest(), "/authentications.json");
	}

	public long postNewMessage(String secret, JSONObject payload) throws NabludatelServerException, JSONException {
		String request = toRequest(payload);
		String url = toDigestUrl("/messages.json", payload, secret);
		return post(request, url).getLong("message_id");
	}

	public long editMessage(long messageId, String secret, JSONObject payload) throws NabludatelServerException, JSONException {
		String request = toRequest(payload);
		String url = toDigestUrl("/messages/" + messageId + ".json", payload, secret);
		return put(request, url).getLong("message_id");
	}

	public long attachMediaToMessage(long messageId, String secret, JSONObject payload) throws NabludatelServerException, JSONException {
		String request = toRequest(payload);
		String url = toDigestUrl("/messages/" + messageId + "/media_items.json", payload, secret);
		return post(request, url).getLong("media_item_id");
	}

	// todo: This method will throw 404, it not implemented on server.
	public long deleteMediaFromMessage(long messageId, long mediaItemId, String secret, JSONObject payload) throws NabludatelServerException, JSONException {
		String request = toRequest(payload);
		String url = toDigestUrl("/messages/" + messageId + "/media_items/" + mediaItemId + ".json", payload, secret);
		return put(request, url).getLong("media_item_id");
	}

	private String toRequest(JSONObject payload) {
		return deviceIdToRequest() + "&payload=" + Encodings.urlEncode(payload.toString());
	}

	private String deviceIdToRequest() {
		return "device_id=" + Encodings.urlEncode(deviceId);
	}

	private String toDigestUrl(String prefix, JSONObject payload, String secret) throws NabludatelServerException {
		try {
			String token = deviceId + payload + secret;
			String digest = Encodings.md5(token).toLowerCase();
			Log.d(T, "MD5 of " + token + " -> " + digest);
			return prefix + "?digest=" + Encodings.urlEncode(digest);
		} catch (NoSuchAlgorithmException e) {
			throw new NabludatelServerException("Can't find MD5 digest algorithm realization", e);
		}
	}

	private JSONObject post(String request, String url) throws NabludatelServerException, JSONException {
		return request(request, new HttpPost(serverApiRoot + url));
	}

	private JSONObject put(String request, String url) throws NabludatelServerException, JSONException {
		return request(request, new HttpPut(serverApiRoot + url));
	}

	private JSONObject request(String request, HttpEntityEnclosingRequestBase method) throws NabludatelServerException, JSONException {
		try {
			JSONObject response = client.request(request, method);
			if (response.getString("status").equals("ok")) {
				return response.getJSONObject("result");
			}
			if (response.has("error")) {
				throw new NabludatelServerException(response.getString("error"));
			}
			throw new NabludatelServerException("Unknown response: " + response);
		} catch (IOException e) {
			throw new NabludatelServerException(e);
		}
	}
}
