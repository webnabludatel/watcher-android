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

/**
 * <p>
 * The result of purchasing a Reserved Instances offering. Contains the
 * new, unique ID of the Reserved Instances purchased for your account.
 * </p>
 */
public class PurchaseReservedInstancesOfferingResult {

    /**
     * The unique ID of the Reserved Instances purchased for your account.
     */
    private String reservedInstancesId;

    /**
     * The unique ID of the Reserved Instances purchased for your account.
     *
     * @return The unique ID of the Reserved Instances purchased for your account.
     */
    public String getReservedInstancesId() {
        return reservedInstancesId;
    }
    
    /**
     * The unique ID of the Reserved Instances purchased for your account.
     *
     * @param reservedInstancesId The unique ID of the Reserved Instances purchased for your account.
     */
    public void setReservedInstancesId(String reservedInstancesId) {
        this.reservedInstancesId = reservedInstancesId;
    }
    
    /**
     * The unique ID of the Reserved Instances purchased for your account.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param reservedInstancesId The unique ID of the Reserved Instances purchased for your account.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public PurchaseReservedInstancesOfferingResult withReservedInstancesId(String reservedInstancesId) {
        this.reservedInstancesId = reservedInstancesId;
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
        sb.append("ReservedInstancesId: " + reservedInstancesId + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    