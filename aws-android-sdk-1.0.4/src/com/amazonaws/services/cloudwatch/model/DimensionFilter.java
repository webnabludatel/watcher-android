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
package com.amazonaws.services.cloudwatch.model;

/**
 * <p>
 * The <code>DimensionFilter</code> data type is used to filter
 * ListMetrics results.
 * </p>
 */
public class DimensionFilter {

    /**
     * The dimension name to be matched.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     */
    private String name;

    /**
     * The value of the dimension to be matched. <note> Specifying a
     * <code>Name</code> without specifying a <code>Value</code> returns all
     * values associated with that <code>Name</code>. </note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     */
    private String value;

    /**
     * The dimension name to be matched.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @return The dimension name to be matched.
     */
    public String getName() {
        return name;
    }
    
    /**
     * The dimension name to be matched.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param name The dimension name to be matched.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * The dimension name to be matched.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param name The dimension name to be matched.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DimensionFilter withName(String name) {
        this.name = name;
        return this;
    }
    
    
    /**
     * The value of the dimension to be matched. <note> Specifying a
     * <code>Name</code> without specifying a <code>Value</code> returns all
     * values associated with that <code>Name</code>. </note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @return The value of the dimension to be matched. <note> Specifying a
     *         <code>Name</code> without specifying a <code>Value</code> returns all
     *         values associated with that <code>Name</code>. </note>
     */
    public String getValue() {
        return value;
    }
    
    /**
     * The value of the dimension to be matched. <note> Specifying a
     * <code>Name</code> without specifying a <code>Value</code> returns all
     * values associated with that <code>Name</code>. </note>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param value The value of the dimension to be matched. <note> Specifying a
     *         <code>Name</code> without specifying a <code>Value</code> returns all
     *         values associated with that <code>Name</code>. </note>
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     * The value of the dimension to be matched. <note> Specifying a
     * <code>Name</code> without specifying a <code>Value</code> returns all
     * values associated with that <code>Name</code>. </note>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param value The value of the dimension to be matched. <note> Specifying a
     *         <code>Name</code> without specifying a <code>Value</code> returns all
     *         values associated with that <code>Name</code>. </note>
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DimensionFilter withValue(String value) {
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
        sb.append("Name: " + name + ", ");
        sb.append("Value: " + value + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    