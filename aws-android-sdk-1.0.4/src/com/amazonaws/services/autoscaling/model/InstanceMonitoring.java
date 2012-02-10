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
package com.amazonaws.services.autoscaling.model;

/**
 * <p>
 * The InstanceMonitoring Data Type.
 * </p>
 */
public class InstanceMonitoring {

    /**
     * If true, instance monitoring is enabled.
     */
    private Boolean enabled;

    /**
     * If true, instance monitoring is enabled.
     *
     * @return If true, instance monitoring is enabled.
     */
    public Boolean isEnabled() {
        return enabled;
    }
    
    /**
     * If true, instance monitoring is enabled.
     *
     * @param enabled If true, instance monitoring is enabled.
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
    /**
     * If true, instance monitoring is enabled.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param enabled If true, instance monitoring is enabled.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public InstanceMonitoring withEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }
    
    
    /**
     * If true, instance monitoring is enabled.
     *
     * @return If true, instance monitoring is enabled.
     */
    public Boolean getEnabled() {
        return enabled;
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
        sb.append("Enabled: " + enabled + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    