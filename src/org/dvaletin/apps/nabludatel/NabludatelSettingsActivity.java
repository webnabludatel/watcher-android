package org.dvaletin.apps.nabludatel;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.dvaletin.apps.nabludatel.server.NabludatelCloud;
import org.dvaletin.apps.nabludatel.utils.Consts;



public class NabludatelSettingsActivity extends ABSNabludatelActivity {
	private static final int NABLUDATEL_MANUAL_SETUP = 1001;
	private static final int NABLUDATEL_TWITTER_SETUP = 1002;
	private static final int NABLUDATEL_FACEBOOK_SETUP = 1003;

	private NabludatelCloud cloudHelper;

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nabludatel_settings);
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		
		cloudHelper = new NabludatelCloud(deviceId);
		final ProgressBar auth_wheel = (ProgressBar)findViewById(R.id.auth_wheel);
		TimerTask authTask = new TimerTask(){

			@Override
			public void run() {
				NabludatelSettingsActivity.this.runOnUiThread(new Runnable(){

					@Override
					public void run() {
						auth_wheel.setVisibility(View.VISIBLE);
						if(cloudHelper.tryAuthenticate()){
							((TextView) findViewById(R.id.nabludatel_registration_status))
							.setText("Зарегистрирован " + cloudHelper.getAuthenticatedUserId());
						}else{
							((TextView) findViewById(R.id.nabludatel_registration_status))
							.setText("Нет связи с сервером.");
						}
						auth_wheel.setVisibility(View.INVISIBLE);
					}});
				
				
			}};
		Timer authTimer = new Timer();
		authTimer.schedule(authTask, 5000);
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

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == Consts.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode != 0) {
			this.mElectionsDB.open();
			savePhotos();
		}
	}
}
