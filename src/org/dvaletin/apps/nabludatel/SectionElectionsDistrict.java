package org.dvaletin.apps.nabludatel;

import org.dvaletin.apps.nabludatel.utils.Consts;
import org.dvaletin.apps.nabludatel.utils.S3Helper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class SectionElectionsDistrict extends ABSNabludatelActivity {
	
	EditText et_district_number;
	EditText et_chairman_name;
	EditText et_secretary_name;
	EditText et_watchers_count;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_elections_district);
		restore(getIntent());
	}
	
	@Override
	public void onBackPressed(){
		saveActivityResult();
		super.onBackPressed();
	}
	
	public void saveActivityResult(){

		prefs.edit().putString((String)et_district_number.getTag(), et_district_number.getText().toString()).commit();
		prefs.edit().putString((String)et_chairman_name.getTag(), et_chairman_name.getText().toString()).commit();
		prefs.edit().putString((String)et_secretary_name.getTag(), et_secretary_name.getText().toString()).commit();
		prefs.edit().putString((String)et_watchers_count.getTag(), et_watchers_count.getText().toString()).commit();
		
		
		this.setResult(RESULT_OK, save());
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		
		et_district_number = (EditText) findViewById(R.id.uik_district_number);
		et_district_number.setText(prefs.getString((String) et_district_number.getTag(), ""));
		
		et_chairman_name = (EditText) findViewById(R.id.uik_district_chairman);
		et_chairman_name.setText(prefs.getString((String) et_chairman_name.getTag(), ""));
		
		et_secretary_name = (EditText) findViewById(R.id.uik_district_secretary);
		et_secretary_name.setText(prefs.getString((String) et_secretary_name.getTag(), ""));
		
		et_watchers_count = (EditText) findViewById(R.id.uik_district_watchers_count);
		et_watchers_count.setText(prefs.getString((String) et_watchers_count.getTag(), ""));
	}

	public void onSelectPhotoFromGalleryClick(View v){
		
	}
	
	public void onMakePhotoClick(View v){
		startNarusheniyePhoto();
	}
	
	public void onStart(){
		super.onStart();
	}
	
	public void onPause(){
		saveActivityResult();
		super.onPause();
	}
	

}
