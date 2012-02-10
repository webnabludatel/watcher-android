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
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

// Activity used to display the messages in the queue.
public class MessageQueueActivity extends Activity {	
	protected static final String[] MENU_ITEMS = new String[] { "Delete", "Cancel" }; 

	protected Button backButton;
	protected ListView messageQueue;
	protected MessageQueueAdapter adapter;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.message_queue);
        
        this.backButton = (Button)findViewById(R.id.backButton); 
        this.backButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				MessageQueueActivity.this.finish();
			}
		});
        
        this.messageQueue = (ListView)findViewById(R.id.queueList);        
        adapter = new MessageQueueAdapter( this, R.layout.messageitem);        
        this.messageQueue.setAdapter( adapter );
        registerForContextMenu( this.messageQueue );
    }  
    
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	if ( v.getId() == R.id.queueList ) {
    		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
			String messageText = this.adapter.getMessageText( info.position );
    		menu.setHeaderTitle( messageText );
    		for (int i = 0; i < MENU_ITEMS.length; i++) {
    			menu.add(Menu.NONE, i, i, MENU_ITEMS[i]);
			}
      	}
    }    
    
    public boolean onContextItemSelected(MenuItem item) {
	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	    int menuItemIndex = item.getItemId();
		String menuItemName = MENU_ITEMS[menuItemIndex];

		if ( menuItemName.equals( "Delete") ) {
			this.adapter.removeItemAt( info.position );
			this.messageQueue.invalidateViews();
		}
		
		return true;
    }    
    
}
