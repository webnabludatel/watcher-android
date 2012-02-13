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
package com.amazonaws.services.autoscaling.model;
import com.amazonaws.AmazonWebServiceRequest;

/**
 * Container for the parameters to the {@link com.amazonaws.services.autoscaling.AmazonAutoScaling#describeAutoScalingInstances(DescribeAutoScalingInstancesRequest) DescribeAutoScalingInstances operation}.
 * <p>
 * Returns a description of each Auto Scaling instance in the
 * InstanceIds list. If a list is not provided, the service returns the
 * full details of all instances up to a maximum of fifty. By default,
 * the service returns a list of 20 items.
 * </p>
 * <p>
 * This action supports pagination by returning a token if there are
 * more pages to retrieve. To get the next page, call this action again
 * with the returned token as the NextToken parameter.
 * </p>
 *
 * @see com.amazonaws.services.autoscaling.AmazonAutoScaling#describeAutoScalingInstances(DescribeAutoScalingInstancesRequest)
 */
public class DescribeAutoScalingInstancesRequest extends AmazonWebServiceRequest {

    /**
     * The list of Auto Scaling instances to describe. If this list is
     * omitted, all auto scaling instances are described. The list of
     * requested instances cannot contain more than 50 items. If unknown
     * instances are requested, they are ignored with no error.
     */
    private java.util.List<String> instanceIds;

    /**
     * The maximum number of Auto Scaling instances to be described with each
     * call.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - 50<br/>
     */
    private Integer maxRecords;

    /**
     * The token returned by a previous call to indicate that there is more
     * data available.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     */
    private String nextToken;

    /**
     * The list of Auto Scaling instances to describe. If this list is
     * omitted, all auto scaling instances are described. The list of
     * requested instances cannot contain more than 50 items. If unknown
     * instances are requested, they are ignored with no error.
     *
     * @return The list of Auto Scaling instances to describe. If this list is
     *         omitted, all auto scaling instances are described. The list of
     *         requested instances cannot contain more than 50 items. If unknown
     *         instances are requested, they are ignored with no error.
     */
    public java.util.List<String> getInstanceIds() {
        
        if (instanceIds == null) {
            instanceIds = new java.util.ArrayList<String>();
        }
        return instanceIds;
    }
    
    /**
     * The list of Auto Scaling instances to describe. If this list is
     * omitted, all auto scaling instances are described. The list of
     * requested instances cannot contain more than 50 items. If unknown
     * instances are requested, they are ignored with no error.
     *
     * @param instanceIds The list of Auto Scaling instances to describe. If this list is
     *         omitted, all auto scaling instances are described. The list of
     *         requested instances cannot contain more than 50 items. If unknown
     *         instances are requested, they are ignored with no error.
     */
    public void setInstanceIds(java.util.Collection<String> instanceIds) {
        java.util.List<String> instanceIdsCopy = new java.util.ArrayList<String>();
        if (instanceIds != null) {
            instanceIdsCopy.addAll(instanceIds);
        }
        this.instanceIds = instanceIdsCopy;
    }
    
    /**
     * The list of Auto Scaling instances to describe. If this list is
     * omitted, all auto scaling instances are described. The list of
     * requested instances cannot contain more than 50 items. If unknown
     * instances are requested, they are ignored with no error.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param instanceIds The list of Auto Scaling instances to describe. If this list is
     *         omitted, all auto scaling instances are described. The list of
     *         requested instances cannot contain more than 50 items. If unknown
     *         instances are requested, they are ignored with no error.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAutoScalingInstancesRequest withInstanceIds(String... instanceIds) {
        if (getInstanceIds() == null) setInstanceIds(new java.util.ArrayList<String>());
        for (String value : instanceIds) {
            getInstanceIds().add(value);
        }
        return this;
    }
    
    /**
     * The list of Auto Scaling instances to describe. If this list is
     * omitted, all auto scaling instances are described. The list of
     * requested instances cannot contain more than 50 items. If unknown
     * instances are requested, they are ignored with no error.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param instanceIds The list of Auto Scaling instances to describe. If this list is
     *         omitted, all auto scaling instances are described. The list of
     *         requested instances cannot contain more than 50 items. If unknown
     *         instances are requested, they are ignored with no error.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAutoScalingInstancesRequest withInstanceIds(java.util.Collection<String> instanceIds) {
        java.util.List<String> instanceIdsCopy = new java.util.ArrayList<String>();
        if (instanceIds != null) {
            instanceIdsCopy.addAll(instanceIds);
        }
        this.instanceIds = instanceIdsCopy;

        return this;
    }
    
    /**
     * The maximum number of Auto Scaling instances to be described with each
     * call.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - 50<br/>
     *
     * @return The maximum number of Auto Scaling instances to be described with each
     *         call.
     */
    public Integer getMaxRecords() {
        return maxRecords;
    }
    
    /**
     * The maximum number of Auto Scaling instances to be described with each
     * call.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - 50<br/>
     *
     * @param maxRecords The maximum number of Auto Scaling instances to be described with each
     *         call.
     */
    public void setMaxRecords(Integer maxRecords) {
        this.maxRecords = maxRecords;
    }
    
    /**
     * The maximum number of Auto Scaling instances to be described with each
     * call.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - 50<br/>
     *
     * @param maxRecords The maximum number of Auto Scaling instances to be described with each
     *         call.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAutoScalingInstancesRequest withMaxRecords(Integer maxRecords) {
        this.maxRecords = maxRecords;
        return this;
    }
    
    
    /**
     * The token returned by a previous call to indicate that there is more
     * data available.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     *
     * @return The token returned by a previous call to indicate that there is more
     *         data available.
     */
    public String getNextToken() {
        return nextToken;
    }
    
    /**
     * The token returned by a previous call to indicate that there is more
     * data available.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     *
     * @param nextToken The token returned by a previous call to indicate that there is more
     *         data available.
     */
    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }
    
    /**
     * The token returned by a previous call to indicate that there is more
     * data available.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     *
     * @param nextToken The token returned by a previous call to indicate that there is more
     *         data available.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAutoScalingInstancesRequest withNextToken(String nextToken) {
        this.nextToken = nextToken;
        return this;
    }
    
    
    /**
     * Returns a string representation of this object; useful for testing and
     * debugging.
     *
     * @return A string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("InstanceIds: " + instanceIds + ", ");
        sb.append("MaxRecords: " + maxRecords + ", ");
        sb.append("NextToken: " + nextToken + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    