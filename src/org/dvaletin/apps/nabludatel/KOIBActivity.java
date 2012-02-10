package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.Consts;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;

public class KOIBActivity extends ABSNabludatelActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_before_elections_koib);
		Button mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setVisibility(View.VISIBLE);
        mBackButton.setText(Consts.ROOT_MENU_ITEMS[1]);
        mBackButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				KOIBActivity.this.finish();
				
			}
		});
	}
	@Override
	public void onResume(){
		super.onResume();
		SeekBar counter_set_on_zero = (SeekBar) findViewById(R.id.counter_set_on_zero);
		counter_set_on_zero.setProgress(prefs.getInt((String)counter_set_on_zero.getTag(), 1));
		
		SeekBar counter_test_failures = (SeekBar) findViewById(R.id.counter_test_failures);
		counter_set_on_zero.setProgress(prefs.getInt((String)counter_test_failures.getTag(), 1));
	}
	
	public void onPause(){
		TelephonyManager t = (TelephonyManager) getSystemService(Context. TELEPHONY_SERVICE);
		S3Helper mS3Helper = new S3Helper(t.getDeviceId());
		if(photo != null){
			mS3Helper.uploadImageToS3(photo);
		}
		
		SeekBar counter_set_on_zero = (SeekBar) findViewById(R.id.counter_set_on_zero);
		prefs.edit().putInt((String)counter_set_on_zero.getTag(), counter_set_on_zero.getProgress()).commit();
		
		SeekBar counter_test_failures = (SeekBar) findViewById(R.id.counter_test_failures);
		prefs.edit().putInt((String)counter_test_failures.getTag(), counter_test_failures.getProgress()).commit();
		
		
		super.onPause();
	}
}
