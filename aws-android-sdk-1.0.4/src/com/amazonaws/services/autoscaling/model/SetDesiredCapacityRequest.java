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
package com.amazonaws.services.autoscaling.model;
import com.amazonaws.AmazonWebServiceRequest;

/**
 * Container for the parameters to the {@link com.amazonaws.services.autoscaling.AmazonAutoScaling#setDesiredCapacity(SetDesiredCapacityRequest) SetDesiredCapacity operation}.
 * <p>
 * Adjusts the desired size of the AutoScalingGroup by initiating
 * scaling activities. When reducing the size of the group, it is not
 * possible to define which EC2 instances will be terminated. This
 * applies to any auto-scaling decisions that might result in terminating
 * instances.
 * </p>
 * <p>
 * There are two common use cases for <code>SetDesiredCapacity</code> :
 * one for users of the Auto Scaling triggering system, and
 * another for developers who write their own triggering systems. Both
 * use cases relate to the concept of cooldown.
 * </p>
 * <p>
 * In the first case, if you use the Auto Scaling triggering system,
 * <code>SetDesiredCapacity</code> changes the size of your Auto Scaling
 * group without regard to the cooldown period. This could be useful, for
 * example, if Auto Scaling did something unexpected for some reason. If
 * your cooldown period is 10 minutes, Auto Scaling would normally reject
 * requests to change the size of the group for that entire 10 minute
 * period. The <code>SetDesiredCapacity</code> command allows you to
 * circumvent this restriction and change the size of the group before
 * the end of the cooldown period.
 * </p>
 * <p>
 * In the second case, if you write your own triggering system, you can
 * use <code>SetDesiredCapacity</code> to control the size of your Auto
 * Scaling group. If you want the same cooldown functionality that Auto
 * Scaling offers, you can configure <code>SetDesiredCapacity</code> to
 * honor cooldown by setting the <code>HonorCooldown</code> parameter to
 * <code>true</code> .
 * 
 * </p>
 *
 * @see com.amazonaws.services.autoscaling.AmazonAutoScaling#setDesiredCapacity(SetDesiredCapacityRequest)
 */
public class SetDesiredCapacityRequest extends AmazonWebServiceRequest {

    /**
     * The name of the AutoScalingGroup.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1600<br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     */
    private String autoScalingGroupName;

    /**
     * The new capacity setting for the AutoScalingGroup.
     */
    private Integer desiredCapacity;

    /**
     * By default, <code>SetDesiredCapacity</code> overrides any cooldown
     * period. Set to True if you want Auto Scaling to reject this request if
     * the Auto Scaling group is in cooldown.
     */
    private Boolean honorCooldown;

    /**
     * The name of the AutoScalingGroup.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1600<br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     *
     * @return The name of the AutoScalingGroup.
     */
    public String getAutoScalingGroupName() {
        return autoScalingGroupName;
    }
    
    /**
     * The name of the AutoScalingGroup.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1600<br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     *
     * @param autoScalingGroupName The name of the AutoScalingGroup.
     */
    public void setAutoScalingGroupName(String autoScalingGroupName) {
        this.autoScalingGroupName = autoScalingGroupName;
    }
    
    /**
     * The name of the AutoScalingGroup.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1600<br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     *
     * @param autoScalingGroupName The name of the AutoScalingGroup.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SetDesiredCapacityRequest withAutoScalingGroupName(String autoScalingGroupName) {
        this.autoScalingGroupName = autoScalingGroupName;
        return this;
    }
    
    
    /**
     * The new capacity setting for the AutoScalingGroup.
     *
     * @return The new capacity setting for the AutoScalingGroup.
     */
    public Integer getDesiredCapacity() {
        return desiredCapacity;
    }
    
    /**
     * The new capacity setting for the AutoScalingGroup.
     *
     * @param desiredCapacity The new capacity setting for the AutoScalingGroup.
     */
    public void setDesiredCapacity(Integer desiredCapacity) {
        this.desiredCapacity = desiredCapacity;
    }
    
    /**
     * The new capacity setting for the AutoScalingGroup.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param desiredCapacity The new capacity setting for the AutoScalingGroup.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SetDesiredCapacityRequest withDesiredCapacity(Integer desiredCapacity) {
        this.desiredCapacity = desiredCapacity;
        return this;
    }
    
    
    /**
     * By default, <code>SetDesiredCapacity</code> overrides any cooldown
     * period. Set to True if you want Auto Scaling to reject this request if
     * the Auto Scaling group is in cooldown.
     *
     * @return By default, <code>SetDesiredCapacity</code> overrides any cooldown
     *         period. Set to True if you want Auto Scaling to reject this request if
     *         the Auto Scaling group is in cooldown.
     */
    public Boolean isHonorCooldown() {
        return honorCooldown;
    }
    
    /**
     * By default, <code>SetDesiredCapacity</code> overrides any cooldown
     * period. Set to True if you want Auto Scaling to reject this request if
     * the Auto Scaling group is in cooldown.
     *
     * @param honorCooldown By default, <code>SetDesiredCapacity</code> overrides any cooldown
     *         period. Set to True if you want Auto Scaling to reject this request if
     *         the Auto Scaling group is in cooldown.
     */
    public void setHonorCooldown(Boolean honorCooldown) {
        this.honorCooldown = honorCooldown;
    }
    
    /**
     * By default, <code>SetDesiredCapacity</code> overrides any cooldown
     * period. Set to True if you want Auto Scaling to reject this request if
     * the Auto Scaling group is in cooldown.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param honorCooldown By default, <code>SetDesiredCapacity</code> overrides any cooldown
     *         period. Set to True if you want Auto Scaling to reject this request if
     *         the Auto Scaling group is in cooldown.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public SetDesiredCapacityRequest withHonorCooldown(Boolean honorCooldown) {
        this.honorCooldown = honorCooldown;
        return this;
    }
    
    
    /**
     * By default, <code>SetDesiredCapacity</code> overrides any cooldown
     * period. Set to True if you want Auto Scaling to reject this request if
     * the Auto Scaling group is in cooldown.
     *
     * @return By default, <code>SetDesiredCapacity</code> overrides any cooldown
     *         period. Set to True if you want Auto Scaling to reject this request if
     *         the Auto Scaling group is in cooldown.
     */
    public Boolean getHonorCooldown() {
        return honorCooldown;
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
        sb.append("AutoScalingGroupName: " + autoScalingGroupName + ", ");
        sb.append("DesiredCapacity: " + desiredCapacity + ", ");
        sb.append("HonorCooldown: " + honorCooldown + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    