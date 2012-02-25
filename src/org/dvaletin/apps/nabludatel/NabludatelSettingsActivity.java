package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.server.NabludatelCloud;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;



public class NabludatelSettingsActivity extends ABSNabludatelActivity {
	private static final int NABLUDATEL_MANUAL_SETUP = 1001;
	private static final int NABLUDATEL_TWITTER_SETUP = 1002;
	private static final int NABLUDATEL_FACEBOOK_SETUP = 1003;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nabludatel_settings);
	}
	
	
	public void onTwitterSetupClick(View v){
		
	}
	
	public void onFacebookSetupClick(View v){
		
	}

	
	public void onManualSetipClick(View v){
		Intent intent = new Intent(this, NabludatelProfileActivity.class);
		startActivityForResult(intent, NABLUDATEL_MANUAL_SETUP);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		switch(requestCode){
		case NABLUDATEL_MANUAL_SETUP:{
			String last_name = data.getStringExtra("last_name");
			cloudHelper.postNewMessage("last_name", last_name, lat, lng, System.currentTimeMillis());
			String first_name = data.getStringExtra("first_name");
			cloudHelper.postNewMessage("first_name", first_name, lat, lng, System.currentTimeMillis());
			String middle_name = data.getStringExtra("middle_name");
			cloudHelper.postNewMessage("middle_name", middle_name, lat, lng, System.currentTimeMillis());
			String email = data.getStringExtra("email");
			cloudHelper.postNewMessage("email", email, lat, lng, System.currentTimeMillis());
			String phone = data.getStringExtra("phone");
			cloudHelper.postNewMessage("phone", phone, lat, lng, System.currentTimeMillis());
			
			break;
		}
		case NABLUDATEL_TWITTER_SETUP:{
			break;
		}
		case NABLUDATEL_FACEBOOK_SETUP:{
			break;
		}
		}
	}
	
}
