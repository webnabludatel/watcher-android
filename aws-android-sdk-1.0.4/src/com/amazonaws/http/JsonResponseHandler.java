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
package com.amazonaws.http;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;

import com.amazonaws.AmazonWebServiceResponse;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.VoidJsonUnmarshaller;

/**
 * Default implementation of HttpResponseHandler that handles a successful
 * response from an AWS service and unmarshalls the result using a StAX
 * unmarshaller.
 *
 * @param <T>
 *            Indicates the type being unmarshalled by this response handler.
 */
public class JsonResponseHandler<T> implements HttpResponseHandler<AmazonWebServiceResponse<T>> {

    /** The StAX unmarshaller to use when handling the response */
    private Unmarshaller<T, JsonUnmarshallerContext> responseUnmarshaller;

    /** Shared logger for profiling information */
    private static final Log log = LogFactory.getLog("com.amazonaws.request");

    private static JsonFactory jsonFactory = new JsonFactory();


    /**
     * Constructs a new response handler that will use the specified StAX
     * unmarshaller to unmarshall the service response and uses the specified
     * response element path to find the root of the business data in the
     * service's response.
     *
     * @param responseUnmarshaller
     *            The StAX unmarshaller to use on the response.
     */
    public JsonResponseHandler(Unmarshaller<T, JsonUnmarshallerContext> responseUnmarshaller) {
        this.responseUnmarshaller = responseUnmarshaller;

        /*
         * Even if the invoked operation just returns null, we still need an
         * unmarshaller to run so we can pull out response metadata.
         *
         * We might want to pass this in through the client class so that we
         * don't have to do this check here.
         */
        if (this.responseUnmarshaller == null) {
            this.responseUnmarshaller = new VoidJsonUnmarshaller<T>();
        }
    }


    /**
     * @see com.amazonaws.http.HttpResponseHandler#handle(com.amazonaws.http.HttpResponse)
     */
    public AmazonWebServiceResponse<T> handle(HttpResponse response) throws Exception {
        log.trace("Parsing service response JSON");
        JsonParser jsonParser = jsonFactory.createJsonParser(response.getContent());
        try {
            AmazonWebServiceResponse<T> awsResponse = new AmazonWebServiceResponse<T>();
            JsonUnmarshallerContext unmarshallerContext = new JsonUnmarshallerContext(jsonParser);

            // TODO: Anything else to do for JSON metadata?
            unmarshallerContext.registerMetadataExpression("ResponseMetadata/RequestId", 2, ResponseMetadata.AWS_REQUEST_ID);
            unmarshallerContext.registerMetadataExpression("requestId", 2, ResponseMetadata.AWS_REQUEST_ID);
            registerAdditionalMetadataExpressions(unmarshallerContext);

            T result = responseUnmarshaller.unmarshall(unmarshallerContext);
            awsResponse.setResult(result);

            Map<String, String> metadata = unmarshallerContext.getMetadata();
            awsResponse.setResponseMetadata(new ResponseMetadata(metadata));

            log.trace("Done parsing service response");
            return awsResponse;
        } finally {
            try {jsonParser.close();} catch (Exception e) {}
        }
    }

    /**
     * Hook for subclasses to override in order to collect additional metadata
     * from service responses.
     *
     * @param unmarshallerContext
     *            The unmarshaller context used to process a service's response
     *            data.
     */
    protected void registerAdditionalMetadataExpressions(JsonUnmarshallerContext unmarshallerContext) {}

    /**
     * Since this response handler completely consumes all the data from the
     * underlying HTTP connection during the handle method, we don't need to
     * keep the HTTP connection open.
     *
     * @see com.amazonaws.http.HttpResponseHandler#needsConnectionLeftOpen()
     */
    public boolean needsConnectionLeftOpen() {
        return false;
    }

}
