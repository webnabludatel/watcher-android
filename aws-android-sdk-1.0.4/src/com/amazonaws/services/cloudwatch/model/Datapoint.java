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
 * The <code>Datapoint</code> data type encapsulates the statistical
 * data that Amazon CloudWatch computes from metric data.
 * </p>
 */
public class Datapoint {

    /**
     * The time stamp used for the datapoint.
     */
    private java.util.Date timestamp;

    /**
     * The number of metric values that contributed to the aggregate value of
     * this datapoint.
     */
    private Double sampleCount;

    /**
     * The average of metric values that correspond to the datapoint.
     */
    private Double average;

    /**
     * The sum of metric values used for the datapoint.
     */
    private Double sum;

    /**
     * The minimum metric value used for the datapoint.
     */
    private Double minimum;

    /**
     * The maximum of the metric value used for the datapoint.
     */
    private Double maximum;

    /**
     * The standard unit used for the datapoint.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>Seconds, Microseconds, Milliseconds, Bytes, Kilobytes, Megabytes, Gigabytes, Terabytes, Bits, Kilobits, Megabits, Gigabits, Terabits, Percent, Count, Bytes/Second, Kilobytes/Second, Megabytes/Second, Gigabytes/Second, Terabytes/Second, Bits/Second, Kilobits/Second, Megabits/Second, Gigabits/Second, Terabits/Second, Count/Second, None
     */
    private String unit;

    /**
     * The time stamp used for the datapoint.
     *
     * @return The time stamp used for the datapoint.
     */
    public java.util.Date getTimestamp() {
        return timestamp;
    }
    
    /**
     * The time stamp used for the datapoint.
     *
     * @param timestamp The time stamp used for the datapoint.
     */
    public void setTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
    }
    
    /**
     * The time stamp used for the datapoint.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param timestamp The time stamp used for the datapoint.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Datapoint withTimestamp(java.util.Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }
    
    
    /**
     * The number of metric values that contributed to the aggregate value of
     * this datapoint.
     *
     * @return The number of metric values that contributed to the aggregate value of
     *         this datapoint.
     */
    public Double getSampleCount() {
        return sampleCount;
    }
    
    /**
     * The number of metric values that contributed to the aggregate value of
     * this datapoint.
     *
     * @param sampleCount The number of metric values that contributed to the aggregate value of
     *         this datapoint.
     */
    public void setSampleCount(Double sampleCount) {
        this.sampleCount = sampleCount;
    }
    
    /**
     * The number of metric values that contributed to the aggregate value of
     * this datapoint.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param sampleCount The number of metric values that contributed to the aggregate value of
     *         this datapoint.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Datapoint withSampleCount(Double sampleCount) {
        this.sampleCount = sampleCount;
        return this;
    }
    
    
    /**
     * The average of metric values that correspond to the datapoint.
     *
     * @return The average of metric values that correspond to the datapoint.
     */
    public Double getAverage() {
        return average;
    }
    
    /**
     * The average of metric values that correspond to the datapoint.
     *
     * @param average The average of metric values that correspond to the datapoint.
     */
    public void setAverage(Double average) {
        this.average = average;
    }
    
    /**
     * The average of metric values that correspond to the datapoint.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param average The average of metric values that correspond to the datapoint.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Datapoint withAverage(Double average) {
        this.average = average;
        return this;
    }
    
    
    /**
     * The sum of metric values used for the datapoint.
     *
     * @return The sum of metric values used for the datapoint.
     */
    public Double getSum() {
        return sum;
    }
    
    /**
     * The sum of metric values used for the datapoint.
     *
     * @param sum The sum of metric values used for the datapoint.
     */
    public void setSum(Double sum) {
        this.sum = sum;
    }
    
    /**
     * The sum of metric values used for the datapoint.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param sum The sum of metric values used for the datapoint.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Datapoint withSum(Double sum) {
        this.sum = sum;
        return this;
    }
    
    
    /**
     * The minimum metric value used for the datapoint.
     *
     * @return The minimum metric value used for the datapoint.
     */
    public Double getMinimum() {
        return minimum;
    }
    
    /**
     * The minimum metric value used for the datapoint.
     *
     * @param minimum The minimum metric value used for the datapoint.
     */
    public void setMinimum(Double minimum) {
        this.minimum = minimum;
    }
    
    /**
     * The minimum metric value used for the datapoint.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param minimum The minimum metric value used for the datapoint.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Datapoint withMinimum(Double minimum) {
        this.minimum = minimum;
        return this;
    }
    
    
    /**
     * The maximum of the metric value used for the datapoint.
     *
     * @return The maximum of the metric value used for the datapoint.
     */
    public Double getMaximum() {
        return maximum;
    }
    
    /**
     * The maximum of the metric value used for the datapoint.
     *
     * @param maximum The maximum of the metric value used for the datapoint.
     */
    public void setMaximum(Double maximum) {
        this.maximum = maximum;
    }
    
    /**
     * The maximum of the metric value used for the datapoint.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param maximum The maximum of the metric value used for the datapoint.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     */
    public Datapoint withMaximum(Double maximum) {
        this.maximum = maximum;
        return this;
    }
    
    
    /**
     * The standard unit used for the datapoint.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>Seconds, Microseconds, Milliseconds, Bytes, Kilobytes, Megabytes, Gigabytes, Terabytes, Bits, Kilobits, Megabits, Gigabits, Terabits, Percent, Count, Bytes/Second, Kilobytes/Second, Megabytes/Second, Gigabytes/Second, Terabytes/Second, Bits/Second, Kilobits/Second, Megabits/Second, Gigabits/Second, Terabits/Second, Count/Second, None
     *
     * @return The standard unit used for the datapoint.
     *
     * @see StandardUnit
     */
    public String getUnit() {
        return unit;
    }
    
    /**
     * The standard unit used for the datapoint.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>Seconds, Microseconds, Milliseconds, Bytes, Kilobytes, Megabytes, Gigabytes, Terabytes, Bits, Kilobits, Megabits, Gigabits, Terabits, Percent, Count, Bytes/Second, Kilobytes/Second, Megabytes/Second, Gigabytes/Second, Terabytes/Second, Bits/Second, Kilobits/Second, Megabits/Second, Gigabits/Second, Terabits/Second, Count/Second, None
     *
     * @param unit The standard unit used for the datapoint.
     *
     * @see StandardUnit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    /**
     * The standard unit used for the datapoint.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>Seconds, Microseconds, Milliseconds, Bytes, Kilobytes, Megabytes, Gigabytes, Terabytes, Bits, Kilobits, Megabits, Gigabits, Terabits, Percent, Count, Bytes/Second, Kilobytes/Second, Megabytes/Second, Gigabytes/Second, Terabytes/Second, Bits/Second, Kilobits/Second, Megabits/Second, Gigabits/Second, Terabits/Second, Count/Second, None
     *
     * @param unit The standard unit used for the datapoint.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together. 
     *
     * @see StandardUnit
     */
    public Datapoint withUnit(String unit) {
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
        sb.append("Timestamp: " + timestamp + ", ");
        sb.append("SampleCount: " + sampleCount + ", ");
        sb.append("Average: " + average + ", ");
        sb.append("Sum: " + sum + ", ");
        sb.append("Minimum: " + minimum + ", ");
        sb.append("Maximum: " + maximum + ", ");
        sb.append("Unit: " + unit + ", ");
        sb.append("}");
        return sb.toString();
    }
    
}
    