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
 * Container for the parameters to the {@link com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancing#deleteLoadBalancerPolicy(DeleteLoadBalancerPolicyRequest) DeleteLoadBalancerPolicy operation}.
 * <p>
 * Deletes a policy from the LoadBalancer. The specified policy must not
 * be enabled for any listeners.
 * </p>
 *
 * @see com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancing#deleteLoadBalancerPolicy(DeleteLoadBalancerPolicyRequest)
 */
public class DeleteLoadBalancerPolicyRequest extends AmazonWebServiceRequest {

    /**
     * The mnemonic name associated with the LoadBalancer. The name must be
     * unique within your AWS account.
     */
    private String loadBalancerName;

    /**
     * The mnemonic name for the policy being deleted.
     */
    private String policyName;

    /**
     * Default constructor for a new DeleteLoadBalancerPolicyRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public DeleteLoadBalancerPolicyRequest() {}
    
    /**
     * Constructs a new DeleteLoadBalancerPolicyRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param loadBalancerName The mnemonic name associated with the
     * LoadBalancer. The name must be unique within your AWS account.
     * @param policyName The mnemonic name for the policy being deleted.
     */
    public DeleteLoadBalancerPolicyRequest(String loadBalancerName, String policyName) {
        this.loadBalancerName = loadBalancerName;
        this.policyName = policyName;
    }
    
    /**
     * The mnemonic name associated with the LoadBalancer. The name must be
     * unique within your AWS account.
     *
     * @return The mnemonic name associated with the LoadBalancer. The name must be
     *         unique within your AWS account.
     */
    public String getLoadBalancerName() {
        return loadBalancerName;
    }
    
    /**
     * The mnemonic name associated with the LoadBalancer. The name must be
     * unique within your AWS account.
     *
     * @param loadBalancerName The mnemonic name associated with the LoadBalancer. The name must be
     *         unique within your AWS account.
     */
    public void setLoadBalancerName(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
    }
    
    /**
     * The mnemonic name associated with the LoadBalancer. The name must be
     * unique within your AWS account.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param loadBalancerName The mnemonic name associated with the LoadBalancer. The name must be
     *         unique within your AWS account.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DeleteLoadBalancerPolicyRequest withLoadBalancerName(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
        return this;
    }
    
    
    /**
     * The mnemonic name for the policy being deleted.
     *
     * @return The mnemonic name for the policy being deleted.
     */
    public String getPolicyName() {
        return policyName;
    }
    
    /**
     * The mnemonic name for the policy being deleted.
     *
     * @param policyName The mnemonic name for the policy being deleted.
     */
    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }
    
    /**
     * The mnemonic name for the policy being deleted.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param policyName The mnemonic name for the policy being deleted.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DeleteLoadBalancerPolicyRequest withPolicyName(String policyName) {
        this.policyName = policyName;
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
        sb.append("}");
        return sb.toString();
    }
    
}
    