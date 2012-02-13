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
 * Container for the parameters to the {@link com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancing#createLoadBalancer(CreateLoadBalancerRequest) CreateLoadBalancer operation}.
 * <p>
 * Creates a new LoadBalancer.
 * </p>
 * <p>
 * After the call has completed successfully, a new LoadBalancer is
 * created; however, it will not be usable until at least one instance
 * has been registered. When the LoadBalancer creation is completed, the
 * client can check whether or not it is usable by using the
 * DescribeInstanceHealth API. The LoadBalancer is usable as soon as any
 * registered instance is <i>InService</i> .
 * 
 * </p>
 * <p>
 * <b>NOTE:</b> Currently, the client's quota of LoadBalancers is limited
 * to ten per Region.
 * </p>
 * <p>
 * <b>NOTE:</b> LoadBalancer DNS names vary depending on the Region
 * they're created in. For LoadBalancers created in the United States,
 * the DNS name ends with: us-east-1.elb.amazonaws.com (for the US
 * Standard Region) us-west-1.elb.amazonaws.com (for the Northern
 * California Region) For LoadBalancers created in the EU (Ireland)
 * Region, the DNS name ends with: eu-west-1.elb.amazonaws.com
 * </p>
 *
 * @see com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancing#createLoadBalancer(CreateLoadBalancerRequest)
 */
public class CreateLoadBalancerRequest extends AmazonWebServiceRequest {

    /**
     * The name associated with the LoadBalancer. The name must be unique
     * within your set of LoadBalancers.
     */
    private String loadBalancerName;

    /**
     * A list of the following tuples: LoadBalancerPort, InstancePort, and
     * Protocol.
     */
    private java.util.List<Listener> listeners;

    /**
     * A list of Availability Zones. <p> At least one Availability Zone must
     * be specified. Specified Availability Zones must be in the same EC2
     * Region as the LoadBalancer. Traffic will be equally distributed across
     * all zones. <p> This list can be modified after the creation of the
     * LoadBalancer.
     */
    private java.util.List<String> availabilityZones;

    /**
     * Default constructor for a new CreateLoadBalancerRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public CreateLoadBalancerRequest() {}
    
    /**
     * Constructs a new CreateLoadBalancerRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param loadBalancerName The name associated with the LoadBalancer. The
     * name must be unique within your set of LoadBalancers.
     */
    public CreateLoadBalancerRequest(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
    }
    
    /**
     * Constructs a new CreateLoadBalancerRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param loadBalancerName The name associated with the LoadBalancer. The
     * name must be unique within your set of LoadBalancers.
     * @param listeners A list of the following tuples: LoadBalancerPort,
     * InstancePort, and Protocol.
     * @param availabilityZones A list of Availability Zones. <p> At least
     * one Availability Zone must be specified. Specified Availability Zones
     * must be in the same EC2 Region as the LoadBalancer. Traffic will be
     * equally distributed across all zones. <p> This list can be modified
     * after the creation of the LoadBalancer.
     */
    public CreateLoadBalancerRequest(String loadBalancerName, java.util.List<Listener> listeners, java.util.List<String> availabilityZones) {
        this.loadBalancerName = loadBalancerName;
        this.listeners = listeners;
        this.availabilityZones = availabilityZones;
    }
    
    /**
     * The name associated with the LoadBalancer. The name must be unique
     * within your set of LoadBalancers.
     *
     * @return The name associated with the LoadBalancer. The name must be unique
     *         within your set of LoadBalancers.
     */
    public String getLoadBalancerName() {
        return loadBalancerName;
    }
    
    /**
     * The name associated with the LoadBalancer. The name must be unique
     * within your set of LoadBalancers.
     *
     * @param loadBalancerName The name associated with the LoadBalancer. The name must be unique
     *         within your set of LoadBalancers.
     */
    public void setLoadBalancerName(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
    }
    
    /**
     * The name associated with the LoadBalancer. The name must be unique
     * within your set of LoadBalancers.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param loadBalancerName The name associated with the LoadBalancer. The name must be unique
     *         within your set of LoadBalancers.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateLoadBalancerRequest withLoadBalancerName(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
        return this;
    }
    
    
    /**
     * A list of the following tuples: LoadBalancerPort, InstancePort, and
     * Protocol.
     *
     * @return A list of the following tuples: LoadBalancerPort, InstancePort, and
     *         Protocol.
     */
    public java.util.List<Listener> getListeners() {
        
        if (listeners == null) {
            listeners = new java.util.ArrayList<Listener>();
        }
        return listeners;
    }
    
    /**
     * A list of the following tuples: LoadBalancerPort, InstancePort, and
     * Protocol.
     *
     * @param listeners A list of the following tuples: LoadBalancerPort, InstancePort, and
     *         Protocol.
     */
    public void setListeners(java.util.Collection<Listener> listeners) {
        java.util.List<Listener> listenersCopy = new java.util.ArrayList<Listener>();
        if (listeners != null) {
            listenersCopy.addAll(listeners);
        }
        this.listeners = listenersCopy;
    }
    
    /**
     * A list of the following tuples: LoadBalancerPort, InstancePort, and
     * Protocol.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param listeners A list of the following tuples: LoadBalancerPort, InstancePort, and
     *         Protocol.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateLoadBalancerRequest withListeners(Listener... listeners) {
        if (getListeners() == null) setListeners(new java.util.ArrayList<Listener>());
        for (Listener value : listeners) {
            getListeners().add(value);
        }
        return this;
    }
    
    /**
     * A list of the following tuples: LoadBalancerPort, InstancePort, and
     * Protocol.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param listeners A list of the following tuples: LoadBalancerPort, InstancePort, and
     *         Protocol.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateLoadBalancerRequest withListeners(java.util.Collection<Listener> listeners) {
        java.util.List<Listener> listenersCopy = new java.util.ArrayList<Listener>();
        if (listeners != null) {
            listenersCopy.addAll(listeners);
        }
        this.listeners = listenersCopy;

        return this;
    }
    
    /**
     * A list of Availability Zones. <p> At least one Availability Zone must
     * be specified. Specified Availability Zones must be in the same EC2
     * Region as the LoadBalancer. Traffic will be equally distributed across
     * all zones. <p> This list can be modified after the creation of the
     * LoadBalancer.
     *
     * @return A list of Availability Zones. <p> At least one Availability Zone must
     *         be specified. Specified Availability Zones must be in the same EC2
     *         Region as the LoadBalancer. Traffic will be equally distributed across
     *         all zones. <p> This list can be modified after the creation of the
     *         LoadBalancer.
     */
    public java.util.List<String> getAvailabilityZones() {
        
        if (availabilityZones == null) {
            availabilityZones = new java.util.ArrayList<String>();
        }
        return availabilityZones;
    }
    
    /**
     * A list of Availability Zones. <p> At least one Availability Zone must
     * be specified. Specified Availability Zones must be in the same EC2
     * Region as the LoadBalancer. Traffic will be equally distributed across
     * all zones. <p> This list can be modified after the creation of the
     * LoadBalancer.
     *
     * @param availabilityZones A list of Availability Zones. <p> At least one Availability Zone must
     *         be specified. Specified Availability Zones must be in the same EC2
     *         Region as the LoadBalancer. Traffic will be equally distributed across
     *         all zones. <p> This list can be modified after the creation of the
     *         LoadBalancer.
     */
    public void setAvailabilityZones(java.util.Collection<String> availabilityZones) {
        java.util.List<String> availabilityZonesCopy = new java.util.ArrayList<String>();
        if (availabilityZones != null) {
            availabilityZonesCopy.addAll(availabilityZones);
        }
        this.availabilityZones = availabilityZonesCopy;
    }
    
    /**
     * A list of Availability Zones. <p> At least one Availability Zone must
     * be specified. Specified Availability Zones must be in the same EC2
     * Region as the LoadBalancer. Traffic will be equally distributed across
     * all zones. <p> This list can be modified after the creation of the
     * LoadBalancer.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param availabilityZones A list of Availability Zones. <p> At least one Availability Zone must
     *         be specified. Specified Availability Zones must be in the same EC2
     *         Region as the LoadBalancer. Traffic will be equally distributed across
     *         all zones. <p> This list can be modified after the creation of the
     *         LoadBalancer.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateLoadBalancerRequest withAvailabilityZones(String... availabilityZones) {
        if (getAvailabilityZones() == null) setAvailabilityZones(new java.util.ArrayList<String>());
        for (String value : availabilityZones) {
            getAvailabilityZones().add(value);
        }
        return this;
    }
    
    /**
     * A list of Availability Zones. <p> At least one Availability Zone must
     * be specified. Specified Availability Zones must be in the same EC2
     * Region as the LoadBalancer. Traffic will be equally distributed across
     * all zones. <p> This list can be modified after the creation of the
     * LoadBalancer.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param availabilityZones A list of Availability Zones. <p> At least one Availability Zone must
     *         be specified. Specified Availability Zones must be in the same EC2
     *         Region as the LoadBalancer. Traffic will be equally distributed across
     *         all zones. <p> This list can be modified after the creation of the
     *         LoadBalancer.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateLoadBalancerRequest withAvailabilityZones(java.util.Collection<String> availabilityZones) {
        java.util.List<String> availabilityZonesCopy = new java.util.ArrayList<String>();
        if (availabilityZones != null) {
            availabilityZonesCopy.addAll(availabilityZones);
        }
        this.availabilityZones = availabilityZonesCopy;

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
        sb.append("Listeners: " + listeners + ", ");
        sb.append("AvailabilityZones: " + availabilityZones + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    