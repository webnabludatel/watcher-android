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
package com.amazonaws.services.autoscaling.model.transform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.services.autoscaling.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/**
 * Describe Notification Configurations Request Marshaller
 */
public class DescribeNotificationConfigurationsRequestMarshaller implements Marshaller<Request<DescribeNotificationConfigurationsRequest>, DescribeNotificationConfigurationsRequest> {

    public Request<DescribeNotificationConfigurationsRequest> marshall(DescribeNotificationConfigurationsRequest describeNotificationConfigurationsRequest) {

        if (describeNotificationConfigurationsRequest == null) {
		    throw new AmazonClientException("Invalid argument passed to marshall(...)");
		}

        Request<DescribeNotificationConfigurationsRequest> request = new DefaultRequest<DescribeNotificationConfigurationsRequest>(describeNotificationConfigurationsRequest, "AmazonAutoScaling");
        request.addParameter("Action", "DescribeNotificationConfigurations");
        request.addParameter("Version", "2011-01-01");


        java.util.List<String> autoScalingGroupNamesList = describeNotificationConfigurationsRequest.getAutoScalingGroupNames();
        int autoScalingGroupNamesListIndex = 1;

        for (String autoScalingGroupNamesListValue : autoScalingGroupNamesList) {
            if (autoScalingGroupNamesListValue != null) {
                request.addParameter("AutoScalingGroupNames.member." + autoScalingGroupNamesListIndex, StringUtils.fromString(autoScalingGroupNamesListValue));
            }

            autoScalingGroupNamesListIndex++;
        }
        if (describeNotificationConfigurationsRequest.getNextToken() != null) {
            request.addParameter("NextToken", StringUtils.fromString(describeNotificationConfigurationsRequest.getNextToken()));
        }
        if (describeNotificationConfigurationsRequest.getMaxRecords() != null) {
            request.addParameter("MaxRecords", StringUtils.fromInteger(describeNotificationConfigurationsRequest.getMaxRecords()));
        }


        return request;
    }
}
