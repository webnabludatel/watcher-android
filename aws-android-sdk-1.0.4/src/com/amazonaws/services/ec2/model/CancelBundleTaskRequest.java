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
 * Container for the parameters to the {@link com.amazonaws.services.ec2.AmazonEC2#cancelBundleTask(CancelBundleTaskRequest) CancelBundleTask operation}.
 * <p>
 * CancelBundleTask operation cancels a pending or in-progress bundling
 * task. This is an asynchronous call and it make take a while for the
 * task to be canceled. If a task is canceled while it is storing items,
 * there may be parts of the incomplete AMI stored in S3. It is up to the
 * caller to clean up these parts from S3.
 * </p>
 *
 * @see com.amazonaws.services.ec2.AmazonEC2#cancelBundleTask(CancelBundleTaskRequest)
 */
public class CancelBundleTaskRequest extends AmazonWebServiceRequest {

    /**
     * The ID of the bundle task to cancel.
     */
    private String bundleId;

    /**
     * Default constructor for a new CancelBundleTaskRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public CancelBundleTaskRequest() {}
    
    /**
     * Constructs a new CancelBundleTaskRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param bundleId The ID of the bundle task to cancel.
     */
    public CancelBundleTaskRequest(String bundleId) {
        this.bundleId = bundleId;
    }
    
    /**
     * The ID of the bundle task to cancel.
     *
     * @return The ID of the bundle task to cancel.
     */
    public String getBundleId() {
        return bundleId;
    }
    
    /**
     * The ID of the bundle task to cancel.
     *
     * @param bundleId The ID of the bundle task to cancel.
     */
    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }
    
    /**
     * The ID of the bundle task to cancel.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param bundleId The ID of the bundle task to cancel.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public CancelBundleTaskRequest withBundleId(String bundleId) {
        this.bundleId = bundleId;
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
        sb.append("BundleId: " + bundleId + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    