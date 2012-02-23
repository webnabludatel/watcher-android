package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.Consts;

import android.content.Intent;
import android.os.Bundle;


public class ViolationCheckListActivity extends ABSNabludatelActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		Intent intent = getIntent();
		int layoutId = intent.getIntExtra(Consts.PREFS_LAYOUT_ID, -1);
		String title = intent.getStringExtra(Consts.PREFS_TITLE);
		if(title != null){
			setTitle(title);
		}
		if(layoutId != -1){
			this.setContentView(layoutId);
		}
	}
}
