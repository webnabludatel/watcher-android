package org.dvaletin.apps.nabludatel;

import android.os.Bundle;
import android.widget.SeekBar;

public class SectionDuringElectionsBundleOfBallots extends ABSNabludatelActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_during_elections_bundle_of_ballots);
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		
		SeekBar bundle_of_ballots_vote = (SeekBar) findViewById(R.id.bundle_of_ballots_vote);
		bundle_of_ballots_vote.setProgress(prefs.getInt(bundle_of_ballots_vote.getTag().toString(), 1));	
	}
	
	public void onPause(){
		SeekBar bundle_of_ballots_vote = (SeekBar) findViewById(R.id.bundle_of_ballots_vote);
		prefs.edit().putInt(bundle_of_ballots_vote.getTag().toString(), bundle_of_ballots_vote.getProgress()).commit();
		
		super.onPause();
	}
}
