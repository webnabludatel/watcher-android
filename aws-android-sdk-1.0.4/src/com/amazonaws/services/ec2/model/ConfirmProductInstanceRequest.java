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
 * Container for the parameters to the {@link com.amazonaws.services.ec2.AmazonEC2#confirmProductInstance(ConfirmProductInstanceRequest) ConfirmProductInstance operation}.
 * <p>
 * The ConfirmProductInstance operation returns true if the specified
 * product code is attached to the specified instance. The operation
 * returns false if the product code is not attached to the instance.
 * </p>
 * <p>
 * The ConfirmProductInstance operation can only be executed by the
 * owner of the AMI. This feature is useful when an AMI owner is
 * providing support and wants to verify whether a user's instance is
 * eligible.
 * </p>
 *
 * @see com.amazonaws.services.ec2.AmazonEC2#confirmProductInstance(ConfirmProductInstanceRequest)
 */
public class ConfirmProductInstanceRequest extends AmazonWebServiceRequest {

    /**
     * The product code to confirm.
     */
    private String productCode;

    /**
     * The ID of the instance to confirm.
     */
    private String instanceId;

    /**
     * Default constructor for a new ConfirmProductInstanceRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public ConfirmProductInstanceRequest() {}
    
    /**
     * Constructs a new ConfirmProductInstanceRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param productCode The product code to confirm.
     * @param instanceId The ID of the instance to confirm.
     */
    public ConfirmProductInstanceRequest(String productCode, String instanceId) {
        this.productCode = productCode;
        this.instanceId = instanceId;
    }
    
    /**
     * The product code to confirm.
     *
     * @return The product code to confirm.
     */
    public String getProductCode() {
        return productCode;
    }
    
    /**
     * The product code to confirm.
     *
     * @param productCode The product code to confirm.
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
    
    /**
     * The product code to confirm.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param productCode The product code to confirm.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public ConfirmProductInstanceRequest withProductCode(String productCode) {
        this.productCode = productCode;
        return this;
    }
    
    
    /**
     * The ID of the instance to confirm.
     *
     * @return The ID of the instance to confirm.
     */
    public String getInstanceId() {
        return instanceId;
    }
    
    /**
     * The ID of the instance to confirm.
     *
     * @param instanceId The ID of the instance to confirm.
     */
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
    
    /**
     * The ID of the instance to confirm.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param instanceId The ID of the instance to confirm.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public ConfirmProductInstanceRequest withInstanceId(String instanceId) {
        this.instanceId = instanceId;
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
        sb.append("ProductCode: " + productCode + ", ");
        sb.append("InstanceId: " + instanceId + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    