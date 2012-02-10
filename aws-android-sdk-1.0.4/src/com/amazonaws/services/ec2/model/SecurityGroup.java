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
 * An Amazon EC2 security group, describing how EC2 instances in this
 * group can receive network traffic.
 * </p>
 */
public class SecurityGroup {

    /**
     * The AWS Access Key ID of the owner of the security group.
     */
    private String ownerId;

    /**
     * The name of this security group.
     */
    private String groupName;

    private String groupId;

    /**
     * The description of this security group.
     */
    private String description;

    /**
     * The permissions enabled for this security group.
     */
    private java.util.List<IpPermission> ipPermissions;

    private java.util.List<IpPermission> ipPermissionsEgress;

    private String vpcId;

    private java.util.List<Tag> tags;

    /**
     * The AWS Access Key ID of the owner of the security group.
     *
     * @return The AWS Access Key ID of the owner of the security group.
     */
    public String getOwnerId() {
        return ownerId;
    }
    
    /**
     * The AWS Access Key ID of the owner of the security group.
     *
     * @param ownerId The AWS Access Key ID of the owner of the security group.
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    
    /**
     * The AWS Access Key ID of the owner of the security group.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param ownerId The AWS Access Key ID of the owner of the security group.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SecurityGroup withOwnerId(String ownerId) {
        this.ownerId = ownerId;
        return this;
    }
    
    
    /**
     * The name of this security group.
     *
     * @return The name of this security group.
     */
    public String getGroupName() {
        return groupName;
    }
    
    /**
     * The name of this security group.
     *
     * @param groupName The name of this security group.
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    /**
     * The name of this security group.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param groupName The name of this security group.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SecurityGroup withGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }
    
    
    /**
     * Returns the value of the GroupId property for this object.
     *
     * @return The value of the GroupId property for this object.
     */
    public String getGroupId() {
        return groupId;
    }
    
    /**
     * Sets the value of the GroupId property for this object.
     *
     * @param groupId The new value for the GroupId property for this object.
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    
    /**
     * Sets the value of the GroupId property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param groupId The new value for the GroupId property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SecurityGroup withGroupId(String groupId) {
        this.groupId = groupId;
        return this;
    }
    
    
    /**
     * The description of this security group.
     *
     * @return The description of this security group.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * The description of this security group.
     *
     * @param description The description of this security group.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * The description of this security group.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param description The description of this security group.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SecurityGroup withDescription(String description) {
        this.description = description;
        return this;
    }
    
    
    /**
     * The permissions enabled for this security group.
     *
     * @return The permissions enabled for this security group.
     */
    public java.util.List<IpPermission> getIpPermissions() {
        
        if (ipPermissions == null) {
            ipPermissions = new java.util.ArrayList<IpPermission>();
        }
        return ipPermissions;
    }
    
    /**
     * The permissions enabled for this security group.
     *
     * @param ipPermissions The permissions enabled for this security group.
     */
    public void setIpPermissions(java.util.Collection<IpPermission> ipPermissions) {
        java.util.List<IpPermission> ipPermissionsCopy = new java.util.ArrayList<IpPermission>();
        if (ipPermissions != null) {
            ipPermissionsCopy.addAll(ipPermissions);
        }
        this.ipPermissions = ipPermissionsCopy;
    }
    
    /**
     * The permissions enabled for this security group.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param ipPermissions The permissions enabled for this security group.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SecurityGroup withIpPermissions(IpPermission... ipPermissions) {
        if (getIpPermissions() == null) setIpPermissions(new java.util.ArrayList<IpPermission>());
        for (IpPermission value : ipPermissions) {
            getIpPermissions().add(value);
        }
        return this;
    }
    
    /**
     * The permissions enabled for this security group.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param ipPermissions The permissions enabled for this security group.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SecurityGroup withIpPermissions(java.util.Collection<IpPermission> ipPermissions) {
        java.util.List<IpPermission> ipPermissionsCopy = new java.util.ArrayList<IpPermission>();
        if (ipPermissions != null) {
            ipPermissionsCopy.addAll(ipPermissions);
        }
        this.ipPermissions = ipPermissionsCopy;

        return this;
    }
    
    /**
     * Returns the value of the IpPermissionsEgress property for this object.
     *
     * @return The value of the IpPermissionsEgress property for this object.
     */
    public java.util.List<IpPermission> getIpPermissionsEgress() {
        
        if (ipPermissionsEgress == null) {
            ipPermissionsEgress = new java.util.ArrayList<IpPermission>();
        }
        return ipPermissionsEgress;
    }
    
    /**
     * Sets the value of the IpPermissionsEgress property for this object.
     *
     * @param ipPermissionsEgress The new value for the IpPermissionsEgress property for this object.
     */
    public void setIpPermissionsEgress(java.util.Collection<IpPermission> ipPermissionsEgress) {
        java.util.List<IpPermission> ipPermissionsEgressCopy = new java.util.ArrayList<IpPermission>();
        if (ipPermissionsEgress != null) {
            ipPermissionsEgressCopy.addAll(ipPermissionsEgress);
        }
        this.ipPermissionsEgress = ipPermissionsEgressCopy;
    }
    
    /**
     * Sets the value of the IpPermissionsEgress property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param ipPermissionsEgress The new value for the IpPermissionsEgress property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SecurityGroup withIpPermissionsEgress(IpPermission... ipPermissionsEgress) {
        if (getIpPermissionsEgress() == null) setIpPermissionsEgress(new java.util.ArrayList<IpPermission>());
        for (IpPermission value : ipPermissionsEgress) {
            getIpPermissionsEgress().add(value);
        }
        return this;
    }
    
    /**
     * Sets the value of the IpPermissionsEgress property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param ipPermissionsEgress The new value for the IpPermissionsEgress property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SecurityGroup withIpPermissionsEgress(java.util.Collection<IpPermission> ipPermissionsEgress) {
        java.util.List<IpPermission> ipPermissionsEgressCopy = new java.util.ArrayList<IpPermission>();
        if (ipPermissionsEgress != null) {
            ipPermissionsEgressCopy.addAll(ipPermissionsEgress);
        }
        this.ipPermissionsEgress = ipPermissionsEgressCopy;

        return this;
    }
    
    /**
     * Returns the value of the VpcId property for this object.
     *
     * @return The value of the VpcId property for this object.
     */
    public String getVpcId() {
        return vpcId;
    }
    
    /**
     * Sets the value of the VpcId property for this object.
     *
     * @param vpcId The new value for the VpcId property for this object.
     */
    public void setVpcId(String vpcId) {
        this.vpcId = vpcId;
    }
    
    /**
     * Sets the value of the VpcId property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param vpcId The new value for the VpcId property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SecurityGroup withVpcId(String vpcId) {
        this.vpcId = vpcId;
        return this;
    }
    
    
    /**
     * Returns the value of the Tags property for this object.
     *
     * @return The value of the Tags property for this object.
     */
    public java.util.List<Tag> getTags() {
        
        if (tags == null) {
            tags = new java.util.ArrayList<Tag>();
        }
        return tags;
    }
    
    /**
     * Sets the value of the Tags property for this object.
     *
     * @param tags The new value for the Tags property for this object.
     */
    public void setTags(java.util.Collection<Tag> tags) {
        java.util.List<Tag> tagsCopy = new java.util.ArrayList<Tag>();
        if (tags != null) {
            tagsCopy.addAll(tags);
        }
        this.tags = tagsCopy;
    }
    
    /**
     * Sets the value of the Tags property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param tags The new value for the Tags property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SecurityGroup withTags(Tag... tags) {
        if (getTags() == null) setTags(new java.util.ArrayList<Tag>());
        for (Tag value : tags) {
            getTags().add(value);
        }
        return this;
    }
    
    /**
     * Sets the value of the Tags property for this object.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param tags The new value for the Tags property for this object.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SecurityGroup withTags(java.util.Collection<Tag> tags) {
        java.util.List<Tag> tagsCopy = new java.util.ArrayList<Tag>();
        if (tags != null) {
            tagsCopy.addAll(tags);
        }
        this.tags = tagsCopy;

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
        sb.append("OwnerId: " + ownerId + ", ");
        sb.append("GroupName: " + groupName + ", ");
        sb.append("GroupId: " + groupId + ", ");
        sb.append("Description: " + description + ", ");
        sb.append("IpPermissions: " + ipPermissions + ", ");
        sb.append("IpPermissionsEgress: " + ipPermissionsEgress + ", ");
        sb.append("VpcId: " + vpcId + ", ");
        sb.append("Tags: " + tags + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    