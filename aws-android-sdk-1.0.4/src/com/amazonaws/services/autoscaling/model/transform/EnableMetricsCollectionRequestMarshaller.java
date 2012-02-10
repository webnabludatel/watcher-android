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
 * Enable Metrics Collection Request Marshaller
 */
public class EnableMetricsCollectionRequestMarshaller implements Marshaller<Request<EnableMetricsCollectionRequest>, EnableMetricsCollectionRequest> {

    public Request<EnableMetricsCollectionRequest> marshall(EnableMetricsCollectionRequest enableMetricsCollectionRequest) {

        if (enableMetricsCollectionRequest == null) {
		    throw new AmazonClientException("Invalid argument passed to marshall(...)");
		}

        Request<EnableMetricsCollectionRequest> request = new DefaultRequest<EnableMetricsCollectionRequest>(enableMetricsCollectionRequest, "AmazonAutoScaling");
        request.addParameter("Action", "EnableMetricsCollection");
        request.addParameter("Version", "2011-01-01");

        if (enableMetricsCollectionRequest.getAutoScalingGroupName() != null) {
            request.addParameter("AutoScalingGroupName", StringUtils.fromString(enableMetricsCollectionRequest.getAutoScalingGroupName()));
        }

        java.util.List<String> metricsList = enableMetricsCollectionRequest.getMetrics();
        int metricsListIndex = 1;

        for (String metricsListValue : metricsList) {
            if (metricsListValue != null) {
                request.addParameter("Metrics.member." + metricsListIndex, StringUtils.fromString(metricsListValue));
            }

            metricsListIndex++;
        }
        if (enableMetricsCollectionRequest.getGranularity() != null) {
            request.addParameter("Granularity", StringUtils.fromString(enableMetricsCollectionRequest.getGranularity()));
        }


        return request;
    }
}
