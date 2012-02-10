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
 * Container for the parameters to the {@link com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancing#deregisterInstancesFromLoadBalancer(DeregisterInstancesFromLoadBalancerRequest) DeregisterInstancesFromLoadBalancer operation}.
 * <p>
 * Deregisters instances from the LoadBalancer. Once the instance is
 * deregistered, it will stop receiving traffic from the LoadBalancer.
 * </p>
 * <p>
 * In order to successfully call this API, the same account credentials
 * as those used to create the LoadBalancer must be provided.
 * </p>
 *
 * @see com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancing#deregisterInstancesFromLoadBalancer(DeregisterInstancesFromLoadBalancerRequest)
 */
public class DeregisterInstancesFromLoadBalancerRequest extends AmazonWebServiceRequest {

    /**
     * The name associated with the LoadBalancer. The name must be unique
     * within the client AWS account.
     */
    private String loadBalancerName;

    /**
     * A list of EC2 instance IDs consisting of all instances to be
     * deregistered.
     */
    private java.util.List<Instance> instances;

    /**
     * Default constructor for a new DeregisterInstancesFromLoadBalancerRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public DeregisterInstancesFromLoadBalancerRequest() {}
    
    /**
     * Constructs a new DeregisterInstancesFromLoadBalancerRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param loadBalancerName The name associated with the LoadBalancer. The
     * name must be unique within the client AWS account.
     * @param instances A list of EC2 instance IDs consisting of all
     * instances to be deregistered.
     */
    public DeregisterInstancesFromLoadBalancerRequest(String loadBalancerName, java.util.List<Instance> instances) {
        this.loadBalancerName = loadBalancerName;
        this.instances = instances;
    }
    
    /**
     * The name associated with the LoadBalancer. The name must be unique
     * within the client AWS account.
     *
     * @return The name associated with the LoadBalancer. The name must be unique
     *         within the client AWS account.
     */
    public String getLoadBalancerName() {
        return loadBalancerName;
    }
    
    /**
     * The name associated with the LoadBalancer. The name must be unique
     * within the client AWS account.
     *
     * @param loadBalancerName The name associated with the LoadBalancer. The name must be unique
     *         within the client AWS account.
     */
    public void setLoadBalancerName(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
    }
    
    /**
     * The name associated with the LoadBalancer. The name must be unique
     * within the client AWS account.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param loadBalancerName The name associated with the LoadBalancer. The name must be unique
     *         within the client AWS account.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DeregisterInstancesFromLoadBalancerRequest withLoadBalancerName(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
        return this;
    }
    
    
    /**
     * A list of EC2 instance IDs consisting of all instances to be
     * deregistered.
     *
     * @return A list of EC2 instance IDs consisting of all instances to be
     *         deregistered.
     */
    public java.util.List<Instance> getInstances() {
        
        if (instances == null) {
            instances = new java.util.ArrayList<Instance>();
        }
        return instances;
    }
    
    /**
     * A list of EC2 instance IDs consisting of all instances to be
     * deregistered.
     *
     * @param instances A list of EC2 instance IDs consisting of all instances to be
     *         deregistered.
     */
    public void setInstances(java.util.Collection<Instance> instances) {
        java.util.List<Instance> instancesCopy = new java.util.ArrayList<Instance>();
        if (instances != null) {
            instancesCopy.addAll(instances);
        }
        this.instances = instancesCopy;
    }
    
    /**
     * A list of EC2 instance IDs consisting of all instances to be
     * deregistered.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param instances A list of EC2 instance IDs consisting of all instances to be
     *         deregistered.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DeregisterInstancesFromLoadBalancerRequest withInstances(Instance... instances) {
        if (getInstances() == null) setInstances(new java.util.ArrayList<Instance>());
        for (Instance value : instances) {
            getInstances().add(value);
        }
        return this;
    }
    
    /**
     * A list of EC2 instance IDs consisting of all instances to be
     * deregistered.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param instances A list of EC2 instance IDs consisting of all instances to be
     *         deregistered.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DeregisterInstancesFromLoadBalancerRequest withInstances(java.util.Collection<Instance> instances) {
        java.util.List<Instance> instancesCopy = new java.util.ArrayList<Instance>();
        if (instances != null) {
            instancesCopy.addAll(instances);
        }
        this.instances = instancesCopy;

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
        sb.append("Instances: " + instances + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    