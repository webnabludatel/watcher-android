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
package com.amazonaws.services.sns.model;

/**
 * <p>
 * A wrapper type for the attributes of an SNS subscription.
 * </p>
 */
public class Subscription {

    /**
     * The subscription's ARN.
     */
    private String subscriptionArn;

    /**
     * The subscription's owner.
     */
    private String owner;

    /**
     * The subscription's protocol.
     */
    private String protocol;

    /**
     * The subscription's endpoint (format depends on the protocol).
     */
    private String endpoint;

    /**
     * The ARN of the subscription's topic.
     */
    private String topicArn;

    /**
     * Default constructor for a new Subscription object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public Subscription() {}
    
    /**
     * The subscription's ARN.
     *
     * @return The subscription's ARN.
     */
    public String getSubscriptionArn() {
        return subscriptionArn;
    }
    
    /**
     * The subscription's ARN.
     *
     * @param subscriptionArn The subscription's ARN.
     */
    public void setSubscriptionArn(String subscriptionArn) {
        this.subscriptionArn = subscriptionArn;
    }
    
    /**
     * The subscription's ARN.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param subscriptionArn The subscription's ARN.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Subscription withSubscriptionArn(String subscriptionArn) {
        this.subscriptionArn = subscriptionArn;
        return this;
    }
    
    
    /**
     * The subscription's owner.
     *
     * @return The subscription's owner.
     */
    public String getOwner() {
        return owner;
    }
    
    /**
     * The subscription's owner.
     *
     * @param owner The subscription's owner.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    /**
     * The subscription's owner.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param owner The subscription's owner.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Subscription withOwner(String owner) {
        this.owner = owner;
        return this;
    }
    
    
    /**
     * The subscription's protocol.
     *
     * @return The subscription's protocol.
     */
    public String getProtocol() {
        return protocol;
    }
    
    /**
     * The subscription's protocol.
     *
     * @param protocol The subscription's protocol.
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }
    
    /**
     * The subscription's protocol.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param protocol The subscription's protocol.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Subscription withProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }
    
    
    /**
     * The subscription's endpoint (format depends on the protocol).
     *
     * @return The subscription's endpoint (format depends on the protocol).
     */
    public String getEndpoint() {
        return endpoint;
    }
    
    /**
     * The subscription's endpoint (format depends on the protocol).
     *
     * @param endpoint The subscription's endpoint (format depends on the protocol).
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
    
    /**
     * The subscription's endpoint (format depends on the protocol).
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param endpoint The subscription's endpoint (format depends on the protocol).
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Subscription withEndpoint(String endpoint) {
        this.endpoint = endpoint;
        return this;
    }
    
    
    /**
     * The ARN of the subscription's topic.
     *
     * @return The ARN of the subscription's topic.
     */
    public String getTopicArn() {
        return topicArn;
    }
    
    /**
     * The ARN of the subscription's topic.
     *
     * @param topicArn The ARN of the subscription's topic.
     */
    public void setTopicArn(String topicArn) {
        this.topicArn = topicArn;
    }
    
    /**
     * The ARN of the subscription's topic.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param topicArn The ARN of the subscription's topic.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Subscription withTopicArn(String topicArn) {
        this.topicArn = topicArn;
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
        sb.append("SubscriptionArn: " + subscriptionArn + ", ");
        sb.append("Owner: " + owner + ", ");
        sb.append("Protocol: " + protocol + ", ");
        sb.append("Endpoint: " + endpoint + ", ");
        sb.append("TopicArn: " + topicArn + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    