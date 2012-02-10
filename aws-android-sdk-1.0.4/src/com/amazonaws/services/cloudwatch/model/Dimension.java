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
 * The <code>Dimension</code> data type further expands on the identity
 * of a metric using a Name, Value pair.
 * </p>
 */
public class Dimension {

    /**
     * The name of the dimension.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     */
    private String name;

    /**
     * The value representing the dimension measurement
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     */
    private String value;

    /**
     * The name of the dimension.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @return The name of the dimension.
     */
    public String getName() {
        return name;
    }
    
    /**
     * The name of the dimension.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param name The name of the dimension.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * The name of the dimension.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param name The name of the dimension.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Dimension withName(String name) {
        this.name = name;
        return this;
    }
    
    
    /**
     * The value representing the dimension measurement
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @return The value representing the dimension measurement
     */
    public String getValue() {
        return value;
    }
    
    /**
     * The value representing the dimension measurement
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param value The value representing the dimension measurement
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     * The value representing the dimension measurement
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param value The value representing the dimension measurement
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Dimension withValue(String value) {
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
    