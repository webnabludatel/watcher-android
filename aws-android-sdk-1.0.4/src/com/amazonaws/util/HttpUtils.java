/*
 * Copyright 2010-2011 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.util;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

public class HttpUtils {

    private static final String DEFAULT_ENCODING = "UTF-8";

    public static String urlEncode(String value, boolean path) {
        try {
            String encoded = URLEncoder.encode(value, DEFAULT_ENCODING)
                    .replace("+", "%20").replace("*", "%2A")
                    .replace("%7E", "~");
            if (path) {
                encoded = encoded.replace("%2F", "/");
            }

            return encoded;
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Returns true if the specified URI is using a non-standard port (i.e. any
     * port other than 80 for HTTP URIs or any port other than 443 for HTTPS
     * URIs).
     *
     * @param uri
     *
     * @return True if the specified URI is using a non-standard port, otherwise
     *         false.
     */
    public static boolean isUsingNonDefaultPort(URI uri) {
        String scheme = uri.getScheme().toLowerCase();
        int port = uri.getPort();

        if (port <= 0) return false;
        if (scheme.equals("http") && port == 80) return false;
        if (scheme.equals("https") && port == 443) return false;

        return true;
    }

}
