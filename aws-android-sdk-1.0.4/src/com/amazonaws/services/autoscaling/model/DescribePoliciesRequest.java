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
 * Container for the parameters to the {@link com.amazonaws.services.autoscaling.AmazonAutoScaling#describePolicies(DescribePoliciesRequest) DescribePolicies operation}.
 * <p>
 * Returns descriptions of what each policy does. This action supports
 * pagination. If the response includes a token, there are more records
 * available. To get the additional records, repeat the request with the
 * response token as the NextToken parameter.
 * </p>
 *
 * @see com.amazonaws.services.autoscaling.AmazonAutoScaling#describePolicies(DescribePoliciesRequest)
 */
public class DescribePoliciesRequest extends AmazonWebServiceRequest {

    /**
     * The name of the Auto Scaling group.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1600<br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     */
    private String autoScalingGroupName;

    /**
     * A list of policy names or policy ARNs to be described. If this list is
     * omitted, all policy names are described. If an auto scaling group name
     * is provided, the results are limited to that group.The list of
     * requested policy names cannot contain more than 50 items. If unknown
     * policy names are requested, they are ignored with no error.
     */
    private java.util.List<String> policyNames;

    /**
     * A string that is used to mark the start of the next batch of returned
     * results for pagination.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     */
    private String nextToken;

    /**
     * The maximum number of policies that will be described with each call.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - 50<br/>
     */
    private Integer maxRecords;

    /**
     * The name of the Auto Scaling group.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1600<br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     *
     * @return The name of the Auto Scaling group.
     */
    public String getAutoScalingGroupName() {
        return autoScalingGroupName;
    }
    
    /**
     * The name of the Auto Scaling group.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1600<br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     *
     * @param autoScalingGroupName The name of the Auto Scaling group.
     */
    public void setAutoScalingGroupName(String autoScalingGroupName) {
        this.autoScalingGroupName = autoScalingGroupName;
    }
    
    /**
     * The name of the Auto Scaling group.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1600<br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     *
     * @param autoScalingGroupName The name of the Auto Scaling group.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribePoliciesRequest withAutoScalingGroupName(String autoScalingGroupName) {
        this.autoScalingGroupName = autoScalingGroupName;
        return this;
    }
    
    
    /**
     * A list of policy names or policy ARNs to be described. If this list is
     * omitted, all policy names are described. If an auto scaling group name
     * is provided, the results are limited to that group.The list of
     * requested policy names cannot contain more than 50 items. If unknown
     * policy names are requested, they are ignored with no error.
     *
     * @return A list of policy names or policy ARNs to be described. If this list is
     *         omitted, all policy names are described. If an auto scaling group name
     *         is provided, the results are limited to that group.The list of
     *         requested policy names cannot contain more than 50 items. If unknown
     *         policy names are requested, they are ignored with no error.
     */
    public java.util.List<String> getPolicyNames() {
        
        if (policyNames == null) {
            policyNames = new java.util.ArrayList<String>();
        }
        return policyNames;
    }
    
    /**
     * A list of policy names or policy ARNs to be described. If this list is
     * omitted, all policy names are described. If an auto scaling group name
     * is provided, the results are limited to that group.The list of
     * requested policy names cannot contain more than 50 items. If unknown
     * policy names are requested, they are ignored with no error.
     *
     * @param policyNames A list of policy names or policy ARNs to be described. If this list is
     *         omitted, all policy names are described. If an auto scaling group name
     *         is provided, the results are limited to that group.The list of
     *         requested policy names cannot contain more than 50 items. If unknown
     *         policy names are requested, they are ignored with no error.
     */
    public void setPolicyNames(java.util.Collection<String> policyNames) {
        java.util.List<String> policyNamesCopy = new java.util.ArrayList<String>();
        if (policyNames != null) {
            policyNamesCopy.addAll(policyNames);
        }
        this.policyNames = policyNamesCopy;
    }
    
    /**
     * A list of policy names or policy ARNs to be described. If this list is
     * omitted, all policy names are described. If an auto scaling group name
     * is provided, the results are limited to that group.The list of
     * requested policy names cannot contain more than 50 items. If unknown
     * policy names are requested, they are ignored with no error.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param policyNames A list of policy names or policy ARNs to be described. If this list is
     *         omitted, all policy names are described. If an auto scaling group name
     *         is provided, the results are limited to that group.The list of
     *         requested policy names cannot contain more than 50 items. If unknown
     *         policy names are requested, they are ignored with no error.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribePoliciesRequest withPolicyNames(String... policyNames) {
        if (getPolicyNames() == null) setPolicyNames(new java.util.ArrayList<String>());
        for (String value : policyNames) {
            getPolicyNames().add(value);
        }
        return this;
    }
    
    /**
     * A list of policy names or policy ARNs to be described. If this list is
     * omitted, all policy names are described. If an auto scaling group name
     * is provided, the results are limited to that group.The list of
     * requested policy names cannot contain more than 50 items. If unknown
     * policy names are requested, they are ignored with no error.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param policyNames A list of policy names or policy ARNs to be described. If this list is
     *         omitted, all policy names are described. If an auto scaling group name
     *         is provided, the results are limited to that group.The list of
     *         requested policy names cannot contain more than 50 items. If unknown
     *         policy names are requested, they are ignored with no error.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribePoliciesRequest withPolicyNames(java.util.Collection<String> policyNames) {
        java.util.List<String> policyNamesCopy = new java.util.ArrayList<String>();
        if (policyNames != null) {
            policyNamesCopy.addAll(policyNames);
        }
        this.policyNames = policyNamesCopy;

        return this;
    }
    
    /**
     * A string that is used to mark the start of the next batch of returned
     * results for pagination.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     *
     * @return A string that is used to mark the start of the next batch of returned
     *         results for pagination.
     */
    public String getNextToken() {
        return nextToken;
    }
    
    /**
     * A string that is used to mark the start of the next batch of returned
     * results for pagination.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     *
     * @param nextToken A string that is used to mark the start of the next batch of returned
     *         results for pagination.
     */
    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }
    
    /**
     * A string that is used to mark the start of the next batch of returned
     * results for pagination.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     *
     * @param nextToken A string that is used to mark the start of the next batch of returned
     *         results for pagination.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribePoliciesRequest withNextToken(String nextToken) {
        this.nextToken = nextToken;
        return this;
    }
    
    
    /**
     * The maximum number of policies that will be described with each call.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - 50<br/>
     *
     * @return The maximum number of policies that will be described with each call.
     */
    public Integer getMaxRecords() {
        return maxRecords;
    }
    
    /**
     * The maximum number of policies that will be described with each call.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - 50<br/>
     *
     * @param maxRecords The maximum number of policies that will be described with each call.
     */
    public void setMaxRecords(Integer maxRecords) {
        this.maxRecords = maxRecords;
    }
    
    /**
     * The maximum number of policies that will be described with each call.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - 50<br/>
     *
     * @param maxRecords The maximum number of policies that will be described with each call.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribePoliciesRequest withMaxRecords(Integer maxRecords) {
        this.maxRecords = maxRecords;
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
        sb.append("AutoScalingGroupName: " + autoScalingGroupName + ", ");
        sb.append("PolicyNames: " + policyNames + ", ");
        sb.append("NextToken: " + nextToken + ", ");
        sb.append("MaxRecords: " + maxRecords + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    