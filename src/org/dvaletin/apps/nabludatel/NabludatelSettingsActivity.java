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

	public void onManualSetupClick(View v){
		Intent intent = new Intent(this, NabludatelProfileActivity.class);
		intent.putExtra(Consts.PREFS_ELECTIONS_DISRICT, 0l);
		startActivityForResult(intent, NABLUDATEL_MANUAL_SETUP);
	}
}
