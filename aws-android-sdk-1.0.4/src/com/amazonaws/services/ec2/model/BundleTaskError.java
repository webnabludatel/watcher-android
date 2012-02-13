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
 * Represents an error that occurred during a bundle task.
 * </p>
 */
public class BundleTaskError {

    /**
     * Error code.
     */
    private String code;

    /**
     * Error message.
     */
    private String message;

    /**
     * Error code.
     *
     * @return Error code.
     */
    public String getCode() {
        return code;
    }
    
    /**
     * Error code.
     *
     * @param code Error code.
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Error code.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param code Error code.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public BundleTaskError withCode(String code) {
        this.code = code;
        return this;
    }
    
    
    /**
     * Error message.
     *
     * @return Error message.
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Error message.
     *
     * @param message Error message.
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * Error message.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param message Error message.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public BundleTaskError withMessage(String message) {
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
    