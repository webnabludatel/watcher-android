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
 * Container for the parameters to the {@link com.amazonaws.services.sns.AmazonSNS#getTopicAttributes(GetTopicAttributesRequest) GetTopicAttributes operation}.
 * <p>
 * The GetTopicAttribtues action returns all of the properties of a topic
 * customers have created. Topic properties returned might differ based
 * on the authorization of the user.
 * </p>
 *
 * @see com.amazonaws.services.sns.AmazonSNS#getTopicAttributes(GetTopicAttributesRequest)
 */
public class GetTopicAttributesRequest extends AmazonWebServiceRequest {

    /**
     * The ARN of the topic whose properties you want to get.
     */
    private String topicArn;

    /**
     * Default constructor for a new GetTopicAttributesRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public GetTopicAttributesRequest() {}
    
    /**
     * Constructs a new GetTopicAttributesRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param topicArn The ARN of the topic whose properties you want to get.
     */
    public GetTopicAttributesRequest(String topicArn) {
        this.topicArn = topicArn;
    }
    
    /**
     * The ARN of the topic whose properties you want to get.
     *
     * @return The ARN of the topic whose properties you want to get.
     */
    public String getTopicArn() {
        return topicArn;
    }
    
    /**
     * The ARN of the topic whose properties you want to get.
     *
     * @param topicArn The ARN of the topic whose properties you want to get.
     */
    public void setTopicArn(String topicArn) {
        this.topicArn = topicArn;
    }
    
    /**
     * The ARN of the topic whose properties you want to get.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param topicArn The ARN of the topic whose properties you want to get.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public GetTopicAttributesRequest withTopicArn(String topicArn) {
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
        sb.append("TopicArn: " + topicArn + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    