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
import java.util.List;

import com.amazonaws.services.sqs.model.Message;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MessageQueueAdapter extends ArrayAdapter<Message> {
	
	protected List<Message> messages = new ArrayList<Message>(1000);
	
	public MessageQueueAdapter( Context context, int resourceId ) {
		super( context, resourceId );
		this.messages = MessageBoard.instance().getMessageQueue();
	}
	
	public int getCount() {
		return this.messages.size();
	}
	
	public Message getItem( int pos ) {
		return this.messages.get( pos );
	}
	
	public String getMessageText( int pos ) {
		String messageText = "";
		
		Message message = this.getItem( pos );
		if ( message != null && message.getBody() != null ) {
	    	messageText = this.extractMessageFromJsonObject( message.getBody() );
		}

		return messageText;
	}
	
	public void removeItemAt( int pos ) {
		Message message = this.getItem( pos );	
		MessageBoard.instance().deleteMessageFromQueue( message );
		this.messages.remove( pos );
	}
	
	public View getView( int pos, View convertView, ViewGroup parent ) {
		TextView messageText = new TextView( parent.getContext() );
		messageText.setText( this.getMessageText( pos ) );
		messageText.setGravity( Gravity.LEFT );
		messageText.setPadding(10, 10, 10, 10);
		messageText.setMaxWidth( 200 );
		messageText.setMinWidth( 200 );
		messageText.setTextSize(16);
						
		return messageText;
	}
	
	//-------------------------------------------
	// Better to use an actual JSON Parser here.
	//-------------------------------------------
	private String extractMessageFromJsonObject( String json ) {
		try {
			int startIndex = json.indexOf( "\"Message\"" );
			if ( startIndex != -1 ) {			
				int colonIndex = json.indexOf( ":", startIndex );
				int startQuoteIndex = json.indexOf( "\"", colonIndex );
				int endQuoteIndex = json.indexOf( "\"", startQuoteIndex + 1 );
				
				return json.substring( startQuoteIndex + 1, endQuoteIndex );
			}
			else {
				return null;
			}
		}
		catch ( Exception exception ) {
			return null;
		}
	}
}
