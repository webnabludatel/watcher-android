package org.dvaletin.apps.nabludatel;

import android.os.Bundle;
import android.widget.EditText;

public class SectionBeforeElectionsVotersCount extends ABSNabludatelActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.section_before_elections_voters_count);
	}

	@Override
	public void onResume() {
		super.onResume();
		EditText voters_count_total = (EditText) findViewById(R.id.voters_count_total);

		voters_count_total.setText(String.valueOf(prefs.getInt(voters_count_total.getTag().toString(), 0) == 0 ? "": prefs.getInt(voters_count_total.getTag().toString(), 0)));
		
		EditText voters_count_ballots_total = (EditText) findViewById(R.id.voters_count_ballots_total);
		voters_count_ballots_total.setText(String.valueOf(prefs.getInt(voters_count_ballots_total.getTag().toString(), 0) == 0 ? "": prefs.getInt(voters_count_ballots_total.getTag().toString(), 0)));
		
		
		EditText voters_count_ballot_at_home = (EditText) findViewById(R.id.voters_count_ballot_at_home);
		voters_count_ballot_at_home.setText(String.valueOf(prefs.getInt(voters_count_ballot_at_home.getTag().toString(), 0) == 0 ? "": prefs.getInt(voters_count_ballot_at_home.getTag().toString(), 0)));
	}

	public void onPause() {
	
		EditText voters_count_total = (EditText) findViewById(R.id.voters_count_total);
		if(!voters_count_total.getText().toString().equals("")){
			prefs.edit().putInt(voters_count_total.getTag().toString(), Integer.valueOf(voters_count_total.getText().toString())).commit();
		}
		
		EditText voters_count_ballots_total = (EditText) findViewById(R.id.voters_count_ballots_total);
		if(!voters_count_ballots_total.getText().toString().equals("")){
			prefs.edit().putInt(voters_count_ballots_total.getTag().toString(), Integer.valueOf(voters_count_ballots_total.getText().toString())).commit();
		}
		
		EditText voters_count_ballot_at_home = (EditText) findViewById(R.id.voters_count_ballot_at_home);
		if(!voters_count_ballot_at_home.getText().toString().equals("")){
			prefs.edit().putInt(voters_count_ballot_at_home.getTag().toString(), Integer.valueOf(voters_count_ballot_at_home.getText().toString())).commit();
		}
		
		super.onPause();
	}
}
