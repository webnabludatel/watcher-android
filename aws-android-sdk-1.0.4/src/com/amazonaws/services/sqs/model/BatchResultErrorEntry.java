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
package com.amazonaws.services.sqs.model;

/**
 * <p>
 * This is used in the responses of batch API to give a detailed
 * description of the result of an operation on each entry in the
 * request.
 * </p>
 */
public class BatchResultErrorEntry {

    /**
     * The id of an entry in a batch request.
     */
    private String id;

    /**
     * Whether the error happened due to the sender's fault.
     */
    private Boolean senderFault;

    /**
     * An error code representing why the operation failed on this entry.
     */
    private String code;

    /**
     * A message explaining why the operation failed on this entry.
     */
    private String message;

    /**
     * The id of an entry in a batch request.
     *
     * @return The id of an entry in a batch request.
     */
    public String getId() {
        return id;
    }
    
    /**
     * The id of an entry in a batch request.
     *
     * @param id The id of an entry in a batch request.
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * The id of an entry in a batch request.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param id The id of an entry in a batch request.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public BatchResultErrorEntry withId(String id) {
        this.id = id;
        return this;
    }
    
    
    /**
     * Whether the error happened due to the sender's fault.
     *
     * @return Whether the error happened due to the sender's fault.
     */
    public Boolean isSenderFault() {
        return senderFault;
    }
    
    /**
     * Whether the error happened due to the sender's fault.
     *
     * @param senderFault Whether the error happened due to the sender's fault.
     */
    public void setSenderFault(Boolean senderFault) {
        this.senderFault = senderFault;
    }
    
    /**
     * Whether the error happened due to the sender's fault.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param senderFault Whether the error happened due to the sender's fault.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public BatchResultErrorEntry withSenderFault(Boolean senderFault) {
        this.senderFault = senderFault;
        return this;
    }
    
    
    /**
     * Whether the error happened due to the sender's fault.
     *
     * @return Whether the error happened due to the sender's fault.
     */
    public Boolean getSenderFault() {
        return senderFault;
    }
    
    /**
     * An error code representing why the operation failed on this entry.
     *
     * @return An error code representing why the operation failed on this entry.
     */
    public String getCode() {
        return code;
    }
    
    /**
     * An error code representing why the operation failed on this entry.
     *
     * @param code An error code representing why the operation failed on this entry.
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * An error code representing why the operation failed on this entry.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param code An error code representing why the operation failed on this entry.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public BatchResultErrorEntry withCode(String code) {
        this.code = code;
        return this;
    }
    
    
    /**
     * A message explaining why the operation failed on this entry.
     *
     * @return A message explaining why the operation failed on this entry.
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * A message explaining why the operation failed on this entry.
     *
     * @param message A message explaining why the operation failed on this entry.
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * A message explaining why the operation failed on this entry.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param message A message explaining why the operation failed on this entry.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public BatchResultErrorEntry withMessage(String message) {
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
        sb.append("Id: " + id + ", ");
        sb.append("SenderFault: " + senderFault + ", ");
        sb.append("Code: " + code + ", ");
        sb.append("Message: " + message + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    