package org.dvaletin.apps.nabludatel;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import org.dvaletin.apps.nabludatel.server.NabludatelCloud;
import org.dvaletin.apps.nabludatel.utils.Consts;
import org.dvaletin.apps.nabludatel.utils.ElectionsDBHelper;
import org.dvaletin.apps.nabludatel.utils.MediaSyncTask;
import org.dvaletin.apps.nabludatel.utils.MediaSyncTask.IMediaSyncCallCallback;
import org.dvaletin.apps.nabludatel.utils.ViolationSyncTask;
import org.dvaletin.apps.nabludatel.utils.ViolationSyncTask.IViolationSyncCallCallback;

import java.util.Timer;
import java.util.TimerTask;

public class MainWindow extends TabActivity implements IViolationSyncCallCallback, IMediaSyncCallCallback {
	private final Timer timer = new Timer();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		int buttonResource = getResources().getIdentifier("drawable/button", null, getPackageName());
		Drawable button = getResources().getDrawable(buttonResource);
		setContentView(R.layout.main_tabs);
		TabHost host = getTabHost();
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

		final NabludatelCloud cloud = new NabludatelCloud(deviceId);
		final ElectionsDBHelper db = new ElectionsDBHelper(this);
		db.open();

		timer.schedule(new TimerTask() {
			public void run() {
				runOnUiThread(new Runnable() {
					public void run() {
						new ViolationSyncTask(cloud, MainWindow.this).execute(db);
					}
				});
			}
		}, 1000, 1000);


		timer.schedule(new TimerTask() {
			public void run() {
				runOnUiThread(new Runnable() {
					public void run() {
						new MediaSyncTask(cloud, MainWindow.this).execute(db);
					}
				});
			}

		}, 3000, 30000);
	}

	private void setupUI() {
		RadioButton rbFirst = (RadioButton) findViewById(R.id.first);
		RadioButton rbSecond = (RadioButton) findViewById(R.id.second);
		RadioButton rbThird = (RadioButton) findViewById(R.id.third);
		RadioButton rbFourth = (RadioButton) findViewById(R.id.fourth);
		RadioButton rbFifth = (RadioButton) findViewById(R.id.fifth);

		rbFirst.setButtonDrawable(R.drawable.button_radio);

		rbSecond.setButtonDrawable(R.drawable.button_radio);

		rbThird.setButtonDrawable(R.drawable.button_radio);

		rbFourth.setButtonDrawable(R.drawable.button_radio);

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
	}

	@Override
	public void onViolationSyncFinish() {
	}

	@Override
	public void onViolationSyncError(String msg) {
		showErrorMessage(msg);
	}

	@Override
	public void onViolationSyncProgressUpdate(int progress) {
	}

	@Override
	public void onMediaSyncStart() {
	}

	@Override
	public void onMediaSyncFinish() {
	}

	@Override
	public void onMediaSyncProgresUpdate(int progress) {
	}

	@Override
	public void onMediaSyncError(String msg) {
		showErrorMessage(msg);
	}

	private void showErrorMessage(final String msg) {
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
}
