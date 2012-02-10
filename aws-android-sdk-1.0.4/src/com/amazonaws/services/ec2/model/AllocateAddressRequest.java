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
 * Container for the parameters to the {@link com.amazonaws.services.ec2.AmazonEC2#allocateAddress(AllocateAddressRequest) AllocateAddress operation}.
 * <p>
 * The AllocateAddress operation acquires an elastic IP address for use
 * with your account.
 * </p>
 *
 * @see com.amazonaws.services.ec2.AmazonEC2#allocateAddress(AllocateAddressRequest)
 */
public class AllocateAddressRequest extends AmazonWebServiceRequest {

    /**
     * Set to <code>vpc</code> to allocate the address to your VPC. By
     * default, will allocate to EC2.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>vpc, standard
     */
    private String domain;

    /**
     * Set to <code>vpc</code> to allocate the address to your VPC. By
     * default, will allocate to EC2.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>vpc, standard
     *
     * @return Set to <code>vpc</code> to allocate the address to your VPC. By
     *         default, will allocate to EC2.
     *
     * @see DomainType
     */
    public String getDomain() {
        return domain;
    }
    
    /**
     * Set to <code>vpc</code> to allocate the address to your VPC. By
     * default, will allocate to EC2.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>vpc, standard
     *
     * @param domain Set to <code>vpc</code> to allocate the address to your VPC. By
     *         default, will allocate to EC2.
     *
     * @see DomainType
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }
    
    /**
     * Set to <code>vpc</code> to allocate the address to your VPC. By
     * default, will allocate to EC2.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>vpc, standard
     *
     * @param domain Set to <code>vpc</code> to allocate the address to your VPC. By
     *         default, will allocate to EC2.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     *
     * @see DomainType
     */
    public AllocateAddressRequest withDomain(String domain) {
        this.domain = domain;
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
        sb.append("Domain: " + domain + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    