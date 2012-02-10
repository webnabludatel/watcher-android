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
package com.amazonaws.services.ec2.model;
import com.amazonaws.AmazonWebServiceRequest;

/**
 * Container for the parameters to the {@link com.amazonaws.services.ec2.AmazonEC2#describeAvailabilityZones(DescribeAvailabilityZonesRequest) DescribeAvailabilityZones operation}.
 * <p>
 * The DescribeAvailabilityZones operation describes availability zones
 * that are currently available to the account and their states.
 * </p>
 * <p>
 * Availability zones are not the same across accounts. The availability
 * zone <code>us-east-1a</code> for account A is not necessarily the same
 * as <code>us-east-1a</code> for account B. Zone assignments are mapped
 * independently for each account.
 * </p>
 *
 * @see com.amazonaws.services.ec2.AmazonEC2#describeAvailabilityZones(DescribeAvailabilityZonesRequest)
 */
public class DescribeAvailabilityZonesRequest extends AmazonWebServiceRequest {

    /**
     * A list of the availability zone names to describe.
     */
    private java.util.List<String> zoneNames;

    /**
     * A list of filters used to match properties for AvailabilityZones. For
     * a complete reference to the available filter keys for this operation,
     * see the <a
     * "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/">Amazon
     * EC2 API reference</a>.
     */
    private java.util.List<Filter> filters;

    /**
     * A list of the availability zone names to describe.
     *
     * @return A list of the availability zone names to describe.
     */
    public java.util.List<String> getZoneNames() {
        
        if (zoneNames == null) {
            zoneNames = new java.util.ArrayList<String>();
        }
        return zoneNames;
    }
    
    /**
     * A list of the availability zone names to describe.
     *
     * @param zoneNames A list of the availability zone names to describe.
     */
    public void setZoneNames(java.util.Collection<String> zoneNames) {
        java.util.List<String> zoneNamesCopy = new java.util.ArrayList<String>();
        if (zoneNames != null) {
            zoneNamesCopy.addAll(zoneNames);
        }
        this.zoneNames = zoneNamesCopy;
    }
    
    /**
     * A list of the availability zone names to describe.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param zoneNames A list of the availability zone names to describe.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAvailabilityZonesRequest withZoneNames(String... zoneNames) {
        if (getZoneNames() == null) setZoneNames(new java.util.ArrayList<String>());
        for (String value : zoneNames) {
            getZoneNames().add(value);
        }
        return this;
    }
    
    /**
     * A list of the availability zone names to describe.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param zoneNames A list of the availability zone names to describe.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAvailabilityZonesRequest withZoneNames(java.util.Collection<String> zoneNames) {
        java.util.List<String> zoneNamesCopy = new java.util.ArrayList<String>();
        if (zoneNames != null) {
            zoneNamesCopy.addAll(zoneNames);
        }
        this.zoneNames = zoneNamesCopy;

        return this;
    }
    
    /**
     * A list of filters used to match properties for AvailabilityZones. For
     * a complete reference to the available filter keys for this operation,
     * see the <a
     * "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/">Amazon
     * EC2 API reference</a>.
     *
     * @return A list of filters used to match properties for AvailabilityZones. For
     *         a complete reference to the available filter keys for this operation,
     *         see the <a
     *         "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/">Amazon
     *         EC2 API reference</a>.
     */
    public java.util.List<Filter> getFilters() {
        
        if (filters == null) {
            filters = new java.util.ArrayList<Filter>();
        }
        return filters;
    }
    
    /**
     * A list of filters used to match properties for AvailabilityZones. For
     * a complete reference to the available filter keys for this operation,
     * see the <a
     * "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/">Amazon
     * EC2 API reference</a>.
     *
     * @param filters A list of filters used to match properties for AvailabilityZones. For
     *         a complete reference to the available filter keys for this operation,
     *         see the <a
     *         "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/">Amazon
     *         EC2 API reference</a>.
     */
    public void setFilters(java.util.Collection<Filter> filters) {
        java.util.List<Filter> filtersCopy = new java.util.ArrayList<Filter>();
        if (filters != null) {
            filtersCopy.addAll(filters);
        }
        this.filters = filtersCopy;
    }
    
    /**
     * A list of filters used to match properties for AvailabilityZones. For
     * a complete reference to the available filter keys for this operation,
     * see the <a
     * "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/">Amazon
     * EC2 API reference</a>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param filters A list of filters used to match properties for AvailabilityZones. For
     *         a complete reference to the available filter keys for this operation,
     *         see the <a
     *         "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/">Amazon
     *         EC2 API reference</a>.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAvailabilityZonesRequest withFilters(Filter... filters) {
        if (getFilters() == null) setFilters(new java.util.ArrayList<Filter>());
        for (Filter value : filters) {
            getFilters().add(value);
        }
        return this;
    }
    
    /**
     * A list of filters used to match properties for AvailabilityZones. For
     * a complete reference to the available filter keys for this operation,
     * see the <a
     * "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/">Amazon
     * EC2 API reference</a>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param filters A list of filters used to match properties for AvailabilityZones. For
     *         a complete reference to the available filter keys for this operation,
     *         see the <a
     *         "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/">Amazon
     *         EC2 API reference</a>.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAvailabilityZonesRequest withFilters(java.util.Collection<Filter> filters) {
        java.util.List<Filter> filtersCopy = new java.util.ArrayList<Filter>();
        if (filters != null) {
            filtersCopy.addAll(filters);
        }
        this.filters = filtersCopy;

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
        sb.append("ZoneNames: " + zoneNames + ", ");
        sb.append("Filters: " + filters + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    