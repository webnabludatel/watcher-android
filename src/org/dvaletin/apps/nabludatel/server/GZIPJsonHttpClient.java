package org.dvaletin.apps.nabludatel.server;

import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/**
 * Used GZIPed request.
 *
 * @author Alexey Efimov
 */
public class GZIPJsonHttpClient extends JsonHttpClient {
	public static final String T = GZIPJsonHttpClient.class.getSimpleName();

	private boolean enabled = true;

	@Override
	public JSONObject request(String request, HttpEntityEnclosingRequestBase method) throws JSONException, IOException {
		try {
			return super.request(request, method);
		} catch (HttpResponseException e) {
			if (enabled && HttpStatus.SC_INTERNAL_SERVER_ERROR == e.getStatusCode()) {
				enabled = false;
				Log.w(T, "Disable GZIP requests to server. Possible server not support this.", e);
				// Retry again
				return super.request(request, method);
			}
			throw e;
		}
	}

	@Override
	protected HttpEntity getEntity(String request, HttpEntityEnclosingRequestBase method) throws IOException {
		// See http://code.google.com/speed/page-speed/docs/payload.html#GzipCompression
		if (!enabled || request.length() <= 150) {
			return super.getEntity(request, method);
		} else {
			method.setHeader("Content-Encoding", "gzip");
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				GZIPOutputStream gzipOutputStream = new GZIPOutputStream(os);
				gzipOutputStream.write(request.getBytes("UTF-8"));
				gzipOutputStream.finish();
				return new ByteArrayEntity(os.toByteArray());
			} finally {
				try {
					os.close();
				} catch (IOException e) {
					Log.w(T, "Error closing stream", e);
				}
			}
		}
	}
}
