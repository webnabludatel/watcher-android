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
 * Put Notification Configuration Request Marshaller
 */
public class PutNotificationConfigurationRequestMarshaller implements Marshaller<Request<PutNotificationConfigurationRequest>, PutNotificationConfigurationRequest> {

    public Request<PutNotificationConfigurationRequest> marshall(PutNotificationConfigurationRequest putNotificationConfigurationRequest) {

        if (putNotificationConfigurationRequest == null) {
		    throw new AmazonClientException("Invalid argument passed to marshall(...)");
		}

        Request<PutNotificationConfigurationRequest> request = new DefaultRequest<PutNotificationConfigurationRequest>(putNotificationConfigurationRequest, "AmazonAutoScaling");
        request.addParameter("Action", "PutNotificationConfiguration");
        request.addParameter("Version", "2011-01-01");

        if (putNotificationConfigurationRequest.getAutoScalingGroupName() != null) {
            request.addParameter("AutoScalingGroupName", StringUtils.fromString(putNotificationConfigurationRequest.getAutoScalingGroupName()));
        }
        if (putNotificationConfigurationRequest.getTopicARN() != null) {
            request.addParameter("TopicARN", StringUtils.fromString(putNotificationConfigurationRequest.getTopicARN()));
        }

        java.util.List<String> notificationTypesList = putNotificationConfigurationRequest.getNotificationTypes();
        int notificationTypesListIndex = 1;

        for (String notificationTypesListValue : notificationTypesList) {
            if (notificationTypesListValue != null) {
                request.addParameter("NotificationTypes.member." + notificationTypesListIndex, StringUtils.fromString(notificationTypesListValue));
            }

            notificationTypesListIndex++;
        }


        return request;
    }
}
