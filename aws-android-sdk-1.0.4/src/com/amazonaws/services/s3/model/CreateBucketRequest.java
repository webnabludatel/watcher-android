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
package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

/**
 * Provides options for creating an Amazon S3 bucket.
 *
 * @see DeleteBucketRequest
 * @see CopyObjectRequest
 */
public class CreateBucketRequest extends AmazonWebServiceRequest {

    /** The name of the Amazon S3 bucket to create. */
    private String bucketName;

    /** The name of the Amazon S3 region in which to create this bucket. */
    private String region;

    /** The optional Canned ACL to set for the new bucket. */
    private CannedAccessControlList cannedAcl;


	/**
     * Constructs a new {@link CreateBucketRequest},
     * ready to be executed to create the
     * specified bucket in the <code>US_Standard</code> region.
     *
     * @param bucketName
     *            The name of the Amazon S3 bucket to create.
     *
     * @see CreateBucketRequest#CreateBucketRequest(String, Region)
     * @see CreateBucketRequest#CreateBucketRequest(String, String)
     */
    public CreateBucketRequest(String bucketName) {
        this(bucketName, Region.US_Standard);
    }

    /**
     * Constructs a new {@link CreateBucketRequest},
     * ready to be executed to create the
     * specified bucket in the specified region.
     *
     * @param bucketName
     *            The name of the Amazon S3 bucket to create.
     * @param region
     *            The region in which to create this bucket.
     *
     * @see CreateBucketRequest#CreateBucketRequest(String)
     * @see CreateBucketRequest#CreateBucketRequest(String, String)
     */
    public CreateBucketRequest(String bucketName, Region region) {
        this(bucketName, region.toString());
    }

    /**
     * Constructs a new {@link CreateBucketRequest},
     * ready to be executed and create the
     * specified bucket in the specified region.
     *
     * @param bucketName
     *            The name of the Amazon S3 bucket to create.
     * @param region
     *            The region in which to create this bucket.
     *
     * @see CreateBucketRequest#CreateBucketRequest(String)
     * @see CreateBucketRequest#CreateBucketRequest(String, Region)
     */
    public CreateBucketRequest(String bucketName, String region) {
        setBucketName(bucketName);
        setRegion(region);
    }

    /**
     * Sets the name of the Amazon S3 bucket to create.
     *
     * @param bucketName
     *              The name of the Amazon S3 bucket to create.
     *
     * @see CreateBucketRequest#getBucketName()
     */
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * Gets the name of the Amazon S3 bucket to create.
     *
     * @return The name of the Amazon S3 bucket to create.
     *
     * @see CreateBucketRequest#setBucketName(String)
     */
    public String getBucketName() {
        return bucketName;
    }

    /**
     * Sets the name of the Amazon S3 region in which this bucket will be created.
     *
     * @param region
     *              The name of the Amazon S3 region in which this bucket will be created.
     *
     * @see CreateBucketRequest#getRegion()
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Gets the name of the Amazon S3 region in which this bucket will be created.
     *
     * @return The name of the Amazon S3 region in which this bucket will be created.
     *
     * @see CreateBucketRequest#setRegion(String)
     */
    public String getRegion() {
        return region;
    }

	/**
	 * Returns the optional Canned ACL to set for the new bucket.
	 *
	 * @return The optional Canned ACL to set for the new bucket.
	 */
    public CannedAccessControlList getCannedAcl() {
		return cannedAcl;
	}

	/**
	 * Sets the optional Canned ACL to set for the new bucket.
	 *
	 * @param cannedAcl
	 *            The optional Canned ACL to set for the new bucket.
	 */
	public void setCannedAcl(CannedAccessControlList cannedAcl) {
		this.cannedAcl = cannedAcl;
	}

	/**
	 * Sets the optional Canned ACL to set for the new bucket, and returns this
	 * updated object so that additional method calls can be chained together.
	 *
	 * @param cannedAcl
	 *            The optional Canned ACL to set for the new bucket.
	 *
	 * @return This updated object, so that additional method calls can be
	 *         chained together.
	 */
	public CreateBucketRequest withCannedAcl(CannedAccessControlList cannedAcl) {
		setCannedAcl(cannedAcl);
		return this;
	}
}
