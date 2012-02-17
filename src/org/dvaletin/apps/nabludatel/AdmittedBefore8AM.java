package org.dvaletin.apps.nabludatel;


import org.dvaletin.apps.nabludatel.utils.Consts;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;

public class AdmittedBefore8AM extends ABSNabludatelActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_before_elections_admitted_before_eight);
		Button mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setVisibility(View.VISIBLE);
        mBackButton.setText(Consts.ROOT_MENU_ITEMS[1]);
        mBackButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				AdmittedBefore8AM.this.finish();
				
			}
		});
	}
	
	@Override
	public void onResume(){
		super.onResume();
		SeekBar admitted_before_eight = (SeekBar) findViewById(R.id.admitted_before_eight);
		admitted_before_eight.setProgress(prefs.getInt((String)admitted_before_eight.getTag(), 1));
	}
	
	public void onPause(){
		SeekBar admitted_before_eight = (SeekBar) findViewById(R.id.admitted_before_eight);
		prefs.edit().putInt((String)admitted_before_eight.getTag(), admitted_before_eight.getProgress()).commit();
		super.onPause();
	}
}
