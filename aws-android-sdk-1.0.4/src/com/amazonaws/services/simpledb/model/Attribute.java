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
package com.amazonaws.services.simpledb.model;

/**
 * <p>
 * </p>
 */
public class Attribute {

    /**
     * The name of the attribute.
     */
    private String name;

    /**
     * 
     */
    private String alternateNameEncoding;

    /**
     * The value of the attribute.
     */
    private String value;

    /**
     * 
     */
    private String alternateValueEncoding;

    /**
     * Default constructor for a new Attribute object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public Attribute() {}
    
    /**
     * Constructs a new Attribute object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param name The name of the attribute.
     * @param value The value of the attribute.
     */
    public Attribute(String name, String value) {
        this.name = name;
        this.value = value;
    }
    
    /**
     * The name of the attribute.
     *
     * @return The name of the attribute.
     */
    public String getName() {
        return name;
    }
    
    /**
     * The name of the attribute.
     *
     * @param name The name of the attribute.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * The name of the attribute.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param name The name of the attribute.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Attribute withName(String name) {
        this.name = name;
        return this;
    }
    
    
    /**
     * 
     *
     * @return 
     */
    public String getAlternateNameEncoding() {
        return alternateNameEncoding;
    }
    
    /**
     * 
     *
     * @param alternateNameEncoding 
     */
    public void setAlternateNameEncoding(String alternateNameEncoding) {
        this.alternateNameEncoding = alternateNameEncoding;
    }
    
    /**
     * 
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param alternateNameEncoding 
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Attribute withAlternateNameEncoding(String alternateNameEncoding) {
        this.alternateNameEncoding = alternateNameEncoding;
        return this;
    }
    
    
    /**
     * The value of the attribute.
     *
     * @return The value of the attribute.
     */
    public String getValue() {
        return value;
    }
    
    /**
     * The value of the attribute.
     *
     * @param value The value of the attribute.
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     * The value of the attribute.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param value The value of the attribute.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Attribute withValue(String value) {
        this.value = value;
        return this;
    }
    
    
    /**
     * 
     *
     * @return 
     */
    public String getAlternateValueEncoding() {
        return alternateValueEncoding;
    }
    
    /**
     * 
     *
     * @param alternateValueEncoding 
     */
    public void setAlternateValueEncoding(String alternateValueEncoding) {
        this.alternateValueEncoding = alternateValueEncoding;
    }
    
    /**
     * 
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param alternateValueEncoding 
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Attribute withAlternateValueEncoding(String alternateValueEncoding) {
        this.alternateValueEncoding = alternateValueEncoding;
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
        sb.append("Name: " + name + ", ");
        sb.append("AlternateNameEncoding: " + alternateNameEncoding + ", ");
        sb.append("Value: " + value + ", ");
        sb.append("AlternateValueEncoding: " + alternateValueEncoding + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    