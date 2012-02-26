package org.dvaletin.apps.nabludatel;


import java.util.Timer;
import java.util.TimerTask;

import org.dvaletin.apps.nabludatel.utils.Consts;
import org.dvaletin.apps.nabludatel.utils.ElectionsDBHelper;
import org.dvaletin.apps.nabludatel.utils.MediaSyncThread;
import org.dvaletin.apps.nabludatel.utils.MediaSyncThread.IMediaSyncCallCallback;
import org.dvaletin.apps.nabludatel.utils.ViolationSyncThread;
import org.dvaletin.apps.nabludatel.utils.ViolationSyncThread.IViolationSyncCallCallback;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;

public class MainWindow extends TabActivity implements IViolationSyncCallCallback, IMediaSyncCallCallback{
	private static final int DIALOG_SYNC_ERROR = 1000;
	
	ViolationSyncThread violationSync;
	MediaSyncThread mediaSync;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int buttonResource = getResources().getIdentifier("drawable/button", null, getPackageName());
	    Drawable button = getResources().getDrawable(buttonResource);
		setContentView(R.layout.main_tabs);
		TabHost host=getTabHost();
		host.addTab(host.newTabSpec("one")
				.setIndicator("Я", button)
				.setContent(new Intent(this, NabludatelSettingsActivity.class)));
		host.addTab(host.newTabSpec("two")
				.setIndicator("Наблюдение")
				.setContent(new Intent(this, NabludatelActivity.class)));
				
		host.addTab(host.newTabSpec("three")
				.setIndicator("Отчет")
				.setContent(new Intent(this, ReportActivity.class)));
		


		String url = "file:///android_asset/spravochnik/golos_index.html";
		Intent intent = new Intent(this, SpravochnikActivity.class);
		intent.putExtra(Consts.ACTIVITY_URL_DATA, url);

		host.addTab(host.newTabSpec("four")
				.setIndicator("Справочник")
				.setContent(intent));
		
		host.addTab(host.newTabSpec("five")
				.setIndicator("S.O.S")
				.setContent(new Intent(this, SosActivity.class)));
		
		setupUI();
		setupUpdateThreads();
	}
	
	private void setupUpdateThreads() {
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		violationSync = new ViolationSyncThread();
		
		violationSync.init(this, deviceId);
		violationSync.setViolationSyncCallCallback(this);
		TimerTask violationSyncTask;
		final Handler violationSyncHandler = new Handler();
		Timer violationSyncTimer = new Timer();
		violationSyncTask = new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				violationSyncHandler.post(violationSync);
			}
			
		};
		violationSyncTimer.schedule(violationSyncTask, 300, 30000);
		
		
		mediaSync = new MediaSyncThread();
		mediaSync.init(this, deviceId);
		mediaSync.setMediaSyncCallback(this);
		final Handler mediaSyncHandler = new Handler();
		Timer mediaSyncTimer = new Timer();
		TimerTask mediaSyncTask = new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mediaSyncHandler.post(mediaSync);
			}
			
		};
		mediaSyncTimer.schedule(mediaSyncTask, 3000, 30000);
	}

	private void setupUI() {
		RadioButton rbFirst = (RadioButton) findViewById(R.id.first);
		RadioButton rbSecond = (RadioButton) findViewById(R.id.second);
		RadioButton rbThird = (RadioButton) findViewById(R.id.third);
		RadioButton rbFourth = (RadioButton) findViewById(R.id.fourth);
		RadioButton rbFifth = (RadioButton) findViewById(R.id.fifth);
		
		rbFirst.setButtonDrawable(R.drawable.button_radio);
//		rbFirst.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_1_30, 0, 0);
//		rbFirst.setText("Я");
//		rbFirst.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8.0f);
//		rbFirst.setGravity(Gravity.CENTER_HORIZONTAL+Gravity.CENTER_VERTICAL);
		
		rbSecond.setButtonDrawable(R.drawable.button_radio);
//		rbSecond.setGravity(Gravity.CENTER_HORIZONTAL+Gravity.CENTER_VERTICAL);
//		rbSecond.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_2_30, 0, 0);
//		rbSecond.setText("Наблюдение");
//		rbSecond.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8.0f);
		
		rbThird.setButtonDrawable(R.drawable.button_radio);
//		rbThird.setGravity(Gravity.CENTER_HORIZONTAL+Gravity.CENTER_VERTICAL);
//		rbThird.setText("Справочник");
//		rbThird.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_3_30, 0, 0);
//		rbThird.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8f);
		
		rbFourth.setButtonDrawable(R.drawable.button_radio);
//		rbFourth.setGravity(Gravity.CENTER_HORIZONTAL+Gravity.CENTER_VERTICAL);
//		rbFourth.setText("S.O.S");
//		rbFourth.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.tab_icon_4_30, 0, 0);
//		rbFourth.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8f);
		
		rbFifth.setButtonDrawable(R.drawable.button_radio);
		
		RadioGroup rg = (RadioGroup) findViewById(R.id.states);
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, final int checkedId) {
				switch (checkedId) {
				case R.id.first:
					getTabHost().setCurrentTab(0);
					break;
				case R.id.second:
					getTabHost().setCurrentTab(1);
					break;
				case R.id.third:
					getTabHost().setCurrentTab(2);
					break;
				case R.id.fourth:
					getTabHost().setCurrentTab(3);
					break;
				}
			}
		});
	}

	@Override
	public void onViolationSyncStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onViolationSyncFinish() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onViolationSyncError(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
		       .setCancelable(false)
		       .setPositiveButton("Понятно", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}

	@Override
	public void onViolationSyncProgresUpdate(int progress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMediaSyncStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMediaSyncFinish() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMediaSyncProgresUpdate(int progress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMediaSyncError(String msg) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
		       .setCancelable(false)
		       .setPositiveButton("Понятно", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		                dialog.cancel();
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
}
