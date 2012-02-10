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

package com.amazonaws.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.CreateTopicRequest;
import com.amazonaws.services.sns.model.CreateTopicResult;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicRequest;
import com.amazonaws.services.sns.model.ListSubscriptionsByTopicResult;
import com.amazonaws.services.sns.model.ListTopicsRequest;
import com.amazonaws.services.sns.model.ListTopicsResult;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SetTopicAttributesRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.Subscription;
import com.amazonaws.services.sns.model.Topic;
import com.amazonaws.services.sns.model.UnsubscribeRequest;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesRequest;
import com.amazonaws.services.sqs.model.GetQueueAttributesResult;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SetQueueAttributesRequest;

// This singleton class provides all the functionality to manipulate the Amazon 
// SNS Topic and Amazon SQS Queue used in this sample application.
public class MessageBoard {

	private static MessageBoard instance = null;
	private AmazonSNSClient snsClient = null;
	private AmazonSQSClient sqsClient = null;
	private String topicARN;
	private String queueUrl;
	
	public synchronized static MessageBoard instance() {
		if ( instance == null ) {
			instance = new MessageBoard();
		}
		
		return instance;
	}
	
	public MessageBoard() {
		AWSCredentials credentials = new BasicAWSCredentials( Constants.ACCESS_KEY_ID, Constants.SECRET_KEY );		
		this.snsClient = new AmazonSNSClient( credentials );
		this.sqsClient = new AmazonSQSClient( credentials );

	    // Find the Topic for this App or create one.
		this.topicARN = this.findTopicArn();
		if ( topicARN == null ) {
			this.topicARN = this.createTopic();
		}

	    // Find the Queue for this App or create one.
		this.queueUrl = this.findQueueUrl();
		if ( this.queueUrl == null ) {
			this.queueUrl = this.createQueue();

			// Allow time for the queue to be created.
	        try { Thread.sleep( 4 * 1000 ); } catch ( Exception exception ) {}
			
			this.subscribeQueue();
		}
	}
	
	protected String createTopic() {
		try {
	        CreateTopicRequest ctr = new CreateTopicRequest( Constants.TOPIC_NAME );
	        CreateTopicResult result = snsClient.createTopic( ctr );
	        
	        // Adding the DisplayName attribute to the Topic allows for SMS notifications.
	        SetTopicAttributesRequest tar = new SetTopicAttributesRequest( result.getTopicArn(), "DisplayName", "MessageBoard" );
	        this.snsClient.setTopicAttributes( tar );  

	        return result.getTopicArn();
		}
		catch ( Exception exception ) {
			System.out.println( "Exception  = " + exception );
			return null;
		}	        
	}
			
	public void subscribeEmail( String email ) {
		try {
			SubscribeRequest sr = new SubscribeRequest( this.topicARN, "email", email );
			this.snsClient.subscribe( sr );
		}
		catch ( Exception exception ) {
			System.out.println( "Exception  = " + exception );
		}			
	}
	
	public void subscribeSms( String sms ) {
		try {
			SubscribeRequest sr = new SubscribeRequest( this.topicARN, "sms", sms );
			this.snsClient.subscribe( sr );
		}
		catch ( Exception exception ) {
			System.out.println( "Exception  = " + exception );
		}			
	}
	
	// Post a notification to the topic.
	public void post( String message ) {
		try {
			PublishRequest pr = new PublishRequest( this.topicARN, message );
			this.snsClient.publish( pr );	
		}
		catch ( Exception exception ) {
			System.out.println( "Exception  = " + exception );
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Subscription> listSubscribers() {
		try {
	        ListSubscriptionsByTopicRequest ls = new ListSubscriptionsByTopicRequest( this.topicARN );
	        ListSubscriptionsByTopicResult response = this.snsClient.listSubscriptionsByTopic( ls );
	        return response.getSubscriptions();
		}
		catch ( Exception exception ) {
			System.out.println( "Exception  = " + exception );
			return Collections.emptyList();
		}
	}
	
	// Undsubscribe an endpoint from the topic.
	public void removeSubscriber( String subscriptionArn ) {
		try {
			UnsubscribeRequest unsubscribeRequest = new UnsubscribeRequest( subscriptionArn );
			this.snsClient.unsubscribe( unsubscribeRequest );
		}
		catch ( Exception exception ) {
			System.out.println( "Exception  = " + exception );
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Message> getMessageQueue() {
		try {
			ReceiveMessageRequest rmr = new ReceiveMessageRequest( this.queueUrl );
			rmr.setMaxNumberOfMessages( 10 );
			rmr.setVisibilityTimeout( 30 );

			List<Message> messages = null;
			ArrayList<Message> allMessages = new ArrayList<Message>( 100 );
			do {
				ReceiveMessageResult result = this.sqsClient.receiveMessage( rmr );
				messages = result.getMessages();	
				allMessages.addAll( messages );
				try { Thread.sleep( 1 * 1000 ); } catch ( Exception exception ) {}
			}
			while ( !messages.isEmpty() );
			
			return allMessages;
		}
		catch ( Exception exception ) {
			System.out.println( "Exception  = " + exception );
			return Collections.emptyList();			
		}
	} 
	
	protected void subscribeQueue() {
		try {
			String queueArn = this.getQueueArn( this.queueUrl );
		
			SubscribeRequest request = new SubscribeRequest();
			request.withEndpoint( queueArn ).withProtocol( "sqs" ).withTopicArn( this.topicARN );
		
			this.snsClient.subscribe( request );
		}
		catch ( Exception exception ) {
			System.out.println( "Exception = " + exception );
		}
	}
	
	private String createQueue() {
		try {
			CreateQueueRequest cqr = new CreateQueueRequest( Constants.QUEUE_NAME );
			CreateQueueResult result = this.sqsClient.createQueue( cqr );

	        String queueArn = this.getQueueArn( result.getQueueUrl() );
	        this.addPolicyToQueueForTopic( result.getQueueUrl(), queueArn );

	        return result.getQueueUrl();
		}
		catch ( Exception exception ) {
			System.out.println( "Exception = " + exception );
			return null;			
		}		
	}
	
	// Get the QueueArn attribute from the Queue.  The QueueArn is necessary for create a policy on the queue
	// that allows for messages from the Amazon SNS Topic.
	private String getQueueArn( String queueUrl ) {
		try {
			GetQueueAttributesRequest gqar = new GetQueueAttributesRequest( queueUrl ).withAttributeNames( new String[] { "QueueArn" } );
			GetQueueAttributesResult result = this.sqsClient.getQueueAttributes( gqar );
			
	        return (String)result.getAttributes().get( "QueueArn" );
		}
		catch ( Exception exception ) {
			System.out.println( "Exception = " + exception );
			return null;
		}
	}
	
	// Add a policy to a specific queue by setting the queue's Policy attribute.
	// Assigning a policy to the queue is necessary as described in Amazon SNS' FAQ:
	// http://aws.amazon.com/sns/faqs/#26
	private void addPolicyToQueueForTopic( String queueUrl, String queueArn ) {
        HashMap<String, String> attributes = new HashMap<String, String>();
        attributes.put("Policy", generateSqsPolicyForTopic( queueArn, this.topicARN ) );
        this.sqsClient.setQueueAttributes(new SetQueueAttributesRequest( queueUrl, attributes ) );

        // It can take some time for policy to propagate to the queue.
	}

	// Creates the policy object that is necessary to allow the topic to send message to the queue.  The topic will 
	// send all topic notifications to the queue.
	private String generateSqsPolicyForTopic( String queueArn, String topicArn ) {
        String policy =
            "{ " +
            "  \"Version\":\"2008-10-17\"," +
            "  \"Id\":\"" + queueArn + "/policyId\"," +
            "  \"Statement\": [" +
            "    {" +
            "        \"Sid\":\"" + queueArn + "/statementId\"," +
            "        \"Effect\":\"Allow\"," +
            "        \"Principal\":{\"AWS\":\"*\"}," +
            "        \"Action\":\"SQS:SendMessage\"," +
            "        \"Resource\": \"" + queueArn + "\"," +
            "        \"Condition\":{" +
            "            \"StringEquals\":{\"aws:SourceArn\":\"" + topicArn + "\"}" +
            "        }" +
            "    }" +
            "  ]" +
            "}";

        return policy;
    }
	
	// Determines if a topic exists with the given topic name.
	// The topic name is assigned in the Constants.java file.
	@SuppressWarnings("unchecked")
	protected String findTopicArn() {
	    try {
	        String topicNameToFind = ":" + Constants.TOPIC_NAME;
	        String nextToken = null;
	        do {
	            ListTopicsRequest listTopicsRequest = new ListTopicsRequest( nextToken );
	            ListTopicsResult result = this.snsClient.listTopics( listTopicsRequest );   
	            
	            for ( Topic topic : (List<Topic>)result.getTopics() ) {
	                if ( topic.getTopicArn().endsWith( topicNameToFind ) ) {
	                    return topic.getTopicArn();
	                }
	            }
	            
	            nextToken = result.getNextToken();
	        }
	        while ( nextToken != null );
	        
	        return null;
	    }
	    catch ( Exception exception ) {
			System.out.println( "Exception  = " + exception );
	        return null;
	    }    		
	}
	
	// Determine if a queue exists with the given queue name.
	// The queue name is assigned in the Constants.java file.
	@SuppressWarnings("unchecked")
	protected String findQueueUrl() {
	    try {
	        String queueNameToFind = "/" + Constants.QUEUE_NAME;
	        ListQueuesResult queuesResult = this.sqsClient.listQueues();
	            
            for ( String queueUrl : (List<String>)queuesResult.getQueueUrls() ) {
                if ( queueUrl.endsWith( queueNameToFind ) ) {
                    return queueUrl;
                }
            }
	            
	        return null;
	    }
	    catch ( Exception exception ) {
			System.out.println( "Exception  = " + exception );
	        return null;
	    }    		
	}
			
	public void deleteMessageFromQueue( Message message ) {
		try {
			DeleteMessageRequest request = new DeleteMessageRequest( this.queueUrl, message.getReceiptHandle() );
			this.sqsClient.deleteMessage( request );
		}
		catch ( Exception exception ) {
			System.out.println( "Exception = " + exception );
		}
	}
}
