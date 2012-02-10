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
 * Represents the destination of the message, consisting of To:, CC:, and
 * BCC: fields.
 * </p>
 */
public class Destination {

    /**
     * The To: field(s) of the message.
     */
    private java.util.List<String> toAddresses;

    /**
     * The CC: field(s) of the message.
     */
    private java.util.List<String> ccAddresses;

    /**
     * The BCC: field(s) of the message.
     */
    private java.util.List<String> bccAddresses;

    /**
     * Default constructor for a new Destination object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public Destination() {}
    
    /**
     * Constructs a new Destination object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param toAddresses The To: field(s) of the message.
     */
    public Destination(java.util.List<String> toAddresses) {
        this.toAddresses = toAddresses;
    }
    
    /**
     * The To: field(s) of the message.
     *
     * @return The To: field(s) of the message.
     */
    public java.util.List<String> getToAddresses() {
        
        if (toAddresses == null) {
            toAddresses = new java.util.ArrayList<String>();
        }
        return toAddresses;
    }
    
    /**
     * The To: field(s) of the message.
     *
     * @param toAddresses The To: field(s) of the message.
     */
    public void setToAddresses(java.util.Collection<String> toAddresses) {
        java.util.List<String> toAddressesCopy = new java.util.ArrayList<String>();
        if (toAddresses != null) {
            toAddressesCopy.addAll(toAddresses);
        }
        this.toAddresses = toAddressesCopy;
    }
    
    /**
     * The To: field(s) of the message.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param toAddresses The To: field(s) of the message.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Destination withToAddresses(String... toAddresses) {
        if (getToAddresses() == null) setToAddresses(new java.util.ArrayList<String>());
        for (String value : toAddresses) {
            getToAddresses().add(value);
        }
        return this;
    }
    
    /**
     * The To: field(s) of the message.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param toAddresses The To: field(s) of the message.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Destination withToAddresses(java.util.Collection<String> toAddresses) {
        java.util.List<String> toAddressesCopy = new java.util.ArrayList<String>();
        if (toAddresses != null) {
            toAddressesCopy.addAll(toAddresses);
        }
        this.toAddresses = toAddressesCopy;

        return this;
    }
    
    /**
     * The CC: field(s) of the message.
     *
     * @return The CC: field(s) of the message.
     */
    public java.util.List<String> getCcAddresses() {
        
        if (ccAddresses == null) {
            ccAddresses = new java.util.ArrayList<String>();
        }
        return ccAddresses;
    }
    
    /**
     * The CC: field(s) of the message.
     *
     * @param ccAddresses The CC: field(s) of the message.
     */
    public void setCcAddresses(java.util.Collection<String> ccAddresses) {
        java.util.List<String> ccAddressesCopy = new java.util.ArrayList<String>();
        if (ccAddresses != null) {
            ccAddressesCopy.addAll(ccAddresses);
        }
        this.ccAddresses = ccAddressesCopy;
    }
    
    /**
     * The CC: field(s) of the message.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param ccAddresses The CC: field(s) of the message.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Destination withCcAddresses(String... ccAddresses) {
        if (getCcAddresses() == null) setCcAddresses(new java.util.ArrayList<String>());
        for (String value : ccAddresses) {
            getCcAddresses().add(value);
        }
        return this;
    }
    
    /**
     * The CC: field(s) of the message.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param ccAddresses The CC: field(s) of the message.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Destination withCcAddresses(java.util.Collection<String> ccAddresses) {
        java.util.List<String> ccAddressesCopy = new java.util.ArrayList<String>();
        if (ccAddresses != null) {
            ccAddressesCopy.addAll(ccAddresses);
        }
        this.ccAddresses = ccAddressesCopy;

        return this;
    }
    
    /**
     * The BCC: field(s) of the message.
     *
     * @return The BCC: field(s) of the message.
     */
    public java.util.List<String> getBccAddresses() {
        
        if (bccAddresses == null) {
            bccAddresses = new java.util.ArrayList<String>();
        }
        return bccAddresses;
    }
    
    /**
     * The BCC: field(s) of the message.
     *
     * @param bccAddresses The BCC: field(s) of the message.
     */
    public void setBccAddresses(java.util.Collection<String> bccAddresses) {
        java.util.List<String> bccAddressesCopy = new java.util.ArrayList<String>();
        if (bccAddresses != null) {
            bccAddressesCopy.addAll(bccAddresses);
        }
        this.bccAddresses = bccAddressesCopy;
    }
    
    /**
     * The BCC: field(s) of the message.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param bccAddresses The BCC: field(s) of the message.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Destination withBccAddresses(String... bccAddresses) {
        if (getBccAddresses() == null) setBccAddresses(new java.util.ArrayList<String>());
        for (String value : bccAddresses) {
            getBccAddresses().add(value);
        }
        return this;
    }
    
    /**
     * The BCC: field(s) of the message.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param bccAddresses The BCC: field(s) of the message.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Destination withBccAddresses(java.util.Collection<String> bccAddresses) {
        java.util.List<String> bccAddressesCopy = new java.util.ArrayList<String>();
        if (bccAddresses != null) {
            bccAddressesCopy.addAll(bccAddresses);
        }
        this.bccAddresses = bccAddressesCopy;

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
        sb.append("ToAddresses: " + toAddresses + ", ");
        sb.append("CcAddresses: " + ccAddresses + ", ");
        sb.append("BccAddresses: " + bccAddresses + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    