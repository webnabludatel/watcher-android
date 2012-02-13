package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.Consts;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class ElectionsBundleBullots extends ABSNabludatelActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_during_elections_bundle_of_ballots);
		Button mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setVisibility(View.VISIBLE);
        mBackButton.setText(Consts.ROOT_MENU_ITEMS[2]);
        mBackButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				ElectionsBundleBullots.this.finish();
				
			}
		});
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
