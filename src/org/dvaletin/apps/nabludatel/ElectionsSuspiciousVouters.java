package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.Consts;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class ElectionsSuspiciousVouters extends ABSNabludatelActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_during_elections_suspicious_voters);
		Button mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setVisibility(View.VISIBLE);
        mBackButton.setText(Consts.ROOT_MENU_ITEMS[2]);
        mBackButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				ElectionsSuspiciousVouters.this.finish();
				
			}
		});
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		
		SeekBar suspicious_voters_no_passport = (SeekBar) findViewById(R.id.suspicious_voters_no_passport);
		suspicious_voters_no_passport.setProgress(prefs.getInt(suspicious_voters_no_passport.getTag().toString(), 1));
		
		SeekBar suspicious_voters_no_absentee_ballot = (SeekBar) findViewById(R.id.suspicious_voters_no_absentee_ballot);
		suspicious_voters_no_absentee_ballot.setProgress(prefs.getInt(suspicious_voters_no_absentee_ballot.getTag().toString(), 1));
		
		SeekBar suspicious_voters_invalid_absentee_ballot = (SeekBar) findViewById(R.id.suspicious_voters_invalid_absentee_ballot);
		suspicious_voters_invalid_absentee_ballot.setProgress(prefs.getInt(suspicious_voters_invalid_absentee_ballot.getTag().toString(), 1));
		
		SeekBar suspicious_voters_absentee_ballot_returned_or_replaced = (SeekBar) findViewById(R.id.suspicious_voters_absentee_ballot_returned_or_replaced);
		suspicious_voters_absentee_ballot_returned_or_replaced.setProgress(prefs.getInt(suspicious_voters_absentee_ballot_returned_or_replaced.getTag().toString(), 1));
		
		SeekBar suspicious_voters_brought_by_transport = (SeekBar) findViewById(R.id.suspicious_voters_brought_by_transport);
		suspicious_voters_brought_by_transport.setProgress(prefs.getInt(suspicious_voters_brought_by_transport.getTag().toString(), 1));
		
		SeekBar suspicious_voters_voted_for_absent_person = (SeekBar) findViewById(R.id.suspicious_voters_voted_for_absent_person);
		suspicious_voters_voted_for_absent_person.setProgress(prefs.getInt(suspicious_voters_voted_for_absent_person.getTag().toString(), 1));

	}
	
	public void onPause(){
		SeekBar suspicious_voters_no_passport = (SeekBar) findViewById(R.id.suspicious_voters_no_passport);
		prefs.edit().putInt(suspicious_voters_no_passport.getTag().toString(), suspicious_voters_no_passport.getProgress()).commit();
		
		SeekBar suspicious_voters_no_absentee_ballot = (SeekBar) findViewById(R.id.suspicious_voters_no_absentee_ballot);
		prefs.edit().putInt(suspicious_voters_no_absentee_ballot.getTag().toString(), suspicious_voters_no_absentee_ballot.getProgress()).commit();

		SeekBar suspicious_voters_invalid_absentee_ballot = (SeekBar) findViewById(R.id.suspicious_voters_invalid_absentee_ballot);
		prefs.edit().putInt(suspicious_voters_invalid_absentee_ballot.getTag().toString(), suspicious_voters_invalid_absentee_ballot.getProgress()).commit();
		
		SeekBar suspicious_voters_absentee_ballot_returned_or_replaced = (SeekBar) findViewById(R.id.suspicious_voters_absentee_ballot_returned_or_replaced);
		prefs.edit().putInt(suspicious_voters_absentee_ballot_returned_or_replaced.getTag().toString(), suspicious_voters_absentee_ballot_returned_or_replaced.getProgress()).commit();
		
		SeekBar suspicious_voters_brought_by_transport = (SeekBar) findViewById(R.id.suspicious_voters_brought_by_transport);
		prefs.edit().putInt(suspicious_voters_brought_by_transport.getTag().toString(), suspicious_voters_brought_by_transport.getProgress()).commit();
		
		SeekBar suspicious_voters_voted_for_absent_person = (SeekBar) findViewById(R.id.suspicious_voters_voted_for_absent_person);
		prefs.edit().putInt(suspicious_voters_voted_for_absent_person.getTag().toString(), suspicious_voters_voted_for_absent_person.getProgress()).commit();

		super.onPause();
	}
}
