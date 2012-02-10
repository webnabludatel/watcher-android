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
package com.amazonaws.services.autoscaling;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import com.amazonaws.*;
import com.amazonaws.auth.*;
import com.amazonaws.handlers.HandlerChainFactory;
import com.amazonaws.handlers.RequestHandler;
import com.amazonaws.http.StaxResponseHandler;
import com.amazonaws.http.DefaultErrorResponseHandler;
import com.amazonaws.http.ExecutionContext;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.StandardErrorUnmarshaller;

import com.amazonaws.services.autoscaling.model.*;
import com.amazonaws.services.autoscaling.model.transform.*;


/**
 * Client for accessing AmazonAutoScaling.  All service calls made
 * using this client are blocking, and will not return until the service call
 * completes.
 * <p>
 * Auto Scaling <p>
 * This is the <i>Auto Scaling API Reference</i> . This guide provides
 * detailed information about Auto Scaling actions, data types,
 * parameters, and errors. For detailed information about Auto Scaling
 * features and their associated API calls, go to the <a
 * http://docs.amazonwebservices.com/AutoScaling/latest/DeveloperGuide/">
 * Auto Scaling Developer Guide </a> .
 * </p>
 * <p>
 * Auto Scaling is a web service designed to automatically launch or
 * terminate EC2 instances based on user-defined policies, schedules, and
 * health checks. This service is used in conjunction with Amazon
 * CloudWatch and Elastic Load Balancing services.
 * </p>
 * <p>
 * This reference is based on the current WSDL, which is available at:
 * </p>
 * <p>
 * <a
 * ef="http://autoscaling.amazonaws.com/doc/2011-01-01/AutoScaling.wsdl">
 * http://autoscaling.amazonaws.com/doc/2011-01-01/AutoScaling.wsdl </a>
 * </p>
 * <p>
 * <b>Endpoints</b>
 * </p>
 * <p>
 * For information about this product's regions and endpoints, go to <a
 * //docs.amazonwebservices.com/general/latest/gr/index.html?rande.html">
 * Regions and Endpoints </a> in the Amazon Web Services General
 * Reference.
 * </p>
 */
public class AmazonAutoScalingClient extends AmazonWebServiceClient implements AmazonAutoScaling {

    /** Provider for AWS credentials. */
    private AWSCredentialsProvider awsCredentialsProvider;

    /**
     * List of exception unmarshallers for all AmazonAutoScaling exceptions.
     */
    protected final List<Unmarshaller<AmazonServiceException, Node>> exceptionUnmarshallers
            = new ArrayList<Unmarshaller<AmazonServiceException, Node>>();
    
    
    /** AWS signer for authenticating requests. */
    private QueryStringSigner signer;


    /**
     * Constructs a new client to invoke service methods on
     * AmazonAutoScaling using the specified AWS account credentials.
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @param awsCredentials The AWS credentials (access key ID and secret key) to use
     *                       when authenticating with AWS services.
     */
    public AmazonAutoScalingClient(AWSCredentials awsCredentials) {
        this(awsCredentials, new ClientConfiguration());
    }

    /**
     * Constructs a new client to invoke service methods on
     * AmazonAutoScaling using the specified AWS account credentials
     * and client configuration options.
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @param awsCredentials The AWS credentials (access key ID and secret key) to use
     *                       when authenticating with AWS services.
     * @param clientConfiguration The client configuration options controlling how this
     *                       client connects to AmazonAutoScaling
     *                       (ex: proxy settings, retry counts, etc.).
     */
    public AmazonAutoScalingClient(AWSCredentials awsCredentials, ClientConfiguration clientConfiguration) {
        super(clientConfiguration);
        this.awsCredentialsProvider = new StaticCredentialsProvider(awsCredentials);
        init();
    }
    
    /**
     * Constructs a new client to invoke service methods on
     * AmazonAutoScaling using the specified AWS account credentials provider.
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @param awsCredentialsProvider 
     *            The AWS credentials provider which will provide credentials
     *            to authenticate requests with AWS services.
     */
    public AmazonAutoScalingClient(AWSCredentialsProvider awsCredentialsProvider) {
        this(awsCredentialsProvider, new ClientConfiguration());
    }

    /**
     * Constructs a new client to invoke service methods on
     * AmazonAutoScaling using the specified AWS account credentials
     * provider and client configuration options.
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @param awsCredentialsProvider 
     *            The AWS credentials provider which will provide credentials
     *            to authenticate requests with AWS services.
     * @param clientConfiguration The client configuration options controlling how this
     *                       client connects to AmazonAutoScaling
     *                       (ex: proxy settings, retry counts, etc.).
     */
    public AmazonAutoScalingClient(AWSCredentialsProvider awsCredentialsProvider, ClientConfiguration clientConfiguration) {
        super(clientConfiguration);
        this.awsCredentialsProvider = awsCredentialsProvider;
        init();
    }

    private void init() { 
        exceptionUnmarshallers.add(new InvalidNextTokenExceptionUnmarshaller());
        exceptionUnmarshallers.add(new ScalingActivityInProgressExceptionUnmarshaller());
        exceptionUnmarshallers.add(new LimitExceededExceptionUnmarshaller());
        exceptionUnmarshallers.add(new AlreadyExistsExceptionUnmarshaller());
        exceptionUnmarshallers.add(new ResourceInUseExceptionUnmarshaller());
        
        exceptionUnmarshallers.add(new StandardErrorUnmarshaller());
        setEndpoint("autoscaling.amazonaws.com");

        signer = new QueryStringSigner();

        HandlerChainFactory chainFactory = new HandlerChainFactory();
		requestHandlers.addAll(chainFactory.newRequestHandlerChain(
                "/com/amazonaws/services/autoscaling/request.handlers"));
    }
    
    
    /**
     * <p>
     * Returns a full description of each Auto Scaling group in the given
     * list. This includes all Amazon EC2 instances that are members of the
     * group. If a list of names is not provided, the service returns the
     * full details of all Auto Scaling groups.
     * </p>
     * <p>
     * This action supports pagination by returning a token if there are
     * more pages to retrieve. To get the next page, call this action again
     * with the returned token as the NextToken parameter.
     * </p>
     *
     * @param describeAutoScalingGroupsRequest Container for the necessary
     *           parameters to execute the DescribeAutoScalingGroups service method on
     *           AmazonAutoScaling.
     * 
     * @return The response from the DescribeAutoScalingGroups service
     *         method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeAutoScalingGroupsResult describeAutoScalingGroups(DescribeAutoScalingGroupsRequest describeAutoScalingGroupsRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribeAutoScalingGroupsRequest> request = new DescribeAutoScalingGroupsRequestMarshaller().marshall(describeAutoScalingGroupsRequest);
        return invoke(request, new DescribeAutoScalingGroupsResultStaxUnmarshaller());
    }
    
    /**
     * <p>
     * Enables monitoring of group metrics for the Auto Scaling group
     * specified in AutoScalingGroupName. You can specify the list of enabled
     * metrics with the Metrics parameter.
     * </p>
     * <p>
     * Auto scaling metrics collection can be turned on only if the
     * <code>InstanceMonitoring.Enabled</code> flag, in the Auto Scaling
     * group's launch configuration, is set to <code>true</code> .
     * 
     * </p>
     *
     * @param enableMetricsCollectionRequest Container for the necessary
     *           parameters to execute the EnableMetricsCollection service method on
     *           AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void enableMetricsCollection(EnableMetricsCollectionRequest enableMetricsCollectionRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<EnableMetricsCollectionRequest> request = new EnableMetricsCollectionRequestMarshaller().marshall(enableMetricsCollectionRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * Resumes Auto Scaling processes for an Auto Scaling group. For more
     * information, see SuspendProcesses and ProcessType.
     * </p>
     *
     * @param resumeProcessesRequest Container for the necessary parameters
     *           to execute the ResumeProcesses service method on AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void resumeProcesses(ResumeProcessesRequest resumeProcessesRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<ResumeProcessesRequest> request = new ResumeProcessesRequestMarshaller().marshall(resumeProcessesRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * Deletes the specified LaunchConfiguration.
     * </p>
     * <p>
     * The specified launch configuration must not be attached to an Auto
     * Scaling group. When this call completes, the launch configuration is
     * no longer available for use.
     * </p>
     *
     * @param deleteLaunchConfigurationRequest Container for the necessary
     *           parameters to execute the DeleteLaunchConfiguration service method on
     *           AmazonAutoScaling.
     * 
     * @throws ResourceInUseException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void deleteLaunchConfiguration(DeleteLaunchConfigurationRequest deleteLaunchConfigurationRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DeleteLaunchConfigurationRequest> request = new DeleteLaunchConfigurationRequestMarshaller().marshall(deleteLaunchConfigurationRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * Returns descriptions of what each policy does. This action supports
     * pagination. If the response includes a token, there are more records
     * available. To get the additional records, repeat the request with the
     * response token as the NextToken parameter.
     * </p>
     *
     * @param describePoliciesRequest Container for the necessary parameters
     *           to execute the DescribePolicies service method on AmazonAutoScaling.
     * 
     * @return The response from the DescribePolicies service method, as
     *         returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribePoliciesResult describePolicies(DescribePoliciesRequest describePoliciesRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribePoliciesRequest> request = new DescribePoliciesRequestMarshaller().marshall(describePoliciesRequest);
        return invoke(request, new DescribePoliciesResultStaxUnmarshaller());
    }
    
    /**
     * <p>
     * Returns scaling process types for use in the ResumeProcesses and
     * SuspendProcesses actions.
     * </p>
     *
     * @param describeScalingProcessTypesRequest Container for the necessary
     *           parameters to execute the DescribeScalingProcessTypes service method
     *           on AmazonAutoScaling.
     * 
     * @return The response from the DescribeScalingProcessTypes service
     *         method, as returned by AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeScalingProcessTypesResult describeScalingProcessTypes(DescribeScalingProcessTypesRequest describeScalingProcessTypesRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribeScalingProcessTypesRequest> request = new DescribeScalingProcessTypesRequestMarshaller().marshall(describeScalingProcessTypesRequest);
        return invoke(request, new DescribeScalingProcessTypesResultStaxUnmarshaller());
    }
    
    /**
     * <p>
     * Creates a new Auto Scaling group with the specified name. When the
     * creation request is completed, the Auto Scaling group is ready to be
     * used in other calls.
     * </p>
     * <p>
     * <b>NOTE:</b> The Auto Scaling group name must be unique within the
     * scope of your AWS account, and under the quota of Auto Scaling groups
     * allowed for your account.
     * </p>
     *
     * @param createAutoScalingGroupRequest Container for the necessary
     *           parameters to execute the CreateAutoScalingGroup service method on
     *           AmazonAutoScaling.
     * 
     * @throws LimitExceededException
     * @throws AlreadyExistsException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void createAutoScalingGroup(CreateAutoScalingGroupRequest createAutoScalingGroupRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<CreateAutoScalingGroupRequest> request = new CreateAutoScalingGroupRequestMarshaller().marshall(createAutoScalingGroupRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * Returns the scaling activities for the specified Auto Scaling group.
     * </p>
     * <p>
     * If the specified <i>ActivityIds</i> list is empty, all the activities
     * from the past six weeks are returned. Activities are sorted by
     * completion time. Activities still in progress appear first on the
     * list.
     * </p>
     * <p>
     * This action supports pagination. If the response includes a token,
     * there are more records available. To get the additional records,
     * repeat the request with the response token as the NextToken parameter.
     * </p>
     *
     * @param describeScalingActivitiesRequest Container for the necessary
     *           parameters to execute the DescribeScalingActivities service method on
     *           AmazonAutoScaling.
     * 
     * @return The response from the DescribeScalingActivities service
     *         method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeScalingActivitiesResult describeScalingActivities(DescribeScalingActivitiesRequest describeScalingActivitiesRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribeScalingActivitiesRequest> request = new DescribeScalingActivitiesRequestMarshaller().marshall(describeScalingActivitiesRequest);
        return invoke(request, new DescribeScalingActivitiesResultStaxUnmarshaller());
    }
    
    /**
     * <p>
     * Returns a list of notification actions associated with Auto Scaling
     * groups for specified events.
     * </p>
     *
     * @param describeNotificationConfigurationsRequest Container for the
     *           necessary parameters to execute the DescribeNotificationConfigurations
     *           service method on AmazonAutoScaling.
     * 
     * @return The response from the DescribeNotificationConfigurations
     *         service method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeNotificationConfigurationsResult describeNotificationConfigurations(DescribeNotificationConfigurationsRequest describeNotificationConfigurationsRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribeNotificationConfigurationsRequest> request = new DescribeNotificationConfigurationsRequestMarshaller().marshall(describeNotificationConfigurationsRequest);
        return invoke(request, new DescribeNotificationConfigurationsResultStaxUnmarshaller());
    }
    
    /**
     * <p>
     * Runs the policy you create for your Auto Scaling group in
     * PutScalingPolicy.
     * </p>
     *
     * @param executePolicyRequest Container for the necessary parameters to
     *           execute the ExecutePolicy service method on AmazonAutoScaling.
     * 
     * @throws ScalingActivityInProgressException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void executePolicy(ExecutePolicyRequest executePolicyRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<ExecutePolicyRequest> request = new ExecutePolicyRequestMarshaller().marshall(executePolicyRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * Creates or updates a policy for an Auto Scaling group. To update an
     * existing policy, use the existing policy name and set the parameter(s)
     * you want to change. Any existing parameter not changed in an update to
     * an existing policy is not changed in this update request.
     * </p>
     *
     * @param putScalingPolicyRequest Container for the necessary parameters
     *           to execute the PutScalingPolicy service method on AmazonAutoScaling.
     * 
     * @return The response from the PutScalingPolicy service method, as
     *         returned by AmazonAutoScaling.
     * 
     * @throws LimitExceededException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public PutScalingPolicyResult putScalingPolicy(PutScalingPolicyRequest putScalingPolicyRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<PutScalingPolicyRequest> request = new PutScalingPolicyRequestMarshaller().marshall(putScalingPolicyRequest);
        return invoke(request, new PutScalingPolicyResultStaxUnmarshaller());
    }
    
    /**
     * <p>
     * Configures an Auto Scaling group to send notifications when specified
     * events take place. Subscribers to this topic can have messages for
     * events delivered to an endpoint such as a web server or email address.
     * </p>
     * <p>
     * A new <code>PutNotificationConfiguration</code> overwrites an
     * existing configuration.
     * </p>
     *
     * @param putNotificationConfigurationRequest Container for the necessary
     *           parameters to execute the PutNotificationConfiguration service method
     *           on AmazonAutoScaling.
     * 
     * @throws LimitExceededException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void putNotificationConfiguration(PutNotificationConfigurationRequest putNotificationConfigurationRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<PutNotificationConfigurationRequest> request = new PutNotificationConfigurationRequestMarshaller().marshall(putNotificationConfigurationRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * Deletes a policy created by PutScalingPolicy
     * </p>
     *
     * @param deletePolicyRequest Container for the necessary parameters to
     *           execute the DeletePolicy service method on AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void deletePolicy(DeletePolicyRequest deletePolicyRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DeletePolicyRequest> request = new DeletePolicyRequestMarshaller().marshall(deletePolicyRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * Deletes notifications created by PutNotificationConfiguration.
     * </p>
     *
     * @param deleteNotificationConfigurationRequest Container for the
     *           necessary parameters to execute the DeleteNotificationConfiguration
     *           service method on AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void deleteNotificationConfiguration(DeleteNotificationConfigurationRequest deleteNotificationConfigurationRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DeleteNotificationConfigurationRequest> request = new DeleteNotificationConfigurationRequestMarshaller().marshall(deleteNotificationConfigurationRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * Deletes a scheduled action previously created using the
     * PutScheduledUpdateGroupAction.
     * </p>
     *
     * @param deleteScheduledActionRequest Container for the necessary
     *           parameters to execute the DeleteScheduledAction service method on
     *           AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void deleteScheduledAction(DeleteScheduledActionRequest deleteScheduledActionRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DeleteScheduledActionRequest> request = new DeleteScheduledActionRequestMarshaller().marshall(deleteScheduledActionRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * Sets the health status of an instance.
     * </p>
     *
     * @param setInstanceHealthRequest Container for the necessary parameters
     *           to execute the SetInstanceHealth service method on AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void setInstanceHealth(SetInstanceHealthRequest setInstanceHealthRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<SetInstanceHealthRequest> request = new SetInstanceHealthRequestMarshaller().marshall(setInstanceHealthRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * Returns a list of all notification types that are supported by Auto
     * Scaling.
     * </p>
     *
     * @param describeAutoScalingNotificationTypesRequest Container for the
     *           necessary parameters to execute the
     *           DescribeAutoScalingNotificationTypes service method on
     *           AmazonAutoScaling.
     * 
     * @return The response from the DescribeAutoScalingNotificationTypes
     *         service method, as returned by AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeAutoScalingNotificationTypesResult describeAutoScalingNotificationTypes(DescribeAutoScalingNotificationTypesRequest describeAutoScalingNotificationTypesRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribeAutoScalingNotificationTypesRequest> request = new DescribeAutoScalingNotificationTypesRequestMarshaller().marshall(describeAutoScalingNotificationTypesRequest);
        return invoke(request, new DescribeAutoScalingNotificationTypesResultStaxUnmarshaller());
    }
    
    /**
     * <p>
     * Suspends Auto Scaling processes for an Auto Scaling group. To suspend
     * specific process types, specify them by name with the
     * <code>ScalingProcesses.member.N</code> parameter. To suspend all
     * process types, omit the <code>ScalingProcesses.member.N</code>
     * parameter.
     * </p>
     * <p>
     * <b>IMPORTANT:</b> Suspending either of the two primary process types,
     * Launch or Terminate, can prevent other process types from functioning
     * properly. For more information about processes and their dependencies,
     * see ProcessType.
     * </p>
     * <p>
     * To resume processes that have been suspended, use ResumeProcesses.
     * </p>
     *
     * @param suspendProcessesRequest Container for the necessary parameters
     *           to execute the SuspendProcesses service method on AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void suspendProcesses(SuspendProcessesRequest suspendProcessesRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<SuspendProcessesRequest> request = new SuspendProcessesRequestMarshaller().marshall(suspendProcessesRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * Returns a description of each Auto Scaling instance in the
     * InstanceIds list. If a list is not provided, the service returns the
     * full details of all instances up to a maximum of fifty. By default,
     * the service returns a list of 20 items.
     * </p>
     * <p>
     * This action supports pagination by returning a token if there are
     * more pages to retrieve. To get the next page, call this action again
     * with the returned token as the NextToken parameter.
     * </p>
     *
     * @param describeAutoScalingInstancesRequest Container for the necessary
     *           parameters to execute the DescribeAutoScalingInstances service method
     *           on AmazonAutoScaling.
     * 
     * @return The response from the DescribeAutoScalingInstances service
     *         method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeAutoScalingInstancesResult describeAutoScalingInstances(DescribeAutoScalingInstancesRequest describeAutoScalingInstancesRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribeAutoScalingInstancesRequest> request = new DescribeAutoScalingInstancesRequestMarshaller().marshall(describeAutoScalingInstancesRequest);
        return invoke(request, new DescribeAutoScalingInstancesResultStaxUnmarshaller());
    }
    
    /**
     * <p>
     * Creates a new launch configuration. The launch configuration name
     * must be unique within the scope of the client's AWS account. The
     * maximum limit of launch configurations, which by default is 100, must
     * not yet have been met; otherwise, the call will fail. When created,
     * the new launch configuration is available for immediate use.
     * </p>
     * <p>
     * You can create a launch configuration with Amazon EC2 security groups
     * or with Amazon VPC security groups. However, you can't use Amazon EC2
     * security groups together with Amazon VPC security groups, or vice
     * versa. In addition, you can only create Auto Scaling launch
     * configurations with Amazon VPC security groups in the Regions where
     * Amazon VPC is supported. Amazon VPC is currently available only in the
     * Amazon EC2 US-East (Northern Virginia) Region, and in the Amazon EC2
     * EU-West (Ireland) Region.
     * </p>
     * <p>
     * <b>NOTE:</b> At this time, Auto Scaling launch configurations don't
     * support compressed (e.g. gzipped) user data files.
     * </p>
     *
     * @param createLaunchConfigurationRequest Container for the necessary
     *           parameters to execute the CreateLaunchConfiguration service method on
     *           AmazonAutoScaling.
     * 
     * @throws LimitExceededException
     * @throws AlreadyExistsException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void createLaunchConfiguration(CreateLaunchConfigurationRequest createLaunchConfigurationRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<CreateLaunchConfigurationRequest> request = new CreateLaunchConfigurationRequestMarshaller().marshall(createLaunchConfigurationRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * Deletes the specified auto scaling group if the group has no
     * instances and no scaling activities in progress.
     * </p>
     * <p>
     * <b>NOTE:</b> To remove all instances before calling
     * DeleteAutoScalingGroup, you can call UpdateAutoScalingGroup to set the
     * minimum and maximum size of the AutoScalingGroup to zero.
     * </p>
     *
     * @param deleteAutoScalingGroupRequest Container for the necessary
     *           parameters to execute the DeleteAutoScalingGroup service method on
     *           AmazonAutoScaling.
     * 
     * @throws ResourceInUseException
     * @throws ScalingActivityInProgressException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void deleteAutoScalingGroup(DeleteAutoScalingGroupRequest deleteAutoScalingGroupRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DeleteAutoScalingGroupRequest> request = new DeleteAutoScalingGroupRequestMarshaller().marshall(deleteAutoScalingGroupRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * Disables monitoring of group metrics for the Auto Scaling group
     * specified in AutoScalingGroupName. You can specify the list of
     * affected metrics with the Metrics parameter.
     * </p>
     *
     * @param disableMetricsCollectionRequest Container for the necessary
     *           parameters to execute the DisableMetricsCollection service method on
     *           AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void disableMetricsCollection(DisableMetricsCollectionRequest disableMetricsCollectionRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DisableMetricsCollectionRequest> request = new DisableMetricsCollectionRequestMarshaller().marshall(disableMetricsCollectionRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * Updates the configuration for the specified AutoScalingGroup.
     * </p>
     * <p>
     * <b>NOTE:</b> To update an Auto Scaling group with a launch
     * configuration that has the InstanceMonitoring.enabled flag set to
     * false, you must first ensure that collection of group metrics is
     * disabled. Otherwise, calls to UpdateAutoScalingGroup will fail. If you
     * have previously enabled group metrics collection, you can disable
     * collection of all group metrics by calling DisableMetricsCollection.
     * </p>
     * <p>
     * The new settings are registered upon the completion of this call. Any
     * launch configuration settings take effect on any triggers after this
     * call returns. Triggers that are currently in progress aren't affected.
     * </p>
     * <p>
     * <b>NOTE:</b> If the new values are specified for the MinSize or
     * MaxSize parameters, then there will be an implicit call to
     * SetDesiredCapacity to set the group to the new MaxSize. All optional
     * parameters are left unchanged if not passed in the request.
     * </p>
     *
     * @param updateAutoScalingGroupRequest Container for the necessary
     *           parameters to execute the UpdateAutoScalingGroup service method on
     *           AmazonAutoScaling.
     * 
     * @throws ScalingActivityInProgressException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void updateAutoScalingGroup(UpdateAutoScalingGroupRequest updateAutoScalingGroupRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<UpdateAutoScalingGroupRequest> request = new UpdateAutoScalingGroupRequestMarshaller().marshall(updateAutoScalingGroupRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * Returns a full description of the launch configurations given the
     * specified names.
     * </p>
     * <p>
     * If no names are specified, then the full details of all launch
     * configurations are returned.
     * </p>
     *
     * @param describeLaunchConfigurationsRequest Container for the necessary
     *           parameters to execute the DescribeLaunchConfigurations service method
     *           on AmazonAutoScaling.
     * 
     * @return The response from the DescribeLaunchConfigurations service
     *         method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeLaunchConfigurationsResult describeLaunchConfigurations(DescribeLaunchConfigurationsRequest describeLaunchConfigurationsRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribeLaunchConfigurationsRequest> request = new DescribeLaunchConfigurationsRequestMarshaller().marshall(describeLaunchConfigurationsRequest);
        return invoke(request, new DescribeLaunchConfigurationsResultStaxUnmarshaller());
    }
    
    /**
     * <p>
     * Returns policy adjustment types for use in the PutScalingPolicy
     * action.
     * </p>
     *
     * @param describeAdjustmentTypesRequest Container for the necessary
     *           parameters to execute the DescribeAdjustmentTypes service method on
     *           AmazonAutoScaling.
     * 
     * @return The response from the DescribeAdjustmentTypes service method,
     *         as returned by AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeAdjustmentTypesResult describeAdjustmentTypes(DescribeAdjustmentTypesRequest describeAdjustmentTypesRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribeAdjustmentTypesRequest> request = new DescribeAdjustmentTypesRequestMarshaller().marshall(describeAdjustmentTypesRequest);
        return invoke(request, new DescribeAdjustmentTypesResultStaxUnmarshaller());
    }
    
    /**
     * <p>
     * Lists all the actions scheduled for your Auto Scaling group that
     * haven't been executed. To see a list of action already executed, see
     * the activity record returned in DescribeScalingActivities.
     * </p>
     *
     * @param describeScheduledActionsRequest Container for the necessary
     *           parameters to execute the DescribeScheduledActions service method on
     *           AmazonAutoScaling.
     * 
     * @return The response from the DescribeScheduledActions service method,
     *         as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeScheduledActionsResult describeScheduledActions(DescribeScheduledActionsRequest describeScheduledActionsRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribeScheduledActionsRequest> request = new DescribeScheduledActionsRequestMarshaller().marshall(describeScheduledActionsRequest);
        return invoke(request, new DescribeScheduledActionsResultStaxUnmarshaller());
    }
    
    /**
     * <p>
     * Creates a scheduled scaling action for a Auto Scaling group. If you
     * leave a parameter unspecified, the corresponding value remains
     * unchanged in the affected Auto Scaling group.
     * </p>
     *
     * @param putScheduledUpdateGroupActionRequest Container for the
     *           necessary parameters to execute the PutScheduledUpdateGroupAction
     *           service method on AmazonAutoScaling.
     * 
     * @throws LimitExceededException
     * @throws AlreadyExistsException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void putScheduledUpdateGroupAction(PutScheduledUpdateGroupActionRequest putScheduledUpdateGroupActionRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<PutScheduledUpdateGroupActionRequest> request = new PutScheduledUpdateGroupActionRequestMarshaller().marshall(putScheduledUpdateGroupActionRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * Returns a list of metrics and a corresponding list of granularities
     * for each metric.
     * </p>
     *
     * @param describeMetricCollectionTypesRequest Container for the
     *           necessary parameters to execute the DescribeMetricCollectionTypes
     *           service method on AmazonAutoScaling.
     * 
     * @return The response from the DescribeMetricCollectionTypes service
     *         method, as returned by AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeMetricCollectionTypesResult describeMetricCollectionTypes(DescribeMetricCollectionTypesRequest describeMetricCollectionTypesRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribeMetricCollectionTypesRequest> request = new DescribeMetricCollectionTypesRequestMarshaller().marshall(describeMetricCollectionTypesRequest);
        return invoke(request, new DescribeMetricCollectionTypesResultStaxUnmarshaller());
    }
    
    /**
     * <p>
     * Adjusts the desired size of the AutoScalingGroup by initiating
     * scaling activities. When reducing the size of the group, it is not
     * possible to define which EC2 instances will be terminated. This
     * applies to any auto-scaling decisions that might result in terminating
     * instances.
     * </p>
     * <p>
     * There are two common use cases for <code>SetDesiredCapacity</code> :
     * one for users of the Auto Scaling triggering system, and
     * another for developers who write their own triggering systems. Both
     * use cases relate to the concept of cooldown.
     * </p>
     * <p>
     * In the first case, if you use the Auto Scaling triggering system,
     * <code>SetDesiredCapacity</code> changes the size of your Auto Scaling
     * group without regard to the cooldown period. This could be useful, for
     * example, if Auto Scaling did something unexpected for some reason. If
     * your cooldown period is 10 minutes, Auto Scaling would normally reject
     * requests to change the size of the group for that entire 10 minute
     * period. The <code>SetDesiredCapacity</code> command allows you to
     * circumvent this restriction and change the size of the group before
     * the end of the cooldown period.
     * </p>
     * <p>
     * In the second case, if you write your own triggering system, you can
     * use <code>SetDesiredCapacity</code> to control the size of your Auto
     * Scaling group. If you want the same cooldown functionality that Auto
     * Scaling offers, you can configure <code>SetDesiredCapacity</code> to
     * honor cooldown by setting the <code>HonorCooldown</code> parameter to
     * <code>true</code> .
     * 
     * </p>
     *
     * @param setDesiredCapacityRequest Container for the necessary
     *           parameters to execute the SetDesiredCapacity service method on
     *           AmazonAutoScaling.
     * 
     * @throws ScalingActivityInProgressException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public void setDesiredCapacity(SetDesiredCapacityRequest setDesiredCapacityRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<SetDesiredCapacityRequest> request = new SetDesiredCapacityRequestMarshaller().marshall(setDesiredCapacityRequest);
        invoke(request, null);
    }
    
    /**
     * <p>
     * Terminates the specified instance. Optionally, the desired group size
     * can be adjusted.
     * </p>
     * <p>
     * <b>NOTE:</b> This call simply registers a termination request. The
     * termination of the instance cannot happen immediately.
     * </p>
     *
     * @param terminateInstanceInAutoScalingGroupRequest Container for the
     *           necessary parameters to execute the
     *           TerminateInstanceInAutoScalingGroup service method on
     *           AmazonAutoScaling.
     * 
     * @return The response from the TerminateInstanceInAutoScalingGroup
     *         service method, as returned by AmazonAutoScaling.
     * 
     * @throws ScalingActivityInProgressException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public TerminateInstanceInAutoScalingGroupResult terminateInstanceInAutoScalingGroup(TerminateInstanceInAutoScalingGroupRequest terminateInstanceInAutoScalingGroupRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<TerminateInstanceInAutoScalingGroupRequest> request = new TerminateInstanceInAutoScalingGroupRequestMarshaller().marshall(terminateInstanceInAutoScalingGroupRequest);
        return invoke(request, new TerminateInstanceInAutoScalingGroupResultStaxUnmarshaller());
    }
    
    /**
     * <p>
     * Returns a full description of each Auto Scaling group in the given
     * list. This includes all Amazon EC2 instances that are members of the
     * group. If a list of names is not provided, the service returns the
     * full details of all Auto Scaling groups.
     * </p>
     * <p>
     * This action supports pagination by returning a token if there are
     * more pages to retrieve. To get the next page, call this action again
     * with the returned token as the NextToken parameter.
     * </p>
     * 
     * @return The response from the DescribeAutoScalingGroups service
     *         method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeAutoScalingGroupsResult describeAutoScalingGroups() throws AmazonServiceException, AmazonClientException {
        return describeAutoScalingGroups(new DescribeAutoScalingGroupsRequest());
    }
    
    /**
     * <p>
     * Returns descriptions of what each policy does. This action supports
     * pagination. If the response includes a token, there are more records
     * available. To get the additional records, repeat the request with the
     * response token as the NextToken parameter.
     * </p>
     * 
     * @return The response from the DescribePolicies service method, as
     *         returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribePoliciesResult describePolicies() throws AmazonServiceException, AmazonClientException {
        return describePolicies(new DescribePoliciesRequest());
    }
    
    /**
     * <p>
     * Returns scaling process types for use in the ResumeProcesses and
     * SuspendProcesses actions.
     * </p>
     * 
     * @return The response from the DescribeScalingProcessTypes service
     *         method, as returned by AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeScalingProcessTypesResult describeScalingProcessTypes() throws AmazonServiceException, AmazonClientException {
        return describeScalingProcessTypes(new DescribeScalingProcessTypesRequest());
    }
    
    /**
     * <p>
     * Returns the scaling activities for the specified Auto Scaling group.
     * </p>
     * <p>
     * If the specified <i>ActivityIds</i> list is empty, all the activities
     * from the past six weeks are returned. Activities are sorted by
     * completion time. Activities still in progress appear first on the
     * list.
     * </p>
     * <p>
     * This action supports pagination. If the response includes a token,
     * there are more records available. To get the additional records,
     * repeat the request with the response token as the NextToken parameter.
     * </p>
     * 
     * @return The response from the DescribeScalingActivities service
     *         method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeScalingActivitiesResult describeScalingActivities() throws AmazonServiceException, AmazonClientException {
        return describeScalingActivities(new DescribeScalingActivitiesRequest());
    }
    
    /**
     * <p>
     * Returns a list of notification actions associated with Auto Scaling
     * groups for specified events.
     * </p>
     * 
     * @return The response from the DescribeNotificationConfigurations
     *         service method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeNotificationConfigurationsResult describeNotificationConfigurations() throws AmazonServiceException, AmazonClientException {
        return describeNotificationConfigurations(new DescribeNotificationConfigurationsRequest());
    }
    
    /**
     * <p>
     * Returns a list of all notification types that are supported by Auto
     * Scaling.
     * </p>
     * 
     * @return The response from the DescribeAutoScalingNotificationTypes
     *         service method, as returned by AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeAutoScalingNotificationTypesResult describeAutoScalingNotificationTypes() throws AmazonServiceException, AmazonClientException {
        return describeAutoScalingNotificationTypes(new DescribeAutoScalingNotificationTypesRequest());
    }
    
    /**
     * <p>
     * Returns a description of each Auto Scaling instance in the
     * InstanceIds list. If a list is not provided, the service returns the
     * full details of all instances up to a maximum of fifty. By default,
     * the service returns a list of 20 items.
     * </p>
     * <p>
     * This action supports pagination by returning a token if there are
     * more pages to retrieve. To get the next page, call this action again
     * with the returned token as the NextToken parameter.
     * </p>
     * 
     * @return The response from the DescribeAutoScalingInstances service
     *         method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeAutoScalingInstancesResult describeAutoScalingInstances() throws AmazonServiceException, AmazonClientException {
        return describeAutoScalingInstances(new DescribeAutoScalingInstancesRequest());
    }
    
    /**
     * <p>
     * Returns a full description of the launch configurations given the
     * specified names.
     * </p>
     * <p>
     * If no names are specified, then the full details of all launch
     * configurations are returned.
     * </p>
     * 
     * @return The response from the DescribeLaunchConfigurations service
     *         method, as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeLaunchConfigurationsResult describeLaunchConfigurations() throws AmazonServiceException, AmazonClientException {
        return describeLaunchConfigurations(new DescribeLaunchConfigurationsRequest());
    }
    
    /**
     * <p>
     * Returns policy adjustment types for use in the PutScalingPolicy
     * action.
     * </p>
     * 
     * @return The response from the DescribeAdjustmentTypes service method,
     *         as returned by AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeAdjustmentTypesResult describeAdjustmentTypes() throws AmazonServiceException, AmazonClientException {
        return describeAdjustmentTypes(new DescribeAdjustmentTypesRequest());
    }
    
    /**
     * <p>
     * Lists all the actions scheduled for your Auto Scaling group that
     * haven't been executed. To see a list of action already executed, see
     * the activity record returned in DescribeScalingActivities.
     * </p>
     * 
     * @return The response from the DescribeScheduledActions service method,
     *         as returned by AmazonAutoScaling.
     * 
     * @throws InvalidNextTokenException
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeScheduledActionsResult describeScheduledActions() throws AmazonServiceException, AmazonClientException {
        return describeScheduledActions(new DescribeScheduledActionsRequest());
    }
    
    /**
     * <p>
     * Returns a list of metrics and a corresponding list of granularities
     * for each metric.
     * </p>
     * 
     * @return The response from the DescribeMetricCollectionTypes service
     *         method, as returned by AmazonAutoScaling.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AmazonAutoScaling indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public DescribeMetricCollectionTypesResult describeMetricCollectionTypes() throws AmazonServiceException, AmazonClientException {
        return describeMetricCollectionTypes(new DescribeMetricCollectionTypesRequest());
    }
    


    /**
     * Returns additional metadata for a previously executed successful, request, typically used for
     * debugging issues where a service isn't acting as expected.  This data isn't considered part
     * of the result data returned by an operation, so it's available through this separate,
     * diagnostic interface.
     * <p>
     * Response metadata is only cached for a limited period of time, so if you need to access
     * this extra diagnostic information for an executed request, you should use this method
     * to retrieve it as soon as possible after executing the request.
     *
     * @param request
     *            The originally executed request
     *
     * @return The response metadata for the specified request, or null if none
     *         is available.
     */
    public ResponseMetadata getCachedResponseMetadata(AmazonWebServiceRequest request) {
        return client.getResponseMetadataForRequest(request);
    }

    private <X, Y extends AmazonWebServiceRequest> X invoke(Request<Y> request, Unmarshaller<X, StaxUnmarshallerContext> unmarshaller) {
        request.setEndpoint(endpoint);
        for (Entry<String, String> entry : request.getOriginalRequest().copyPrivateRequestParameters().entrySet()) {
            request.addParameter(entry.getKey(), entry.getValue());
        }

        AWSCredentials credentials = awsCredentialsProvider.getCredentials(); 
        AmazonWebServiceRequest originalRequest = request.getOriginalRequest();
        if (originalRequest != null && originalRequest.getRequestCredentials() != null) {
        	credentials = originalRequest.getRequestCredentials();
        }

        ExecutionContext executionContext = createExecutionContext();
        executionContext.setSigner(signer);
        executionContext.setCredentials(credentials);
        
        StaxResponseHandler<X> responseHandler = new StaxResponseHandler<X>(unmarshaller);
        DefaultErrorResponseHandler errorResponseHandler = new DefaultErrorResponseHandler(exceptionUnmarshallers);
        
        return (X)client.execute(request, responseHandler, errorResponseHandler, executionContext);
    }
}
        