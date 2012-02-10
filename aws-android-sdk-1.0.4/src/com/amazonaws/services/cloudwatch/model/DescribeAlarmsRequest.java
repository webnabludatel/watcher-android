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
package com.amazonaws.services.cloudwatch.model;
import com.amazonaws.AmazonWebServiceRequest;

/**
 * Container for the parameters to the {@link com.amazonaws.services.cloudwatch.AmazonCloudWatch#describeAlarms(DescribeAlarmsRequest) DescribeAlarms operation}.
 * <p>
 * Retrieves alarms with the specified names. If no name is specified,
 * all alarms for the user are returned. Alarms can be retrieved by using
 * only a prefix for the alarm name, the alarm state, or a prefix for any
 * action.
 * </p>
 *
 * @see com.amazonaws.services.cloudwatch.AmazonCloudWatch#describeAlarms(DescribeAlarmsRequest)
 */
public class DescribeAlarmsRequest extends AmazonWebServiceRequest {

    /**
     * A list of alarm names to retrieve information for.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>0 - 100<br/>
     */
    private java.util.List<String> alarmNames;

    /**
     * The alarm name prefix. <code>AlarmNames</code> cannot be specified if
     * this parameter is specified.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     */
    private String alarmNamePrefix;

    /**
     * The state value to be used in matching alarms.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>OK, ALARM, INSUFFICIENT_DATA
     */
    private String stateValue;

    /**
     * The action name prefix.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1024<br/>
     */
    private String actionPrefix;

    /**
     * The maximum number of alarm descriptions to retrieve.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - 100<br/>
     */
    private Integer maxRecords;

    /**
     * The token returned by a previous call to indicate that there is more
     * data available.
     */
    private String nextToken;

    /**
     * A list of alarm names to retrieve information for.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>0 - 100<br/>
     *
     * @return A list of alarm names to retrieve information for.
     */
    public java.util.List<String> getAlarmNames() {
        
        if (alarmNames == null) {
            alarmNames = new java.util.ArrayList<String>();
        }
        return alarmNames;
    }
    
    /**
     * A list of alarm names to retrieve information for.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>0 - 100<br/>
     *
     * @param alarmNames A list of alarm names to retrieve information for.
     */
    public void setAlarmNames(java.util.Collection<String> alarmNames) {
        java.util.List<String> alarmNamesCopy = new java.util.ArrayList<String>();
        if (alarmNames != null) {
            alarmNamesCopy.addAll(alarmNames);
        }
        this.alarmNames = alarmNamesCopy;
    }
    
    /**
     * A list of alarm names to retrieve information for.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>0 - 100<br/>
     *
     * @param alarmNames A list of alarm names to retrieve information for.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAlarmsRequest withAlarmNames(String... alarmNames) {
        if (getAlarmNames() == null) setAlarmNames(new java.util.ArrayList<String>());
        for (String value : alarmNames) {
            getAlarmNames().add(value);
        }
        return this;
    }
    
    /**
     * A list of alarm names to retrieve information for.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>0 - 100<br/>
     *
     * @param alarmNames A list of alarm names to retrieve information for.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAlarmsRequest withAlarmNames(java.util.Collection<String> alarmNames) {
        java.util.List<String> alarmNamesCopy = new java.util.ArrayList<String>();
        if (alarmNames != null) {
            alarmNamesCopy.addAll(alarmNames);
        }
        this.alarmNames = alarmNamesCopy;

        return this;
    }
    
    /**
     * The alarm name prefix. <code>AlarmNames</code> cannot be specified if
     * this parameter is specified.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @return The alarm name prefix. <code>AlarmNames</code> cannot be specified if
     *         this parameter is specified.
     */
    public String getAlarmNamePrefix() {
        return alarmNamePrefix;
    }
    
    /**
     * The alarm name prefix. <code>AlarmNames</code> cannot be specified if
     * this parameter is specified.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param alarmNamePrefix The alarm name prefix. <code>AlarmNames</code> cannot be specified if
     *         this parameter is specified.
     */
    public void setAlarmNamePrefix(String alarmNamePrefix) {
        this.alarmNamePrefix = alarmNamePrefix;
    }
    
    /**
     * The alarm name prefix. <code>AlarmNames</code> cannot be specified if
     * this parameter is specified.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param alarmNamePrefix The alarm name prefix. <code>AlarmNames</code> cannot be specified if
     *         this parameter is specified.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAlarmsRequest withAlarmNamePrefix(String alarmNamePrefix) {
        this.alarmNamePrefix = alarmNamePrefix;
        return this;
    }
    
    
    /**
     * The state value to be used in matching alarms.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>OK, ALARM, INSUFFICIENT_DATA
     *
     * @return The state value to be used in matching alarms.
     *
     * @see StateValue
     */
    public String getStateValue() {
        return stateValue;
    }
    
    /**
     * The state value to be used in matching alarms.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>OK, ALARM, INSUFFICIENT_DATA
     *
     * @param stateValue The state value to be used in matching alarms.
     *
     * @see StateValue
     */
    public void setStateValue(String stateValue) {
        this.stateValue = stateValue;
    }
    
    /**
     * The state value to be used in matching alarms.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>OK, ALARM, INSUFFICIENT_DATA
     *
     * @param stateValue The state value to be used in matching alarms.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     *
     * @see StateValue
     */
    public DescribeAlarmsRequest withStateValue(String stateValue) {
        this.stateValue = stateValue;
        return this;
    }
    
    
    /**
     * The action name prefix.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1024<br/>
     *
     * @return The action name prefix.
     */
    public String getActionPrefix() {
        return actionPrefix;
    }
    
    /**
     * The action name prefix.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1024<br/>
     *
     * @param actionPrefix The action name prefix.
     */
    public void setActionPrefix(String actionPrefix) {
        this.actionPrefix = actionPrefix;
    }
    
    /**
     * The action name prefix.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 1024<br/>
     *
     * @param actionPrefix The action name prefix.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAlarmsRequest withActionPrefix(String actionPrefix) {
        this.actionPrefix = actionPrefix;
        return this;
    }
    
    
    /**
     * The maximum number of alarm descriptions to retrieve.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - 100<br/>
     *
     * @return The maximum number of alarm descriptions to retrieve.
     */
    public Integer getMaxRecords() {
        return maxRecords;
    }
    
    /**
     * The maximum number of alarm descriptions to retrieve.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - 100<br/>
     *
     * @param maxRecords The maximum number of alarm descriptions to retrieve.
     */
    public void setMaxRecords(Integer maxRecords) {
        this.maxRecords = maxRecords;
    }
    
    /**
     * The maximum number of alarm descriptions to retrieve.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - 100<br/>
     *
     * @param maxRecords The maximum number of alarm descriptions to retrieve.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAlarmsRequest withMaxRecords(Integer maxRecords) {
        this.maxRecords = maxRecords;
        return this;
    }
    
    
    /**
     * The token returned by a previous call to indicate that there is more
     * data available.
     *
     * @return The token returned by a previous call to indicate that there is more
     *         data available.
     */
    public String getNextToken() {
        return nextToken;
    }
    
    /**
     * The token returned by a previous call to indicate that there is more
     * data available.
     *
     * @param nextToken The token returned by a previous call to indicate that there is more
     *         data available.
     */
    public void setNextToken(String nextToken) {
        this.nextToken = nextToken;
    }
    
    /**
     * The token returned by a previous call to indicate that there is more
     * data available.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param nextToken The token returned by a previous call to indicate that there is more
     *         data available.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAlarmsRequest withNextToken(String nextToken) {
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
        sb.append("AlarmNames: " + alarmNames + ", ");
        sb.append("AlarmNamePrefix: " + alarmNamePrefix + ", ");
        sb.append("StateValue: " + stateValue + ", ");
        sb.append("ActionPrefix: " + actionPrefix + ", ");
        sb.append("MaxRecords: " + maxRecords + ", ");
        sb.append("NextToken: " + nextToken + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    