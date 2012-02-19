package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.GenericSQLHelper;
import android.os.Bundle;



public class NabludatelProfileActivity extends ABSNabludatelActivity {
	GenericSQLHelper mPollingPlaceSQLHelper = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPollingPlaceSQLHelper = new GenericSQLHelper(this);
		setContentView(R.layout.nabludatel_profile);
	}
	
	
	
}
