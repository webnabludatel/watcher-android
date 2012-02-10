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
 * Represents sending statistics data. Each <code>SendDataPoint</code>
 * contains statistics for a 15-minute period of sending activity.
 * </p>
 */
public class SendDataPoint {

    /**
     * Time of the data point.
     */
    private java.util.Date timestamp;

    /**
     * Number of emails that have been enqueued for sending.
     */
    private Long deliveryAttempts;

    /**
     * Number of emails that have bounced.
     */
    private Long bounces;

    /**
     * Number of unwanted emails that were rejected by recipients.
     */
    private Long complaints;

    /**
     * Number of emails rejected by Amazon SES.
     */
    private Long rejects;

    /**
     * Time of the data point.
     *
     * @return Time of the data point.
     */
    public java.util.Date getTimestamp() {
        return timestamp;
    }
    
    /**
     * Time of the data point.
     *
     * @param timestamp Time of the data point.
     */
    public void setTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
    }
    
    /**
     * Time of the data point.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param timestamp Time of the data point.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SendDataPoint withTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }
    
    
    /**
     * Number of emails that have been enqueued for sending.
     *
     * @return Number of emails that have been enqueued for sending.
     */
    public Long getDeliveryAttempts() {
        return deliveryAttempts;
    }
    
    /**
     * Number of emails that have been enqueued for sending.
     *
     * @param deliveryAttempts Number of emails that have been enqueued for sending.
     */
    public void setDeliveryAttempts(Long deliveryAttempts) {
        this.deliveryAttempts = deliveryAttempts;
    }
    
    /**
     * Number of emails that have been enqueued for sending.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param deliveryAttempts Number of emails that have been enqueued for sending.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SendDataPoint withDeliveryAttempts(Long deliveryAttempts) {
        this.deliveryAttempts = deliveryAttempts;
        return this;
    }
    
    
    /**
     * Number of emails that have bounced.
     *
     * @return Number of emails that have bounced.
     */
    public Long getBounces() {
        return bounces;
    }
    
    /**
     * Number of emails that have bounced.
     *
     * @param bounces Number of emails that have bounced.
     */
    public void setBounces(Long bounces) {
        this.bounces = bounces;
    }
    
    /**
     * Number of emails that have bounced.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param bounces Number of emails that have bounced.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SendDataPoint withBounces(Long bounces) {
        this.bounces = bounces;
        return this;
    }
    
    
    /**
     * Number of unwanted emails that were rejected by recipients.
     *
     * @return Number of unwanted emails that were rejected by recipients.
     */
    public Long getComplaints() {
        return complaints;
    }
    
    /**
     * Number of unwanted emails that were rejected by recipients.
     *
     * @param complaints Number of unwanted emails that were rejected by recipients.
     */
    public void setComplaints(Long complaints) {
        this.complaints = complaints;
    }
    
    /**
     * Number of unwanted emails that were rejected by recipients.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param complaints Number of unwanted emails that were rejected by recipients.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SendDataPoint withComplaints(Long complaints) {
        this.complaints = complaints;
        return this;
    }
    
    
    /**
     * Number of emails rejected by Amazon SES.
     *
     * @return Number of emails rejected by Amazon SES.
     */
    public Long getRejects() {
        return rejects;
    }
    
    /**
     * Number of emails rejected by Amazon SES.
     *
     * @param rejects Number of emails rejected by Amazon SES.
     */
    public void setRejects(Long rejects) {
        this.rejects = rejects;
    }
    
    /**
     * Number of emails rejected by Amazon SES.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param rejects Number of emails rejected by Amazon SES.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SendDataPoint withRejects(Long rejects) {
        this.rejects = rejects;
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
        sb.append("Timestamp: " + timestamp + ", ");
        sb.append("DeliveryAttempts: " + deliveryAttempts + ", ");
        sb.append("Bounces: " + bounces + ", ");
        sb.append("Complaints: " + complaints + ", ");
        sb.append("Rejects: " + rejects + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    