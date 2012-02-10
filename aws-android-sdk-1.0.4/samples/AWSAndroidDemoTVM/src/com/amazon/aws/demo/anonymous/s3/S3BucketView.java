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
package com.amazon.aws.demo.anonymous.s3;


import java.util.List;

import com.amazon.aws.demo.anonymous.CustomListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class S3BucketView extends CustomListActivity{
	
	protected List<String> objectNameList;
	protected String bucketName;
	
	private static final String SUCCESS = "Object List";
	private static final int NUM_OBJECTS = 6;
	
	private final Runnable postResults = new Runnable() {
		@Override
		public void run(){
			updateUi(objectNameList, SUCCESS);
		}
	};
	
	private final Runnable postMore = new Runnable() {
		@Override
		public void run(){
			updateList(objectNameList);
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enablePagination();
        Bundle extras = this.getIntent().getExtras();
        bucketName = extras.getString(S3.BUCKET_NAME);
        startPopulateList();
    }
    
    protected void obtainListItems(){
		objectNameList = S3.getObjectNamesForBucket(bucketName, NUM_OBJECTS);
        getHandler().post(postResults);
    }
        
    protected void obtainMoreItems(){
    	objectNameList = S3.getMoreObjectNamesForBucket();
    	getHandler().post(postMore);
    }

	protected void wireOnListClick(){
		getItemList().setOnItemClickListener(new OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> list, View view, int position, long id) {
			    	final String objectName = ((TextView)view).getText().toString();
					Intent objectViewIntent = new Intent(S3BucketView.this, S3ObjectView.class);
					objectViewIntent.putExtra( S3.BUCKET_NAME, bucketName);
					objectViewIntent.putExtra( S3.OBJECT_NAME, objectName );
					startActivity(objectViewIntent);
		    }
		 });
	}
    		
}
