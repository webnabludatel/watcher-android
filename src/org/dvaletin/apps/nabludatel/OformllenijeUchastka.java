package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.Consts;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;

public class OformllenijeUchastka extends ABSNabludatelActivity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.section_before_elections_appearance);
		Button mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setVisibility(View.VISIBLE);
        mBackButton.setText(Consts.ROOT_MENU_ITEMS[1]);
        mBackButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				OformllenijeUchastka.this.finish();
				
			}
		});
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		SeekBar appearance_candidates_info = (SeekBar) findViewById(R.id.appearance_candidates_info);
		appearance_candidates_info.setProgress(prefs.getInt((String)appearance_candidates_info.getTag(), 1));
		
		SeekBar appearance_ballot_sample_present = (SeekBar) findViewById(R.id.appearance_ballot_sample_present);
		appearance_ballot_sample_present.setProgress(prefs.getInt((String)appearance_ballot_sample_present.getTag(), 1));
		
		SeekBar appearance_ballot_extended_protocol = (SeekBar) findViewById(R.id.appearance_ballot_extended_protocol);
		appearance_ballot_extended_protocol.setProgress(prefs.getInt((String)appearance_ballot_extended_protocol.getTag(), 1));
		
		SeekBar appearance_campaign_materials_closer_than_50m = (SeekBar) findViewById(R.id.appearance_campaign_materials_closer_than_50m);
		appearance_campaign_materials_closer_than_50m.setProgress(prefs.getInt((String)appearance_campaign_materials_closer_than_50m.getTag(), 1));
		
		SeekBar appearance_vote_secrecy = (SeekBar) findViewById(R.id.appearance_vote_secrecy);
		appearance_vote_secrecy.setProgress(prefs.getInt((String)appearance_vote_secrecy.getTag(), 1));
	}
	
	@Override
	public void onPause(){
		TelephonyManager t = (TelephonyManager) getSystemService(Context. TELEPHONY_SERVICE);
		S3Helper mS3Helper = new S3Helper(t.getDeviceId());
		if(photo != null){
			mS3Helper.uploadImageToS3(photo);
		}
		
		SeekBar appearance_candidates_info = (SeekBar) findViewById(R.id.appearance_candidates_info);
		prefs.edit().putInt((String)appearance_candidates_info.getTag(), appearance_candidates_info.getProgress()).commit();
		
		SeekBar appearance_ballot_sample_present = (SeekBar) findViewById(R.id.appearance_ballot_sample_present);
		prefs.edit().putInt((String)appearance_ballot_sample_present.getTag(), appearance_ballot_sample_present.getProgress()).commit();
		
		SeekBar appearance_ballot_extended_protocol = (SeekBar) findViewById(R.id.appearance_ballot_extended_protocol);
		prefs.edit().putInt((String)appearance_ballot_extended_protocol.getTag(), appearance_ballot_extended_protocol.getProgress()).commit();
		
		SeekBar appearance_campaign_materials_closer_than_50m = (SeekBar) findViewById(R.id.appearance_campaign_materials_closer_than_50m);
		prefs.edit().putInt((String)appearance_campaign_materials_closer_than_50m.getTag(), appearance_campaign_materials_closer_than_50m.getProgress()).commit();
		
		SeekBar appearance_vote_secrecy = (SeekBar) findViewById(R.id.appearance_vote_secrecy);
		prefs.edit().putInt((String)appearance_vote_secrecy.getTag(), appearance_vote_secrecy.getProgress()).commit();
		
		super.onPause();
	}

}
