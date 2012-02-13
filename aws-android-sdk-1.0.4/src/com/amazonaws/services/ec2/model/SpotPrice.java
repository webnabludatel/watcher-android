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
 * Spot Price
 */
public class SpotPrice {

    private String instanceType;

    private String productDescription;

    private String spotPrice;

    private java.util.Date timestamp;

    private String availabilityZone;

    /**
     * Returns the value of the InstanceType property for this object.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>t1.micro, m1.small, m1.large, m1.xlarge, m2.xlarge, m2.2xlarge, m2.4xlarge, c1.medium, c1.xlarge, cc1.4xlarge, cg1.4xlarge
     *
     * @return The value of the InstanceType property for this object.
     *
     * @see InstanceType
     */
    public String getInstanceType() {
        return instanceType;
    }
    
    /**
     * Sets the value of the InstanceType property for this object.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>t1.micro, m1.small, m1.large, m1.xlarge, m2.xlarge, m2.2xlarge, m2.4xlarge, c1.medium, c1.xlarge, cc1.4xlarge, cg1.4xlarge
     *
     * @param instanceType The new value for the InstanceType property for this object.
     *
     * @see InstanceType
     */
    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }
    
    /**
     * Sets the value of the InstanceType property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>t1.micro, m1.small, m1.large, m1.xlarge, m2.xlarge, m2.2xlarge, m2.4xlarge, c1.medium, c1.xlarge, cc1.4xlarge, cg1.4xlarge
     *
     * @param instanceType The new value for the InstanceType property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     *
     * @see InstanceType
     */
    public SpotPrice withInstanceType(String instanceType) {
        this.instanceType = instanceType;
        return this;
    }
    
    
    /**
     * Returns the value of the ProductDescription property for this object.
     *
     * @return The value of the ProductDescription property for this object.
     */
    public String getProductDescription() {
        return productDescription;
    }
    
    /**
     * Sets the value of the ProductDescription property for this object.
     *
     * @param productDescription The new value for the ProductDescription property for this object.
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    
    /**
     * Sets the value of the ProductDescription property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param productDescription The new value for the ProductDescription property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SpotPrice withProductDescription(String productDescription) {
        this.productDescription = productDescription;
        return this;
    }
    
    
    /**
     * Returns the value of the SpotPrice property for this object.
     *
     * @return The value of the SpotPrice property for this object.
     */
    public String getSpotPrice() {
        return spotPrice;
    }
    
    /**
     * Sets the value of the SpotPrice property for this object.
     *
     * @param spotPrice The new value for the SpotPrice property for this object.
     */
    public void setSpotPrice(String spotPrice) {
        this.spotPrice = spotPrice;
    }
    
    /**
     * Sets the value of the SpotPrice property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param spotPrice The new value for the SpotPrice property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SpotPrice withSpotPrice(String spotPrice) {
        this.spotPrice = spotPrice;
        return this;
    }
    
    
    /**
     * Returns the value of the Timestamp property for this object.
     *
     * @return The value of the Timestamp property for this object.
     */
    public java.util.Date getTimestamp() {
        return timestamp;
    }
    
    /**
     * Sets the value of the Timestamp property for this object.
     *
     * @param timestamp The new value for the Timestamp property for this object.
     */
    public void setTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
    }
    
    /**
     * Sets the value of the Timestamp property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param timestamp The new value for the Timestamp property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SpotPrice withTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }
    
    
    /**
     * Returns the value of the AvailabilityZone property for this object.
     *
     * @return The value of the AvailabilityZone property for this object.
     */
    public String getAvailabilityZone() {
        return availabilityZone;
    }
    
    /**
     * Sets the value of the AvailabilityZone property for this object.
     *
     * @param availabilityZone The new value for the AvailabilityZone property for this object.
     */
    public void setAvailabilityZone(String availabilityZone) {
        this.availabilityZone = availabilityZone;
    }
    
    /**
     * Sets the value of the AvailabilityZone property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param availabilityZone The new value for the AvailabilityZone property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SpotPrice withAvailabilityZone(String availabilityZone) {
        this.availabilityZone = availabilityZone;
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
        sb.append("InstanceType: " + instanceType + ", ");
        sb.append("ProductDescription: " + productDescription + ", ");
        sb.append("SpotPrice: " + spotPrice + ", ");
        sb.append("Timestamp: " + timestamp + ", ");
        sb.append("AvailabilityZone: " + availabilityZone + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    