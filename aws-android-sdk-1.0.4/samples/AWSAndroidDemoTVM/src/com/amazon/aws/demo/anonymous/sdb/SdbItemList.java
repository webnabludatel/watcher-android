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
package com.amazon.aws.demo.anonymous.sdb;

import android.os.Bundle;

import com.amazon.aws.demo.anonymous.CustomListActivity;

public class SdbItemList extends CustomListActivity {
	
	protected String[] itemNameList;
    protected String domainName;
	
	private static final String SUCCESS = "Item List";
	
	private final Runnable postResults = new Runnable() {
		@Override
		public void run(){
			updateUi(itemNameList, SUCCESS);
		}
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = this.getIntent().getExtras();
        domainName = extras.getString(SimpleDB.DOMAIN_NAME);
        startPopulateList();
    }
    
    protected void obtainListItems(){
		itemNameList = SimpleDB.getItemNamesForDomain(domainName);
        getHandler().post(postResults);
    }
    
}
