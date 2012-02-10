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
import com.amazonaws.AmazonWebServiceRequest;

/**
 * Container for the parameters to the {@link com.amazonaws.services.simpledb.AmazonSimpleDB#deleteDomain(DeleteDomainRequest) DeleteDomain operation}.
 * <p>
 * The <code>DeleteDomain</code> operation deletes a domain. Any items
 * (and their attributes) in the domain are deleted as well. The
 * <code>DeleteDomain</code> operation might take 10 or more seconds to
 * complete.
 * </p>
 * <p>
 * <b>NOTE:</b> Running DeleteDomain on a domain that does not exist or
 * running the function multiple times using the same domain name will
 * not result in an error response.
 * </p>
 *
 * @see com.amazonaws.services.simpledb.AmazonSimpleDB#deleteDomain(DeleteDomainRequest)
 */
public class DeleteDomainRequest extends AmazonWebServiceRequest {

    /**
     * The name of the domain to delete.
     */
    private String domainName;

    /**
     * Default constructor for a new DeleteDomainRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public DeleteDomainRequest() {}
    
    /**
     * Constructs a new DeleteDomainRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param domainName The name of the domain to delete.
     */
    public DeleteDomainRequest(String domainName) {
        this.domainName = domainName;
    }
    
    /**
     * The name of the domain to delete.
     *
     * @return The name of the domain to delete.
     */
    public String getDomainName() {
        return domainName;
    }
    
    /**
     * The name of the domain to delete.
     *
     * @param domainName The name of the domain to delete.
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
    
    /**
     * The name of the domain to delete.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param domainName The name of the domain to delete.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DeleteDomainRequest withDomainName(String domainName) {
        this.domainName = domainName;
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
        sb.append("DomainName: " + domainName + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    