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
 * Represents a placement group into which multiple Amazon EC2 instances
 * can be launched. A placement group ensures that Amazon EC2 instances
 * are physically located close enough to support HPC features, such as
 * higher IO network connections between instances in the group.
 * </p>
 */
public class PlacementGroup {

    /**
     * The name of this <code>PlacementGroup</code>.
     */
    private String groupName;

    /**
     * The strategy to use when allocating Amazon EC2 instances for the
     * <code>PlacementGroup</code>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>cluster
     */
    private String strategy;

    /**
     * The state of this <code>PlacementGroup</code>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>pending, available, deleting, deleted
     */
    private String state;

    /**
     * Default constructor for a new PlacementGroup object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public PlacementGroup() {}
    
    /**
     * Constructs a new PlacementGroup object.
     * Callers should use the setter or fluent setter (with...) methods to
     * initialize any additional object members.
     * 
     * @param groupName The name of this <code>PlacementGroup</code>.
     */
    public PlacementGroup(String groupName) {
        this.groupName = groupName;
    }
    
    /**
     * The name of this <code>PlacementGroup</code>.
     *
     * @return The name of this <code>PlacementGroup</code>.
     */
    public String getGroupName() {
        return groupName;
    }
    
    /**
     * The name of this <code>PlacementGroup</code>.
     *
     * @param groupName The name of this <code>PlacementGroup</code>.
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
    /**
     * The name of this <code>PlacementGroup</code>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param groupName The name of this <code>PlacementGroup</code>.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public PlacementGroup withGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }
    
    
    /**
     * The strategy to use when allocating Amazon EC2 instances for the
     * <code>PlacementGroup</code>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>cluster
     *
     * @return The strategy to use when allocating Amazon EC2 instances for the
     *         <code>PlacementGroup</code>.
     *
     * @see PlacementStrategy
     */
    public String getStrategy() {
        return strategy;
    }
    
    /**
     * The strategy to use when allocating Amazon EC2 instances for the
     * <code>PlacementGroup</code>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>cluster
     *
     * @param strategy The strategy to use when allocating Amazon EC2 instances for the
     *         <code>PlacementGroup</code>.
     *
     * @see PlacementStrategy
     */
    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
    
    /**
     * The strategy to use when allocating Amazon EC2 instances for the
     * <code>PlacementGroup</code>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>cluster
     *
     * @param strategy The strategy to use when allocating Amazon EC2 instances for the
     *         <code>PlacementGroup</code>.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     *
     * @see PlacementStrategy
     */
    public PlacementGroup withStrategy(String strategy) {
        this.strategy = strategy;
        return this;
    }
    
    
    /**
     * The state of this <code>PlacementGroup</code>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>pending, available, deleting, deleted
     *
     * @return The state of this <code>PlacementGroup</code>.
     *
     * @see PlacementGroupState
     */
    public String getState() {
        return state;
    }
    
    /**
     * The state of this <code>PlacementGroup</code>.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>pending, available, deleting, deleted
     *
     * @param state The state of this <code>PlacementGroup</code>.
     *
     * @see PlacementGroupState
     */
    public void setState(String state) {
        this.state = state;
    }
    
    /**
     * The state of this <code>PlacementGroup</code>.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>pending, available, deleting, deleted
     *
     * @param state The state of this <code>PlacementGroup</code>.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     *
     * @see PlacementGroupState
     */
    public PlacementGroup withState(String state) {
        this.state = state;
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
        sb.append("GroupName: " + groupName + ", ");
        sb.append("Strategy: " + strategy + ", ");
        sb.append("State: " + state + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    