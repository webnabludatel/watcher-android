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
package com.amazonaws.services.elasticloadbalancing.model;
import com.amazonaws.AmazonWebServiceRequest;

/**
 * Container for the parameters to the {@link com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancing#createLoadBalancerPolicy(CreateLoadBalancerPolicyRequest) CreateLoadBalancerPolicy operation}.
 * <p>
 * Creates a new policy that contains the necessary attributes depending
 * on the policy type. Policies are settings that are saved for your
 * Elastic LoadBalancer and that can be applied to the front-end
 * listener, or the back-end application server, depending on your policy
 * type.
 * </p>
 *
 * @see com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancing#createLoadBalancerPolicy(CreateLoadBalancerPolicyRequest)
 */
public class CreateLoadBalancerPolicyRequest extends AmazonWebServiceRequest {

    /**
     * The name associated with the LoadBalancer for which the policy is
     * being created. This name must be unique within the client AWS account.
     */
    private String loadBalancerName;

    /**
     * The name of the LoadBalancer policy being created. The name must be
     * unique within the set of policies for this LoadBalancer.
     */
    private String policyName;

    /**
     * The name of the base policy type being used to create this policy. To
     * get the list of policy types, use the
     * <a>DescribeLoadBalancerPolicyTypes</a> action.
     */
    private String policyTypeName;

    /**
     * A list of attributes associated with the policy being created.
     */
    private java.util.List<PolicyAttribute> policyAttributes;

    /**
     * The name associated with the LoadBalancer for which the policy is
     * being created. This name must be unique within the client AWS account.
     *
     * @return The name associated with the LoadBalancer for which the policy is
     *         being created. This name must be unique within the client AWS account.
     */
    public String getLoadBalancerName() {
        return loadBalancerName;
    }
    
    /**
     * The name associated with the LoadBalancer for which the policy is
     * being created. This name must be unique within the client AWS account.
     *
     * @param loadBalancerName The name associated with the LoadBalancer for which the policy is
     *         being created. This name must be unique within the client AWS account.
     */
    public void setLoadBalancerName(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
    }
    
    /**
     * The name associated with the LoadBalancer for which the policy is
     * being created. This name must be unique within the client AWS account.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param loadBalancerName The name associated with the LoadBalancer for which the policy is
     *         being created. This name must be unique within the client AWS account.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateLoadBalancerPolicyRequest withLoadBalancerName(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
        return this;
    }
    
    
    /**
     * The name of the LoadBalancer policy being created. The name must be
     * unique within the set of policies for this LoadBalancer.
     *
     * @return The name of the LoadBalancer policy being created. The name must be
     *         unique within the set of policies for this LoadBalancer.
     */
    public String getPolicyName() {
        return policyName;
    }
    
    /**
     * The name of the LoadBalancer policy being created. The name must be
     * unique within the set of policies for this LoadBalancer.
     *
     * @param policyName The name of the LoadBalancer policy being created. The name must be
     *         unique within the set of policies for this LoadBalancer.
     */
    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }
    
    /**
     * The name of the LoadBalancer policy being created. The name must be
     * unique within the set of policies for this LoadBalancer.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param policyName The name of the LoadBalancer policy being created. The name must be
     *         unique within the set of policies for this LoadBalancer.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateLoadBalancerPolicyRequest withPolicyName(String policyName) {
        this.policyName = policyName;
        return this;
    }
    
    
    /**
     * The name of the base policy type being used to create this policy. To
     * get the list of policy types, use the
     * <a>DescribeLoadBalancerPolicyTypes</a> action.
     *
     * @return The name of the base policy type being used to create this policy. To
     *         get the list of policy types, use the
     *         <a>DescribeLoadBalancerPolicyTypes</a> action.
     */
    public String getPolicyTypeName() {
        return policyTypeName;
    }
    
    /**
     * The name of the base policy type being used to create this policy. To
     * get the list of policy types, use the
     * <a>DescribeLoadBalancerPolicyTypes</a> action.
     *
     * @param policyTypeName The name of the base policy type being used to create this policy. To
     *         get the list of policy types, use the
     *         <a>DescribeLoadBalancerPolicyTypes</a> action.
     */
    public void setPolicyTypeName(String policyTypeName) {
        this.policyTypeName = policyTypeName;
    }
    
    /**
     * The name of the base policy type being used to create this policy. To
     * get the list of policy types, use the
     * <a>DescribeLoadBalancerPolicyTypes</a> action.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param policyTypeName The name of the base policy type being used to create this policy. To
     *         get the list of policy types, use the
     *         <a>DescribeLoadBalancerPolicyTypes</a> action.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateLoadBalancerPolicyRequest withPolicyTypeName(String policyTypeName) {
        this.policyTypeName = policyTypeName;
        return this;
    }
    
    
    /**
     * A list of attributes associated with the policy being created.
     *
     * @return A list of attributes associated with the policy being created.
     */
    public java.util.List<PolicyAttribute> getPolicyAttributes() {
        
        if (policyAttributes == null) {
            policyAttributes = new java.util.ArrayList<PolicyAttribute>();
        }
        return policyAttributes;
    }
    
    /**
     * A list of attributes associated with the policy being created.
     *
     * @param policyAttributes A list of attributes associated with the policy being created.
     */
    public void setPolicyAttributes(java.util.Collection<PolicyAttribute> policyAttributes) {
        java.util.List<PolicyAttribute> policyAttributesCopy = new java.util.ArrayList<PolicyAttribute>();
        if (policyAttributes != null) {
            policyAttributesCopy.addAll(policyAttributes);
        }
        this.policyAttributes = policyAttributesCopy;
    }
    
    /**
     * A list of attributes associated with the policy being created.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param policyAttributes A list of attributes associated with the policy being created.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateLoadBalancerPolicyRequest withPolicyAttributes(PolicyAttribute... policyAttributes) {
        if (getPolicyAttributes() == null) setPolicyAttributes(new java.util.ArrayList<PolicyAttribute>());
        for (PolicyAttribute value : policyAttributes) {
            getPolicyAttributes().add(value);
        }
        return this;
    }
    
    /**
     * A list of attributes associated with the policy being created.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param policyAttributes A list of attributes associated with the policy being created.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateLoadBalancerPolicyRequest withPolicyAttributes(java.util.Collection<PolicyAttribute> policyAttributes) {
        java.util.List<PolicyAttribute> policyAttributesCopy = new java.util.ArrayList<PolicyAttribute>();
        if (policyAttributes != null) {
            policyAttributesCopy.addAll(policyAttributes);
        }
        this.policyAttributes = policyAttributesCopy;

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
        sb.append("LoadBalancerName: " + loadBalancerName + ", ");
        sb.append("PolicyName: " + policyName + ", ");
        sb.append("PolicyTypeName: " + policyTypeName + ", ");
        sb.append("PolicyAttributes: " + policyAttributes + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    