package org.dvaletin.apps.nabludatel;

import java.io.File;
import java.util.HashMap;

import org.dvaletin.apps.nabludatel.server.NabludatelCloud;
import org.dvaletin.apps.nabludatel.utils.Consts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;



public class NabludatelSettingsActivity extends ABSNabludatelActivity {
	private static final int NABLUDATEL_MANUAL_SETUP = 1001;
	private static final int NABLUDATEL_TWITTER_SETUP = 1002;
	private static final int NABLUDATEL_FACEBOOK_SETUP = 1003;
	
	NabludatelCloud cloudHelper;
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nabludatel_settings);
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		
		cloudHelper = new NabludatelCloud(deviceId);
		
		cloudHelper.authentication();
		if(cloudHelper.isAuthenticated()){
			((TextView) findViewById(R.id.nabludatel_registration_status))
			.setText("Зарегистрирован " + cloudHelper.getAuthenticatedUserId());
		}
	}
	
	
	public void onTwitterSetupClick(View v){
		
	}
	
	public void onFacebookSetupClick(View v){
		
	}

	@Override
	public void onMakePhotoClick(View v) {
		if(v.getTag() != null)
			startNarusheniyePhoto(v.getTag().toString());
	}
	
	public void onManualSetipClick(View v){
		Intent intent = new Intent(this, NabludatelProfileActivity.class);
		intent.putExtra(Consts.PREFS_ELECTIONS_DISRICT, 0l);
		startActivityForResult(intent, NABLUDATEL_MANUAL_SETUP);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		switch(requestCode){
		case Consts.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE: {
			if (resultCode == 0) {
				// The user has cancelled image capture
				if (photo.size() > 0) {
					photo.remove(photo.size() - 1);
				}
				
			}else{
				this.mElectionsDB.open();
				savePhotos();
				photo = new HashMap<File, String>();
			}
			break;
		}
		case NABLUDATEL_MANUAL_SETUP:{
			String last_name = data.getStringExtra("last_name");
			long res;
			if(cloudHelper.isAuthenticated()){
				res = cloudHelper.postNewMessage("last_name", last_name, lat, lng, System.currentTimeMillis());
				String first_name = data.getStringExtra("first_name");
				res = cloudHelper.postNewMessage("first_name", first_name, lat, lng, System.currentTimeMillis());
				String middle_name = data.getStringExtra("middle_name");
				res = cloudHelper.postNewMessage("middle_name", middle_name, lat, lng, System.currentTimeMillis());
				String email = data.getStringExtra("email");
				res = cloudHelper.postNewMessage("email", email, lat, lng, System.currentTimeMillis());
				String phone = data.getStringExtra("phone");
				res = cloudHelper.postNewMessage("phone", phone, lat, lng, System.currentTimeMillis());
			}
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
