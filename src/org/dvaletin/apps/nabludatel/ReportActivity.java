package org.dvaletin.apps.nabludatel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Timer;
import java.util.TimerTask;

import org.dvaletin.apps.nabludatel.server.NabludatelCloud;
import org.dvaletin.apps.nabludatel.utils.Consts;
import org.dvaletin.apps.nabludatel.utils.ElectionsDBHelper;
import org.dvaletin.apps.nabludatel.utils.LocalProperties;
import org.dvaletin.apps.nabludatel.utils.ReportImageView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.SessionEvents;
import com.facebook.android.SessionStore;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.SessionEvents.AuthListener;
import com.facebook.android.SessionEvents.LogoutListener;
import com.facebook.android.FacebookError;

public class ReportActivity extends ABSNabludatelActivity {
	
	private static final String T = ReportActivity.class.getSimpleName();
	private static final int DIALOG_PROGRESS_SHOW = 1000;
	private Facebook mFacebook;
	private String reportMessage;
	ProgressDialog mSpinner;
	private AsyncFacebookRunner mAsyncRunner;
	private SessionListener mSessionListener = new SessionListener();
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nabludatel_report);
		mSpinner = new ProgressDialog(this);
        mSpinner.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mSpinner.setMessage("Отправляю...");
        
        mFacebook = new Facebook(LocalProperties.getFacebookSecret());
		SessionStore.restore(mFacebook, this);
		SessionEvents.addAuthListener(mSessionListener);
		SessionEvents.addLogoutListener(mSessionListener);
		mAsyncRunner = new AsyncFacebookRunner(mFacebook);
//        mSpinner.show();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		this.setTitle("Отчёт");
		TextView reportViolationsTitle = (TextView)findViewById(R.id.report_violation_list_title);
		LinearLayout reportFrame = (LinearLayout)findViewById(R.id.report_frame);
		LinearLayout howToComplainPane = (LinearLayout)findViewById(R.id.howComplaintPane);

		long pollingPlaceId = prefs.getLong(Consts.PREFS_CURRENT_POLLING_PLACE_ID, -1);
		if (pollingPlaceId > 0) {
			String pollingPlaceName = mElectionsDB.getPollingPlaceNameByNumber(pollingPlaceId);
			this.setTitle("Отчёт по участку " + pollingPlaceName);
			reportFrame.removeAllViews();
			Cursor badCursor = mElectionsDB.getViolationsByPollingPlaceId(pollingPlaceId);
			try {
				((Button) findViewById(R.id.report_facebook)).setVisibility(View.VISIBLE);
				Cursor goodCursor = mElectionsDB.getNoneViolationsByPollingPlaceId(pollingPlaceId);
				try {
					int bad = badCursor.getCount();
					int good = goodCursor.getCount();
					badCursor.moveToFirst();
					ImageView list_divider = null;
					if (badCursor.getCount() <= 0) {
						reportViolationsTitle.setText(getString(R.string.report_no_violations_title, pollingPlaceName));
						reportFrame.setVisibility(View.INVISIBLE);
						howToComplainPane.setVisibility(View.INVISIBLE);
						reportMessage = "На участке " + pollingPlaceName + " нарушений не зафиксировано";
					} else {
						reportViolationsTitle.setText(getString(R.string.report_violations_title, pollingPlaceName));
						reportFrame.setVisibility(View.VISIBLE);
						howToComplainPane.setVisibility(View.VISIBLE);
						reportMessage = "На участке " + pollingPlaceName + "зафиксированы следующие нарушения: ";
					}
					for(int i = 0; i < badCursor.getCount(); i++){
						TextView report_item = new TextView(this);
						report_item.setTextAppearance(this, R.style.TextStyle);
						report_item.setText(badCursor.getString(badCursor.getColumnIndex(ElectionsDBHelper.CHECKLISTITEM_VIOLATION_KEY)));
						reportFrame.addView(report_item);
						list_divider = new ImageView(this, null, R.style.list_divider_style);
						list_divider.setImageResource(R.drawable.list_devider);
						list_divider.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
						reportFrame.addView(list_divider);
						badCursor.moveToNext();
					}
					if(list_divider != null){
						reportFrame.removeView(list_divider);
					}
					ReportImageView report_image = (ReportImageView) findViewById(R.id.report_image);
					report_image.setNewValues(good, bad);
					TextView good_text = (TextView) findViewById(R.id.good_text);
					TextView bad_text = (TextView) findViewById(R.id.bad_text);
					good_text.setText(Consts.getGoodText(good));
					bad_text.setText(Consts.getBadText(bad));
					reportMessage = "Отчет наблюдения по участку "
							+pollingPlaceName
							+": "
							+Consts.getGoodText(good)
							+" и ";
					if(bad == 0){
						reportMessage += Consts.getBadText(bad);
					}else {
						reportMessage += "зафиксировано " + Consts.getBadText(bad);
					}
				} finally {
					goodCursor.close();
				}
			} finally {
				badCursor.close();
			}
		} else {
			reportViolationsTitle.setText(getString(R.string.report_no_polling_place_title));
			reportFrame.setVisibility(View.INVISIBLE);
			howToComplainPane.setVisibility(View.INVISIBLE);
			((Button) findViewById(R.id.report_facebook)).setVisibility(View.INVISIBLE);
		}
	}

	public void onReportOnSiteButtonClick(View v){
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		NabludatelCloud cloud = new NabludatelCloud(deviceId);
		if(cloud.tryAuthenticate()){
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://webnabludatel.org/user/"+String.valueOf(cloud.getAuthenticatedUserId())));
			startActivity(intent);
		}
		
	}

	private void loginToFaceBook() {
		
		
		mFacebook = new Facebook(LocalProperties.getFacebookSecret());
//		prefs = getPreferences(MODE_PRIVATE);
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
                    ReportActivity.this.setFacebookLoginOk();
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
        	ReportActivity.this.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					ReportActivity.this.setFacebookLoginOk();
				}
        		
        	});
        	
        }
		
	}
	
	protected void setFacebookLoginOk() {
		
		
	}

	public void onPostToFaceBook(View v){
		
		if (!mFacebook.isSessionValid()) {
			mFacebook.authorize(this, new String[] { "publish_stream",
					"publish_checkins", "publish_actions" },
					new LoginDialogListener());
		}else{
			mSpinner.show();
			Bundle parameters = new Bundle();
			parameters.putString("message", reportMessage);
			startSpinner();
            mAsyncRunner.request("me/feed", parameters, "POST", new PostRequestListener(), null);
		}
//		
//		
//		loginToFaceBook();
//		if(mFacebook==null) return;
//		this.runOnUiThread(new Runnable(){
//
//			@Override
//			public void run() {
//				try {
//					if(mFacebook.isSessionValid()){
//						startSpinner();
//
//						String response = "";
//						Bundle parameters = new Bundle();
//						parameters.putString("message", reportMessage);
//						response = mFacebook.request("me/feed", parameters, "POST");
//						Log.d(T, "got response: " + response);
//						if (response == null || response.equals("") || 
//								response.equals("false")) {
//							Log.v("Error", "Blank response");
//						}
//						
//					}
//				} catch(Exception e) {
//					e.printStackTrace();
//				} finally {
//					stopSpinner();
//				}
//				
//			}});	
	}

	
	void startSpinner(){
		this.runOnUiThread(new Runnable(){

			@Override
			public void run() {
				mSpinner.show();
			}});
	}
	
	void stopSpinner(){
		this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				TimerTask spinnerTask = new TimerTask(){

					@Override
					public void run() {
						mSpinner.dismiss();
					}
				};
				Timer spinerTimer = new Timer();
				spinerTimer.schedule(spinnerTask, 2000);
				
			}});
	}
	public void onHowToComplainInfoClick(View v){
		showInfoDialog(R.string.report_whatnext);
	}
	
	@Override
	protected Dialog onCreateDialog(int id)
	{
		switch(id){
		case DIALOG_PROGRESS_SHOW:{
			return mSpinner;
		}
		default:{
			return null;
		}
	}
	}
	
	
	private class SessionListener implements AuthListener, LogoutListener {

		public void onAuthSucceed() {
			SessionStore.save(mFacebook, ReportActivity.this);
			Bundle parameters = new Bundle();
			parameters.putString("message", reportMessage);
			startSpinner();
            ReportActivity.this.mAsyncRunner.request("me/feed", parameters, "POST", new PostRequestListener(), null);
		}

		public void onAuthFail(String error) {
		}

		public void onLogoutBegin() {
		}

		public void onLogoutFinish() {
			SessionStore.clear(ReportActivity.this);
		}
	}
	
	
	private final class LoginDialogListener implements DialogListener {
		public void onComplete(Bundle values) {
			SessionEvents.onLoginSuccess();
			
		}

		public void onFacebookError(FacebookError error) {
			SessionEvents.onLoginError(error.getMessage());
		}

		public void onError(DialogError error) {
			SessionEvents.onLoginError(error.getMessage());
		}

		public void onCancel() {
			SessionEvents.onLoginError("Action Canceled");
		}
	}
	
	public final class PostRequestListener implements RequestListener{

		@Override
		public void onComplete(String response, Object state) {
			stopSpinner();
			
		}

		@Override
		public void onIOException(IOException e, Object state) {
			// TODO Auto-generated method stub
			stopSpinner();
		}

		@Override
		public void onFileNotFoundException(FileNotFoundException e,
				Object state) {
			// TODO Auto-generated method stub
			stopSpinner();
		}

		@Override
		public void onMalformedURLException(MalformedURLException e,
				Object state) {
			// TODO Auto-generated method stub
			stopSpinner();
		}

		@Override
		public void onFacebookError(FacebookError e, Object state) {
			// TODO Auto-generated method stub
			stopSpinner();
		}
		
	}
	
	
}
