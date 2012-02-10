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
 * The result of the GetPasswordData operation.
 * </p>
 */
public class GetPasswordDataResult {

    /**
     * The ID of the instance whose Windows administrator password was
     * requested.
     */
    private String instanceId;

    /**
     * The time the data was last updated.
     */
    private java.util.Date timestamp;

    /**
     * The Windows administrator password of the specified instance.
     */
    private String passwordData;

    /**
     * The ID of the instance whose Windows administrator password was
     * requested.
     *
     * @return The ID of the instance whose Windows administrator password was
     *         requested.
     */
    public String getInstanceId() {
        return instanceId;
    }
    
    /**
     * The ID of the instance whose Windows administrator password was
     * requested.
     *
     * @param instanceId The ID of the instance whose Windows administrator password was
     *         requested.
     */
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
    
    /**
     * The ID of the instance whose Windows administrator password was
     * requested.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param instanceId The ID of the instance whose Windows administrator password was
     *         requested.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public GetPasswordDataResult withInstanceId(String instanceId) {
        this.instanceId = instanceId;
        return this;
    }
    
    
    /**
     * The time the data was last updated.
     *
     * @return The time the data was last updated.
     */
    public java.util.Date getTimestamp() {
        return timestamp;
    }
    
    /**
     * The time the data was last updated.
     *
     * @param timestamp The time the data was last updated.
     */
    public void setTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
    }
    
    /**
     * The time the data was last updated.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param timestamp The time the data was last updated.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public GetPasswordDataResult withTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }
    
    
    /**
     * The Windows administrator password of the specified instance.
     *
     * @return The Windows administrator password of the specified instance.
     */
    public String getPasswordData() {
        return passwordData;
    }
    
    /**
     * The Windows administrator password of the specified instance.
     *
     * @param passwordData The Windows administrator password of the specified instance.
     */
    public void setPasswordData(String passwordData) {
        this.passwordData = passwordData;
    }
    
    /**
     * The Windows administrator password of the specified instance.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param passwordData The Windows administrator password of the specified instance.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public GetPasswordDataResult withPasswordData(String passwordData) {
        this.passwordData = passwordData;
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
        sb.append("InstanceId: " + instanceId + ", ");
        sb.append("Timestamp: " + timestamp + ", ");
        sb.append("PasswordData: " + passwordData + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    