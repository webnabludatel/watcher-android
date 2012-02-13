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
package com.amazon.aws.demo.anonymous.sns;

import java.util.List;

import com.amazon.aws.demo.anonymous.CustomListActivity;
import com.amazon.aws.demo.anonymous.sqs.SimpleQueue;
import com.amazon.aws.demo.anonymous.sqs.SqsDeleteQueueList;
import com.amazonaws.services.sns.model.DeleteTopicRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SnsDeleteTopicList extends CustomListActivity{
	
	protected List<String> topicNameList;
	protected String clickedTopicArn;
	
	private static final String SUCCESS = "Delete Topic List";
	
	private final Runnable postResults = new Runnable() {
		@Override
		public void run(){
			updateUi(topicNameList, SUCCESS);
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startPopulateList();
    }
    
    protected void obtainListItems(){
		topicNameList = SimpleNotification.getTopicNames();
        getHandler().post(postResults);
    }
    
	protected void wireOnListClick(){
		getItemList().setOnItemClickListener(new OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> list, View view, int position, long id) {
		    	clickedTopicArn = ((TextView)view).getText().toString();
		    	AlertDialog.Builder builder = new AlertDialog.Builder(SnsDeleteTopicList.this);
		    	builder.setMessage("Are you sure you want to delete: "+ clickedTopicArn)
		    		   .setCancelable(false)
		    		   .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
								SimpleNotification.getInstance().deleteTopic(new DeleteTopicRequest(clickedTopicArn));
								startPopulateList();
						}
					})
						.setNegativeButton("No", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								dialog.cancel();
							}
						});
		    	builder.show();
			}
		 });
	}
}
