package org.dvaletin.apps.nabludatel;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;

import org.dvaletin.apps.nabludatel.server.NabludatelCloud;
import org.dvaletin.apps.nabludatel.utils.Consts;
import org.dvaletin.apps.nabludatel.utils.LocalProperties;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;



public class NabludatelSettingsActivity extends ABSNabludatelActivity {
	private static final int NABLUDATEL_MANUAL_SETUP = 1001;

	private NabludatelCloud cloudHelper;
	private Facebook mFacebook;

	public void onRegistrationStatusTitleInfoClick(View v) {
		showInfoDialog(R.string.registration_status_hint);
	}

	public void onOfficialObserverTitleInfoClick(View v) {
		showInfoDialog(R.string.official_observer_title_hint);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCurrentPollingPlaceId = 0;
		setContentView(R.layout.nabludatel_settings);
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		
		cloudHelper = new NabludatelCloud(deviceId);
		if(prefs.getInt(Consts.PREFS_USER_ID, -1) == -1){
			tryAuthenticate(5000);
		}else {
			((TextView) findViewById(R.id.nabludatel_registration_status))
			.setText("Зарегистрирован");
		}
		Spinner observerStatusS = (Spinner) findViewById(R.id.observer_status);
		Resources res = getResources();
		String [] observerStatusItems = res.getStringArray(R.array.observer_status);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, observerStatusItems);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		observerStatusS.setAdapter(adapter);
		observerStatusS.setSelection(prefs.getInt(observerStatusS.getTag().toString(), 0));
		observerStatusS.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				String key = parent.getTag().toString();
				prefs.edit().putInt(key, position).commit();
				saveCheckListItem(updateCheckListItem(key, String.valueOf(position), ""));
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
			}
		});
		Button facebookButton = (Button) NabludatelSettingsActivity.this.findViewById(R.id.facebook);
		facebookButton.setText(" "+prefs.getString(Consts.PREFS_FACEBOOK_EMAIL, getString(R.string.nabludatel_settings_facebook)));
	}
	
	public void tryAuthenticate(int delay){
		final ProgressBar auth_wheel = (ProgressBar)findViewById(R.id.auth_wheel);
		TimerTask authTask = new TimerTask(){

			@Override
			public void run() {
				NabludatelSettingsActivity.this.runOnUiThread(new Runnable(){
					@Override
					public void run() {
						auth_wheel.setVisibility(View.VISIBLE);
						((TextView) findViewById(R.id.nabludatel_registration_status))
						.setText("Получаю статус...");
						auth_wheel.setVisibility(View.VISIBLE);
					}
				});
				

				if(cloudHelper.tryAuthenticate()){
					NabludatelSettingsActivity.this.runOnUiThread(new Runnable(){
						@Override
						public void run() {
							((TextView) findViewById(R.id.nabludatel_registration_status))
								.setText("Зарегистрирован");
							auth_wheel.setVisibility(View.INVISIBLE);
						}});
				}
				else{
					NabludatelSettingsActivity.this.runOnUiThread(new Runnable(){
						@Override
						public void run() {
							((TextView) findViewById(R.id.nabludatel_registration_status))
							.setText("Нет связи с сервером.");
							NabludatelSettingsActivity.this.tryAuthenticate(300000);
							auth_wheel.setVisibility(View.INVISIBLE);
						}
					});
				}
				
			}
				
		};
		Timer authTimer = new Timer();
		authTimer.schedule(authTask, delay);
	}
	
	public void onFacebookSetupClick(View v){
		loginToFaceBook();
	}

	public void onManualSetupClick(View v){
		Intent intent = new Intent(this, NabludatelProfileActivity.class);
		intent.putExtra(Consts.PREFS_CURRENT_POLLING_PLACE_ID, 0l);
		startActivityForResult(intent, NABLUDATEL_MANUAL_SETUP);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == Consts.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode != 0) {
			savePhotos();
		}
	}
	
	private void loginToFaceBook() {
		
		
		mFacebook = new Facebook(LocalProperties.getFacebookSecret());
		prefs = getPreferences(MODE_PRIVATE);
		String access_token = prefs.getString(Consts.PREFS_FACEBOOK_ACCESS_TOKEN, null);
		long expires = prefs.getLong(Consts.PREFS_FACEBOOK_ACCESS_EXPIRES, 0);
		
		if(access_token != null) {
            mFacebook.setAccessToken(access_token);
        }
        if(expires != 0) {
            mFacebook.setAccessExpires(expires);
        }
        if(!mFacebook.isSessionValid()) {
			mFacebook.authorize(this, new String[]{ "publish_stream", "publish_checkins", "publish_actions"},new DialogListener() {
	            @Override
	            public void onComplete(Bundle values) {
	            	SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(Consts.PREFS_FACEBOOK_ACCESS_TOKEN, mFacebook.getAccessToken());
                    editor.putLong(Consts.PREFS_FACEBOOK_ACCESS_EXPIRES, mFacebook.getAccessExpires());
                    editor.commit();
                    NabludatelSettingsActivity.this.setFacebookLoginOk();
	            }
	
	            @Override
	            public void onFacebookError(FacebookError error) {
	            }
	
	            @Override
	            public void onError(DialogError e) {
	            	SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(Consts.PREFS_FACEBOOK_ACCESS_TOKEN, null);
                    editor.putLong(Consts.PREFS_FACEBOOK_ACCESS_EXPIRES, 0);
                    editor.commit();
	            }
	
	            @Override
	            public void onCancel() {
	            	SharedPreferences.Editor editor = prefs.edit();
                    editor.putString(Consts.PREFS_FACEBOOK_ACCESS_TOKEN, null);
                    editor.putLong(Consts.PREFS_FACEBOOK_ACCESS_EXPIRES, 0);
                    editor.commit();
	            }
	        });
        }else{
        	NabludatelSettingsActivity.this.setFacebookLoginOk();
        }
		
	}


	protected void setFacebookLoginOk() {
		TimerTask authTask = new TimerTask(){

			@Override
			public void run() {
				NabludatelSettingsActivity.this.runOnUiThread(new Runnable(){
					@Override
					public void run() {
						try {
							JSONObject me = new JSONObject(mFacebook.request("/me"));
							String first = me.getString("first_name"); // gets first name
							String last = me.getString("last_name");
							String email = me.getString("email");
							
							NabludatelSettingsActivity.this.saveFaceBook(first, last, email);
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}});
				
				
			}};
		Timer authTimer = new Timer();
		authTimer.schedule(authTask, 5000);
		
	}



	protected void saveFaceBook(String first, String last, String email) {
		Button facebookButton = (Button) NabludatelSettingsActivity.this.findViewById(R.id.facebook);
		facebookButton.setText(" "+email);
		prefs.edit().putString(Consts.PREFS_FACEBOOK_EMAIL, email).commit();
		saveCheckListItem(updateCheckListItem("last_name", last, ""));
		saveCheckListItem(updateCheckListItem("first_name", first, ""));
		saveCheckListItem(updateCheckListItem("email", email, ""));
	}
	
	
}
