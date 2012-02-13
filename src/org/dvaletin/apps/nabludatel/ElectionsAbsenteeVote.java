package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.Consts;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class ElectionsAbsenteeVote extends ABSNabludatelActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_during_elections_absentee_vote);
		Button mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setVisibility(View.VISIBLE);
        mBackButton.setText(Consts.ROOT_MENU_ITEMS[2]);
        mBackButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				ElectionsAbsenteeVote.this.finish();
				
			}
		});
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		
		SeekBar absentee_vote_registry_check_allowed = (SeekBar) findViewById(R.id.absentee_vote_registry_check_allowed);
		absentee_vote_registry_check_allowed.setProgress(prefs.getInt(absentee_vote_registry_check_allowed.getTag().toString(), 1));
		
		SeekBar absentee_vote_registry_is_valid = (SeekBar) findViewById(R.id.absentee_vote_registry_is_valid);
		absentee_vote_registry_is_valid.setProgress(prefs.getInt(absentee_vote_registry_is_valid.getTag().toString(), 1));
		
		SeekBar absentee_vote_ballots_number_mismatch = (SeekBar) findViewById(R.id.absentee_vote_ballots_number_mismatch);
		absentee_vote_ballots_number_mismatch.setProgress(prefs.getInt(absentee_vote_ballots_number_mismatch.getTag().toString(), 1));
		
		SeekBar absentee_vote_registry_is_correct = (SeekBar) findViewById(R.id.absentee_vote_registry_is_correct);
		absentee_vote_registry_is_correct.setProgress(prefs.getInt(absentee_vote_registry_is_correct.getTag().toString(), 1));
		
		SeekBar absentee_vote_voters_not_in_registry = (SeekBar) findViewById(R.id.absentee_vote_voters_not_in_registry);
		absentee_vote_voters_not_in_registry.setProgress(prefs.getInt(absentee_vote_voters_not_in_registry.getTag().toString(), 1));
		
		SeekBar absentee_vote_protocol_concluded = (SeekBar) findViewById(R.id.absentee_vote_protocol_concluded);
		absentee_vote_protocol_concluded.setProgress(prefs.getInt(absentee_vote_protocol_concluded.getTag().toString(), 1));

	}
	
	public void onPause(){
		SeekBar absentee_vote_registry_check_allowed = (SeekBar) findViewById(R.id.absentee_vote_registry_check_allowed);
		prefs.edit().putInt(absentee_vote_registry_check_allowed.getTag().toString(), absentee_vote_registry_check_allowed.getProgress()).commit();
		
		SeekBar absentee_vote_registry_is_valid = (SeekBar) findViewById(R.id.absentee_vote_registry_is_valid);
		prefs.edit().putInt(absentee_vote_registry_is_valid.getTag().toString(), absentee_vote_registry_is_valid.getProgress()).commit();

		SeekBar absentee_vote_ballots_number_mismatch = (SeekBar) findViewById(R.id.absentee_vote_ballots_number_mismatch);
		prefs.edit().putInt(absentee_vote_ballots_number_mismatch.getTag().toString(), absentee_vote_ballots_number_mismatch.getProgress()).commit();
		
		SeekBar absentee_vote_registry_is_correct = (SeekBar) findViewById(R.id.absentee_vote_registry_is_correct);
		prefs.edit().putInt(absentee_vote_registry_is_correct.getTag().toString(), absentee_vote_registry_is_correct.getProgress()).commit();
		
		SeekBar absentee_vote_voters_not_in_registry = (SeekBar) findViewById(R.id.absentee_vote_voters_not_in_registry);
		prefs.edit().putInt(absentee_vote_voters_not_in_registry.getTag().toString(), absentee_vote_voters_not_in_registry.getProgress()).commit();
		
		SeekBar absentee_vote_protocol_concluded = (SeekBar) findViewById(R.id.absentee_vote_protocol_concluded);
		prefs.edit().putInt(absentee_vote_protocol_concluded.getTag().toString(), absentee_vote_protocol_concluded.getProgress()).commit();

		super.onPause();
	}
}
