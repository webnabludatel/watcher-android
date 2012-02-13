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
 * Container for the parameters to the {@link com.amazonaws.services.sqs.AmazonSQS#getQueueAttributes(GetQueueAttributesRequest) GetQueueAttributes operation}.
 * <p>
 * Gets attributes for the specified queue. The following attributes are
 * supported:
 * <ul>
 * <li> <code>All</code> - returns all values.</li>
 * <li> <code>ApproximateNumberOfMessages</code> - returns the
 * approximate number of visible messages in a queue. For more
 * information, see Resources Required to Process Messages in the Amazon
 * SQS Developer Guide.</li>
 * <li> <code>ApproximateNumberOfMessagesNotVisible</code> - returns the
 * approximate number of messages that are not timed-out and not deleted.
 * For more information, see Resources Required to Process Messages in
 * the Amazon SQS Developer Guide.</li>
 * <li> <code>VisibilityTimeout</code> - returns the visibility timeout
 * for the queue. For more information about visibility timeout, see
 * Visibility Timeout in the Amazon SQS Developer Guide.</li>
 * <li> <code>CreatedTimestamp</code> - returns the time when the queue
 * was created (epoch time in seconds).</li>
 * <li> <code>LastModifiedTimestamp</code> - returns the time when the
 * queue was last changed (epoch time in seconds).</li>
 * <li> <code>Policy</code> - returns the queue's policy.</li>
 * <li> <code>MaximumMessageSize</code> - returns the limit of how many
 * bytes a message can contain before Amazon SQS rejects it.</li>
 * <li> <code>MessageRetentionPeriod</code> - returns the number of
 * seconds Amazon SQS retains a message.</li>
 * <li> <code>QueueArn</code> - returns the queue's Amazon resource name
 * (ARN).</li>
 * <li> <code>ApproximateNumberOfMessagesDelayed</code> - returns the
 * approximate number of messages that are pending to be added to the
 * queue.</li>
 * <li> <code>DelaySeconds</code> - returns the default delay on the
 * queue in seconds.</li>
 * 
 * </ul>
 * 
 * </p>
 *
 * @see com.amazonaws.services.sqs.AmazonSQS#getQueueAttributes(GetQueueAttributesRequest)
 */
public class GetQueueAttributesRequest extends AmazonWebServiceRequest {

    /**
     * The URL of the SQS queue to take action on.
     */
    private String queueUrl;

    /**
     * A list of attributes to retrieve information for.
     */
    private java.util.List<String> attributeNames;

    /**
     * Default constructor for a new GetQueueAttributesRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public GetQueueAttributesRequest() {}
    
    /**
     * Constructs a new GetQueueAttributesRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param queueUrl The URL of the SQS queue to take action on.
     */
    public GetQueueAttributesRequest(String queueUrl) {
        this.queueUrl = queueUrl;
    }
    
    /**
     * The URL of the SQS queue to take action on.
     *
     * @return The URL of the SQS queue to take action on.
     */
    public String getQueueUrl() {
        return queueUrl;
    }
    
    /**
     * The URL of the SQS queue to take action on.
     *
     * @param queueUrl The URL of the SQS queue to take action on.
     */
    public void setQueueUrl(String queueUrl) {
        this.queueUrl = queueUrl;
    }
    
    /**
     * The URL of the SQS queue to take action on.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param queueUrl The URL of the SQS queue to take action on.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public GetQueueAttributesRequest withQueueUrl(String queueUrl) {
        this.queueUrl = queueUrl;
        return this;
    }
    
    
    /**
     * A list of attributes to retrieve information for.
     *
     * @return A list of attributes to retrieve information for.
     */
    public java.util.List<String> getAttributeNames() {
        
        if (attributeNames == null) {
            attributeNames = new java.util.ArrayList<String>();
        }
        return attributeNames;
    }
    
    /**
     * A list of attributes to retrieve information for.
     *
     * @param attributeNames A list of attributes to retrieve information for.
     */
    public void setAttributeNames(java.util.Collection<String> attributeNames) {
        java.util.List<String> attributeNamesCopy = new java.util.ArrayList<String>();
        if (attributeNames != null) {
            attributeNamesCopy.addAll(attributeNames);
        }
        this.attributeNames = attributeNamesCopy;
    }
    
    /**
     * A list of attributes to retrieve information for.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param attributeNames A list of attributes to retrieve information for.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public GetQueueAttributesRequest withAttributeNames(String... attributeNames) {
        if (getAttributeNames() == null) setAttributeNames(new java.util.ArrayList<String>());
        for (String value : attributeNames) {
            getAttributeNames().add(value);
        }
        return this;
    }
    
    /**
     * A list of attributes to retrieve information for.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param attributeNames A list of attributes to retrieve information for.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public GetQueueAttributesRequest withAttributeNames(java.util.Collection<String> attributeNames) {
        java.util.List<String> attributeNamesCopy = new java.util.ArrayList<String>();
        if (attributeNames != null) {
            attributeNamesCopy.addAll(attributeNames);
        }
        this.attributeNames = attributeNamesCopy;

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
        sb.append("AttributeNames: " + attributeNames + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    