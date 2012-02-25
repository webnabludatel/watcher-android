package org.dvaletin.apps.nabludatel.utils;

import android.util.Log;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Alexey Efimov
 */
public class Encodings {
	public static final String T = Encodings.class.getSimpleName();

	public static String urlEncode(String param) {
		try {
			return URLEncoder.encode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Log.w(T, "Unsupported encoding UTF-8", e);
			return URLEncoder.encode(param);
		}
	}

	public static String md5(String s, String encoding) throws NoSuchAlgorithmException {
		// Create MD5 Hash
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(toBytesFailSafe(s, encoding));

		// Create Hex String
		return new String(Hex.encodeHex(digest.digest()));
	}

	private static byte[] toBytesFailSafe(String s, String encoding) {
		byte[] bytes;
		try {
			bytes = s.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
		    Log.w(T, "Unsupported encoding UTF-8", e);
			bytes = s.getBytes();
		}
		return bytes;
	}

	public static String md5(String s) throws NoSuchAlgorithmException {
		return md5(s, "UTF-8");
	}
}
