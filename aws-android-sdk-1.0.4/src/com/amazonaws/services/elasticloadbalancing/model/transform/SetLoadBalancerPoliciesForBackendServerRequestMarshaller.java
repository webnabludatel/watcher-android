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
package com.amazonaws.services.elasticloadbalancing.model.transform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.services.elasticloadbalancing.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/**
 * Set Load Balancer Policies For Backend Server Request Marshaller
 */
public class SetLoadBalancerPoliciesForBackendServerRequestMarshaller implements Marshaller<Request<SetLoadBalancerPoliciesForBackendServerRequest>, SetLoadBalancerPoliciesForBackendServerRequest> {

    public Request<SetLoadBalancerPoliciesForBackendServerRequest> marshall(SetLoadBalancerPoliciesForBackendServerRequest setLoadBalancerPoliciesForBackendServerRequest) {

        if (setLoadBalancerPoliciesForBackendServerRequest == null) {
		    throw new AmazonClientException("Invalid argument passed to marshall(...)");
		}

        Request<SetLoadBalancerPoliciesForBackendServerRequest> request = new DefaultRequest<SetLoadBalancerPoliciesForBackendServerRequest>(setLoadBalancerPoliciesForBackendServerRequest, "AmazonElasticLoadBalancing");
        request.addParameter("Action", "SetLoadBalancerPoliciesForBackendServer");
        request.addParameter("Version", "2011-08-15");

        if (setLoadBalancerPoliciesForBackendServerRequest.getLoadBalancerName() != null) {
            request.addParameter("LoadBalancerName", StringUtils.fromString(setLoadBalancerPoliciesForBackendServerRequest.getLoadBalancerName()));
        }
        if (setLoadBalancerPoliciesForBackendServerRequest.getInstancePort() != null) {
            request.addParameter("InstancePort", StringUtils.fromInteger(setLoadBalancerPoliciesForBackendServerRequest.getInstancePort()));
        }

        java.util.List<String> policyNamesList = setLoadBalancerPoliciesForBackendServerRequest.getPolicyNames();
        int policyNamesListIndex = 1;

            if (policyNamesList.isEmpty()) request.addParameter("PolicyNames", "");
        for (String policyNamesListValue : policyNamesList) {
            if (policyNamesListValue != null) {
                request.addParameter("PolicyNames.member." + policyNamesListIndex, StringUtils.fromString(policyNamesListValue));
            }

            policyNamesListIndex++;
        }


        return request;
    }
}
