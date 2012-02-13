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
 * Contains the result of a successful invocation of
 * DescribeLoadBalancers.
 * </p>
 */
public class LoadBalancerDescription {

    /**
     * Specifies the name associated with the LoadBalancer.
     */
    private String loadBalancerName;

    /**
     * Specifies the external DNS name associated with the LoadBalancer.
     */
    private String dNSName;

    /**
     * Provides the name of the Amazon Route 53 hosted zone that is
     * associated with the LoadBalancer. For information on how to associate
     * your load balancer with a hosted zone, go to <a
     * alancing/latest/DeveloperGuide/using-domain-names-with-elb.html">Using
     * Domain Names With Elastic Load Balancing</a> in the <i>Elastic Load
     * Balancing Developer Guide</i>.
     */
    private String canonicalHostedZoneName;

    /**
     * Provides the ID of the Amazon Route 53 hosted zone name that is
     * associated with the LoadBalancer. For information on how to associate
     * or disassociate your load balancer with a hosted zone, go to <a
     * alancing/latest/DeveloperGuide/using-domain-names-with-elb.html">Using
     * Domain Names With Elastic Load Balancing</a> in the <i>Elastic Load
     * Balancing Developer Guide</i>.
     */
    private String canonicalHostedZoneNameID;

    /**
     * LoadBalancerPort, InstancePort, Protocol, InstanceProtocol, and
     * PolicyNames are returned in a list of tuples in the
     * ListenerDescriptions element.
     */
    private java.util.List<ListenerDescription> listenerDescriptions;

    /**
     * Provides a list of policies defined for the LoadBalancer.
     */
    private Policies policies;

    /**
     * Contains a list of back-end server descriptions.
     */
    private java.util.List<BackendServerDescription> backendServerDescriptions;

    /**
     * Specifies a list of Availability Zones.
     */
    private java.util.List<String> availabilityZones;

    /**
     * Provides a list of EC2 instance IDs for the LoadBalancer.
     */
    private java.util.List<Instance> instances;

    /**
     * Specifies information regarding the various health probes conducted on
     * the LoadBalancer.
     */
    private HealthCheck healthCheck;

    /**
     * The security group that you can use as part of your inbound rules for
     * your LoadBalancer's back-end Amazon EC2 application instances. To only
     * allow traffic from LoadBalancers, add a security group rule to your
     * back end instance that specifies this source security group as the
     * inbound source.
     */
    private SourceSecurityGroup sourceSecurityGroup;

    /**
     * Provides the date and time the LoadBalancer was created.
     */
    private java.util.Date createdTime;

    /**
     * Default constructor for a new LoadBalancerDescription object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public LoadBalancerDescription() {}
    
    /**
     * Specifies the name associated with the LoadBalancer.
     *
     * @return Specifies the name associated with the LoadBalancer.
     */
    public String getLoadBalancerName() {
        return loadBalancerName;
    }
    
    /**
     * Specifies the name associated with the LoadBalancer.
     *
     * @param loadBalancerName Specifies the name associated with the LoadBalancer.
     */
    public void setLoadBalancerName(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
    }
    
    /**
     * Specifies the name associated with the LoadBalancer.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param loadBalancerName Specifies the name associated with the LoadBalancer.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public LoadBalancerDescription withLoadBalancerName(String loadBalancerName) {
        this.loadBalancerName = loadBalancerName;
        return this;
    }
    
    
    /**
     * Specifies the external DNS name associated with the LoadBalancer.
     *
     * @return Specifies the external DNS name associated with the LoadBalancer.
     */
    public String getDNSName() {
        return dNSName;
    }
    
    /**
     * Specifies the external DNS name associated with the LoadBalancer.
     *
     * @param dNSName Specifies the external DNS name associated with the LoadBalancer.
     */
    public void setDNSName(String dNSName) {
        this.dNSName = dNSName;
    }
    
    /**
     * Specifies the external DNS name associated with the LoadBalancer.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param dNSName Specifies the external DNS name associated with the LoadBalancer.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public LoadBalancerDescription withDNSName(String dNSName) {
        this.dNSName = dNSName;
        return this;
    }
    
    
    /**
     * Provides the name of the Amazon Route 53 hosted zone that is
     * associated with the LoadBalancer. For information on how to associate
     * your load balancer with a hosted zone, go to <a
     * alancing/latest/DeveloperGuide/using-domain-names-with-elb.html">Using
     * Domain Names With Elastic Load Balancing</a> in the <i>Elastic Load
     * Balancing Developer Guide</i>.
     *
     * @return Provides the name of the Amazon Route 53 hosted zone that is
     *         associated with the LoadBalancer. For information on how to associate
     *         your load balancer with a hosted zone, go to <a
     *         alancing/latest/DeveloperGuide/using-domain-names-with-elb.html">Using
     *         Domain Names With Elastic Load Balancing</a> in the <i>Elastic Load
     *         Balancing Developer Guide</i>.
     */
    public String getCanonicalHostedZoneName() {
        return canonicalHostedZoneName;
    }
    
    /**
     * Provides the name of the Amazon Route 53 hosted zone that is
     * associated with the LoadBalancer. For information on how to associate
     * your load balancer with a hosted zone, go to <a
     * alancing/latest/DeveloperGuide/using-domain-names-with-elb.html">Using
     * Domain Names With Elastic Load Balancing</a> in the <i>Elastic Load
     * Balancing Developer Guide</i>.
     *
     * @param canonicalHostedZoneName Provides the name of the Amazon Route 53 hosted zone that is
     *         associated with the LoadBalancer. For information on how to associate
     *         your load balancer with a hosted zone, go to <a
     *         alancing/latest/DeveloperGuide/using-domain-names-with-elb.html">Using
     *         Domain Names With Elastic Load Balancing</a> in the <i>Elastic Load
     *         Balancing Developer Guide</i>.
     */
    public void setCanonicalHostedZoneName(String canonicalHostedZoneName) {
        this.canonicalHostedZoneName = canonicalHostedZoneName;
    }
    
    /**
     * Provides the name of the Amazon Route 53 hosted zone that is
     * associated with the LoadBalancer. For information on how to associate
     * your load balancer with a hosted zone, go to <a
     * alancing/latest/DeveloperGuide/using-domain-names-with-elb.html">Using
     * Domain Names With Elastic Load Balancing</a> in the <i>Elastic Load
     * Balancing Developer Guide</i>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param canonicalHostedZoneName Provides the name of the Amazon Route 53 hosted zone that is
     *         associated with the LoadBalancer. For information on how to associate
     *         your load balancer with a hosted zone, go to <a
     *         alancing/latest/DeveloperGuide/using-domain-names-with-elb.html">Using
     *         Domain Names With Elastic Load Balancing</a> in the <i>Elastic Load
     *         Balancing Developer Guide</i>.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public LoadBalancerDescription withCanonicalHostedZoneName(String canonicalHostedZoneName) {
        this.canonicalHostedZoneName = canonicalHostedZoneName;
        return this;
    }
    
    
    /**
     * Provides the ID of the Amazon Route 53 hosted zone name that is
     * associated with the LoadBalancer. For information on how to associate
     * or disassociate your load balancer with a hosted zone, go to <a
     * alancing/latest/DeveloperGuide/using-domain-names-with-elb.html">Using
     * Domain Names With Elastic Load Balancing</a> in the <i>Elastic Load
     * Balancing Developer Guide</i>.
     *
     * @return Provides the ID of the Amazon Route 53 hosted zone name that is
     *         associated with the LoadBalancer. For information on how to associate
     *         or disassociate your load balancer with a hosted zone, go to <a
     *         alancing/latest/DeveloperGuide/using-domain-names-with-elb.html">Using
     *         Domain Names With Elastic Load Balancing</a> in the <i>Elastic Load
     *         Balancing Developer Guide</i>.
     */
    public String getCanonicalHostedZoneNameID() {
        return canonicalHostedZoneNameID;
    }
    
    /**
     * Provides the ID of the Amazon Route 53 hosted zone name that is
     * associated with the LoadBalancer. For information on how to associate
     * or disassociate your load balancer with a hosted zone, go to <a
     * alancing/latest/DeveloperGuide/using-domain-names-with-elb.html">Using
     * Domain Names With Elastic Load Balancing</a> in the <i>Elastic Load
     * Balancing Developer Guide</i>.
     *
     * @param canonicalHostedZoneNameID Provides the ID of the Amazon Route 53 hosted zone name that is
     *         associated with the LoadBalancer. For information on how to associate
     *         or disassociate your load balancer with a hosted zone, go to <a
     *         alancing/latest/DeveloperGuide/using-domain-names-with-elb.html">Using
     *         Domain Names With Elastic Load Balancing</a> in the <i>Elastic Load
     *         Balancing Developer Guide</i>.
     */
    public void setCanonicalHostedZoneNameID(String canonicalHostedZoneNameID) {
        this.canonicalHostedZoneNameID = canonicalHostedZoneNameID;
    }
    
    /**
     * Provides the ID of the Amazon Route 53 hosted zone name that is
     * associated with the LoadBalancer. For information on how to associate
     * or disassociate your load balancer with a hosted zone, go to <a
     * alancing/latest/DeveloperGuide/using-domain-names-with-elb.html">Using
     * Domain Names With Elastic Load Balancing</a> in the <i>Elastic Load
     * Balancing Developer Guide</i>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param canonicalHostedZoneNameID Provides the ID of the Amazon Route 53 hosted zone name that is
     *         associated with the LoadBalancer. For information on how to associate
     *         or disassociate your load balancer with a hosted zone, go to <a
     *         alancing/latest/DeveloperGuide/using-domain-names-with-elb.html">Using
     *         Domain Names With Elastic Load Balancing</a> in the <i>Elastic Load
     *         Balancing Developer Guide</i>.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public LoadBalancerDescription withCanonicalHostedZoneNameID(String canonicalHostedZoneNameID) {
        this.canonicalHostedZoneNameID = canonicalHostedZoneNameID;
        return this;
    }
    
    
    /**
     * LoadBalancerPort, InstancePort, Protocol, InstanceProtocol, and
     * PolicyNames are returned in a list of tuples in the
     * ListenerDescriptions element.
     *
     * @return LoadBalancerPort, InstancePort, Protocol, InstanceProtocol, and
     *         PolicyNames are returned in a list of tuples in the
     *         ListenerDescriptions element.
     */
    public java.util.List<ListenerDescription> getListenerDescriptions() {
        
        if (listenerDescriptions == null) {
            listenerDescriptions = new java.util.ArrayList<ListenerDescription>();
        }
        return listenerDescriptions;
    }
    
    /**
     * LoadBalancerPort, InstancePort, Protocol, InstanceProtocol, and
     * PolicyNames are returned in a list of tuples in the
     * ListenerDescriptions element.
     *
     * @param listenerDescriptions LoadBalancerPort, InstancePort, Protocol, InstanceProtocol, and
     *         PolicyNames are returned in a list of tuples in the
     *         ListenerDescriptions element.
     */
    public void setListenerDescriptions(java.util.Collection<ListenerDescription> listenerDescriptions) {
        java.util.List<ListenerDescription> listenerDescriptionsCopy = new java.util.ArrayList<ListenerDescription>();
        if (listenerDescriptions != null) {
            listenerDescriptionsCopy.addAll(listenerDescriptions);
        }
        this.listenerDescriptions = listenerDescriptionsCopy;
    }
    
    /**
     * LoadBalancerPort, InstancePort, Protocol, InstanceProtocol, and
     * PolicyNames are returned in a list of tuples in the
     * ListenerDescriptions element.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param listenerDescriptions LoadBalancerPort, InstancePort, Protocol, InstanceProtocol, and
     *         PolicyNames are returned in a list of tuples in the
     *         ListenerDescriptions element.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public LoadBalancerDescription withListenerDescriptions(ListenerDescription... listenerDescriptions) {
        if (getListenerDescriptions() == null) setListenerDescriptions(new java.util.ArrayList<ListenerDescription>());
        for (ListenerDescription value : listenerDescriptions) {
            getListenerDescriptions().add(value);
        }
        return this;
    }
    
    /**
     * LoadBalancerPort, InstancePort, Protocol, InstanceProtocol, and
     * PolicyNames are returned in a list of tuples in the
     * ListenerDescriptions element.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param listenerDescriptions LoadBalancerPort, InstancePort, Protocol, InstanceProtocol, and
     *         PolicyNames are returned in a list of tuples in the
     *         ListenerDescriptions element.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public LoadBalancerDescription withListenerDescriptions(java.util.Collection<ListenerDescription> listenerDescriptions) {
        java.util.List<ListenerDescription> listenerDescriptionsCopy = new java.util.ArrayList<ListenerDescription>();
        if (listenerDescriptions != null) {
            listenerDescriptionsCopy.addAll(listenerDescriptions);
        }
        this.listenerDescriptions = listenerDescriptionsCopy;

        return this;
    }
    
    /**
     * Provides a list of policies defined for the LoadBalancer.
     *
     * @return Provides a list of policies defined for the LoadBalancer.
     */
    public Policies getPolicies() {
        return policies;
    }
    
    /**
     * Provides a list of policies defined for the LoadBalancer.
     *
     * @param policies Provides a list of policies defined for the LoadBalancer.
     */
    public void setPolicies(Policies policies) {
        this.policies = policies;
    }
    
    /**
     * Provides a list of policies defined for the LoadBalancer.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param policies Provides a list of policies defined for the LoadBalancer.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public LoadBalancerDescription withPolicies(Policies policies) {
        this.policies = policies;
        return this;
    }
    
    
    /**
     * Contains a list of back-end server descriptions.
     *
     * @return Contains a list of back-end server descriptions.
     */
    public java.util.List<BackendServerDescription> getBackendServerDescriptions() {
        
        if (backendServerDescriptions == null) {
            backendServerDescriptions = new java.util.ArrayList<BackendServerDescription>();
        }
        return backendServerDescriptions;
    }
    
    /**
     * Contains a list of back-end server descriptions.
     *
     * @param backendServerDescriptions Contains a list of back-end server descriptions.
     */
    public void setBackendServerDescriptions(java.util.Collection<BackendServerDescription> backendServerDescriptions) {
        java.util.List<BackendServerDescription> backendServerDescriptionsCopy = new java.util.ArrayList<BackendServerDescription>();
        if (backendServerDescriptions != null) {
            backendServerDescriptionsCopy.addAll(backendServerDescriptions);
        }
        this.backendServerDescriptions = backendServerDescriptionsCopy;
    }
    
    /**
     * Contains a list of back-end server descriptions.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param backendServerDescriptions Contains a list of back-end server descriptions.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public LoadBalancerDescription withBackendServerDescriptions(BackendServerDescription... backendServerDescriptions) {
        if (getBackendServerDescriptions() == null) setBackendServerDescriptions(new java.util.ArrayList<BackendServerDescription>());
        for (BackendServerDescription value : backendServerDescriptions) {
            getBackendServerDescriptions().add(value);
        }
        return this;
    }
    
    /**
     * Contains a list of back-end server descriptions.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param backendServerDescriptions Contains a list of back-end server descriptions.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public LoadBalancerDescription withBackendServerDescriptions(java.util.Collection<BackendServerDescription> backendServerDescriptions) {
        java.util.List<BackendServerDescription> backendServerDescriptionsCopy = new java.util.ArrayList<BackendServerDescription>();
        if (backendServerDescriptions != null) {
            backendServerDescriptionsCopy.addAll(backendServerDescriptions);
        }
        this.backendServerDescriptions = backendServerDescriptionsCopy;

        return this;
    }
    
    /**
     * Specifies a list of Availability Zones.
     *
     * @return Specifies a list of Availability Zones.
     */
    public java.util.List<String> getAvailabilityZones() {
        
        if (availabilityZones == null) {
            availabilityZones = new java.util.ArrayList<String>();
        }
        return availabilityZones;
    }
    
    /**
     * Specifies a list of Availability Zones.
     *
     * @param availabilityZones Specifies a list of Availability Zones.
     */
    public void setAvailabilityZones(java.util.Collection<String> availabilityZones) {
        java.util.List<String> availabilityZonesCopy = new java.util.ArrayList<String>();
        if (availabilityZones != null) {
            availabilityZonesCopy.addAll(availabilityZones);
        }
        this.availabilityZones = availabilityZonesCopy;
    }
    
    /**
     * Specifies a list of Availability Zones.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param availabilityZones Specifies a list of Availability Zones.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public LoadBalancerDescription withAvailabilityZones(String... availabilityZones) {
        if (getAvailabilityZones() == null) setAvailabilityZones(new java.util.ArrayList<String>());
        for (String value : availabilityZones) {
            getAvailabilityZones().add(value);
        }
        return this;
    }
    
    /**
     * Specifies a list of Availability Zones.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param availabilityZones Specifies a list of Availability Zones.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public LoadBalancerDescription withAvailabilityZones(java.util.Collection<String> availabilityZones) {
        java.util.List<String> availabilityZonesCopy = new java.util.ArrayList<String>();
        if (availabilityZones != null) {
            availabilityZonesCopy.addAll(availabilityZones);
        }
        this.availabilityZones = availabilityZonesCopy;

        return this;
    }
    
    /**
     * Provides a list of EC2 instance IDs for the LoadBalancer.
     *
     * @return Provides a list of EC2 instance IDs for the LoadBalancer.
     */
    public java.util.List<Instance> getInstances() {
        
        if (instances == null) {
            instances = new java.util.ArrayList<Instance>();
        }
        return instances;
    }
    
    /**
     * Provides a list of EC2 instance IDs for the LoadBalancer.
     *
     * @param instances Provides a list of EC2 instance IDs for the LoadBalancer.
     */
    public void setInstances(java.util.Collection<Instance> instances) {
        java.util.List<Instance> instancesCopy = new java.util.ArrayList<Instance>();
        if (instances != null) {
            instancesCopy.addAll(instances);
        }
        this.instances = instancesCopy;
    }
    
    /**
     * Provides a list of EC2 instance IDs for the LoadBalancer.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param instances Provides a list of EC2 instance IDs for the LoadBalancer.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public LoadBalancerDescription withInstances(Instance... instances) {
        if (getInstances() == null) setInstances(new java.util.ArrayList<Instance>());
        for (Instance value : instances) {
            getInstances().add(value);
        }
        return this;
    }
    
    /**
     * Provides a list of EC2 instance IDs for the LoadBalancer.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param instances Provides a list of EC2 instance IDs for the LoadBalancer.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public LoadBalancerDescription withInstances(java.util.Collection<Instance> instances) {
        java.util.List<Instance> instancesCopy = new java.util.ArrayList<Instance>();
        if (instances != null) {
            instancesCopy.addAll(instances);
        }
        this.instances = instancesCopy;

        return this;
    }
    
    /**
     * Specifies information regarding the various health probes conducted on
     * the LoadBalancer.
     *
     * @return Specifies information regarding the various health probes conducted on
     *         the LoadBalancer.
     */
    public HealthCheck getHealthCheck() {
        return healthCheck;
    }
    
    /**
     * Specifies information regarding the various health probes conducted on
     * the LoadBalancer.
     *
     * @param healthCheck Specifies information regarding the various health probes conducted on
     *         the LoadBalancer.
     */
    public void setHealthCheck(HealthCheck healthCheck) {
        this.healthCheck = healthCheck;
    }
    
    /**
     * Specifies information regarding the various health probes conducted on
     * the LoadBalancer.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param healthCheck Specifies information regarding the various health probes conducted on
     *         the LoadBalancer.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public LoadBalancerDescription withHealthCheck(HealthCheck healthCheck) {
        this.healthCheck = healthCheck;
        return this;
    }
    
    
    /**
     * The security group that you can use as part of your inbound rules for
     * your LoadBalancer's back-end Amazon EC2 application instances. To only
     * allow traffic from LoadBalancers, add a security group rule to your
     * back end instance that specifies this source security group as the
     * inbound source.
     *
     * @return The security group that you can use as part of your inbound rules for
     *         your LoadBalancer's back-end Amazon EC2 application instances. To only
     *         allow traffic from LoadBalancers, add a security group rule to your
     *         back end instance that specifies this source security group as the
     *         inbound source.
     */
    public SourceSecurityGroup getSourceSecurityGroup() {
        return sourceSecurityGroup;
    }
    
    /**
     * The security group that you can use as part of your inbound rules for
     * your LoadBalancer's back-end Amazon EC2 application instances. To only
     * allow traffic from LoadBalancers, add a security group rule to your
     * back end instance that specifies this source security group as the
     * inbound source.
     *
     * @param sourceSecurityGroup The security group that you can use as part of your inbound rules for
     *         your LoadBalancer's back-end Amazon EC2 application instances. To only
     *         allow traffic from LoadBalancers, add a security group rule to your
     *         back end instance that specifies this source security group as the
     *         inbound source.
     */
    public void setSourceSecurityGroup(SourceSecurityGroup sourceSecurityGroup) {
        this.sourceSecurityGroup = sourceSecurityGroup;
    }
    
    /**
     * The security group that you can use as part of your inbound rules for
     * your LoadBalancer's back-end Amazon EC2 application instances. To only
     * allow traffic from LoadBalancers, add a security group rule to your
     * back end instance that specifies this source security group as the
     * inbound source.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param sourceSecurityGroup The security group that you can use as part of your inbound rules for
     *         your LoadBalancer's back-end Amazon EC2 application instances. To only
     *         allow traffic from LoadBalancers, add a security group rule to your
     *         back end instance that specifies this source security group as the
     *         inbound source.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public LoadBalancerDescription withSourceSecurityGroup(SourceSecurityGroup sourceSecurityGroup) {
        this.sourceSecurityGroup = sourceSecurityGroup;
        return this;
    }
    
    
    /**
     * Provides the date and time the LoadBalancer was created.
     *
     * @return Provides the date and time the LoadBalancer was created.
     */
    public java.util.Date getCreatedTime() {
        return createdTime;
    }
    
    /**
     * Provides the date and time the LoadBalancer was created.
     *
     * @param createdTime Provides the date and time the LoadBalancer was created.
     */
    public void setCreatedTime(java.util.Date createdTime) {
        this.createdTime = createdTime;
    }
    
    /**
     * Provides the date and time the LoadBalancer was created.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param createdTime Provides the date and time the LoadBalancer was created.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public LoadBalancerDescription withCreatedTime(java.util.Date createdTime) {
        this.createdTime = createdTime;
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
        sb.append("DNSName: " + dNSName + ", ");
        sb.append("CanonicalHostedZoneName: " + canonicalHostedZoneName + ", ");
        sb.append("CanonicalHostedZoneNameID: " + canonicalHostedZoneNameID + ", ");
        sb.append("ListenerDescriptions: " + listenerDescriptions + ", ");
        sb.append("Policies: " + policies + ", ");
        sb.append("BackendServerDescriptions: " + backendServerDescriptions + ", ");
        sb.append("AvailabilityZones: " + availabilityZones + ", ");
        sb.append("Instances: " + instances + ", ");
        sb.append("HealthCheck: " + healthCheck + ", ");
        sb.append("SourceSecurityGroup: " + sourceSecurityGroup + ", ");
        sb.append("CreatedTime: " + createdTime + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    