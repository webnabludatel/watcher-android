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
package com.amazonaws.services.simpleemail.model.transform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.services.simpleemail.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/**
 * Send Raw Email Request Marshaller
 */
public class SendRawEmailRequestMarshaller implements Marshaller<Request<SendRawEmailRequest>, SendRawEmailRequest> {

    public Request<SendRawEmailRequest> marshall(SendRawEmailRequest sendRawEmailRequest) {

        if (sendRawEmailRequest == null) {
		    throw new AmazonClientException("Invalid argument passed to marshall(...)");
		}

        Request<SendRawEmailRequest> request = new DefaultRequest<SendRawEmailRequest>(sendRawEmailRequest, "AmazonSimpleEmailService");
        request.addParameter("Action", "SendRawEmail");
        request.addParameter("Version", "2010-12-01");

        if (sendRawEmailRequest.getSource() != null) {
            request.addParameter("Source", StringUtils.fromString(sendRawEmailRequest.getSource()));
        }

        java.util.List<String> destinationsList = sendRawEmailRequest.getDestinations();
        int destinationsListIndex = 1;

        for (String destinationsListValue : destinationsList) {
            if (destinationsListValue != null) {
                request.addParameter("Destinations.member." + destinationsListIndex, StringUtils.fromString(destinationsListValue));
            }

            destinationsListIndex++;
        }
        RawMessage rawMessageRawMessage = sendRawEmailRequest.getRawMessage();
        if (rawMessageRawMessage != null) {
            if (rawMessageRawMessage.getData() != null) {
                request.addParameter("RawMessage.Data", StringUtils.fromByteBuffer(rawMessageRawMessage.getData()));
            }
        }


        return request;
    }
}
