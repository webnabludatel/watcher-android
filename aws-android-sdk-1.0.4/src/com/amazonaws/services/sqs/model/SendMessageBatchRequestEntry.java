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
 * <p>
 * Contains the details of a single SQS message along with a
 * <code>Id</code> .
 * </p>
 */
public class SendMessageBatchRequestEntry {

    /**
     * An identifier for the message in this batch. This is used to
     * communicate the result. Note that the the <code>Id</code>s of a batch
     * request need to be unique within the request.
     */
    private String id;

    /**
     * Body of the message.
     */
    private String messageBody;

    /**
     * The number of seconds for which the message has to be delayed.
     */
    private Integer delaySeconds;

    /**
     * Default constructor for a new SendMessageBatchRequestEntry object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public SendMessageBatchRequestEntry() {}
    
    /**
     * Constructs a new SendMessageBatchRequestEntry object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param id An identifier for the message in this batch. This is used to
     * communicate the result. Note that the the <code>Id</code>s of a batch
     * request need to be unique within the request.
     * @param messageBody Body of the message.
     */
    public SendMessageBatchRequestEntry(String id, String messageBody) {
        this.id = id;
        this.messageBody = messageBody;
    }
    
    /**
     * An identifier for the message in this batch. This is used to
     * communicate the result. Note that the the <code>Id</code>s of a batch
     * request need to be unique within the request.
     *
     * @return An identifier for the message in this batch. This is used to
     *         communicate the result. Note that the the <code>Id</code>s of a batch
     *         request need to be unique within the request.
     */
    public String getId() {
        return id;
    }
    
    /**
     * An identifier for the message in this batch. This is used to
     * communicate the result. Note that the the <code>Id</code>s of a batch
     * request need to be unique within the request.
     *
     * @param id An identifier for the message in this batch. This is used to
     *         communicate the result. Note that the the <code>Id</code>s of a batch
     *         request need to be unique within the request.
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * An identifier for the message in this batch. This is used to
     * communicate the result. Note that the the <code>Id</code>s of a batch
     * request need to be unique within the request.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param id An identifier for the message in this batch. This is used to
     *         communicate the result. Note that the the <code>Id</code>s of a batch
     *         request need to be unique within the request.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SendMessageBatchRequestEntry withId(String id) {
        this.id = id;
        return this;
    }
    
    
    /**
     * Body of the message.
     *
     * @return Body of the message.
     */
    public String getMessageBody() {
        return messageBody;
    }
    
    /**
     * Body of the message.
     *
     * @param messageBody Body of the message.
     */
    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
    
    /**
     * Body of the message.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param messageBody Body of the message.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SendMessageBatchRequestEntry withMessageBody(String messageBody) {
        this.messageBody = messageBody;
        return this;
    }
    
    
    /**
     * The number of seconds for which the message has to be delayed.
     *
     * @return The number of seconds for which the message has to be delayed.
     */
    public Integer getDelaySeconds() {
        return delaySeconds;
    }
    
    /**
     * The number of seconds for which the message has to be delayed.
     *
     * @param delaySeconds The number of seconds for which the message has to be delayed.
     */
    public void setDelaySeconds(Integer delaySeconds) {
        this.delaySeconds = delaySeconds;
    }
    
    /**
     * The number of seconds for which the message has to be delayed.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param delaySeconds The number of seconds for which the message has to be delayed.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SendMessageBatchRequestEntry withDelaySeconds(Integer delaySeconds) {
        this.delaySeconds = delaySeconds;
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
        sb.append("Id: " + id + ", ");
        sb.append("MessageBody: " + messageBody + ", ");
        sb.append("DelaySeconds: " + delaySeconds + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    