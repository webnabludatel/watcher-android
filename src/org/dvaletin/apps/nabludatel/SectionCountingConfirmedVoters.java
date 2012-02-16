package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.Consts;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class SectionCountingConfirmedVoters extends ABSNabludatelActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_counting_confirmed_voters);
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		
		SeekBar confirmed_voters_announced = (SeekBar) findViewById(R.id.confirmed_voters_announced);
		confirmed_voters_announced.setProgress(prefs.getInt(confirmed_voters_announced.getTag().toString(), 1));	
		
		SeekBar confirmed_voters_recorded_in_protocol_immediately = (SeekBar) findViewById(R.id.confirmed_voters_recorded_in_protocol_immediately);
		confirmed_voters_recorded_in_protocol_immediately.setProgress(prefs.getInt(confirmed_voters_recorded_in_protocol_immediately.getTag().toString(), 1));
		
	}
	
	public void onPause(){
		
		SeekBar confirmed_voters_announced = (SeekBar) findViewById(R.id.confirmed_voters_announced);
		prefs.edit().putInt(confirmed_voters_announced.getTag().toString(), confirmed_voters_announced.getProgress()).commit();
		
		SeekBar confirmed_voters_recorded_in_protocol_immediately = (SeekBar) findViewById(R.id.confirmed_voters_recorded_in_protocol_immediately);
		prefs.edit().putInt(confirmed_voters_recorded_in_protocol_immediately.getTag().toString(), confirmed_voters_recorded_in_protocol_immediately.getProgress()).commit();
		
		super.onPause();
	}
}
