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
package com.amazon.aws.demo.anonymous;

import com.amazon.aws.tvmclient.AmazonTVMClient;
import com.amazon.aws.tvmclient.AmazonSharedPreferencesWrapper;
import com.amazon.aws.tvmclient.Response;

import android.content.SharedPreferences;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.sns.AmazonSNSClient;

import com.amazonaws.handlers.SessionBasedAuthenticationRequestHandler;

import android.util.Log;

/**
* This class is used to get clients to the various AWS services.  Before accessing a client 
* the credentials should be checked to ensure validity.
*/
public class AmazonClientManager {
    private static final String LOG_TAG = "AmazonClientManager";


    private AmazonS3Client s3Client = null;
    private AmazonSQSClient sqsClient = null;
    private AmazonSimpleDBClient sdbClient = null;
    private AmazonSNSClient snsClient = null;
    private SharedPreferences sharedPreferences = null;
	    
    public AmazonClientManager( SharedPreferences settings ) {
        this.sharedPreferences = settings;
    }
                
    public AmazonS3Client s3() {
        validateCredentials();
        return s3Client;
    }
        
    public AmazonSQSClient sqs() {
        validateCredentials();    
        return sqsClient;
    }

    public AmazonSimpleDBClient sdb() {
        validateCredentials();    
        return sdbClient;
    }

    public AmazonSNSClient sns() {
        validateCredentials();    
        return snsClient;
    }
    
    public boolean hasCredentials() {
        return PropertyLoader.getInstance().hasCredentials();
    }
    
    public Response validateCredentials() {
        Response ableToGetToken = Response.SUCCESSFUL;
    
        if ( AmazonSharedPreferencesWrapper.areCredentialsExpired( this.sharedPreferences ) ) {
            Log.i( LOG_TAG, "Credentials were expired." );
        
            clearCredentials();        
        
            AmazonTVMClient tvm = new AmazonTVMClient( this.sharedPreferences, PropertyLoader.getInstance().getTokenVendingMachineURL(), PropertyLoader.getInstance().useSSL() );
            ableToGetToken = tvm.anonymousRegister();
            if ( ableToGetToken.requestWasSuccessful() ) {
                ableToGetToken = tvm.getToken();            
            }
        }

        if ( ableToGetToken.requestWasSuccessful() && ( s3Client == null || sqsClient == null || sdbClient == null || snsClient == null ) ) {        
            Log.i( LOG_TAG, "Creating New Credentials." );
        
            AWSCredentials credentials = AmazonSharedPreferencesWrapper.getCredentialsFromSharedPreferences( this.sharedPreferences );

		    s3Client = new AmazonS3Client( credentials );
		    sqsClient = new AmazonSQSClient( credentials );
		    sdbClient = new AmazonSimpleDBClient( credentials );
		    snsClient = new AmazonSNSClient( credentials );
        }

        return ableToGetToken;        
    }
    
    public void clearCredentials() {
        s3Client = null;
        sqsClient = null;
        sdbClient = null;
        snsClient = null;    
    }
    
    public void wipe() {
        AmazonSharedPreferencesWrapper.wipe( this.sharedPreferences );        
    }
}
