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
import com.amazonaws.AmazonWebServiceRequest;

/**
 * Container for the parameters to the {@link com.amazonaws.services.ec2.AmazonEC2#describeImageAttribute(DescribeImageAttributeRequest) DescribeImageAttribute operation}.
 * <p>
 * The DescribeImageAttribute operation returns information about an
 * attribute of an AMI. Only one attribute can be specified per call.
 * </p>
 *
 * @see com.amazonaws.services.ec2.AmazonEC2#describeImageAttribute(DescribeImageAttributeRequest)
 */
public class DescribeImageAttributeRequest extends AmazonWebServiceRequest {

    /**
     * The ID of the AMI whose attribute is to be described.
     */
    private String imageId;

    /**
     * The name of the attribute to describe. <p> Available attribute names:
     * <code>productCodes</code>, <code>kernel</code>, <code>ramdisk</code>,
     * <code>launchPermisson</code>, <code>blockDeviceMapping</code>
     */
    private String attribute;

    /**
     * Default constructor for a new DescribeImageAttributeRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public DescribeImageAttributeRequest() {}
    
    /**
     * Constructs a new DescribeImageAttributeRequest object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param imageId The ID of the AMI whose attribute is to be described.
     * @param attribute The name of the attribute to describe. <p> Available
     * attribute names: <code>productCodes</code>, <code>kernel</code>,
     * <code>ramdisk</code>, <code>launchPermisson</code>,
     * <code>blockDeviceMapping</code>
     */
    public DescribeImageAttributeRequest(String imageId, String attribute) {
        this.imageId = imageId;
        this.attribute = attribute;
    }
    
    /**
     * The ID of the AMI whose attribute is to be described.
     *
     * @return The ID of the AMI whose attribute is to be described.
     */
    public String getImageId() {
        return imageId;
    }
    
    /**
     * The ID of the AMI whose attribute is to be described.
     *
     * @param imageId The ID of the AMI whose attribute is to be described.
     */
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    
    /**
     * The ID of the AMI whose attribute is to be described.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param imageId The ID of the AMI whose attribute is to be described.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeImageAttributeRequest withImageId(String imageId) {
        this.imageId = imageId;
        return this;
    }
    
    
    /**
     * The name of the attribute to describe. <p> Available attribute names:
     * <code>productCodes</code>, <code>kernel</code>, <code>ramdisk</code>,
     * <code>launchPermisson</code>, <code>blockDeviceMapping</code>
     *
     * @return The name of the attribute to describe. <p> Available attribute names:
     *         <code>productCodes</code>, <code>kernel</code>, <code>ramdisk</code>,
     *         <code>launchPermisson</code>, <code>blockDeviceMapping</code>
     */
    public String getAttribute() {
        return attribute;
    }
    
    /**
     * The name of the attribute to describe. <p> Available attribute names:
     * <code>productCodes</code>, <code>kernel</code>, <code>ramdisk</code>,
     * <code>launchPermisson</code>, <code>blockDeviceMapping</code>
     *
     * @param attribute The name of the attribute to describe. <p> Available attribute names:
     *         <code>productCodes</code>, <code>kernel</code>, <code>ramdisk</code>,
     *         <code>launchPermisson</code>, <code>blockDeviceMapping</code>
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
    
    /**
     * The name of the attribute to describe. <p> Available attribute names:
     * <code>productCodes</code>, <code>kernel</code>, <code>ramdisk</code>,
     * <code>launchPermisson</code>, <code>blockDeviceMapping</code>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param attribute The name of the attribute to describe. <p> Available attribute names:
     *         <code>productCodes</code>, <code>kernel</code>, <code>ramdisk</code>,
     *         <code>launchPermisson</code>, <code>blockDeviceMapping</code>
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeImageAttributeRequest withAttribute(String attribute) {
        this.attribute = attribute;
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
        sb.append("ImageId: " + imageId + ", ");
        sb.append("Attribute: " + attribute + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    