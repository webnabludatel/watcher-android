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

/**
 * <p>
 * The LaunchConfigurationsType data type.
 * </p>
 */
public class DescribeLaunchConfigurationsResult {

    /**
     * A list of launch configurations.
     */
    private java.util.List<LaunchConfiguration> launchConfigurations;

    /**
     * A string that marks the start of the next batch of returned results.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     */
    private String nextToken;

    /**
     * A list of launch configurations.
     *
     * @return A list of launch configurations.
     */
    public java.util.List<LaunchConfiguration> getLaunchConfigurations() {
        
        if (launchConfigurations == null) {
            launchConfigurations = new java.util.ArrayList<LaunchConfiguration>();
        }
        return launchConfigurations;
    }
    
    /**
     * A list of launch configurations.
     *
     * @param launchConfigurations A list of launch configurations.
     */
    public void setLaunchConfigurations(java.util.Collection<LaunchConfiguration> launchConfigurations) {
        java.util.List<LaunchConfiguration> launchConfigurationsCopy = new java.util.ArrayList<LaunchConfiguration>();
        if (launchConfigurations != null) {
            launchConfigurationsCopy.addAll(launchConfigurations);
        }
        this.launchConfigurations = launchConfigurationsCopy;
    }
    
    /**
     * A list of launch configurations.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param launchConfigurations A list of launch configurations.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeLaunchConfigurationsResult withLaunchConfigurations(LaunchConfiguration... launchConfigurations) {
        if (getLaunchConfigurations() == null) setLaunchConfigurations(new java.util.ArrayList<LaunchConfiguration>());
        for (LaunchConfiguration value : launchConfigurations) {
            getLaunchConfigurations().add(value);
        }
        return this;
    }
    
    /**
     * A list of launch configurations.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param launchConfigurations A list of launch configurations.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeLaunchConfigurationsResult withLaunchConfigurations(java.util.Collection<LaunchConfiguration> launchConfigurations) {
        java.util.List<LaunchConfiguration> launchConfigurationsCopy = new java.util.ArrayList<LaunchConfiguration>();
        if (launchConfigurations != null) {
            launchConfigurationsCopy.addAll(launchConfigurations);
        }
        this.launchConfigurations = launchConfigurationsCopy;

        return this;
    }
    
    /**
     * A string that marks the start of the next batch of returned results.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     *
     * @return A string that marks the start of the next batch of returned results.
     */
    public String getNextToken() {
        return nextToken;
    }
    
    /**
     * A string that marks the start of the next batch of returned results.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     *
     * @param nextToken A string that marks the start of the next batch of returned results.
     */
    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }
    
    /**
     * A string that marks the start of the next batch of returned results.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Pattern: </b>[\u0020-\uD7FF\uE000-\uFFFD\uD800\uDC00-\uDBFF\uDFFF\r\n\t]*<br/>
     *
     * @param nextToken A string that marks the start of the next batch of returned results.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeLaunchConfigurationsResult withNextToken(String nextToken) {
        this.nextToken = nextToken;
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
        sb.append("LaunchConfigurations: " + launchConfigurations + ", ");
        sb.append("NextToken: " + nextToken + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    