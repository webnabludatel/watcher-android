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
import com.amazonaws.AmazonWebServiceRequest;

/**
 * Container for the parameters to the {@link com.amazonaws.services.sns.AmazonSNS#unsubscribe(UnsubscribeRequest) Unsubscribe operation}.
 * <p>
 * The Unsubscribe action deletes a subscription. If the subscription
 * requires authentication for deletion, only the owner of the
 * subscription or the its topic's owner can unsubscribe, and an AWS
 * signature is required. If the Unsubscribe call does not require
 * authentication and the requester is not the subscription owner, a
 * final cancellation message is delivered to the endpoint, so that the
 * endpoint owner can easily resubscribe to the topic if the Unsubscribe
 * request was unintended.
 * </p>
 *
 * @see com.amazonaws.services.sns.AmazonSNS#unsubscribe(UnsubscribeRequest)
 */
public class UnsubscribeRequest extends AmazonWebServiceRequest {

    /**
     * The ARN of the subscription to be deleted.
     */
    private String subscriptionArn;

    /**
     * Default constructor for a new UnsubscribeRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public UnsubscribeRequest() {}
    
    /**
     * Constructs a new UnsubscribeRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param subscriptionArn The ARN of the subscription to be deleted.
     */
    public UnsubscribeRequest(String subscriptionArn) {
        this.subscriptionArn = subscriptionArn;
    }
    
    /**
     * The ARN of the subscription to be deleted.
     *
     * @return The ARN of the subscription to be deleted.
     */
    public String getSubscriptionArn() {
        return subscriptionArn;
    }
    
    /**
     * The ARN of the subscription to be deleted.
     *
     * @param subscriptionArn The ARN of the subscription to be deleted.
     */
    public void setSubscriptionArn(String subscriptionArn) {
        this.subscriptionArn = subscriptionArn;
    }
    
    /**
     * The ARN of the subscription to be deleted.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param subscriptionArn The ARN of the subscription to be deleted.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public UnsubscribeRequest withSubscriptionArn(String subscriptionArn) {
        this.subscriptionArn = subscriptionArn;
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
        sb.append("}");
        return sb.toString();
    }
    
}
    