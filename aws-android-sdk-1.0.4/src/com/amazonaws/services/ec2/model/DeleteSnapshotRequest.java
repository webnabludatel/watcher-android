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
 * Container for the parameters to the {@link com.amazonaws.services.ec2.AmazonEC2#deleteSnapshot(DeleteSnapshotRequest) DeleteSnapshot operation}.
 * <p>
 * Deletes the snapshot identified by <code>snapshotId</code> .
 * 
 * </p>
 *
 * @see com.amazonaws.services.ec2.AmazonEC2#deleteSnapshot(DeleteSnapshotRequest)
 */
public class DeleteSnapshotRequest extends AmazonWebServiceRequest {

    /**
     * The ID of the snapshot to delete.
     */
    private String snapshotId;

    /**
     * Default constructor for a new DeleteSnapshotRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public DeleteSnapshotRequest() {}
    
    /**
     * Constructs a new DeleteSnapshotRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param snapshotId The ID of the snapshot to delete.
     */
    public DeleteSnapshotRequest(String snapshotId) {
        this.snapshotId = snapshotId;
    }
    
    /**
     * The ID of the snapshot to delete.
     *
     * @return The ID of the snapshot to delete.
     */
    public String getSnapshotId() {
        return snapshotId;
    }
    
    /**
     * The ID of the snapshot to delete.
     *
     * @param snapshotId The ID of the snapshot to delete.
     */
    public void setSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
    }
    
    /**
     * The ID of the snapshot to delete.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param snapshotId The ID of the snapshot to delete.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DeleteSnapshotRequest withSnapshotId(String snapshotId) {
        this.snapshotId = snapshotId;
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
        sb.append("SnapshotId: " + snapshotId + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    