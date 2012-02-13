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
 * Get Queue Url Request Marshaller
 */
public class GetQueueUrlRequestMarshaller implements Marshaller<Request<GetQueueUrlRequest>, GetQueueUrlRequest> {

    public Request<GetQueueUrlRequest> marshall(GetQueueUrlRequest getQueueUrlRequest) {

        if (getQueueUrlRequest == null) {
		    throw new AmazonClientException("Invalid argument passed to marshall(...)");
		}

        Request<GetQueueUrlRequest> request = new DefaultRequest<GetQueueUrlRequest>(getQueueUrlRequest, "AmazonSQS");
        request.addParameter("Action", "GetQueueUrl");
        request.addParameter("Version", "2011-10-01");

        if (getQueueUrlRequest.getQueueName() != null) {
            request.addParameter("QueueName", StringUtils.fromString(getQueueUrlRequest.getQueueName()));
        }
        if (getQueueUrlRequest.getQueueOwnerAWSAccountId() != null) {
            request.addParameter("QueueOwnerAWSAccountId", StringUtils.fromString(getQueueUrlRequest.getQueueOwnerAWSAccountId()));
        }


        return request;
    }
}
