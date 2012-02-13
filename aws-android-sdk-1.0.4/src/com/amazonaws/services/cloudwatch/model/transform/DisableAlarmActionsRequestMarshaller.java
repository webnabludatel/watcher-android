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
package com.amazonaws.services.cloudwatch.model.transform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.services.cloudwatch.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

/**
 * Disable Alarm Actions Request Marshaller
 */
public class DisableAlarmActionsRequestMarshaller implements Marshaller<Request<DisableAlarmActionsRequest>, DisableAlarmActionsRequest> {

    public Request<DisableAlarmActionsRequest> marshall(DisableAlarmActionsRequest disableAlarmActionsRequest) {

        if (disableAlarmActionsRequest == null) {
		    throw new AmazonClientException("Invalid argument passed to marshall(...)");
		}

        Request<DisableAlarmActionsRequest> request = new DefaultRequest<DisableAlarmActionsRequest>(disableAlarmActionsRequest, "AmazonCloudWatch");
        request.addParameter("Action", "DisableAlarmActions");
        request.addParameter("Version", "2010-08-01");


        java.util.List<String> alarmNamesList = disableAlarmActionsRequest.getAlarmNames();
        int alarmNamesListIndex = 1;

        for (String alarmNamesListValue : alarmNamesList) {
            if (alarmNamesListValue != null) {
                request.addParameter("AlarmNames.member." + alarmNamesListIndex, StringUtils.fromString(alarmNamesListValue));
            }

            alarmNamesListIndex++;
        }


        return request;
    }
}
