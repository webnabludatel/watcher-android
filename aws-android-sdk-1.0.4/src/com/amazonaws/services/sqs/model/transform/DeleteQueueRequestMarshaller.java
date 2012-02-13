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
package com.amazonaws.services.sqs.model.transform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.services.sqs.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/**
 * Delete Queue Request Marshaller
 */
public class DeleteQueueRequestMarshaller implements Marshaller<Request<DeleteQueueRequest>, DeleteQueueRequest> {

    public Request<DeleteQueueRequest> marshall(DeleteQueueRequest deleteQueueRequest) {

        if (deleteQueueRequest == null) {
		    throw new AmazonClientException("Invalid argument passed to marshall(...)");
		}

        Request<DeleteQueueRequest> request = new DefaultRequest<DeleteQueueRequest>(deleteQueueRequest, "AmazonSQS");
        request.addParameter("Action", "DeleteQueue");
        request.addParameter("Version", "2011-10-01");

        if (deleteQueueRequest.getQueueUrl() != null) {
            request.addParameter("QueueUrl", StringUtils.fromString(deleteQueueRequest.getQueueUrl()));
        }


        return request;
    }
}
