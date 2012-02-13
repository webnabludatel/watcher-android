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
package com.amazon.aws.tvmclient;

import com.amazonaws.util.HttpUtils;

import android.util.Log;

public class GetTokenRequest extends Request {
    private static final String LOG_TAG = "GetTokenRequest";

    private final String endpoint;
    private final String uid;
    private final String key;
    private final boolean useSSL;
    
    public GetTokenRequest( final String endpoint, final boolean useSSL, final String uid, final String key ) {
        this.endpoint = endpoint;
        this.useSSL = useSSL;
        this.uid = uid;
        this.key = key;
    }
    
    public String buildRequestUrl() {
        StringBuilder builder = new StringBuilder( ( this.useSSL ? "https://" : "http://" ) );
        builder.append( this.endpoint );
        builder.append( "/" );
        
        String timestamp = Utilities.getTimestamp();
        String signature = Utilities.getSignature( timestamp, key );
        
        builder.append( "gettoken" );
        builder.append( "?uid=" + HttpUtils.urlEncode( this.uid, false ) );
        builder.append( "&timestamp=" + HttpUtils.urlEncode( timestamp, false ) );
        builder.append( "&signature=" + HttpUtils.urlEncode( signature, false ) );
        
        return builder.toString();
    }
    
}
