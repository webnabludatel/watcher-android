package org.dvaletin.apps.nabludatel.rest;

import android.util.Log;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 * RESTfull JSON HTTP Client.
 *
 * @author Alexey Efimov
 */
public class JsonHttpClient {
	private static final String T = JsonHttpClient.class.getSimpleName();
	private final boolean gzipEnabled;

	public JsonHttpClient(boolean gzipEnabled) {
		this.gzipEnabled = gzipEnabled;
	}

	/**
	 * Will create new {@link DefaultHttpClient} and perform POST request to url.
	 *
	 * @param url     REST url
	 * @param request JSON request
	 * @return JSON response in form '{httpStatus: {code: 200, ...}, response: {...}}' (even if http response code is not 200 OK)
	 * @throws JSONException If response body contains not a JSON
	 * @throws IOException If network error
	 */
	public JSONObject post(String url, JSONObject request) throws JSONException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		// Set HTTP parameters
		post.setEntity(new StringEntity(request != null ? request.toString() : "{}"));
		post.setHeader("Accept", "application/json");
		post.setHeader("Content-type", "application/json");
		if (gzipEnabled) {
			post.setHeader("Accept-Encoding", "gzip");
		}

		long t = System.currentTimeMillis();
		HttpResponse response = httpClient.execute(post);
		long timeMs = System.currentTimeMillis() - t;
		StatusLine statusLine = response.getStatusLine();
		Log.i(T, "POST response " + statusLine.getStatusCode() + " [" + timeMs + "ms]");

		JSONObject result = new JSONObject();
		JSONObject httpStatus = new JSONObject();
		httpStatus.put("code", statusLine.getStatusCode());
		httpStatus.put("timeMs", timeMs);
		if (statusLine.getReasonPhrase() != null) {
			httpStatus.put("reason", statusLine.getReasonPhrase());
		}
		result.put("httpStatus", httpStatus);

		// Get hold of the response entity (-> the data):
		HttpEntity entity = response.getEntity();

		if (entity != null) {
			// Read the content stream
			InputStream inputStream = entity.getContent();
			try {
				Header contentEncoding = response.getFirstHeader("Content-Encoding");
				if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
					inputStream = new GZIPInputStream(inputStream);
				}

				// convert content stream to a String
				String responseBody = readLines(inputStream);
				// Transform the String into a JSONObject
				result.put("response", new JSONObject(responseBody));
			} finally {
				try {
					inputStream.close();
				} catch (IOException e) {
					Log.w(T, "Error while closing response stream: " + e);
				}
			}
		}
		return result;
	}


	private static String readLines(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder builder = new StringBuilder();

		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}

		return builder.toString();
	}

}
