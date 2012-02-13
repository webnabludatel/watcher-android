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
package com.amazonaws.services.ec2.model.transform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.services.ec2.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/**
 * Create Image Request Marshaller
 */
public class CreateImageRequestMarshaller implements Marshaller<Request<CreateImageRequest>, CreateImageRequest> {

    public Request<CreateImageRequest> marshall(CreateImageRequest createImageRequest) {

        if (createImageRequest == null) {
		    throw new AmazonClientException("Invalid argument passed to marshall(...)");
		}

        Request<CreateImageRequest> request = new DefaultRequest<CreateImageRequest>(createImageRequest, "AmazonEC2");
        request.addParameter("Action", "CreateImage");
        request.addParameter("Version", "2011-05-15");

        if (createImageRequest.getInstanceId() != null) {
            request.addParameter("InstanceId", StringUtils.fromString(createImageRequest.getInstanceId()));
        }
        if (createImageRequest.getName() != null) {
            request.addParameter("Name", StringUtils.fromString(createImageRequest.getName()));
        }
        if (createImageRequest.getDescription() != null) {
            request.addParameter("Description", StringUtils.fromString(createImageRequest.getDescription()));
        }
        if (createImageRequest.isNoReboot() != null) {
            request.addParameter("NoReboot", StringUtils.fromBoolean(createImageRequest.isNoReboot()));
        }


        return request;
    }
}
