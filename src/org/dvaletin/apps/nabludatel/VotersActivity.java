package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.Consts;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;

public class VotersActivity extends ABSNabludatelActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.section_before_elections_koib);
		Button mBackButton = (Button) findViewById(R.id.back_button);
		mBackButton.setVisibility(View.VISIBLE);
		mBackButton.setText(Consts.ROOT_MENU_ITEMS[1]);
		mBackButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				VotersActivity.this.finish();

			}
		});
	}

	@Override
	public void onResume() {
		super.onResume();
		SeekBar voters_lists_checked = (SeekBar) findViewById(R.id.voters_lists_checked);
		voters_lists_checked.setProgress(prefs.getInt(
				(String) voters_lists_checked.getTag(), 1));

		SeekBar voters_lists_are_ok = (SeekBar) findViewById(R.id.voters_lists_are_ok);
		voters_lists_are_ok.setProgress(prefs.getInt(
				(String) voters_lists_are_ok.getTag(), 1));
	}

	public void onPause() {
		TelephonyManager t = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		S3Helper mS3Helper = new S3Helper(t.getDeviceId());
		if (photo != null) {
			mS3Helper.uploadImageToS3(photo);
		}

		SeekBar voters_lists_checked = (SeekBar) findViewById(R.id.voters_lists_checked);
		prefs.edit()
				.putInt((String) voters_lists_checked.getTag(),
						voters_lists_checked.getProgress()).commit();

		SeekBar voters_lists_are_ok = (SeekBar) findViewById(R.id.voters_lists_are_ok);
		prefs.edit()
				.putInt((String) voters_lists_are_ok.getTag(),
						voters_lists_are_ok.getProgress()).commit();

		super.onPause();
	}
}
