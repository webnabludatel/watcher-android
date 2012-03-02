package org.dvaletin.apps.nabludatel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import org.dvaletin.apps.nabludatel.server.NabludatelCloud;
import org.dvaletin.apps.nabludatel.utils.*;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainWindow extends TabActivity {
	private static final String T = MainWindow.class.getSimpleName();
	private final Set<ExecuterWithNotification> syncronizers = new HashSet<ExecuterWithNotification>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_tabs);
		TabHost host = getTabHost();
		host.addTab(host.newTabSpec("one")
				.setIndicator("Я")
				.setContent(new Intent(this, NabludatelSettingsActivity.class)));
		host.addTab(host.newTabSpec("two")
				.setIndicator("Наблюдение")
				.setContent(new Intent(this, NabludatelActivity.class)));

		host.addTab(host.newTabSpec("three")
				.setIndicator("Отчёт")
				.setContent(new Intent(this, ReportActivity.class)));

		String url = "file:///android_asset/spravochnik/index.html";
		Intent intent = new Intent(this, SpravochnikActivity.class);
		intent.putExtra(Consts.ACTIVITY_URL_DATA, url);

		host.addTab(host.newTabSpec("four")
				.setIndicator("Справочник")
				.setContent(intent));

		host.addTab(host.newTabSpec("five")
				.setIndicator("S.O.S.")
				.setContent(new Intent(this, SosActivity.class)));

		setupUI();
		setupUpdateThreads();
	}

	private void setupUpdateThreads() {

		for (ExecuterWithNotification syncronizer : syncronizers) {
			syncronizer.dispose();
		}
		syncronizers.clear();

		NabludatelCloud cloud = new NabludatelCloud(this.getDeviceId());

		SyncTask checkListSyncTask = new CheckListSyncTask(cloud, this,
				new SyncNotification(Consts.DATA_NOTIFICATION_ID, this, getString(R.string.upload_data_notification)));
		syncronizers.add(new ExecuterWithNotification(checkListSyncTask, 5L));

		SyncTask mediaSyncTask = new MediaSyncTask(this, cloud,
				new SyncNotification(Consts.MEDIA_NOTIFICATION_ID, this, getString(R.string.upload_media_notification)));
		syncronizers.add(new ExecuterWithNotification(mediaSyncTask, 30L));
	}

	private void setupUI() {
		final SharedPreferences prefs = getPreferences(MODE_PRIVATE);
		RadioButton rbFirst = (RadioButton) findViewById(R.id.first);
		RadioButton rbSecond = (RadioButton) findViewById(R.id.second);
		RadioButton rbThird = (RadioButton) findViewById(R.id.third);
		RadioButton rbFourth = (RadioButton) findViewById(R.id.fourth);
		RadioButton rbFifth = (RadioButton) findViewById(R.id.fifth);

//		rbFirst.setButtonDrawable(R.drawable.button_radio);
//
//		rbSecond.setButtonDrawable(R.drawable.button_radio);
//
//		rbThird.setButtonDrawable(R.drawable.button_radio);
//
//		rbFourth.setButtonDrawable(R.drawable.button_radio);
//
//		rbFifth.setButtonDrawable(R.drawable.button_radio);

		RadioGroup rg = (RadioGroup) findViewById(R.id.states);
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup group, final int checkedId) {
				prefs.edit().putInt(Consts.PREFS_LAST_TAB, checkedId).commit();
				switch (checkedId) {
					case R.id.first: {
						getTabHost().setCurrentTab(0);
						break;
					}
					case R.id.second:
						getTabHost().setCurrentTab(1);
						break;
					case R.id.third:
						getTabHost().setCurrentTab(2);
						break;
					case R.id.fourth:
						getTabHost().setCurrentTab(3);
						break;
					case R.id.fifth:
						getTabHost().setCurrentTab(4);
						break;
				}
			}
		});
		rg.check(prefs.getInt(Consts.PREFS_LAST_TAB, 0));
	}

	public void showErrorMessage(final String msg) {
		runOnUiThread(new Runnable() {
			public void run() {
				AlertDialog.Builder builder = new AlertDialog.Builder(MainWindow.this);
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
		});
	}

	private final class ExecuterWithNotification {
		private final ScheduledExecutorService executer;
		private final SyncTask task;

		private ExecuterWithNotification(SyncTask task, long fixedDelaySeconds) {
			this.task = task;
			this.executer = Executors.newSingleThreadScheduledExecutor();
			this.executer.scheduleWithFixedDelay(task, 1L, fixedDelaySeconds, TimeUnit.SECONDS);
		}

		public void dispose() {
			try {
				executer.shutdownNow();
			} catch (Exception e) {
				Log.e(ExecuterWithNotification.class.getSimpleName(), "Can't dispose executer", e);
			} finally {
				task.dispose();
			}
		}
	}
	
	@Override protected void onChildTitleChanged (Activity childActivity, CharSequence title){
		this.setTitle(title);
	}
	
	public String getDeviceId(){
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId;
		try {
			deviceId = tm.getDeviceId();
			return deviceId;
		}catch (java.lang.RuntimeException e){
			SharedPreferences prefs = getSharedPreferences(Consts.PREFS_FILENAME, MODE_PRIVATE);

			deviceId = prefs.getString(Consts.PREFS_DEVICE_ID, "");
			if(deviceId.equals("")){
				deviceId = "nogsm"+String.valueOf((long)(Math.random()*100000000L));
				prefs.edit().putString(Consts.PREFS_DEVICE_ID, deviceId).commit();
				Log.d(T, "generated random device id:"+deviceId);
			}
		}
		
		return deviceId;
	}
}
