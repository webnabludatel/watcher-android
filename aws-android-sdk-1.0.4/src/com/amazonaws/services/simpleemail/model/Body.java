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
 * Represents the body of the message. You can specify text, HTML, or
 * both. If you use both, then the message should display correctly in
 * the widest variety of email clients.
 * </p>
 */
public class Body {

    /**
     * The content of the message, in text format. Use this for text-based
     * email clients, or clients on high-latency networks (such as mobile
     * devices).
     */
    private Content text;

    /**
     * The content of the message, in HTML format. Use this for email clients
     * that can process HTML. You can include clickable links, formatted
     * text, and much more in an HTML message.
     */
    private Content html;

    /**
     * Default constructor for a new Body object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public Body() {}
    
    /**
     * Constructs a new Body object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param text The content of the message, in text format. Use this for
     * text-based email clients, or clients on high-latency networks (such as
     * mobile devices).
     */
    public Body(Content text) {
        this.text = text;
    }
    
    /**
     * The content of the message, in text format. Use this for text-based
     * email clients, or clients on high-latency networks (such as mobile
     * devices).
     *
     * @return The content of the message, in text format. Use this for text-based
     *         email clients, or clients on high-latency networks (such as mobile
     *         devices).
     */
    public Content getText() {
        return text;
    }
    
    /**
     * The content of the message, in text format. Use this for text-based
     * email clients, or clients on high-latency networks (such as mobile
     * devices).
     *
     * @param text The content of the message, in text format. Use this for text-based
     *         email clients, or clients on high-latency networks (such as mobile
     *         devices).
     */
    public void setText(Content text) {
        this.text = text;
    }
    
    /**
     * The content of the message, in text format. Use this for text-based
     * email clients, or clients on high-latency networks (such as mobile
     * devices).
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param text The content of the message, in text format. Use this for text-based
     *         email clients, or clients on high-latency networks (such as mobile
     *         devices).
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Body withText(Content text) {
        this.text = text;
        return this;
    }
    
    
    /**
     * The content of the message, in HTML format. Use this for email clients
     * that can process HTML. You can include clickable links, formatted
     * text, and much more in an HTML message.
     *
     * @return The content of the message, in HTML format. Use this for email clients
     *         that can process HTML. You can include clickable links, formatted
     *         text, and much more in an HTML message.
     */
    public Content getHtml() {
        return html;
    }
    
    /**
     * The content of the message, in HTML format. Use this for email clients
     * that can process HTML. You can include clickable links, formatted
     * text, and much more in an HTML message.
     *
     * @param html The content of the message, in HTML format. Use this for email clients
     *         that can process HTML. You can include clickable links, formatted
     *         text, and much more in an HTML message.
     */
    public void setHtml(Content html) {
        this.html = html;
    }
    
    /**
     * The content of the message, in HTML format. Use this for email clients
     * that can process HTML. You can include clickable links, formatted
     * text, and much more in an HTML message.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param html The content of the message, in HTML format. Use this for email clients
     *         that can process HTML. You can include clickable links, formatted
     *         text, and much more in an HTML message.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Body withHtml(Content html) {
        this.html = html;
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
        sb.append("Text: " + text + ", ");
        sb.append("Html: " + html + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    