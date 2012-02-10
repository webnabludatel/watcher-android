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

import com.amazon.aws.demo.AlertActivity;
import com.amazon.aws.demo.R;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class S3CreateBucket extends AlertActivity {
	
	protected Button submitButton;
	protected EditText bucketName;
	protected TextView introText;
	protected Handler mHandler;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
        setContentView(R.layout.create_item);
        submitButton = (Button) findViewById(R.id.create_it_submit_button);
        bucketName = (EditText) findViewById(R.id.create_it_input_field);
        introText = (TextView) findViewById(R.id.create_it_intro_text);
        introText.setText("Enter Bucket Name:");
        wireSubmitButton();
    }
	
	public void wireSubmitButton(){
		submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				bucketName.setVisibility(View.INVISIBLE);
				try{
					S3.createBucket(bucketName.getText().toString());
					finish();
				} catch(Throwable e){
    				setStackAndPost(e);
				}
			}
		});
	}
	
	

}
