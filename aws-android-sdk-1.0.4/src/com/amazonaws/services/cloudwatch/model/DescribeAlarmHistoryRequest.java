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
 * Container for the parameters to the {@link com.amazonaws.services.cloudwatch.AmazonCloudWatch#describeAlarmHistory(DescribeAlarmHistoryRequest) DescribeAlarmHistory operation}.
 * <p>
 * Retrieves history for the specified alarm. Filter alarms by date
 * range or item type. If an alarm name is not specified, Amazon
 * CloudWatch returns histories for all of the owner's alarms.
 * </p>
 * <p>
 * <b>NOTE:</b> Amazon CloudWatch retains the history of an alarm for two
 * weeks, whether or not you delete the alarm.
 * </p>
 *
 * @see com.amazonaws.services.cloudwatch.AmazonCloudWatch#describeAlarmHistory(DescribeAlarmHistoryRequest)
 */
public class DescribeAlarmHistoryRequest extends AmazonWebServiceRequest {

    /**
     * The name of the alarm.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     */
    private String alarmName;

    /**
     * The type of alarm histories to retrieve.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>ConfigurationUpdate, StateUpdate, Action
     */
    private String historyItemType;

    /**
     * The starting date to retrieve alarm history.
     */
    private java.util.Date startDate;

    /**
     * The ending date to retrieve alarm history.
     */
    private java.util.Date endDate;

    /**
     * The maximum number of alarm history records to retrieve.
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
     * The name of the alarm.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @return The name of the alarm.
     */
    public String getAlarmName() {
        return alarmName;
    }
    
    /**
     * The name of the alarm.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param alarmName The name of the alarm.
     */
    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }
    
    /**
     * The name of the alarm.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param alarmName The name of the alarm.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAlarmHistoryRequest withAlarmName(String alarmName) {
        this.alarmName = alarmName;
        return this;
    }
    
    
    /**
     * The type of alarm histories to retrieve.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>ConfigurationUpdate, StateUpdate, Action
     *
     * @return The type of alarm histories to retrieve.
     *
     * @see HistoryItemType
     */
    public String getHistoryItemType() {
        return historyItemType;
    }
    
    /**
     * The type of alarm histories to retrieve.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>ConfigurationUpdate, StateUpdate, Action
     *
     * @param historyItemType The type of alarm histories to retrieve.
     *
     * @see HistoryItemType
     */
    public void setHistoryItemType(String historyItemType) {
        this.historyItemType = historyItemType;
    }
    
    /**
     * The type of alarm histories to retrieve.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>ConfigurationUpdate, StateUpdate, Action
     *
     * @param historyItemType The type of alarm histories to retrieve.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     *
     * @see HistoryItemType
     */
    public DescribeAlarmHistoryRequest withHistoryItemType(String historyItemType) {
        this.historyItemType = historyItemType;
        return this;
    }
    
    
    /**
     * The starting date to retrieve alarm history.
     *
     * @return The starting date to retrieve alarm history.
     */
    public java.util.Date getStartDate() {
        return startDate;
    }
    
    /**
     * The starting date to retrieve alarm history.
     *
     * @param startDate The starting date to retrieve alarm history.
     */
    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }
    
    /**
     * The starting date to retrieve alarm history.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param startDate The starting date to retrieve alarm history.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAlarmHistoryRequest withStartDate(java.util.Date startDate) {
        this.startDate = startDate;
        return this;
    }
    
    
    /**
     * The ending date to retrieve alarm history.
     *
     * @return The ending date to retrieve alarm history.
     */
    public java.util.Date getEndDate() {
        return endDate;
    }
    
    /**
     * The ending date to retrieve alarm history.
     *
     * @param endDate The ending date to retrieve alarm history.
     */
    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }
    
    /**
     * The ending date to retrieve alarm history.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param endDate The ending date to retrieve alarm history.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAlarmHistoryRequest withEndDate(java.util.Date endDate) {
        this.endDate = endDate;
        return this;
    }
    
    
    /**
     * The maximum number of alarm history records to retrieve.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - 100<br/>
     *
     * @return The maximum number of alarm history records to retrieve.
     */
    public Integer getMaxRecords() {
        return maxRecords;
    }
    
    /**
     * The maximum number of alarm history records to retrieve.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - 100<br/>
     *
     * @param maxRecords The maximum number of alarm history records to retrieve.
     */
    public void setMaxRecords(Integer maxRecords) {
        this.maxRecords = maxRecords;
    }
    
    /**
     * The maximum number of alarm history records to retrieve.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>1 - 100<br/>
     *
     * @param maxRecords The maximum number of alarm history records to retrieve.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public DescribeAlarmHistoryRequest withMaxRecords(Integer maxRecords) {
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
    public DescribeAlarmHistoryRequest withNextToken(String nextToken) {
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
        sb.append("AlarmName: " + alarmName + ", ");
        sb.append("HistoryItemType: " + historyItemType + ", ");
        sb.append("StartDate: " + startDate + ", ");
        sb.append("EndDate: " + endDate + ", ");
        sb.append("MaxRecords: " + maxRecords + ", ");
        sb.append("NextToken: " + nextToken + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    