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
 * Describes a permission allowing either a user or group to create a
 * new EBS volume from a snapshot.
 * </p>
 */
public class CreateVolumePermission {

    /**
     * The user ID of the user that can create volumes from the snapshot.
     */
    private String userId;

    /**
     * The group that is allowed to create volumes from the snapshot
     * (currently supports "all").
     */
    private String group;

    /**
     * The user ID of the user that can create volumes from the snapshot.
     *
     * @return The user ID of the user that can create volumes from the snapshot.
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * The user ID of the user that can create volumes from the snapshot.
     *
     * @param userId The user ID of the user that can create volumes from the snapshot.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * The user ID of the user that can create volumes from the snapshot.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param userId The user ID of the user that can create volumes from the snapshot.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateVolumePermission withUserId(String userId) {
        this.userId = userId;
        return this;
    }
    
    
    /**
     * The group that is allowed to create volumes from the snapshot
     * (currently supports "all").
     *
     * @return The group that is allowed to create volumes from the snapshot
     *         (currently supports "all").
     */
    public String getGroup() {
        return group;
    }
    
    /**
     * The group that is allowed to create volumes from the snapshot
     * (currently supports "all").
     *
     * @param group The group that is allowed to create volumes from the snapshot
     *         (currently supports "all").
     */
    public void setGroup(String group) {
        this.group = group;
    }
    
    /**
     * The group that is allowed to create volumes from the snapshot
     * (currently supports "all").
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param group The group that is allowed to create volumes from the snapshot
     *         (currently supports "all").
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CreateVolumePermission withGroup(String group) {
        this.group = group;
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
        sb.append("UserId: " + userId + ", ");
        sb.append("Group: " + group + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    