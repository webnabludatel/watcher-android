package org.dvaletin.apps.nabludatel;

import android.os.Bundle;
import android.widget.SeekBar;

public class SectionCountingBallotBox extends ABSNabludatelActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_counting_ballot_box);
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		
		SeekBar ballot_box_opening_ballot_bundles = (SeekBar) findViewById(R.id.ballot_box_opening_ballot_bundles);
		ballot_box_opening_ballot_bundles.setProgress(prefs.getInt(ballot_box_opening_ballot_bundles.getTag().toString(), 1));	
		
	}
	
	public void onPause(){
		
		SeekBar ballot_box_opening_ballot_bundles = (SeekBar) findViewById(R.id.ballot_box_opening_ballot_bundles);
		prefs.edit().putInt(ballot_box_opening_ballot_bundles.getTag().toString(), ballot_box_opening_ballot_bundles.getProgress()).commit();
				
		super.onPause();
	}
}
