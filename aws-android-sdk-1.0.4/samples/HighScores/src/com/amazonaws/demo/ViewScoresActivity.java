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
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class ViewScoresActivity extends Activity {
	protected static final String[] MENU_ITEMS = new String[] { "View", "Delete", "Cancel" }; 
	protected ScoresAdapter scoresAdapter;
	
	protected Button backButton;
	protected ListView scores;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Intent intent = this.getIntent();
        int sortMethod = intent.getIntExtra( "SortMethod",  HighScoreList.NO_SORT );
        
        setContentView(R.layout.high_score_table);
        
        backButton = (Button)findViewById(R.id.backButton); 
        backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ViewScoresActivity.this.finish();
			}
		});
        
        scores = (ListView)findViewById(R.id.listView);
        registerForContextMenu( scores );
                
        HighScoreList hs = new HighScoreList(sortMethod); 
        scoresAdapter = new ScoresAdapter(hs, this, R.layout.list_item);
        
		scores.setAdapter( scoresAdapter );
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    	if (v.getId()==R.id.listView) {
    		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
    		menu.setHeaderTitle( this.scoresAdapter.getItem( info.position ).getPlayer() );
    		for (int i = 0; i < MENU_ITEMS.length; i++) {
    			menu.add(Menu.NONE, i, i, MENU_ITEMS[i]);
			}
      	}
    }    
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
	    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
	    int menuItemIndex = item.getItemId();
		String menuItemName = MENU_ITEMS[menuItemIndex];

		if ( menuItemName.equals( "Delete") ) {
			this.scoresAdapter.removeItemAt( info.position );
			this.scoresAdapter.notifyDataSetChanged();
		}
		else if ( menuItemName.equals( "View" ) ) {
			HighScore player = new HighScoreList().getPlayer( this.scoresAdapter.getItem( info.position ).getPlayer() );
			
			Intent intent = new Intent( this, ViewPlayerActivity.class);
			intent.putExtra( "player", player );
			
			startActivity( intent );			
		}		
		
		return true;
    }    
}
