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
 * Associate Address Result
 */
public class AssociateAddressResult {

    private String associationId;

    /**
     * Returns the value of the AssociationId property for this object.
     *
     * @return The value of the AssociationId property for this object.
     */
    public String getAssociationId() {
        return associationId;
    }
    
    /**
     * Sets the value of the AssociationId property for this object.
     *
     * @param associationId The new value for the AssociationId property for this object.
     */
    public void setAssociationId(String associationId) {
        this.associationId = associationId;
    }
    
    /**
     * Sets the value of the AssociationId property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param associationId The new value for the AssociationId property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public AssociateAddressResult withAssociationId(String associationId) {
        this.associationId = associationId;
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
        sb.append("AssociationId: " + associationId + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    