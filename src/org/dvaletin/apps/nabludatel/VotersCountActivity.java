package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.Consts;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class VotersCountActivity extends ABSNabludatelActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.section_before_elections_voters_count);
		Button mBackButton = (Button) findViewById(R.id.back_button);
		mBackButton.setVisibility(View.VISIBLE);
		mBackButton.setText(Consts.ROOT_MENU_ITEMS[1]);
		mBackButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				VotersCountActivity.this.finish();

			}
		});
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
		prefs.edit().putInt(voters_count_total.getTag().toString(), Integer.valueOf(voters_count_total.getText().toString())).commit();
		
		EditText voters_count_ballots_total = (EditText) findViewById(R.id.voters_count_ballots_total);
		prefs.edit().putInt(voters_count_ballots_total.getTag().toString(), Integer.valueOf(voters_count_ballots_total.getText().toString())).commit();
		
		EditText voters_count_ballot_at_home = (EditText) findViewById(R.id.voters_count_ballot_at_home);
		prefs.edit().putInt(voters_count_ballot_at_home.getTag().toString(), Integer.valueOf(voters_count_ballot_at_home.getText().toString())).commit();
		
		super.onPause();
	}
}
