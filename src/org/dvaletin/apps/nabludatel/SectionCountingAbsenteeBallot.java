package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.Consts;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class SectionCountingAbsenteeBallot extends ABSNabludatelActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_counting_absentee_ballot);
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		
		SeekBar absentee_ballot_box_opening_number_of_applications_announced = (SeekBar) findViewById(R.id.absentee_ballot_box_opening_number_of_applications_announced);
		absentee_ballot_box_opening_number_of_applications_announced.setProgress(prefs.getInt(absentee_ballot_box_opening_number_of_applications_announced.getTag().toString(), 1));	
		
		SeekBar absentee_ballot_box_opening_ballot_bundles = (SeekBar) findViewById(R.id.absentee_ballot_box_opening_ballot_bundles);
		absentee_ballot_box_opening_ballot_bundles.setProgress(prefs.getInt(absentee_ballot_box_opening_ballot_bundles.getTag().toString(), 1));
		
	}
	
	public void onPause(){
		
		SeekBar absentee_ballot_box_opening_number_of_applications_announced = (SeekBar) findViewById(R.id.absentee_ballot_box_opening_number_of_applications_announced);
		prefs.edit().putInt(absentee_ballot_box_opening_number_of_applications_announced.getTag().toString(), absentee_ballot_box_opening_number_of_applications_announced.getProgress()).commit();
		
		SeekBar absentee_ballot_box_opening_ballot_bundles = (SeekBar) findViewById(R.id.absentee_ballot_box_opening_ballot_bundles);
		prefs.edit().putInt(absentee_ballot_box_opening_ballot_bundles.getTag().toString(), absentee_ballot_box_opening_ballot_bundles.getProgress()).commit();
		
		super.onPause();
	}
}
