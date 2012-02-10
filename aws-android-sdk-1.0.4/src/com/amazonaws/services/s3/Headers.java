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
package com.amazonaws.services.s3;

/**
 * Common S3 HTTP header values used throughout the AWS S3 Java client.
 */
public interface Headers {

    /*
     * Standard HTTP Headers
     */

    public static final String CACHE_CONTROL = "Cache-Control";
    public static final String CONTENT_DISPOSITION = "Content-Disposition";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String CONTENT_LENGTH = "Content-Length";
    public static final String CONTENT_MD5 = "Content-MD5";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String DATE = "Date";
    public static final String ETAG = "ETag";
    public static final String LAST_MODIFIED = "Last-Modified";
    public static final String SERVER = "Server";


    /*
     * Amazon HTTP Headers
     */

    /** Prefix for general Amazon headers: x-amz- */
    public static final String AMAZON_PREFIX = "x-amz-";

    /** S3's canned ACL header: x-amz-acl */
    public static final String S3_CANNED_ACL = "x-amz-acl";

    /** Amazon's alternative date header: x-amz-date */
    public static final String S3_ALTERNATE_DATE = "x-amz-date";

    /** Prefix for S3 user metadata: x-amz-meta- */
    public static final String S3_USER_METADATA_PREFIX = "x-amz-meta-";

    /** S3's version ID header */
    public static final String S3_VERSION_ID = "x-amz-version-id";

    /** S3's Multi-Factor Authentication header */
    public static final String S3_MFA = "x-amz-mfa";

    /** S3 response header for a request's AWS request ID */
    public static final String REQUEST_ID = "x-amz-request-id";

    /** S3 response header for a request's extended debugging ID */
    public static final String EXTENDED_REQUEST_ID = "x-amz-id-2";

    /** S3 request header indicating how to handle metadata when copying an object */
    public static final String METADATA_DIRECTIVE = "x-amz-metadata-directive";

    /** DevPay token header */
    public static final String SECURITY_TOKEN = "x-amz-security-token";

    /** Header describing what class of storage a user wants */
    public static final String STORAGE_CLASS = "x-amz-storage-class";
    
    /** Header for optional server-side encryption algorithm */
    public static final String SERVER_SIDE_ENCRYPTION = "x-amz-server-side-encryption";

    /** ETag matching constraint header for the copy object request */
    public static final String COPY_SOURCE_IF_MATCH = "x-amz-copy-source-if-match";

    /** ETag non-matching constraint header for the copy object request */
    public static final String COPY_SOURCE_IF_NO_MATCH = "x-amz-copy-source-if-none-match";

    /** Unmodified since constraint header for the copy object request */
    public static final String COPY_SOURCE_IF_UNMODIFIED_SINCE = "x-amz-copy-source-if-unmodified-since";

    /** Modified since constraint header for the copy object request */
    public static final String COPY_SOURCE_IF_MODIFIED_SINCE = "x-amz-copy-source-if-modified-since";

    /** Range header for the get object request */
    public static final String RANGE = "Range";
    
    /**Range header for the copy part request */
    public static final String COPY_PART_RANGE = "x-amz-copy-source-range";
    
    /** Modified since constraint header for the get object request */
    public static final String GET_OBJECT_IF_MODIFIED_SINCE = "If-Modified-Since";

    /** Unmodified since constraint header for the get object request */
    public static final String GET_OBJECT_IF_UNMODIFIED_SINCE = "If-Unmodified-Since";

    /** ETag matching constraint header for the get object request */
    public static final String GET_OBJECT_IF_MATCH = "If-Match";

    /** ETag non-matching constraint header for the get object request */
    public static final String GET_OBJECT_IF_NONE_MATCH = "If-None-Match";

    /** Encrypted symmetric key header that is used in the envelope encryption mechanism */
    public static final String CRYPTO_KEY = "x-amz-key";
    
    /** Initialization vector (IV) header that is used in the symmetric and envelope encryption mechanisms */
    public static final String CRYPTO_IV = "x-amz-iv";

    /** JSON-encoded description of encryption materials used during encryption */ 
    public static final String MATERIALS_DESCRIPTION = "x-amz-matdesc";
    
    /** Instruction file header to be placed in the metadata of instruction files */
    public static final String CRYPTO_INSTRUCTION_FILE = "x-amz-crypto-instr-file";
}
