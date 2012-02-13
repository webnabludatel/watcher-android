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
 * The <code>MetricDatum</code> data type encapsulates the information
 * sent with PutMetricData to either create a new metric or add new
 * values to be aggregated into an existing metric.
 * </p>
 */
public class MetricDatum {

    /**
     * The name of the metric.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     */
    private String metricName;

    /**
     * A list of dimensions associated with the metric.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>0 - 10<br/>
     */
    private java.util.List<Dimension> dimensions;

    /**
     * The time stamp used for the metric. If not specified, the default
     * value is set to the time the metric data was received.
     */
    private java.util.Date timestamp;

    /**
     * The value for the metric. <important>Although the <code>Value</code>
     * parameter accepts numbers of type <code>Double</code>, Amazon
     * CloudWatch truncates values with very large exponents. Values with
     * base-10 exponents greater than 126 (1 x 10^126) are truncated.
     * Likewise, values with base-10 exponents less than -130 (1 x 10^-130)
     * are also truncated. </important>
     */
    private Double value;

    /**
     * A set of statistical values describing the metric.
     */
    private StatisticSet statisticValues;

    /**
     * The unit of the metric.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>Seconds, Microseconds, Milliseconds, Bytes, Kilobytes, Megabytes, Gigabytes, Terabytes, Bits, Kilobits, Megabits, Gigabits, Terabits, Percent, Count, Bytes/Second, Kilobytes/Second, Megabytes/Second, Gigabytes/Second, Terabytes/Second, Bits/Second, Kilobits/Second, Megabits/Second, Gigabits/Second, Terabits/Second, Count/Second, None
     */
    private String unit;

    /**
     * The name of the metric.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @return The name of the metric.
     */
    public String getMetricName() {
        return metricName;
    }
    
    /**
     * The name of the metric.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param metricName The name of the metric.
     */
    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }
    
    /**
     * The name of the metric.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>1 - 255<br/>
     *
     * @param metricName The name of the metric.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public MetricDatum withMetricName(String metricName) {
        this.metricName = metricName;
        return this;
    }
    
    
    /**
     * A list of dimensions associated with the metric.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>0 - 10<br/>
     *
     * @return A list of dimensions associated with the metric.
     */
    public java.util.List<Dimension> getDimensions() {
        
        if (dimensions == null) {
            dimensions = new java.util.ArrayList<Dimension>();
        }
        return dimensions;
    }
    
    /**
     * A list of dimensions associated with the metric.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>0 - 10<br/>
     *
     * @param dimensions A list of dimensions associated with the metric.
     */
    public void setDimensions(java.util.Collection<Dimension> dimensions) {
        java.util.List<Dimension> dimensionsCopy = new java.util.ArrayList<Dimension>();
        if (dimensions != null) {
            dimensionsCopy.addAll(dimensions);
        }
        this.dimensions = dimensionsCopy;
    }
    
    /**
     * A list of dimensions associated with the metric.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>0 - 10<br/>
     *
     * @param dimensions A list of dimensions associated with the metric.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public MetricDatum withDimensions(Dimension... dimensions) {
        if (getDimensions() == null) setDimensions(new java.util.ArrayList<Dimension>());
        for (Dimension value : dimensions) {
            getDimensions().add(value);
        }
        return this;
    }
    
    /**
     * A list of dimensions associated with the metric.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Length: </b>0 - 10<br/>
     *
     * @param dimensions A list of dimensions associated with the metric.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public MetricDatum withDimensions(java.util.Collection<Dimension> dimensions) {
        java.util.List<Dimension> dimensionsCopy = new java.util.ArrayList<Dimension>();
        if (dimensions != null) {
            dimensionsCopy.addAll(dimensions);
        }
        this.dimensions = dimensionsCopy;

        return this;
    }
    
    /**
     * The time stamp used for the metric. If not specified, the default
     * value is set to the time the metric data was received.
     *
     * @return The time stamp used for the metric. If not specified, the default
     *         value is set to the time the metric data was received.
     */
    public java.util.Date getTimestamp() {
        return timestamp;
    }
    
    /**
     * The time stamp used for the metric. If not specified, the default
     * value is set to the time the metric data was received.
     *
     * @param timestamp The time stamp used for the metric. If not specified, the default
     *         value is set to the time the metric data was received.
     */
    public void setTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
    }
    
    /**
     * The time stamp used for the metric. If not specified, the default
     * value is set to the time the metric data was received.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param timestamp The time stamp used for the metric. If not specified, the default
     *         value is set to the time the metric data was received.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public MetricDatum withTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }
    
    
    /**
     * The value for the metric. <important>Although the <code>Value</code>
     * parameter accepts numbers of type <code>Double</code>, Amazon
     * CloudWatch truncates values with very large exponents. Values with
     * base-10 exponents greater than 126 (1 x 10^126) are truncated.
     * Likewise, values with base-10 exponents less than -130 (1 x 10^-130)
     * are also truncated. </important>
     *
     * @return The value for the metric. <important>Although the <code>Value</code>
     *         parameter accepts numbers of type <code>Double</code>, Amazon
     *         CloudWatch truncates values with very large exponents. Values with
     *         base-10 exponents greater than 126 (1 x 10^126) are truncated.
     *         Likewise, values with base-10 exponents less than -130 (1 x 10^-130)
     *         are also truncated. </important>
     */
    public Double getValue() {
        return value;
    }
    
    /**
     * The value for the metric. <important>Although the <code>Value</code>
     * parameter accepts numbers of type <code>Double</code>, Amazon
     * CloudWatch truncates values with very large exponents. Values with
     * base-10 exponents greater than 126 (1 x 10^126) are truncated.
     * Likewise, values with base-10 exponents less than -130 (1 x 10^-130)
     * are also truncated. </important>
     *
     * @param value The value for the metric. <important>Although the <code>Value</code>
     *         parameter accepts numbers of type <code>Double</code>, Amazon
     *         CloudWatch truncates values with very large exponents. Values with
     *         base-10 exponents greater than 126 (1 x 10^126) are truncated.
     *         Likewise, values with base-10 exponents less than -130 (1 x 10^-130)
     *         are also truncated. </important>
     */
    public void setValue(Double value) {
        this.value = value;
    }
    
    /**
     * The value for the metric. <important>Although the <code>Value</code>
     * parameter accepts numbers of type <code>Double</code>, Amazon
     * CloudWatch truncates values with very large exponents. Values with
     * base-10 exponents greater than 126 (1 x 10^126) are truncated.
     * Likewise, values with base-10 exponents less than -130 (1 x 10^-130)
     * are also truncated. </important>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param value The value for the metric. <important>Although the <code>Value</code>
     *         parameter accepts numbers of type <code>Double</code>, Amazon
     *         CloudWatch truncates values with very large exponents. Values with
     *         base-10 exponents greater than 126 (1 x 10^126) are truncated.
     *         Likewise, values with base-10 exponents less than -130 (1 x 10^-130)
     *         are also truncated. </important>
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public MetricDatum withValue(Double value) {
        this.value = value;
        return this;
    }
    
    
    /**
     * A set of statistical values describing the metric.
     *
     * @return A set of statistical values describing the metric.
     */
    public StatisticSet getStatisticValues() {
        return statisticValues;
    }
    
    /**
     * A set of statistical values describing the metric.
     *
     * @param statisticValues A set of statistical values describing the metric.
     */
    public void setStatisticValues(StatisticSet statisticValues) {
        this.statisticValues = statisticValues;
    }
    
    /**
     * A set of statistical values describing the metric.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param statisticValues A set of statistical values describing the metric.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public MetricDatum withStatisticValues(StatisticSet statisticValues) {
        this.statisticValues = statisticValues;
        return this;
    }
    
    
    /**
     * The unit of the metric.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>Seconds, Microseconds, Milliseconds, Bytes, Kilobytes, Megabytes, Gigabytes, Terabytes, Bits, Kilobits, Megabits, Gigabits, Terabits, Percent, Count, Bytes/Second, Kilobytes/Second, Megabytes/Second, Gigabytes/Second, Terabytes/Second, Bits/Second, Kilobits/Second, Megabits/Second, Gigabits/Second, Terabits/Second, Count/Second, None
     *
     * @return The unit of the metric.
     *
     * @see StandardUnit
     */
    public String getUnit() {
        return unit;
    }
    
    /**
     * The unit of the metric.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>Seconds, Microseconds, Milliseconds, Bytes, Kilobytes, Megabytes, Gigabytes, Terabytes, Bits, Kilobits, Megabits, Gigabits, Terabits, Percent, Count, Bytes/Second, Kilobytes/Second, Megabytes/Second, Gigabytes/Second, Terabytes/Second, Bits/Second, Kilobits/Second, Megabits/Second, Gigabits/Second, Terabits/Second, Count/Second, None
     *
     * @param unit The unit of the metric.
     *
     * @see StandardUnit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    /**
     * The unit of the metric.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>Seconds, Microseconds, Milliseconds, Bytes, Kilobytes, Megabytes, Gigabytes, Terabytes, Bits, Kilobits, Megabits, Gigabits, Terabits, Percent, Count, Bytes/Second, Kilobytes/Second, Megabytes/Second, Gigabytes/Second, Terabytes/Second, Bits/Second, Kilobits/Second, Megabits/Second, Gigabits/Second, Terabits/Second, Count/Second, None
     *
     * @param unit The unit of the metric.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     *
     * @see StandardUnit
     */
    public MetricDatum withUnit(String unit) {
        this.unit = unit;
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
        sb.append("MetricName: " + metricName + ", ");
        sb.append("Dimensions: " + dimensions + ", ");
        sb.append("Timestamp: " + timestamp + ", ");
        sb.append("Value: " + value + ", ");
        sb.append("StatisticValues: " + statisticValues + ", ");
        sb.append("Unit: " + unit + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    