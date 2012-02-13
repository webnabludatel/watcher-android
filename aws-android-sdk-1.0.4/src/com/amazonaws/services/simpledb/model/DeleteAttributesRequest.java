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
 * Container for the parameters to the {@link com.amazonaws.services.simpledb.AmazonSimpleDB#deleteAttributes(DeleteAttributesRequest) DeleteAttributes operation}.
 * <p>
 * Deletes one or more attributes associated with an item. If all
 * attributes of the item are deleted, the item is deleted.
 * </p>
 * <p>
 * <b>NOTE:</b> If DeleteAttributes is called without being passed any
 * attributes or values specified, all the attributes for the item are
 * deleted.
 * </p>
 * <p>
 * <code>DeleteAttributes</code> is an idempotent operation; running it
 * multiple times on the same item or attribute does not result in an
 * error response.
 * </p>
 * <p>
 * Because Amazon SimpleDB makes multiple copies of item data and uses
 * an eventual consistency update model, performing a GetAttributes or
 * Select operation (read) immediately after a
 * <code>DeleteAttributes</code> or PutAttributes operation (write) might
 * not return updated item data.
 * </p>
 *
 * @see com.amazonaws.services.simpledb.AmazonSimpleDB#deleteAttributes(DeleteAttributesRequest)
 */
public class DeleteAttributesRequest extends AmazonWebServiceRequest {

    /**
     * The name of the domain in which to perform the operation.
     */
    private String domainName;

    /**
     * The name of the item. Similar to rows on a spreadsheet, items
     * represent individual objects that contain one or more value-attribute
     * pairs.
     */
    private String itemName;

    /**
     * A list of Attributes. Similar to columns on a spreadsheet, attributes
     * represent categories of data that can be assigned to items.
     */
    private java.util.List<Attribute> attributes;

    /**
     * The update condition which, if specified, determines whether the
     * specified attributes will be deleted or not. The update condition must
     * be satisfied in order for this request to be processed and the
     * attributes to be deleted.
     */
    private UpdateCondition expected;

    /**
     * Default constructor for a new DeleteAttributesRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public DeleteAttributesRequest() {}
    
    /**
     * Constructs a new DeleteAttributesRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param domainName The name of the domain in which to perform the
     * operation.
     * @param itemName The name of the item. Similar to rows on a
     * spreadsheet, items represent individual objects that contain one or
     * more value-attribute pairs.
     */
    public DeleteAttributesRequest(String domainName, String itemName) {
        this.domainName = domainName;
        this.itemName = itemName;
    }
    
    /**
     * Constructs a new DeleteAttributesRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param domainName The name of the domain in which to perform the
     * operation.
     * @param itemName The name of the item. Similar to rows on a
     * spreadsheet, items represent individual objects that contain one or
     * more value-attribute pairs.
     * @param attributes A list of Attributes. Similar to columns on a
     * spreadsheet, attributes represent categories of data that can be
     * assigned to items.
     */
    public DeleteAttributesRequest(String domainName, String itemName, java.util.List<Attribute> attributes) {
        this.domainName = domainName;
        this.itemName = itemName;
        this.attributes = attributes;
    }
    
    /**
     * Constructs a new DeleteAttributesRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param domainName The name of the domain in which to perform the
     * operation.
     * @param itemName The name of the item. Similar to rows on a
     * spreadsheet, items represent individual objects that contain one or
     * more value-attribute pairs.
     * @param attributes A list of Attributes. Similar to columns on a
     * spreadsheet, attributes represent categories of data that can be
     * assigned to items.
     * @param expected The update condition which, if specified, determines
     * whether the specified attributes will be deleted or not. The update
     * condition must be satisfied in order for this request to be processed
     * and the attributes to be deleted.
     */
    public DeleteAttributesRequest(String domainName, String itemName, java.util.List<Attribute> attributes, UpdateCondition expected) {
        this.domainName = domainName;
        this.itemName = itemName;
        this.attributes = attributes;
        this.expected = expected;
    }
    
    /**
     * The name of the domain in which to perform the operation.
     *
     * @return The name of the domain in which to perform the operation.
     */
    public String getDomainName() {
        return domainName;
    }
    
    /**
     * The name of the domain in which to perform the operation.
     *
     * @param domainName The name of the domain in which to perform the operation.
     */
    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
    
    /**
     * The name of the domain in which to perform the operation.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param domainName The name of the domain in which to perform the operation.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DeleteAttributesRequest withDomainName(String domainName) {
        this.domainName = domainName;
        return this;
    }
    
    
    /**
     * The name of the item. Similar to rows on a spreadsheet, items
     * represent individual objects that contain one or more value-attribute
     * pairs.
     *
     * @return The name of the item. Similar to rows on a spreadsheet, items
     *         represent individual objects that contain one or more value-attribute
     *         pairs.
     */
    public String getItemName() {
        return itemName;
    }
    
    /**
     * The name of the item. Similar to rows on a spreadsheet, items
     * represent individual objects that contain one or more value-attribute
     * pairs.
     *
     * @param itemName The name of the item. Similar to rows on a spreadsheet, items
     *         represent individual objects that contain one or more value-attribute
     *         pairs.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    /**
     * The name of the item. Similar to rows on a spreadsheet, items
     * represent individual objects that contain one or more value-attribute
     * pairs.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param itemName The name of the item. Similar to rows on a spreadsheet, items
     *         represent individual objects that contain one or more value-attribute
     *         pairs.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DeleteAttributesRequest withItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }
    
    
    /**
     * A list of Attributes. Similar to columns on a spreadsheet, attributes
     * represent categories of data that can be assigned to items.
     *
     * @return A list of Attributes. Similar to columns on a spreadsheet, attributes
     *         represent categories of data that can be assigned to items.
     */
    public java.util.List<Attribute> getAttributes() {
        
        if (attributes == null) {
            attributes = new java.util.ArrayList<Attribute>();
        }
        return attributes;
    }
    
    /**
     * A list of Attributes. Similar to columns on a spreadsheet, attributes
     * represent categories of data that can be assigned to items.
     *
     * @param attributes A list of Attributes. Similar to columns on a spreadsheet, attributes
     *         represent categories of data that can be assigned to items.
     */
    public void setAttributes(java.util.Collection<Attribute> attributes) {
        java.util.List<Attribute> attributesCopy = new java.util.ArrayList<Attribute>();
        if (attributes != null) {
            attributesCopy.addAll(attributes);
        }
        this.attributes = attributesCopy;
    }
    
    /**
     * A list of Attributes. Similar to columns on a spreadsheet, attributes
     * represent categories of data that can be assigned to items.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param attributes A list of Attributes. Similar to columns on a spreadsheet, attributes
     *         represent categories of data that can be assigned to items.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DeleteAttributesRequest withAttributes(Attribute... attributes) {
        if (getAttributes() == null) setAttributes(new java.util.ArrayList<Attribute>());
        for (Attribute value : attributes) {
            getAttributes().add(value);
        }
        return this;
    }
    
    /**
     * A list of Attributes. Similar to columns on a spreadsheet, attributes
     * represent categories of data that can be assigned to items.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param attributes A list of Attributes. Similar to columns on a spreadsheet, attributes
     *         represent categories of data that can be assigned to items.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DeleteAttributesRequest withAttributes(java.util.Collection<Attribute> attributes) {
        java.util.List<Attribute> attributesCopy = new java.util.ArrayList<Attribute>();
        if (attributes != null) {
            attributesCopy.addAll(attributes);
        }
        this.attributes = attributesCopy;

        return this;
    }
    
    /**
     * The update condition which, if specified, determines whether the
     * specified attributes will be deleted or not. The update condition must
     * be satisfied in order for this request to be processed and the
     * attributes to be deleted.
     *
     * @return The update condition which, if specified, determines whether the
     *         specified attributes will be deleted or not. The update condition must
     *         be satisfied in order for this request to be processed and the
     *         attributes to be deleted.
     */
    public UpdateCondition getExpected() {
        return expected;
    }
    
    /**
     * The update condition which, if specified, determines whether the
     * specified attributes will be deleted or not. The update condition must
     * be satisfied in order for this request to be processed and the
     * attributes to be deleted.
     *
     * @param expected The update condition which, if specified, determines whether the
     *         specified attributes will be deleted or not. The update condition must
     *         be satisfied in order for this request to be processed and the
     *         attributes to be deleted.
     */
    public void setExpected(UpdateCondition expected) {
        this.expected = expected;
    }
    
    /**
     * The update condition which, if specified, determines whether the
     * specified attributes will be deleted or not. The update condition must
     * be satisfied in order for this request to be processed and the
     * attributes to be deleted.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param expected The update condition which, if specified, determines whether the
     *         specified attributes will be deleted or not. The update condition must
     *         be satisfied in order for this request to be processed and the
     *         attributes to be deleted.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DeleteAttributesRequest withExpected(UpdateCondition expected) {
        this.expected = expected;
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
        sb.append("ItemName: " + itemName + ", ");
        sb.append("Attributes: " + attributes + ", ");
        sb.append("Expected: " + expected + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    