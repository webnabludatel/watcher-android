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

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

// Main Activity Window
public class MessageBoardActivity extends Activity {
	
	protected InputMethodManager inputMethodManager;
	protected EditText message;
	protected EditText email;
	protected EditText sms;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.inputMethodManager = (InputMethodManager)getSystemService( Context.INPUT_METHOD_SERVICE );
        
        this.message = (EditText)findViewById( R.id.message );
        this.email = (EditText)findViewById( R.id.email );
        this.sms = (EditText)findViewById( R.id.sms );
        
        if ( Constants.ACCESS_KEY_ID.equals( "CHANGE ME" ) ) {
        	this.displayCredentialsAlert();
        }
    }
    
    public void post( View view ) {
    	this.inputMethodManager.hideSoftInputFromWindow( this.message.getWindowToken(), 0 );
    	MessageBoard.instance().post( this.message.getText().toString() );
    }

    public void subscribeEmail( View view ) {
    	this.inputMethodManager.hideSoftInputFromWindow( this.email.getWindowToken(), 0 );
    	MessageBoard.instance().subscribeEmail( this.email.getText().toString() );
    	Toast.makeText( this, Constants.CONFIRM_SUBSCRIPTION_MESSAGE, Toast.LENGTH_LONG ).show();
    }

    public void subscribeSms( View view ) {
    	this.inputMethodManager.hideSoftInputFromWindow( this.sms.getWindowToken(), 0 );
    	
    	if ( this.sms.getText().length() < 10 ) {
    		this.displaySmsAlert();
    	}
    	else {
    		MessageBoard.instance().subscribeSms( this.sms.getText().toString() );
    		Toast.makeText( this, Constants.CONFIRM_SUBSCRIPTION_MESSAGE, Toast.LENGTH_LONG ).show();
    	}
    }

    public void viewMembers( View view ) {
		startActivity( new Intent( MessageBoardActivity.this, MemberListActivity.class) ); 
    }
    
    public void viewQueue( View view ) {
       	Toast.makeText( this, Constants.QUEUE_NOTICE, Toast.LENGTH_LONG ).show();
		startActivity( new Intent( MessageBoardActivity.this, MessageQueueActivity.class) ); 
    }    
    
    protected void displayCredentialsAlert() {
        AlertDialog.Builder confirm = new AlertDialog.Builder( this );
        confirm.setTitle("Credential Problem!");
        confirm.setMessage( "AWS Credentials not configured correctly.  Please review the README file." );
        confirm.setNegativeButton( "OK", new DialogInterface.OnClickListener() {
            public void onClick( DialogInterface dialog, int which ) {
            }
        } );
        confirm.show().show();                
    }    
    
    protected void displaySmsAlert() {
        AlertDialog.Builder confirm = new AlertDialog.Builder( this );
        confirm.setTitle("SMS Subscriptions");
        confirm.setMessage( "SMS Subscritions must include country codes.  1 for US phones." );
        confirm.setNegativeButton( "OK", new DialogInterface.OnClickListener() {
            public void onClick( DialogInterface dialog, int which ) {
            }
        } );
        confirm.show().show();                
    }        
}