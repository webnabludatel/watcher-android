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

import com.amazonaws.services.s3.internal.ServerSideEncryptionResult;

/**
 * Contains the details returned from Amazon S3 after calling the UploadPart
 * operation.
 */
public class UploadPartResult implements ServerSideEncryptionResult {

    /** The part number of the newly uploaded part */
    private int partNumber;

    /** The entity tag generated from the content of the upload part */
    private String eTag;

    /** The server side encryption algorithm of the new object */
    private String serverSideEncryption;
    
    /**
     * Returns the part number of the newly uploaded part.
     *
     * @return The part number of the newly uploaded part.
     */
    public int getPartNumber() {
        return partNumber;
    }

    /**
     * Sets the part number of the newly uploaded part.
     *
     * @param partNumber
     *            the part number of the newly uploaded part.
     */
    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * Returns the entity tag of the newly uploaded part. The entity tag is
     * needed later when the multipart upload is completed.
     *
     * @return the entity tag of the newly uploaded part.
     */
    public String getETag() {
        return eTag;
    }

    /**
     * Sets the entity tag of the newly uploaded part.
     *
     * @param eTag
     *            the entity tag of the newly uploaded part.
     */
    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    /**
     * Returns an identifier which identifies the upload part by its part number
     * and the entity tag computed from the part's data. This information is
     * later needed to complete a multipart upload.
     * 
     * @return An identifier which identifies the upload part by its part number
     *         and the entity tag computed from the part's data.
     */
    public PartETag getPartETag() {
        return new PartETag(partNumber, eTag);
    }
    
    /**
     * Returns the server-side encryption algorithm for the newly created
     * object, or null if none was used.
     */
    public String getServerSideEncryption() {
        return serverSideEncryption;
    }

    /**
     * Sets the server-side encryption algorithm for the newly created object.
     * 
     * @param serverSideEncryption
     *            The server-side encryption algorithm for the new object.
     */
    public void setServerSideEncryption(String serverSideEncryption) {
        this.serverSideEncryption = serverSideEncryption;
    }
}
