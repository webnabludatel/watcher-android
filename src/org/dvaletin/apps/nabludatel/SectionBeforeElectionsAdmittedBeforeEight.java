package org.dvaletin.apps.nabludatel;


import android.os.Bundle;
import android.widget.SeekBar;

public class SectionBeforeElectionsAdmittedBeforeEight extends ABSNabludatelActivity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_before_elections_admitted_before_eight);
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
