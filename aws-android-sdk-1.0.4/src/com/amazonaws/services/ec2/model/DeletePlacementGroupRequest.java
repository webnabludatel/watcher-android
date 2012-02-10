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
 * Container for the parameters to the {@link com.amazonaws.services.ec2.AmazonEC2#deletePlacementGroup(DeletePlacementGroupRequest) DeletePlacementGroup operation}.
 * <p>
 * Deletes a PlacementGroup from a user's account. Terminate all Amazon
 * EC2 instances in the placement group before deletion.
 * </p>
 *
 * @see com.amazonaws.services.ec2.AmazonEC2#deletePlacementGroup(DeletePlacementGroupRequest)
 */
public class DeletePlacementGroupRequest extends AmazonWebServiceRequest {

    /**
     * The name of the <code>PlacementGroup</code> to delete.
     */
    private String groupName;

    /**
     * Default constructor for a new DeletePlacementGroupRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public DeletePlacementGroupRequest() {}
    
    /**
     * Constructs a new DeletePlacementGroupRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param groupName The name of the <code>PlacementGroup</code> to
     * delete.
     */
    public DeletePlacementGroupRequest(String groupName) {
        this.groupName = groupName;
    }
    
    /**
     * The name of the <code>PlacementGroup</code> to delete.
     *
     * @return The name of the <code>PlacementGroup</code> to delete.
     */
    public String getGroupName() {
        return groupName;
    }
    
    /**
     * The name of the <code>PlacementGroup</code> to delete.
     *
     * @param groupName The name of the <code>PlacementGroup</code> to delete.
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    /**
     * The name of the <code>PlacementGroup</code> to delete.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param groupName The name of the <code>PlacementGroup</code> to delete.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DeletePlacementGroupRequest withGroupName(String groupName) {
        this.groupName = groupName;
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
        sb.append("GroupName: " + groupName + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    