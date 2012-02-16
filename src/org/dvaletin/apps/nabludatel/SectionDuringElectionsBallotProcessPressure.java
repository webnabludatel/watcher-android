package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.Consts;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class SectionDuringElectionsBallotProcessPressure extends ABSNabludatelActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_during_elections_ballot_process_pressure);
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		
		SeekBar ballot_process_pressure_on_voters = (SeekBar) findViewById(R.id.ballot_process_pressure_on_voters);
		ballot_process_pressure_on_voters.setProgress(prefs.getInt(ballot_process_pressure_on_voters.getTag().toString(), 1));	
	}
	
	public void onPause(){
		SeekBar ballot_process_pressure_on_voters = (SeekBar) findViewById(R.id.ballot_process_pressure_on_voters);
		prefs.edit().putInt(ballot_process_pressure_on_voters.getTag().toString(), ballot_process_pressure_on_voters.getProgress()).commit();
		
		super.onPause();
	}
}
