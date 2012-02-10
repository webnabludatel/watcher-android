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
package com.amazonaws.services.sqs.model;

/**
 * 
 */
public class GetQueueUrlResult {

    /**
     * The URL for the queue.
     */
    private String queueUrl;

    /**
     * The URL for the queue.
     *
     * @return The URL for the queue.
     */
    public String getQueueUrl() {
        return queueUrl;
    }
    
    /**
     * The URL for the queue.
     *
     * @param queueUrl The URL for the queue.
     */
    public void setQueueUrl(String queueUrl) {
        this.queueUrl = queueUrl;
    }
    
    /**
     * The URL for the queue.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param queueUrl The URL for the queue.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public GetQueueUrlResult withQueueUrl(String queueUrl) {
        this.queueUrl = queueUrl;
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
        sb.append("QueueUrl: " + queueUrl + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    