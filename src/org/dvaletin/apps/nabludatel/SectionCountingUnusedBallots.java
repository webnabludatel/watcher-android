package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.Consts;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class SectionCountingUnusedBallots extends ABSNabludatelActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_counting_unused_ballots_counted_after_vote_finish);
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		
		SeekBar unused_ballots_counted_after_vote_finish = (SeekBar) findViewById(R.id.unused_ballots_counted_after_vote_finish);
		unused_ballots_counted_after_vote_finish.setProgress(prefs.getInt(unused_ballots_counted_after_vote_finish.getTag().toString(), 1));	
		
		SeekBar unused_ballots_counted_before_other_actions = (SeekBar) findViewById(R.id.unused_ballots_counted_before_other_actions);
		unused_ballots_counted_before_other_actions.setProgress(prefs.getInt(unused_ballots_counted_before_other_actions.getTag().toString(), 1));
		
		SeekBar unused_ballots_recorded_in_protocol_immediately = (SeekBar) findViewById(R.id.unused_ballots_recorded_in_protocol_immediately);
		unused_ballots_recorded_in_protocol_immediately.setProgress(prefs.getInt(unused_ballots_recorded_in_protocol_immediately.getTag().toString(), 1));
		
	}
	
	public void onPause(){
		
		SeekBar unused_ballots_counted_after_vote_finish = (SeekBar) findViewById(R.id.unused_ballots_counted_after_vote_finish);
		prefs.edit().putInt(unused_ballots_counted_after_vote_finish.getTag().toString(), unused_ballots_counted_after_vote_finish.getProgress()).commit();
		
		SeekBar unused_ballots_counted_before_other_actions = (SeekBar) findViewById(R.id.unused_ballots_counted_before_other_actions);
		prefs.edit().putInt(unused_ballots_counted_before_other_actions.getTag().toString(), unused_ballots_counted_before_other_actions.getProgress()).commit();
		
		SeekBar unused_ballots_recorded_in_protocol_immediately = (SeekBar) findViewById(R.id.unused_ballots_recorded_in_protocol_immediately);
		prefs.edit().putInt(unused_ballots_recorded_in_protocol_immediately.getTag().toString(), unused_ballots_recorded_in_protocol_immediately.getProgress()).commit();
		
		super.onPause();
	}
}
