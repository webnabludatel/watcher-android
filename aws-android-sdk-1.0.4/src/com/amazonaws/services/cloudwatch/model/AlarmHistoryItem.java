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

/**
 * <p>
 * The <code>AlarmHistoryItem</code> data type contains descriptive
 * information about the history of a specific alarm. If you call
 * DescribeAlarmHistory, Amazon CloudWatch returns this data type as part
 * of the DescribeAlarmHistoryResult data type.
 * </p>
 */
public class AlarmHistoryItem {

    /**
     * The descriptive name for the alarm.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     */
    private String alarmName;

    /**
     * The time stamp for the alarm history item.
     */
    private java.util.Date timestamp;

    /**
     * The type of alarm history item.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>ConfigurationUpdate, StateUpdate, Action
     */
    private String historyItemType;

    /**
     * A human-readable summary of the alarm history.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     */
    private String historySummary;

    /**
     * Machine-readable data about the alarm in JSON format.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 4095<br/>
     */
    private String historyData;

    /**
     * The descriptive name for the alarm.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @return The descriptive name for the alarm.
     */
    public String getAlarmName() {
        return alarmName;
    }
    
    /**
     * The descriptive name for the alarm.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param alarmName The descriptive name for the alarm.
     */
    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }
    
    /**
     * The descriptive name for the alarm.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param alarmName The descriptive name for the alarm.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public AlarmHistoryItem withAlarmName(String alarmName) {
        this.alarmName = alarmName;
        return this;
    }
    
    
    /**
     * The time stamp for the alarm history item.
     *
     * @return The time stamp for the alarm history item.
     */
    public java.util.Date getTimestamp() {
        return timestamp;
    }
    
    /**
     * The time stamp for the alarm history item.
     *
     * @param timestamp The time stamp for the alarm history item.
     */
    public void setTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
    }
    
    /**
     * The time stamp for the alarm history item.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param timestamp The time stamp for the alarm history item.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public AlarmHistoryItem withTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }
    
    
    /**
     * The type of alarm history item.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>ConfigurationUpdate, StateUpdate, Action
     *
     * @return The type of alarm history item.
     *
     * @see HistoryItemType
     */
    public String getHistoryItemType() {
        return historyItemType;
    }
    
    /**
     * The type of alarm history item.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>ConfigurationUpdate, StateUpdate, Action
     *
     * @param historyItemType The type of alarm history item.
     *
     * @see HistoryItemType
     */
    public void setHistoryItemType(String historyItemType) {
        this.historyItemType = historyItemType;
    }
    
    /**
     * The type of alarm history item.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>ConfigurationUpdate, StateUpdate, Action
     *
     * @param historyItemType The type of alarm history item.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     *
     * @see HistoryItemType
     */
    public AlarmHistoryItem withHistoryItemType(String historyItemType) {
        this.historyItemType = historyItemType;
        return this;
    }
    
    
    /**
     * A human-readable summary of the alarm history.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @return A human-readable summary of the alarm history.
     */
    public String getHistorySummary() {
        return historySummary;
    }
    
    /**
     * A human-readable summary of the alarm history.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param historySummary A human-readable summary of the alarm history.
     */
    public void setHistorySummary(String historySummary) {
        this.historySummary = historySummary;
    }
    
    /**
     * A human-readable summary of the alarm history.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param historySummary A human-readable summary of the alarm history.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public AlarmHistoryItem withHistorySummary(String historySummary) {
        this.historySummary = historySummary;
        return this;
    }
    
    
    /**
     * Machine-readable data about the alarm in JSON format.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 4095<br/>
     *
     * @return Machine-readable data about the alarm in JSON format.
     */
    public String getHistoryData() {
        return historyData;
    }
    
    /**
     * Machine-readable data about the alarm in JSON format.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 4095<br/>
     *
     * @param historyData Machine-readable data about the alarm in JSON format.
     */
    public void setHistoryData(String historyData) {
        this.historyData = historyData;
    }
    
    /**
     * Machine-readable data about the alarm in JSON format.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 4095<br/>
     *
     * @param historyData Machine-readable data about the alarm in JSON format.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public AlarmHistoryItem withHistoryData(String historyData) {
        this.historyData = historyData;
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
        sb.append("Timestamp: " + timestamp + ", ");
        sb.append("HistoryItemType: " + historyItemType + ", ");
        sb.append("HistorySummary: " + historySummary + ", ");
        sb.append("HistoryData: " + historyData + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    