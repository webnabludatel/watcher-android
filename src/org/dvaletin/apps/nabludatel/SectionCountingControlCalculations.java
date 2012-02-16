package org.dvaletin.apps.nabludatel;

import android.os.Bundle;
import android.widget.SeekBar;

public class SectionCountingControlCalculations extends ABSNabludatelActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_counting_control_calculations);
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		
		SeekBar control_calculations_conducted = (SeekBar) findViewById(R.id.control_calculations_conducted);
		control_calculations_conducted.setProgress(prefs.getInt(control_calculations_conducted.getTag().toString(), 1));	
		
		SeekBar control_calculations_performed = (SeekBar) findViewById(R.id.control_calculations_performed);
		control_calculations_performed.setProgress(prefs.getInt(control_calculations_performed.getTag().toString(), 1));	
		
	}
	
	public void onPause(){
		
		SeekBar control_calculations_conducted = (SeekBar) findViewById(R.id.control_calculations_conducted);
		prefs.edit().putInt(control_calculations_conducted.getTag().toString(), control_calculations_conducted.getProgress()).commit();
		
		SeekBar control_calculations_performed = (SeekBar) findViewById(R.id.control_calculations_performed);
		prefs.edit().putInt(control_calculations_performed.getTag().toString(), control_calculations_performed.getProgress()).commit();
		
		super.onPause();
	}
}
