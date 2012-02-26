package org.dvaletin.apps.nabludatel.server;

import android.util.Log;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Cloud for interaction with server. It wrap all communication errors and write it to log silently.
 * Will return {@code null} or {@code -1} if error happen. No exceptions will thrown from cloud.
 *
 * @author Alexey Efimov
 */
public class NabludatelCloud {
	public static final String T = NabludatelCloud.class.getSimpleName();

	private final String deviceId;

	private final NabludatelServerClient serverClient;
	private JSONObject authentication;

	private NabludatelMediaClient mediaClient;

	public NabludatelCloud(String deviceId) {
		this.deviceId = deviceId;
		this.serverClient = new NabludatelServerClient("http://webnabludatel.org/api/v1", deviceId);
	}

	public NabludatelCloud(String deviceId, JSONObject authentication) {
		this(deviceId);
		this.authentication = authentication;
	}

	private static JSONObject toMessagePayload(String pollingPlaceId, String key, String value, double lat, double lng, long timestamp) throws JSONException {
		JSONObject payload = new JSONObject();
		payload.putOpt("polling_place_id", pollingPlaceId);
		payload.put("key", key);
		payload.put("value", value);
		payload.put("lat", lat);
		payload.put("lng", lng);
		if (timestamp > 0L) {
			payload.put("timestamp", timestamp);
		}
		return payload;
	}

	public long postNewMessage(String key, String value, double lat, double lng, long timestamp) {
		return postNewMessage(null, key, value, lat, lng, timestamp);
	}

	public long postNewMessage(String pollingPlaceId, String key, String value, double lat, double lng, long timestamp) {
		try {
			JSONObject payload = toMessagePayload(pollingPlaceId, key, value, lat, lng, timestamp);
			return postNewMessage(payload);
		} catch (JSONException e) {
			Log.w(T, "Can create JSON object", e);
		}
		return -1L;
	}

	private long postNewMessage(JSONObject payload) {
		try {
			return serverClient.postNewMessage(authenticationSecret(), payload);
		} catch (NabludatelServerException e) {
			Log.w(T, "Can't post new message for " + payload, e);
			if (e.isUnauthorized()) {
				resetAuthentication();
			}
		} catch (JSONException e) {
			Log.w(T, "Parse JSON error", e);
		}
		return -1L;
	}

	public long editMessage(long messageId, String pollingPlaceId, String key, String value, double lat, double lng, long timestamp) {
		try {
			JSONObject payload = toMessagePayload(pollingPlaceId, key, value, lat, lng, timestamp);
			return editMessage(messageId, payload);
		} catch (JSONException e) {
			Log.w(T, "Can create JSON object", e);
		}
		return -1L;
	}

	private long editMessage(long mesageId, JSONObject payload) {
		try {
			return serverClient.editMessage(mesageId, authenticationSecret(), payload);
		} catch (NabludatelServerException e) {
			Log.w(T, "Can't edit message " + mesageId + " with " + payload, e);
			if (e.isUnauthorized()) {
				resetAuthentication();
			}
		} catch (JSONException e) {
			Log.w(T, "Parse JSON error", e);
		}
		return -1L;
	}

	public long uploadMediaToMessage(long messageId, String folderName, File file, String mediaType) throws NabludatelCloudRequestTimeTooSkewedException {
		NabludatelMediaClient mediaClient = getMediaClient();
		if (mediaClient != null) {
			try {
				String url = mediaClient.upload(folderName, file);
				JSONObject payload = new JSONObject();
				payload.put("url", url);
				payload.put("type", mediaType);
				payload.put("timestamp", file.lastModified());
				return serverClient.attachMediaToMessage(messageId, authenticationSecret(), payload);
			} catch (NabludatelServerException e) {
				Log.w("Can't upload media to message " + messageId, e);
				if (e.isUnauthorized()) {
					resetAuthentication();
				}
				if (e.isRequestTimeTooSkewed()) {
					throw new NabludatelCloudRequestTimeTooSkewedException(e);
				}
			} catch (JSONException e) {
				Log.w(T, "Parse JSON error", e);
			}
		} else {
			Log.w(T, "Can't upload media to message " + messageId + ", media client not initialized");
		}
		return -1L;
	}

	private JSONObject doAuthentication() throws JSONException, NabludatelServerException {
		resetAuthentication();
		return serverClient.authentication();
	}

	private void resetAuthentication() {
		authentication = null;
	}

	public JSONObject authentication() {
		if (authentication == null) {
			try {
				authentication = doAuthentication();
			} catch (NabludatelServerException e) {
				Log.w(T, "Authentication error", e);
			} catch (JSONException e) {
				Log.w(T, "Parse JSON error", e);
			}
		}
		return authentication;
	}

	private String authenticationSecret() {
		try {
			JSONObject auth = authentication();
			return auth != null ? auth.getString("secret") : null;
		} catch (JSONException e) {
			Log.w(T, "Parse JSON error", e);
		}
		return null;
	}

	public boolean isAuthenticated() {
		return authentication != null;
	}

	public long getAuthenticatedUserId() {
		try {
			return authentication != null ? authentication.getLong("user_id") : -1L;
		} catch (JSONException e) {
			Log.w(T, "Parse JSON error", e);
		}
		return -1L;
	}

	public NabludatelServerClient getServerClient() {
		return serverClient;
	}

	private AWSCredentials getAWSCredentials() {
		try {
			JSONObject authentication = authentication();
			if (authentication.has("s3")) {
				JSONObject s3 = authentication.getJSONObject("s3");
				if (s3.has("access_key") && s3.has("secret_key")) {
					String accessKey = authentication.getString("access_key");
					String secretKey = authentication.getString("secret_key");
					return new BasicAWSCredentials(accessKey, secretKey);
				}
			}
		} catch (JSONException e) {
			Log.w(T, "Parse JSON error", e);
		}

		try {
			Properties properties = new Properties();
			InputStream awsProperties = NabludatelCloud.class.getResourceAsStream("aws.properties");
			if (awsProperties != null) {
				properties.load(awsProperties);
				String accessKey = properties.getProperty("android.access.key");
				String secretKey = properties.getProperty("android.secret.key");
				return new BasicAWSCredentials(accessKey, secretKey);
			}
		} catch (IOException e) {
			Log.w(T, "Error reading aws.properties", e);
		}
		throw new IllegalStateException("Can't get S3 access keys");
	}

	/**
	 * Return media storage client or {@code null} if it can't be initialized.
	 *
	 * @return Media client
	 */
	public NabludatelMediaClient getMediaClient() {
		if (mediaClient == null) {
			try {
				mediaClient = new NabludatelMediaClient(deviceId, getAWSCredentials());
			} catch (Exception e) {
				Log.w(T, "Can't get media client", e);
			}
		}
		return mediaClient;
	}


}
