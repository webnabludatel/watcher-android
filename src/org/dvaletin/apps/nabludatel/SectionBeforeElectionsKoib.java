package org.dvaletin.apps.nabludatel;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.Bundle;
import android.widget.SeekBar;

public class SectionBeforeElectionsKoib extends ABSNabludatelActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_before_elections_koib);
	}
	@Override
	public void onResume(){
		super.onResume();
		SeekBar counter_set_on_zero = (SeekBar) findViewById(R.id.counter_set_on_zero);
		counter_set_on_zero.setProgress(prefs.getInt((String)counter_set_on_zero.getTag(), 1));
		
		SeekBar counter_test_failures = (SeekBar) findViewById(R.id.counter_test_failures);
		counter_test_failures.setProgress(prefs.getInt((String)counter_test_failures.getTag(), 1));
	}
	
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
//			mNabludatelMediaClient.uploadImageToS3(photo);
		}
		
		SeekBar counter_set_on_zero = (SeekBar) findViewById(R.id.counter_set_on_zero);
		prefs.edit().putInt((String)counter_set_on_zero.getTag(), counter_set_on_zero.getProgress()).commit();
		
		SeekBar counter_test_failures = (SeekBar) findViewById(R.id.counter_test_failures);
		prefs.edit().putInt((String)counter_test_failures.getTag(), counter_test_failures.getProgress()).commit();
		
		
		super.onPause();
	}
}
