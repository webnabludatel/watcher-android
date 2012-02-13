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
 * The Instance data type.
 * </p>
 */
public class Instance {

    /**
     * Provides an EC2 instance ID.
     */
    private String instanceId;

    /**
     * Default constructor for a new Instance object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public Instance() {}
    
    /**
     * Constructs a new Instance object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param instanceId Provides an EC2 instance ID.
     */
    public Instance(String instanceId) {
        this.instanceId = instanceId;
    }
    
    /**
     * Provides an EC2 instance ID.
     *
     * @return Provides an EC2 instance ID.
     */
    public String getInstanceId() {
        return instanceId;
    }
    
    /**
     * Provides an EC2 instance ID.
     *
     * @param instanceId Provides an EC2 instance ID.
     */
    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
    
    /**
     * Provides an EC2 instance ID.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param instanceId Provides an EC2 instance ID.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Instance withInstanceId(String instanceId) {
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
        sb.append("InstanceId: " + instanceId + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    