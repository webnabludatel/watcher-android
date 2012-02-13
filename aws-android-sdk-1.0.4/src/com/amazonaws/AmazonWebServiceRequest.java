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
package com.amazonaws;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.auth.AWSCredentials;

/**
 * Base class for all user facing web service requests.
 */
public abstract class AmazonWebServiceRequest {

    /**
     * Arbitrary options storage for individual {@link AmazonWebServiceRequest}s. This
     * field is not intended to be used by clients.
     */
    private final RequestClientOptions requestClientOptions = new RequestClientOptions();
    
    /** The optional STS security token associated with this request */
    private String delegationToken;

    /**
     * The optional credentials to use for this request - overrides the
     * default credentials set at the client level.
     */
	private AWSCredentials credentials;


    /**
     * Returns the optional STS security token associated with the request.
     *
     * @return the optional STS security token associated with the request.
     */
    public String getDelegationToken() {
        return delegationToken;
    }

    /**
     * Sets the optional STS security token associated with the request.
     *
     * @param delegationToken
     *            The optional STS security token associated with the request.
     */
    public void setDelegationToken(String delegationToken) {
        this.delegationToken = delegationToken;
    }

	/**
	 * Sets the optional credentials to use for this request, overriding the
	 * default credentials set at the client level.
	 *
	 * @param credentials
	 *            The optional AWS security credentials to use for this request,
	 *            overriding the default credentials set at the client level.
	 */
    public void setRequestCredentials(AWSCredentials credentials) {
		this.credentials = credentials;
    }

	/**
	 * Returns the optional credentials to use to sign this request, overriding
	 * the default credentials set at the client level.
	 *
	 * @return The optional credentials to use to sign this request, overriding
	 *         the default credentials set at the client level.
	 */
    public AWSCredentials getRequestCredentials() {
    	return credentials;
    }

    /**
     * Internal only method for accessing private, internal request parameters.
     * Not intended for direct use by callers.
     *
     * @return private, internal request parameter information.
     */

    public Map<String, String> copyPrivateRequestParameters() {
        HashMap<String,String> map = new HashMap<String, String>();
        if (delegationToken != null) map.put("SecurityToken", delegationToken);

        return map;
    }


    /**
     * Gets the options stored with this request object. Intended for internal
     * use only.
     */
    public RequestClientOptions getRequestClientOptions() {
        return requestClientOptions;
    }
}
