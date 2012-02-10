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
import com.amazonaws.AmazonWebServiceRequest;

/**
 * Container for the parameters to the {@link com.amazonaws.services.sqs.AmazonSQS#getQueueUrl(GetQueueUrlRequest) GetQueueUrl operation}.
 * <p>
 * The <code>GetQueueUrl</code> action returns the URL of an existing
 * queue.
 * </p>
 *
 * @see com.amazonaws.services.sqs.AmazonSQS#getQueueUrl(GetQueueUrlRequest)
 */
public class GetQueueUrlRequest extends AmazonWebServiceRequest {

    /**
     * The name of the queue whose URL must be fetched.
     */
    private String queueName;

    /**
     * The AWS account number of the queue's owner.
     */
    private String queueOwnerAWSAccountId;

    /**
     * Default constructor for a new GetQueueUrlRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public GetQueueUrlRequest() {}
    
    /**
     * Constructs a new GetQueueUrlRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param queueName The name of the queue whose URL must be fetched.
     */
    public GetQueueUrlRequest(String queueName) {
        this.queueName = queueName;
    }
    
    /**
     * The name of the queue whose URL must be fetched.
     *
     * @return The name of the queue whose URL must be fetched.
     */
    public String getQueueName() {
        return queueName;
    }
    
    /**
     * The name of the queue whose URL must be fetched.
     *
     * @param queueName The name of the queue whose URL must be fetched.
     */
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
    
    /**
     * The name of the queue whose URL must be fetched.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param queueName The name of the queue whose URL must be fetched.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public GetQueueUrlRequest withQueueName(String queueName) {
        this.queueName = queueName;
        return this;
    }
    
    
    /**
     * The AWS account number of the queue's owner.
     *
     * @return The AWS account number of the queue's owner.
     */
    public String getQueueOwnerAWSAccountId() {
        return queueOwnerAWSAccountId;
    }
    
    /**
     * The AWS account number of the queue's owner.
     *
     * @param queueOwnerAWSAccountId The AWS account number of the queue's owner.
     */
    public void setQueueOwnerAWSAccountId(String queueOwnerAWSAccountId) {
        this.queueOwnerAWSAccountId = queueOwnerAWSAccountId;
    }
    
    /**
     * The AWS account number of the queue's owner.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param queueOwnerAWSAccountId The AWS account number of the queue's owner.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public GetQueueUrlRequest withQueueOwnerAWSAccountId(String queueOwnerAWSAccountId) {
        this.queueOwnerAWSAccountId = queueOwnerAWSAccountId;
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
        sb.append("QueueName: " + queueName + ", ");
        sb.append("QueueOwnerAWSAccountId: " + queueOwnerAWSAccountId + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    