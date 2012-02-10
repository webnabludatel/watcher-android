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
 * Container for the parameters to the {@link com.amazonaws.services.ec2.AmazonEC2#releaseAddress(ReleaseAddressRequest) ReleaseAddress operation}.
 * <p>
 * The ReleaseAddress operation releases an elastic IP address
 * associated with your account.
 * </p>
 * <p>
 * <b>NOTE:</b> Releasing an IP address automatically disassociates it
 * from any instance with which it is associated. For more information,
 * see DisassociateAddress.
 * </p>
 * <p>
 * <b>IMPORTANT:</b> After releasing an elastic IP address, it is
 * released to the IP address pool and might no longer be available to
 * your account. Make sure to update your DNS records and any servers or
 * devices that communicate with the address. If you run this operation
 * on an elastic IP address that is already released, the address might
 * be assigned to another account which will cause Amazon EC2 to return
 * an error.
 * </p>
 *
 * @see com.amazonaws.services.ec2.AmazonEC2#releaseAddress(ReleaseAddressRequest)
 */
public class ReleaseAddressRequest extends AmazonWebServiceRequest {

    /**
     * The elastic IP address that you are releasing from your account.
     */
    private String publicIp;

    /**
     * The allocation ID that AWS provided when you allocated the address for
     * use with Amazon VPC.
     */
    private String allocationId;

    /**
     * Default constructor for a new ReleaseAddressRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public ReleaseAddressRequest() {}
    
    /**
     * Constructs a new ReleaseAddressRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param publicIp The elastic IP address that you are releasing from
     * your account.
     */
    public ReleaseAddressRequest(String publicIp) {
        this.publicIp = publicIp;
    }
    
    /**
     * The elastic IP address that you are releasing from your account.
     *
     * @return The elastic IP address that you are releasing from your account.
     */
    public String getPublicIp() {
        return publicIp;
    }
    
    /**
     * The elastic IP address that you are releasing from your account.
     *
     * @param publicIp The elastic IP address that you are releasing from your account.
     */
    public void setPublicIp(String publicIp) {
        this.publicIp = publicIp;
    }
    
    /**
     * The elastic IP address that you are releasing from your account.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param publicIp The elastic IP address that you are releasing from your account.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public ReleaseAddressRequest withPublicIp(String publicIp) {
        this.publicIp = publicIp;
        return this;
    }
    
    
    /**
     * The allocation ID that AWS provided when you allocated the address for
     * use with Amazon VPC.
     *
     * @return The allocation ID that AWS provided when you allocated the address for
     *         use with Amazon VPC.
     */
    public String getAllocationId() {
        return allocationId;
    }
    
    /**
     * The allocation ID that AWS provided when you allocated the address for
     * use with Amazon VPC.
     *
     * @param allocationId The allocation ID that AWS provided when you allocated the address for
     *         use with Amazon VPC.
     */
    public void setAllocationId(String allocationId) {
        this.allocationId = allocationId;
    }
    
    /**
     * The allocation ID that AWS provided when you allocated the address for
     * use with Amazon VPC.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param allocationId The allocation ID that AWS provided when you allocated the address for
     *         use with Amazon VPC.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public ReleaseAddressRequest withAllocationId(String allocationId) {
        this.allocationId = allocationId;
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
        sb.append("PublicIp: " + publicIp + ", ");
        sb.append("AllocationId: " + allocationId + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    