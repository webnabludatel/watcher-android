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

public class Constants {
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This sample App is for demonstration purposes only.
    // It is not secure to embed your credentials into source code.
    // Please read the following article for getting credentials
    // to devices securely.
    // http://aws.amazon.com/articles/Mobile/4611615499399490
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public static final String ACCESS_KEY_ID = "AKIAJ4IVZVGUAAYUUWZQ";
	public static final String SECRET_KEY = "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";
	
	public static final String PICTURE_BUCKET = "nabludatel";
	public static final String PICTURE_NAME = "test.jpg";
	
	
	public static String getPictureBucket() {
		return (PICTURE_BUCKET).toLowerCase();
	}
	
}
