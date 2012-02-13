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
package com.amazonaws.services.ec2.model;

/**
 * <p>
 * Represents metadata to associate with Amazon EC2 resources. Each tag
 * consists of a user-defined key and value. Use tags to categorize EC2
 * resources, such as by purpose, owner, or environment.
 * </p>
 */
public class Tag {

    /**
     * The tag's key.
     */
    private String key;

    /**
     * The tag's value.
     */
    private String value;

    /**
     * Default constructor for a new Tag object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public Tag() {}
    
    /**
     * Constructs a new Tag object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param key The tag's key.
     * @param value The tag's value.
     */
    public Tag(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    /**
     * Constructs a new Tag object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param key The tag's key.
     */
    public Tag(String key) {
        this.key = key;
    }
    
    /**
     * The tag's key.
     *
     * @return The tag's key.
     */
    public String getKey() {
        return key;
    }
    
    /**
     * The tag's key.
     *
     * @param key The tag's key.
     */
    public void setKey(String key) {
        this.key = key;
    }
    
    /**
     * The tag's key.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param key The tag's key.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Tag withKey(String key) {
        this.key = key;
        return this;
    }
    
    
    /**
     * The tag's value.
     *
     * @return The tag's value.
     */
    public String getValue() {
        return value;
    }
    
    /**
     * The tag's value.
     *
     * @param value The tag's value.
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     * The tag's value.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param value The tag's value.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Tag withValue(String value) {
        this.value = value;
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
        sb.append("Key: " + key + ", ");
        sb.append("Value: " + value + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    