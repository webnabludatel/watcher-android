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

/**
 * <p>
 * The <code>PolicyDescription</code> data type.
 * </p>
 */
public class PolicyDescription {

    /**
     * The name mof the policy associated with the LoadBalancer.
     */
    private String policyName;

    /**
     * The name of the policy type associated with the LoadBalancer.
     */
    private String policyTypeName;

    /**
     * A list of policy attribute description structures.
     */
    private java.util.List<PolicyAttributeDescription> policyAttributeDescriptions;

    /**
     * The name mof the policy associated with the LoadBalancer.
     *
     * @return The name mof the policy associated with the LoadBalancer.
     */
    public String getPolicyName() {
        return policyName;
    }
    
    /**
     * The name mof the policy associated with the LoadBalancer.
     *
     * @param policyName The name mof the policy associated with the LoadBalancer.
     */
    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }
    
    /**
     * The name mof the policy associated with the LoadBalancer.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param policyName The name mof the policy associated with the LoadBalancer.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public PolicyDescription withPolicyName(String policyName) {
        this.policyName = policyName;
        return this;
    }
    
    
    /**
     * The name of the policy type associated with the LoadBalancer.
     *
     * @return The name of the policy type associated with the LoadBalancer.
     */
    public String getPolicyTypeName() {
        return policyTypeName;
    }
    
    /**
     * The name of the policy type associated with the LoadBalancer.
     *
     * @param policyTypeName The name of the policy type associated with the LoadBalancer.
     */
    public void setPolicyTypeName(String policyTypeName) {
        this.policyTypeName = policyTypeName;
    }
    
    /**
     * The name of the policy type associated with the LoadBalancer.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param policyTypeName The name of the policy type associated with the LoadBalancer.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public PolicyDescription withPolicyTypeName(String policyTypeName) {
        this.policyTypeName = policyTypeName;
        return this;
    }
    
    
    /**
     * A list of policy attribute description structures.
     *
     * @return A list of policy attribute description structures.
     */
    public java.util.List<PolicyAttributeDescription> getPolicyAttributeDescriptions() {
        
        if (policyAttributeDescriptions == null) {
            policyAttributeDescriptions = new java.util.ArrayList<PolicyAttributeDescription>();
        }
        return policyAttributeDescriptions;
    }
    
    /**
     * A list of policy attribute description structures.
     *
     * @param policyAttributeDescriptions A list of policy attribute description structures.
     */
    public void setPolicyAttributeDescriptions(java.util.Collection<PolicyAttributeDescription> policyAttributeDescriptions) {
        java.util.List<PolicyAttributeDescription> policyAttributeDescriptionsCopy = new java.util.ArrayList<PolicyAttributeDescription>();
        if (policyAttributeDescriptions != null) {
            policyAttributeDescriptionsCopy.addAll(policyAttributeDescriptions);
        }
        this.policyAttributeDescriptions = policyAttributeDescriptionsCopy;
    }
    
    /**
     * A list of policy attribute description structures.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param policyAttributeDescriptions A list of policy attribute description structures.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public PolicyDescription withPolicyAttributeDescriptions(PolicyAttributeDescription... policyAttributeDescriptions) {
        if (getPolicyAttributeDescriptions() == null) setPolicyAttributeDescriptions(new java.util.ArrayList<PolicyAttributeDescription>());
        for (PolicyAttributeDescription value : policyAttributeDescriptions) {
            getPolicyAttributeDescriptions().add(value);
        }
        return this;
    }
    
    /**
     * A list of policy attribute description structures.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param policyAttributeDescriptions A list of policy attribute description structures.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public PolicyDescription withPolicyAttributeDescriptions(java.util.Collection<PolicyAttributeDescription> policyAttributeDescriptions) {
        java.util.List<PolicyAttributeDescription> policyAttributeDescriptionsCopy = new java.util.ArrayList<PolicyAttributeDescription>();
        if (policyAttributeDescriptions != null) {
            policyAttributeDescriptionsCopy.addAll(policyAttributeDescriptions);
        }
        this.policyAttributeDescriptions = policyAttributeDescriptionsCopy;

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
        sb.append("PolicyName: " + policyName + ", ");
        sb.append("PolicyTypeName: " + policyTypeName + ", ");
        sb.append("PolicyAttributeDescriptions: " + policyAttributeDescriptions + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    