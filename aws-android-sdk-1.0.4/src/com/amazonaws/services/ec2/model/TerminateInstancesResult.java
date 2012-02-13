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
 * The result of calling the TerminateInstances operation. Contains
 * details on how the specified instances are changing state.
 * </p>
 */
public class TerminateInstancesResult {

    /**
     * The list of the terminating instances and details on how their state
     * has changed.
     */
    private java.util.List<InstanceStateChange> terminatingInstances;

    /**
     * The list of the terminating instances and details on how their state
     * has changed.
     *
     * @return The list of the terminating instances and details on how their state
     *         has changed.
     */
    public java.util.List<InstanceStateChange> getTerminatingInstances() {
        
        if (terminatingInstances == null) {
            terminatingInstances = new java.util.ArrayList<InstanceStateChange>();
        }
        return terminatingInstances;
    }
    
    /**
     * The list of the terminating instances and details on how their state
     * has changed.
     *
     * @param terminatingInstances The list of the terminating instances and details on how their state
     *         has changed.
     */
    public void setTerminatingInstances(java.util.Collection<InstanceStateChange> terminatingInstances) {
        java.util.List<InstanceStateChange> terminatingInstancesCopy = new java.util.ArrayList<InstanceStateChange>();
        if (terminatingInstances != null) {
            terminatingInstancesCopy.addAll(terminatingInstances);
        }
        this.terminatingInstances = terminatingInstancesCopy;
    }
    
    /**
     * The list of the terminating instances and details on how their state
     * has changed.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param terminatingInstances The list of the terminating instances and details on how their state
     *         has changed.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public TerminateInstancesResult withTerminatingInstances(InstanceStateChange... terminatingInstances) {
        if (getTerminatingInstances() == null) setTerminatingInstances(new java.util.ArrayList<InstanceStateChange>());
        for (InstanceStateChange value : terminatingInstances) {
            getTerminatingInstances().add(value);
        }
        return this;
    }
    
    /**
     * The list of the terminating instances and details on how their state
     * has changed.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param terminatingInstances The list of the terminating instances and details on how their state
     *         has changed.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public TerminateInstancesResult withTerminatingInstances(java.util.Collection<InstanceStateChange> terminatingInstances) {
        java.util.List<InstanceStateChange> terminatingInstancesCopy = new java.util.ArrayList<InstanceStateChange>();
        if (terminatingInstances != null) {
            terminatingInstancesCopy.addAll(terminatingInstances);
        }
        this.terminatingInstances = terminatingInstancesCopy;

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
        sb.append("TerminatingInstances: " + terminatingInstances + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    