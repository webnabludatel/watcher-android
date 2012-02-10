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
 * Container for the parameters to the {@link com.amazonaws.services.ec2.AmazonEC2#describeVolumes(DescribeVolumesRequest) DescribeVolumes operation}.
 * <p>
 * Describes the status of the indicated volume or, in lieu of any
 * specified, all volumes belonging to the caller. Volumes that have been
 * deleted are not described.
 * </p>
 *
 * @see com.amazonaws.services.ec2.AmazonEC2#describeVolumes(DescribeVolumesRequest)
 */
public class DescribeVolumesRequest extends AmazonWebServiceRequest {

    /**
     * The optional list of EBS volumes to describe.
     */
    private java.util.List<String> volumeIds;

    /**
     * A list of filters used to match properties for Volumes. For a complete
     * reference to the available filter keys for this operation, see the <a
     * "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/">Amazon
     * EC2 API reference</a>.
     */
    private java.util.List<Filter> filters;

    /**
     * Default constructor for a new DescribeVolumesRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public DescribeVolumesRequest() {}
    
    /**
     * Constructs a new DescribeVolumesRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param volumeIds The optional list of EBS volumes to describe.
     */
    public DescribeVolumesRequest(java.util.List<String> volumeIds) {
        this.volumeIds = volumeIds;
    }
    
    /**
     * The optional list of EBS volumes to describe.
     *
     * @return The optional list of EBS volumes to describe.
     */
    public java.util.List<String> getVolumeIds() {
        
        if (volumeIds == null) {
            volumeIds = new java.util.ArrayList<String>();
        }
        return volumeIds;
    }
    
    /**
     * The optional list of EBS volumes to describe.
     *
     * @param volumeIds The optional list of EBS volumes to describe.
     */
    public void setVolumeIds(java.util.Collection<String> volumeIds) {
        java.util.List<String> volumeIdsCopy = new java.util.ArrayList<String>();
        if (volumeIds != null) {
            volumeIdsCopy.addAll(volumeIds);
        }
        this.volumeIds = volumeIdsCopy;
    }
    
    /**
     * The optional list of EBS volumes to describe.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param volumeIds The optional list of EBS volumes to describe.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeVolumesRequest withVolumeIds(String... volumeIds) {
        if (getVolumeIds() == null) setVolumeIds(new java.util.ArrayList<String>());
        for (String value : volumeIds) {
            getVolumeIds().add(value);
        }
        return this;
    }
    
    /**
     * The optional list of EBS volumes to describe.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param volumeIds The optional list of EBS volumes to describe.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeVolumesRequest withVolumeIds(java.util.Collection<String> volumeIds) {
        java.util.List<String> volumeIdsCopy = new java.util.ArrayList<String>();
        if (volumeIds != null) {
            volumeIdsCopy.addAll(volumeIds);
        }
        this.volumeIds = volumeIdsCopy;

        return this;
    }
    
    /**
     * A list of filters used to match properties for Volumes. For a complete
     * reference to the available filter keys for this operation, see the <a
     * "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/">Amazon
     * EC2 API reference</a>.
     *
     * @return A list of filters used to match properties for Volumes. For a complete
     *         reference to the available filter keys for this operation, see the <a
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
     * A list of filters used to match properties for Volumes. For a complete
     * reference to the available filter keys for this operation, see the <a
     * "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/">Amazon
     * EC2 API reference</a>.
     *
     * @param filters A list of filters used to match properties for Volumes. For a complete
     *         reference to the available filter keys for this operation, see the <a
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
     * A list of filters used to match properties for Volumes. For a complete
     * reference to the available filter keys for this operation, see the <a
     * "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/">Amazon
     * EC2 API reference</a>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param filters A list of filters used to match properties for Volumes. For a complete
     *         reference to the available filter keys for this operation, see the <a
     *         "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/">Amazon
     *         EC2 API reference</a>.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeVolumesRequest withFilters(Filter... filters) {
        if (getFilters() == null) setFilters(new java.util.ArrayList<Filter>());
        for (Filter value : filters) {
            getFilters().add(value);
        }
        return this;
    }
    
    /**
     * A list of filters used to match properties for Volumes. For a complete
     * reference to the available filter keys for this operation, see the <a
     * "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/">Amazon
     * EC2 API reference</a>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param filters A list of filters used to match properties for Volumes. For a complete
     *         reference to the available filter keys for this operation, see the <a
     *         "http://docs.amazonwebservices.com/AWSEC2/latest/APIReference/">Amazon
     *         EC2 API reference</a>.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeVolumesRequest withFilters(java.util.Collection<Filter> filters) {
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
        sb.append("VolumeIds: " + volumeIds + ", ");
        sb.append("Filters: " + filters + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    