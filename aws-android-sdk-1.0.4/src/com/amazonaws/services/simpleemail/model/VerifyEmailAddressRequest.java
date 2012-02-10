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
import com.amazonaws.AmazonWebServiceRequest;

/**
 * Container for the parameters to the {@link com.amazonaws.services.simpleemail.AmazonSimpleEmailService#verifyEmailAddress(VerifyEmailAddressRequest) VerifyEmailAddress operation}.
 * <p>
 * Verifies an email address. This action causes a confirmation email
 * message to be sent to the specified address.
 * </p>
 *
 * @see com.amazonaws.services.simpleemail.AmazonSimpleEmailService#verifyEmailAddress(VerifyEmailAddressRequest)
 */
public class VerifyEmailAddressRequest extends AmazonWebServiceRequest {

    /**
     * The email address to be verified.
     */
    private String emailAddress;

    /**
     * The email address to be verified.
     *
     * @return The email address to be verified.
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    
    /**
     * The email address to be verified.
     *
     * @param emailAddress The email address to be verified.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    /**
     * The email address to be verified.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param emailAddress The email address to be verified.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public VerifyEmailAddressRequest withEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
        sb.append("EmailAddress: " + emailAddress + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    