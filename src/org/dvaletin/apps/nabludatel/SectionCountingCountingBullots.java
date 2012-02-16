package org.dvaletin.apps.nabludatel;

import android.os.Bundle;
import android.widget.SeekBar;

public class SectionCountingCountingBullots extends ABSNabludatelActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_counting_counting_ballots);
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		
		SeekBar counting_ballots_presented_or_announced = (SeekBar) findViewById(R.id.counting_ballots_presented_or_announced);
		counting_ballots_presented_or_announced.setProgress(prefs.getInt(counting_ballots_presented_or_announced.getTag().toString(), 1));	
		
		SeekBar counting_ballots_sorted_in_separate_bundles = (SeekBar) findViewById(R.id.counting_ballots_sorted_in_separate_bundles);
		counting_ballots_sorted_in_separate_bundles.setProgress(prefs.getInt(counting_ballots_sorted_in_separate_bundles.getTag().toString(), 1));	
		
		SeekBar counting_ballots_marks_noticed_in_ballots = (SeekBar) findViewById(R.id.counting_ballots_marks_noticed_in_ballots);
		counting_ballots_marks_noticed_in_ballots.setProgress(prefs.getInt(counting_ballots_marks_noticed_in_ballots.getTag().toString(), 1));	
		
		SeekBar counting_ballots_suspicious_counting = (SeekBar) findViewById(R.id.counting_ballots_suspicious_counting);
		counting_ballots_suspicious_counting.setProgress(prefs.getInt(counting_ballots_suspicious_counting.getTag().toString(), 1));	
		
		SeekBar counting_ballots_ballot_check_allowed = (SeekBar) findViewById(R.id.counting_ballots_ballot_check_allowed);
		counting_ballots_ballot_check_allowed.setProgress(prefs.getInt(counting_ballots_ballot_check_allowed.getTag().toString(), 1));	
		
		SeekBar counting_ballots_recorded_in_protocol_immediately = (SeekBar) findViewById(R.id.counting_ballots_recorded_in_protocol_immediately);
		counting_ballots_recorded_in_protocol_immediately.setProgress(prefs.getInt(counting_ballots_recorded_in_protocol_immediately.getTag().toString(), 1));	
		
	}
	
	public void onPause(){
		
		SeekBar counting_ballots_presented_or_announced = (SeekBar) findViewById(R.id.counting_ballots_presented_or_announced);
		prefs.edit().putInt(counting_ballots_presented_or_announced.getTag().toString(), counting_ballots_presented_or_announced.getProgress()).commit();
		
		SeekBar counting_ballots_sorted_in_separate_bundles = (SeekBar) findViewById(R.id.counting_ballots_sorted_in_separate_bundles);
		prefs.edit().putInt(counting_ballots_sorted_in_separate_bundles.getTag().toString(), counting_ballots_sorted_in_separate_bundles.getProgress()).commit();
		
		SeekBar counting_ballots_marks_noticed_in_ballots = (SeekBar) findViewById(R.id.counting_ballots_marks_noticed_in_ballots);
		prefs.edit().putInt(counting_ballots_marks_noticed_in_ballots.getTag().toString(), counting_ballots_marks_noticed_in_ballots.getProgress()).commit();
		
		SeekBar counting_ballots_suspicious_counting = (SeekBar) findViewById(R.id.counting_ballots_suspicious_counting);
		prefs.edit().putInt(counting_ballots_suspicious_counting.getTag().toString(), counting_ballots_suspicious_counting.getProgress()).commit();
		
		SeekBar counting_ballots_ballot_check_allowed = (SeekBar) findViewById(R.id.counting_ballots_ballot_check_allowed);
		prefs.edit().putInt(counting_ballots_ballot_check_allowed.getTag().toString(), counting_ballots_ballot_check_allowed.getProgress()).commit();
		
		SeekBar counting_ballots_recorded_in_protocol_immediately = (SeekBar) findViewById(R.id.counting_ballots_recorded_in_protocol_immediately);
		prefs.edit().putInt(counting_ballots_recorded_in_protocol_immediately.getTag().toString(), counting_ballots_recorded_in_protocol_immediately.getProgress()).commit();
		
		super.onPause();
	}
}
