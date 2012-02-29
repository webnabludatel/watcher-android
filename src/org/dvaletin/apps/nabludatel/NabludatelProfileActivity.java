package org.dvaletin.apps.nabludatel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;



public class NabludatelProfileActivity extends ABSNabludatelActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCurrentPollingPlaceId = 0;
		setContentView(R.layout.nabludatel_profile);
	}
	
	@Override
	public void onBackPressed(){
		Intent intent = getReturnIntent();
		EditText last_name = (EditText) findViewById(R.id.last_name);
		EditText first_name = (EditText) findViewById(R.id.first_name);
		EditText middle_name = (EditText) findViewById(R.id.middle_name);
		EditText email = (EditText) findViewById(R.id.email);
		EditText phone = (EditText) findViewById(R.id.phone);
		intent.putExtra("last_name", last_name.getText().toString());
		intent.putExtra("first_name", first_name.getText().toString());
		intent.putExtra("middle_name", middle_name.getText().toString());
		intent.putExtra("email", email.getText().toString());
		intent.putExtra("phone", phone.getText().toString());
		super.onBackPressed();
	}
	
}
