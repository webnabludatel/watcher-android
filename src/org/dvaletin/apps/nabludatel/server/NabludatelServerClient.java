package org.dvaletin.apps.nabludatel.server;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
	private final JsonHttpClient client = new JsonHttpClient();
	private final String serverApiRoot;

	public NabludatelServerClient(String serverApiRoot) {
		this.serverApiRoot = serverApiRoot;
	}

	public String authentication(String deviceId) throws NabludatelServerException, JSONException {
		String request = "device_id=" + urlEncode(deviceId);
		String url = "/authentications.json";
		return post(request, url).getString("secret");
	}

	public long postNewMessage(String deviceId, String secret, JSONObject payload) throws NabludatelServerException, JSONException {
		String request = toRequest(deviceId, payload);
		String url = toDigestUrl("/messages.json", request, secret);
		return post(request, url).getLong("message_id");
	}

	public long editMessage(long messageId, String deviceId, String secret, JSONObject payload) throws NabludatelServerException, JSONException {
		String request = toRequest(deviceId, payload);
		String url = toDigestUrl("/messages/" + messageId + ".json", request, secret);
		return put(request, url).getLong("message_id");
	}

	public long attachMediaToMessage(long messageId, String deviceId, String secret, JSONObject payload) throws NabludatelServerException, JSONException {
		String request = toRequest(deviceId, payload);
		String url = toDigestUrl("/messages/" + messageId + "/media_items.json", request, secret);
		return post(request, url).getLong("media_item_id");
	}

	private static String toRequest(String deviceId, JSONObject payload) {
		return "device_id=" + urlEncode(deviceId) + "&payload=" + urlEncode(payload.toString());
	}

	private static String toDigestUrl(String prefix, String request, String secret) throws NabludatelServerException {
		try {
			String digest = md5(request + secret, "UTF-8").toLowerCase();
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
		StringBuilder md5 = new StringBuilder();
		byte messageDigest[] = digest.digest();
		for (byte b : messageDigest) {
			md5.append(Integer.toHexString(0xFF & b));
		}
		return md5.toString();
	}
}
