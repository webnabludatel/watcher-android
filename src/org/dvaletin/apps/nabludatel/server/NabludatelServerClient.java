package org.dvaletin.apps.nabludatel.server;

import android.util.Log;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Implementation of API
 * https://progress-engine.basecamphq.com/W4390792
 *
 * @author Alexey Efimov
 */
public class NabludatelServerClient {
	public static final String T = NabludatelServerClient.class.getSimpleName();

	private final JsonHttpClient client = new JsonHttpClient();
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

	private String toRequest(JSONObject payload) {
		return deviceIdToRequest() + "&payload=" + urlEncode(payload.toString());
	}

	private String deviceIdToRequest() {
		return "device_id=" + urlEncode(deviceId);
	}

	private String toDigestUrl(String prefix, JSONObject payload, String secret) throws NabludatelServerException {
		try {
			String token = deviceId + payload + secret;
			String digest = md5(token, "UTF-8").toLowerCase();
			Log.d(T, "MD5 of " + token + " -> " + digest);
			return prefix + "?digest=" + urlEncode(digest);
		} catch (NoSuchAlgorithmException e) {
			throw new NabludatelServerException("Can't find MD5 digest algorithm realization", e);
		} catch (UnsupportedEncodingException e) {
			throw new NabludatelServerException("Can't find UTF-8 encoding", e);
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
			if (response.get("status").equals("ok")) {
				return response.getJSONObject("result");
			}
			throw new NabludatelServerException("Error in input parameters: " + response.get("errors"));
		} catch (IOException e) {
			throw new NabludatelServerException(e);
		}
	}

	private static String urlEncode(String param) {
		try {
			return URLEncoder.encode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return param;
		}
	}

	private static String md5(String s, String encoding) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		// Create MD5 Hash
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(s.getBytes(encoding));

		// Create Hex String
		return new String(Hex.encodeHex(digest.digest()));
	}
}
