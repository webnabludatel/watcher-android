package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.Consts;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class SectionDuringElectionsBullot extends ABSNabludatelActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_during_elections_ballot);
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		
		SeekBar ballot_process_watch_allowed = (SeekBar) findViewById(R.id.ballot_process_watch_allowed);
		ballot_process_watch_allowed.setProgress(prefs.getInt(ballot_process_watch_allowed.getTag().toString(), 1));
		
		SeekBar ballot_box_watch_allowed = (SeekBar) findViewById(R.id.ballot_box_watch_allowed);
		ballot_box_watch_allowed.setProgress(prefs.getInt(ballot_box_watch_allowed.getTag().toString(), 1));
	}
	
	public void onPause(){
		SeekBar ballot_process_watch_allowed = (SeekBar) findViewById(R.id.ballot_process_watch_allowed);
		prefs.edit().putInt(ballot_process_watch_allowed.getTag().toString(), ballot_process_watch_allowed.getProgress()).commit();
		
		SeekBar ballot_box_watch_allowed = (SeekBar) findViewById(R.id.ballot_box_watch_allowed);
		prefs.edit().putInt(ballot_box_watch_allowed.getTag().toString(), ballot_box_watch_allowed.getProgress()).commit();

		super.onPause();
	}
}
