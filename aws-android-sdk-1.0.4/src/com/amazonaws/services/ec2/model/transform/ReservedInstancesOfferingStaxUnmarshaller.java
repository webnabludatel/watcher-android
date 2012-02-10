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

import java.util.Map;
import java.util.Map.Entry;

import javax.xml.stream.events.XMLEvent;

import com.amazonaws.services.ec2.model.*;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.MapEntry;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.*;


/**
 * Reserved Instances Offering StAX Unmarshaller
 */
public class ReservedInstancesOfferingStaxUnmarshaller implements Unmarshaller<ReservedInstancesOffering, StaxUnmarshallerContext> {

    public ReservedInstancesOffering unmarshall(StaxUnmarshallerContext context) throws Exception {
        ReservedInstancesOffering reservedInstancesOffering = new ReservedInstancesOffering();
        int originalDepth = context.getCurrentDepth();
        int targetDepth = originalDepth + 1;

        
        if (context.isStartOfDocument()) targetDepth += 1;
        

        while (true) {
            XMLEvent xmlEvent = context.nextEvent();
            if (xmlEvent.isEndDocument()) return reservedInstancesOffering;

            if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
                if (context.testExpression("reservedInstancesOfferingId", targetDepth)) {
                    reservedInstancesOffering.setReservedInstancesOfferingId(StringStaxUnmarshaller.getInstance().unmarshall(context));
                    continue;
                }
                if (context.testExpression("instanceType", targetDepth)) {
                    reservedInstancesOffering.setInstanceType(StringStaxUnmarshaller.getInstance().unmarshall(context));
                    continue;
                }
                if (context.testExpression("availabilityZone", targetDepth)) {
                    reservedInstancesOffering.setAvailabilityZone(StringStaxUnmarshaller.getInstance().unmarshall(context));
                    continue;
                }
                if (context.testExpression("duration", targetDepth)) {
                    reservedInstancesOffering.setDuration(LongStaxUnmarshaller.getInstance().unmarshall(context));
                    continue;
                }
                if (context.testExpression("usagePrice", targetDepth)) {
                    reservedInstancesOffering.setUsagePrice(FloatStaxUnmarshaller.getInstance().unmarshall(context));
                    continue;
                }
                if (context.testExpression("fixedPrice", targetDepth)) {
                    reservedInstancesOffering.setFixedPrice(FloatStaxUnmarshaller.getInstance().unmarshall(context));
                    continue;
                }
                if (context.testExpression("productDescription", targetDepth)) {
                    reservedInstancesOffering.setProductDescription(StringStaxUnmarshaller.getInstance().unmarshall(context));
                    continue;
                }
                if (context.testExpression("instanceTenancy", targetDepth)) {
                    reservedInstancesOffering.setInstanceTenancy(StringStaxUnmarshaller.getInstance().unmarshall(context));
                    continue;
                }
                if (context.testExpression("currencyCode", targetDepth)) {
                    reservedInstancesOffering.setCurrencyCode(StringStaxUnmarshaller.getInstance().unmarshall(context));
                    continue;
                }
            } else if (xmlEvent.isEndElement()) {
                if (context.getCurrentDepth() < originalDepth) {
                    return reservedInstancesOffering;
                }
            }
        }
    }

    private static ReservedInstancesOfferingStaxUnmarshaller instance;
    public static ReservedInstancesOfferingStaxUnmarshaller getInstance() {
        if (instance == null) instance = new ReservedInstancesOfferingStaxUnmarshaller();
        return instance;
    }
}
    