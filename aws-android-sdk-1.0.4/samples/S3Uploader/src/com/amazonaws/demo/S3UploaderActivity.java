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

import java.io.File;
import java.net.URL;
import java.util.Date;


import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;

public class S3UploaderActivity extends Activity {
	
    private AmazonS3Client s3Client = new AmazonS3Client( new BasicAWSCredentials( Constants.ACCESS_KEY_ID, Constants.SECRET_KEY ) );
		    
	private Button selectPhoto = null;
	private Button showInBrowser = null;
	
	private static final int PHOTO_SELECTED = 1;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        selectPhoto = (Button) findViewById(R.id.select_photo_button);
        selectPhoto.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// Start the image picker.
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("image/*");
				startActivityForResult(intent, PHOTO_SELECTED);
			}
		});

        showInBrowser = (Button) findViewById(R.id.show_in_browser_button);
        showInBrowser.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					// Ensure that the image will be treated as such.
					ResponseHeaderOverrides override = new ResponseHeaderOverrides();
					override.setContentType( "image/jpeg" );
					
					// Generate the presigned URL.
					Date expirationDate = new Date( System.currentTimeMillis() + 3600000 );   // Added an hour's worth of milliseconds to the current time.
					GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest( Constants.getPictureBucket(), Constants.PICTURE_NAME );
					urlRequest.setExpiration( expirationDate );
					urlRequest.setResponseHeaders( override );
					
					URL url = s3Client.generatePresignedUrl( urlRequest );
					
					// Display in Browser.
	                startActivity( new Intent( Intent.ACTION_VIEW, Uri.parse( url.toURI().toString() ) ) );
				}
				catch ( Exception exception ) {
					S3UploaderActivity.this.displayAlert( "Browser Failure", exception.getMessage() );
				}
			}
		});
    }
    
    
    // This method is automatically called by the image picker when an image is selected. 
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) { 
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent); 

        switch(requestCode) { 
        case PHOTO_SELECTED:
            if (resultCode == RESULT_OK) {  
            	// THe file location of the image selected.
                Uri selectedImage = imageReturnedIntent.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);
                File fileToUpload = new File(filePath);
                String fileName = fileToUpload.getName();
                TelephonyManager t = (TelephonyManager) getSystemService(Context. TELEPHONY_SERVICE);
            	String deviceId = t.getDeviceId();
                cursor.close();

                // Put the image data into S3.
                try {
//                	s3Client.createBucket( Constants.getPictureBucket() );
                	
                	PutObjectRequest por = new PutObjectRequest( Constants.getPictureBucket(), "android/"+deviceId+"/"+fileName, fileToUpload );  // Content type is determined by file extension.
                	android.util.Log.d("Upload", s3Client.putObject( por.withCannedAcl(CannedAccessControlList.PublicRead) ).getETag());
                }
                catch ( Exception exception ) {
                	displayAlert( "Upload Failure", exception.getMessage() );
                }
            }
        }
    }
    
    // Display an Alert message for an error or failure.
    protected void displayAlert( String title, String message ) {
        AlertDialog.Builder confirm = new AlertDialog.Builder( this );
        confirm.setTitle( title);
        confirm.setMessage( message );
        confirm.setNegativeButton( "OK", new DialogInterface.OnClickListener() {
            public void onClick( DialogInterface dialog, int which ) {
            	S3UploaderActivity.this.finish();
            }
        } );
        confirm.show().show();                
    }
    
}
