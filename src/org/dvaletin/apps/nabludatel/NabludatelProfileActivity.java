package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.PollingPlaceSQLHelper;

import android.os.Bundle;



public class NabludatelProfileActivity extends ABSNabludatelActivity {
	PollingPlaceSQLHelper mPollingPlaceSQLHelper = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPollingPlaceSQLHelper = new PollingPlaceSQLHelper(this);
		setContentView(R.layout.nabludatel_profile);
	}
	
	
	
}
