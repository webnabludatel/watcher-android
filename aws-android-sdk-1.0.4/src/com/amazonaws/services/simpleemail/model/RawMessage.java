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
 * Represents the raw data of the message.
 * </p>
 */
public class RawMessage {

    /**
     * The raw data of the message. The client must ensure that the message
     * format complies with Internet email standards regarding email header
     * fields, MIME types, MIME encoding, and base64 encoding (if necessary).
     * <p>For more information, go to the <a
     * f="http://docs.amazonwebservices.com/ses/latest/DeveloperGuide">Amazon
     * SES Developer Guide</a>.
     */
    private java.nio.ByteBuffer data;

    /**
     * Default constructor for a new RawMessage object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public RawMessage() {}
    
    /**
     * Constructs a new RawMessage object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param data The raw data of the message. The client must ensure that
     * the message format complies with Internet email standards regarding
     * email header fields, MIME types, MIME encoding, and base64 encoding
     * (if necessary). <p>For more information, go to the <a
     * f="http://docs.amazonwebservices.com/ses/latest/DeveloperGuide">Amazon
     * SES Developer Guide</a>.
     */
    public RawMessage(java.nio.ByteBuffer data) {
        this.data = data;
    }
    
    /**
     * The raw data of the message. The client must ensure that the message
     * format complies with Internet email standards regarding email header
     * fields, MIME types, MIME encoding, and base64 encoding (if necessary).
     * <p>For more information, go to the <a
     * f="http://docs.amazonwebservices.com/ses/latest/DeveloperGuide">Amazon
     * SES Developer Guide</a>.
     *
     * @return The raw data of the message. The client must ensure that the message
     *         format complies with Internet email standards regarding email header
     *         fields, MIME types, MIME encoding, and base64 encoding (if necessary).
     *         <p>For more information, go to the <a
     *         f="http://docs.amazonwebservices.com/ses/latest/DeveloperGuide">Amazon
     *         SES Developer Guide</a>.
     */
    public java.nio.ByteBuffer getData() {
        return data;
    }
    
    /**
     * The raw data of the message. The client must ensure that the message
     * format complies with Internet email standards regarding email header
     * fields, MIME types, MIME encoding, and base64 encoding (if necessary).
     * <p>For more information, go to the <a
     * f="http://docs.amazonwebservices.com/ses/latest/DeveloperGuide">Amazon
     * SES Developer Guide</a>.
     *
     * @param data The raw data of the message. The client must ensure that the message
     *         format complies with Internet email standards regarding email header
     *         fields, MIME types, MIME encoding, and base64 encoding (if necessary).
     *         <p>For more information, go to the <a
     *         f="http://docs.amazonwebservices.com/ses/latest/DeveloperGuide">Amazon
     *         SES Developer Guide</a>.
     */
    public void setData(java.nio.ByteBuffer data) {
        this.data = data;
    }
    
    /**
     * The raw data of the message. The client must ensure that the message
     * format complies with Internet email standards regarding email header
     * fields, MIME types, MIME encoding, and base64 encoding (if necessary).
     * <p>For more information, go to the <a
     * f="http://docs.amazonwebservices.com/ses/latest/DeveloperGuide">Amazon
     * SES Developer Guide</a>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param data The raw data of the message. The client must ensure that the message
     *         format complies with Internet email standards regarding email header
     *         fields, MIME types, MIME encoding, and base64 encoding (if necessary).
     *         <p>For more information, go to the <a
     *         f="http://docs.amazonwebservices.com/ses/latest/DeveloperGuide">Amazon
     *         SES Developer Guide</a>.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public RawMessage withData(java.nio.ByteBuffer data) {
        this.data = data;
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
        sb.append("Data: " + data + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    