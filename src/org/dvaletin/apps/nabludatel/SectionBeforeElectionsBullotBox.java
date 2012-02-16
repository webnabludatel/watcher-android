package org.dvaletin.apps.nabludatel;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class SectionBeforeElectionsBullotBox extends ABSNabludatelActivity {
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.section_before_elections_bullot_box);
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		
		SeekBar ballot_box_check_allowed = (SeekBar) findViewById(R.id.ballot_box_check_allowed);
		ballot_box_check_allowed.setProgress(prefs.getInt((String)ballot_box_check_allowed.getTag(), 1));
		
		SeekBar ballot_box_empty = (SeekBar) findViewById(R.id.ballot_box_empty);
		ballot_box_empty.setProgress(prefs.getInt((String)ballot_box_empty.getTag(), 1));
		
		SeekBar ballot_box_sealed_in_your_presence = (SeekBar) findViewById(R.id.ballot_box_sealed_in_your_presence);
		ballot_box_sealed_in_your_presence.setProgress(prefs.getInt((String)ballot_box_sealed_in_your_presence.getTag(), 1));
		
		SeekBar ballot_box_conform_to_reqs = (SeekBar) findViewById(R.id.ballot_box_conform_to_reqs);
		ballot_box_conform_to_reqs.setProgress(prefs.getInt((String)ballot_box_conform_to_reqs.getTag(), 1));
	}
	
	
	@Override
	public void onPause(){
		JSONArray photos = new JSONArray();
		if(photo != null){
			for(int i = 0; i < photo.size(); i++){
				photos.put(photo.get(i).getAbsolutePath());
			}
			try {
				getJSON().put("URL", photos);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			mS3Helper.uploadImageToS3(photo);
		}
		
		SeekBar ballot_box_check_allowed = (SeekBar) findViewById(R.id.ballot_box_check_allowed);
		prefs.edit().putInt((String)ballot_box_check_allowed.getTag(), ballot_box_check_allowed.getProgress()).commit();
		
		SeekBar ballot_box_empty = (SeekBar) findViewById(R.id.ballot_box_empty);
		prefs.edit().putInt((String)ballot_box_empty.getTag(), ballot_box_empty.getProgress()).commit();
		
		SeekBar ballot_box_sealed_in_your_presence = (SeekBar) findViewById(R.id.ballot_box_sealed_in_your_presence);
		prefs.edit().putInt((String)ballot_box_sealed_in_your_presence.getTag(), ballot_box_sealed_in_your_presence.getProgress()).commit();
		
		SeekBar ballot_box_conform_to_reqs = (SeekBar) findViewById(R.id.ballot_box_conform_to_reqs);
		prefs.edit().putInt((String)ballot_box_conform_to_reqs.getTag(), ballot_box_conform_to_reqs.getProgress()).commit();
		
		//TODO: URL фотографии
		
		super.onPause();
	}
	

	
	public void onMakePhotoClick(View v){
		startNarusheniyePhoto();
	}
}
