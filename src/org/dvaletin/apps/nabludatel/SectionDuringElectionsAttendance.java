package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.Consts;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

public class SectionDuringElectionsAttendance extends ABSNabludatelActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_during_elections_attendance);
	}
	
	
	@Override
	public void onResume(){
		super.onResume();
		SeekBar attendance_data_match_on_10 = (SeekBar) findViewById(R.id.attendance_data_match_on_10);
		attendance_data_match_on_10.setProgress(prefs.getInt(attendance_data_match_on_10.getTag().toString(), 1));
		
		SeekBar attendance_data_match_on_12 = (SeekBar) findViewById(R.id.attendance_data_match_on_12);
		attendance_data_match_on_12.setProgress(prefs.getInt(attendance_data_match_on_12.getTag().toString(), 1));
		
		SeekBar attendance_data_match_on_15 = (SeekBar) findViewById(R.id.attendance_data_match_on_15);
		attendance_data_match_on_15.setProgress(prefs.getInt(attendance_data_match_on_15.getTag().toString(), 1));
		
		SeekBar attendance_data_match_on_18 = (SeekBar) findViewById(R.id.attendance_data_match_on_18);
		attendance_data_match_on_18.setProgress(prefs.getInt(attendance_data_match_on_18.getTag().toString(), 1));
		
		EditText attendance_data_comments = (EditText) findViewById(R.id.attendance_data_comments);
		attendance_data_comments.setText(prefs.getString(attendance_data_comments.getTag().toString(), ""));
	}
	
	public void onPause(){
		SeekBar attendance_data_match_on_10 = (SeekBar) findViewById(R.id.attendance_data_match_on_10);
		prefs.edit().putInt(attendance_data_match_on_10.getTag().toString(), attendance_data_match_on_10.getProgress()).commit();
		
		SeekBar attendance_data_match_on_12 = (SeekBar) findViewById(R.id.attendance_data_match_on_12);
		prefs.edit().putInt(attendance_data_match_on_12.getTag().toString(), attendance_data_match_on_12.getProgress()).commit();
		
		SeekBar attendance_data_match_on_15 = (SeekBar) findViewById(R.id.attendance_data_match_on_15);
		prefs.edit().putInt(attendance_data_match_on_15.getTag().toString(), attendance_data_match_on_15.getProgress()).commit();
		
		SeekBar attendance_data_match_on_18 = (SeekBar) findViewById(R.id.attendance_data_match_on_18);
		prefs.edit().putInt(attendance_data_match_on_18.getTag().toString(), attendance_data_match_on_18.getProgress()).commit();
		
		EditText attendance_data_comments = (EditText) findViewById(R.id.attendance_data_comments);
		prefs.edit().putString(attendance_data_comments.getTag().toString(), attendance_data_comments.getText().toString()).commit();
		
		super.onPause();
	}
}
