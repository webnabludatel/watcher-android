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
package com.amazon.aws.demo.s3;

import java.util.List;

import com.amazon.aws.demo.CustomListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class S3BucketList extends CustomListActivity{
	
	protected List<String> bucketNameList;
	
	private static final String SUCCESS = "Bucket List";

	
	private final Runnable postResults = new Runnable() {
		@Override
		public void run(){
			updateUi(bucketNameList, SUCCESS);
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startPopulateList();
    }
    
    protected void obtainListItems(){
		bucketNameList = S3.getBucketNames();
        getHandler().post(postResults);
    }
    
   	@Override	
	protected void wireOnListClick(){
		getItemList().setOnItemClickListener(new OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> list, View view, int position, long id) {
		    	final String bucketName = ((TextView)view).getText().toString();
				Intent bucketViewIntent = new Intent(S3BucketList.this, S3BucketView.class);
				bucketViewIntent.putExtra( S3.BUCKET_NAME, bucketName );
				startActivity(bucketViewIntent);
		    }
		 });
	}
    		
}
