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
package com.amazonaws.services.s3.transfer.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3EncryptionClient;
import com.amazonaws.services.s3.model.AbortMultipartUploadRequest;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.CompleteMultipartUploadResult;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.ProgressEvent;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.StorageClass;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerConfiguration;
import com.amazonaws.services.s3.transfer.model.UploadResult;

public class UploadCallable implements Callable<UploadResult> {
    private final AmazonS3 s3;
    private final ExecutorService threadPool;
    private final PutObjectRequest putObjectRequest;
    private String multipartUploadId;

    private static final Log log = LogFactory.getLog(UploadCallable.class);
    private final TransferManagerConfiguration configuration;
    private final ProgressListenerChain progressListenerChain;
    private final List<Future<PartETag>> futures = new ArrayList<Future<PartETag>>();

    public UploadCallable(TransferManager transferManager, ExecutorService threadPool, PutObjectRequest putObjectRequest, ProgressListenerChain progressListenerChain) {
        this.s3 = transferManager.getAmazonS3Client();
        this.configuration = transferManager.getConfiguration();

        this.threadPool = threadPool;
        this.putObjectRequest = putObjectRequest;
        this.progressListenerChain = progressListenerChain;
    }

    List<Future<PartETag>> getFutures() {
        return futures;
    }

    String getMultipartUploadId() {
        return multipartUploadId;
    }

    /**
     * Returns true if this UploadCallable is processing a multipart upload.
     * @return True if this UploadCallable is processing a multipart upload.
     */
    public boolean isMultipartUpload() {
    	return TransferManagerUtils.shouldUseMultipartUpload(putObjectRequest, configuration);
    }
    
    public UploadResult call() throws Exception {
        if (isMultipartUpload()) {
        	fireProgressEvent(ProgressEvent.STARTED_EVENT_CODE);
            return uploadInParts();
        } else {
            return uploadInOneChunk();
        }
    }

    /**
     * Uploads the given request in a single chunk and returns the result.
     */
    private UploadResult uploadInOneChunk() {
        PutObjectResult putObjectResult = s3.putObject(putObjectRequest);

        UploadResult uploadResult = new UploadResult();
        uploadResult.setBucketName(putObjectRequest.getBucketName());
        uploadResult.setKey(putObjectRequest.getKey());
        uploadResult.setETag(putObjectResult.getETag());
        uploadResult.setVersionId(putObjectResult.getVersionId());
        return uploadResult;
    }

    /**
     * Uploads the request in multiple chunks, submitting each upload chunk task
     * to the thread pool and recording its corresponding Future object, as well
     * as the multipart upload id.
     */
    private UploadResult uploadInParts() throws Exception {
        final String bucketName = putObjectRequest.getBucketName();
        final String key        = putObjectRequest.getKey();

        boolean isUsingEncryption = s3 instanceof AmazonS3EncryptionClient;
        long optimalPartSize = getOptimalPartSize(isUsingEncryption);

        multipartUploadId = initiateMultipartUpload(putObjectRequest);

        try {
            UploadPartRequestFactory requestFactory = new UploadPartRequestFactory(putObjectRequest, multipartUploadId, optimalPartSize);

            if (TransferManagerUtils.isUploadParallelizable(putObjectRequest, isUsingEncryption)) {
                uploadPartsInParallel(requestFactory);
                return null;
            } else {
                return uploadPartsInSeries(requestFactory);
            }
        } catch (Exception e) {
            fireProgressEvent(ProgressEvent.FAILED_EVENT_CODE);

            try {
                s3.abortMultipartUpload(new AbortMultipartUploadRequest(bucketName, key, multipartUploadId));
            } catch (Exception e2) {
                log.info("Unable to abort multipart upload, you may need to manually remove uploaded parts: " + e2.getMessage(), e2);
            }
            throw e;
        } finally {
            if (putObjectRequest.getInputStream() != null) {
                try {putObjectRequest.getInputStream().close(); } catch (Exception e) {
                    log.warn("Unable to cleanly close input stream: " + e.getMessage(), e);
                }
            }
        }
    }

    /**
     * Computes and returns the optimal part size for the upload.
     */
    private long getOptimalPartSize(boolean isUsingEncryption) {
        long optimalPartSize = TransferManagerUtils.calculateOptimalPartSize(putObjectRequest, configuration);
        if (isUsingEncryption) {
        	// When using encryption, we want to make sure our parts line up correctly along cipher block boundaries
        	optimalPartSize += optimalPartSize % 32;
        }
        log.debug("Calculated optimal part size: " + optimalPartSize);
        return optimalPartSize;
    }

    /**
     * Uploads all parts in the request in serial in this thread, then completes
     * the upload and returns the result.
     */
    private UploadResult uploadPartsInSeries(UploadPartRequestFactory requestFactory) {

        final List<PartETag> partETags = new ArrayList<PartETag>();

        while (requestFactory.hasMoreRequests()) {
            if (threadPool.isShutdown()) throw new CancellationException("TransferManager has been shutdown");
            partETags.add(s3.uploadPart(requestFactory.getNextUploadPartRequest()).getPartETag());
        }

        CompleteMultipartUploadResult completeMultipartUploadResult = s3
                .completeMultipartUpload(new CompleteMultipartUploadRequest(putObjectRequest.getBucketName(),
                        putObjectRequest.getKey(), multipartUploadId, partETags));

        fireProgressEvent(ProgressEvent.COMPLETED_EVENT_CODE);

        UploadResult uploadResult = new UploadResult();
        uploadResult.setBucketName(completeMultipartUploadResult.getBucketName());
        uploadResult.setKey(completeMultipartUploadResult.getKey());
        uploadResult.setETag(completeMultipartUploadResult.getETag());
        uploadResult.setVersionId(completeMultipartUploadResult.getVersionId());
        return uploadResult;
    }

    /**
     * Submits a callable for each part to upload to our thread pool and records its corresponding Future.
     */
    private void uploadPartsInParallel(UploadPartRequestFactory requestFactory) {
        while (requestFactory.hasMoreRequests()) {
            if (threadPool.isShutdown()) throw new CancellationException("TransferManager has been shutdown");
            UploadPartRequest request = requestFactory.getNextUploadPartRequest();
            futures.add(threadPool.submit(new UploadPartCallable(s3, request)));
        }
    }

    /**
     * Initiates a multipart upload and returns the upload id
     */
    private String initiateMultipartUpload(PutObjectRequest putObjectRequest) {

        InitiateMultipartUploadRequest initiateMultipartUploadRequest =
            new InitiateMultipartUploadRequest(putObjectRequest.getBucketName(), putObjectRequest.getKey())
                .withCannedACL(putObjectRequest.getCannedAcl())
                .withObjectMetadata(putObjectRequest.getMetadata());

        if (putObjectRequest.getStorageClass() != null) {
            initiateMultipartUploadRequest.setStorageClass(
                    StorageClass.fromValue(putObjectRequest.getStorageClass()));
        }

        String uploadId = s3.initiateMultipartUpload(initiateMultipartUploadRequest).getUploadId();
        log.debug("Initiated new multipart upload: " + uploadId);

        return uploadId;
    }

    private void fireProgressEvent(int eventType) {
        if (progressListenerChain == null) return;
        ProgressEvent event = new ProgressEvent(0);
        event.setEventCode(eventType);
        progressListenerChain.progressChanged(event);
    }
}
