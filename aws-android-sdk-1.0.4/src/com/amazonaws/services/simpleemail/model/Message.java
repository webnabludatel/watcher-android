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
package com.amazonaws.services.simpleemail.model;

/**
 * <p>
 * Represents the message to be sent, composed of a subject and a body.
 * </p>
 */
public class Message {

    /**
     * The subject of the message: A short summary of the content, which will
     * appear in the recipient's inbox.
     */
    private Content subject;

    /**
     * The message body.
     */
    private Body body;

    /**
     * Default constructor for a new Message object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public Message() {}
    
    /**
     * Constructs a new Message object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param subject The subject of the message: A short summary of the
     * content, which will appear in the recipient's inbox.
     * @param body The message body.
     */
    public Message(Content subject, Body body) {
        this.subject = subject;
        this.body = body;
    }
    
    /**
     * The subject of the message: A short summary of the content, which will
     * appear in the recipient's inbox.
     *
     * @return The subject of the message: A short summary of the content, which will
     *         appear in the recipient's inbox.
     */
    public Content getSubject() {
        return subject;
    }
    
    /**
     * The subject of the message: A short summary of the content, which will
     * appear in the recipient's inbox.
     *
     * @param subject The subject of the message: A short summary of the content, which will
     *         appear in the recipient's inbox.
     */
    public void setSubject(Content subject) {
        this.subject = subject;
    }
    
    /**
     * The subject of the message: A short summary of the content, which will
     * appear in the recipient's inbox.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param subject The subject of the message: A short summary of the content, which will
     *         appear in the recipient's inbox.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Message withSubject(Content subject) {
        this.subject = subject;
        return this;
    }
    
    
    /**
     * The message body.
     *
     * @return The message body.
     */
    public Body getBody() {
        return body;
    }
    
    /**
     * The message body.
     *
     * @param body The message body.
     */
    public void setBody(Body body) {
        this.body = body;
    }
    
    /**
     * The message body.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param body The message body.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Message withBody(Body body) {
        this.body = body;
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
        sb.append("Subject: " + subject + ", ");
        sb.append("Body: " + body + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    