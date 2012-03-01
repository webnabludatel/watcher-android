package org.dvaletin.apps.nabludatel.utils;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Alexey Efimov
 */
public class LocalProperties {
	private static final String T = LocalProperties.class.getSimpleName();

	private static final Properties properties = new Properties();
	static {
		try {
			InputStream localProperties = LocalProperties.class.getResourceAsStream("local.properties");
			if (localProperties != null) {
				properties.load(localProperties);
			}
		} catch (IOException e) {
			Log.w(T, "Error reading local.properties", e);
		}
	}

	public static String getS3AccessKey() {
		return properties.getProperty("s3.access.key");
	}

	public static String getS3SecretKey() {
		return properties.getProperty("s3.secret.key");
	}

	public static String getFacebookSecret() {
		return properties.getProperty("facebook.secret.key");
	}

	public static String getTwitterKey(){
		return properties.getProperty("tw.key");
	}

	public static String getTwitterSecret(){
		return properties.getProperty("tw.secret");
	}

	public static String getTwitterCallback(){
		return properties.getProperty("tw.callback");
	}
}
