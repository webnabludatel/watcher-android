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
 * Spot Instance State Fault
 */
public class SpotInstanceStateFault {

    private String code;

    private String message;

    /**
     * Returns the value of the Code property for this object.
     *
     * @return The value of the Code property for this object.
     */
    public String getCode() {
        return code;
    }
    
    /**
     * Sets the value of the Code property for this object.
     *
     * @param code The new value for the Code property for this object.
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Sets the value of the Code property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param code The new value for the Code property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SpotInstanceStateFault withCode(String code) {
        this.code = code;
        return this;
    }
    
    
    /**
     * Returns the value of the Message property for this object.
     *
     * @return The value of the Message property for this object.
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Sets the value of the Message property for this object.
     *
     * @param message The new value for the Message property for this object.
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * Sets the value of the Message property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param message The new value for the Message property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SpotInstanceStateFault withMessage(String message) {
        this.message = message;
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
        sb.append("Code: " + code + ", ");
        sb.append("Message: " + message + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    