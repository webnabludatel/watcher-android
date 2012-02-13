package org.dvaletin.apps.nabludatel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.dvaletin.apps.nabludatel.utils.Consts;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SectionElectionsDistrict extends ABSNabludatelActivity {
	
	EditText et_district_number;
	EditText et_chairman_name;
	EditText et_secretary_name;
	EditText et_watchers_count;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.section_elections_district);
		Button mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setVisibility(View.VISIBLE);
        mBackButton.setText(Consts.ROOT_MENU_ITEMS[0]);
        mBackButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				SectionElectionsDistrict.this.saveActivityResult();
				SectionElectionsDistrict.this.finish();
				
			}
		});
	}
	
	@Override
	public void onBackPressed(){
		saveActivityResult();
		super.onBackPressed();
	}
	
	public void saveActivityResult(){
		TelephonyManager t = (TelephonyManager) getSystemService(Context. TELEPHONY_SERVICE);
		S3Helper mS3Helper = new S3Helper(t.getDeviceId());
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
		}else{
			// TODO: alert user no photo of the district
			Toast.makeText(this, "Вы не выбрали фото таблички.", Toast.LENGTH_LONG);
		}
		prefs.edit().putString((String)et_district_number.getTag(), et_district_number.getText().toString()).commit();
		prefs.edit().putString((String)et_chairman_name.getTag(), et_chairman_name.getText().toString()).commit();
		prefs.edit().putString((String)et_secretary_name.getTag(), et_secretary_name.getText().toString()).commit();
		prefs.edit().putString((String)et_watchers_count.getTag(), et_watchers_count.getText().toString()).commit();
//		if(photo!=null) prefs.edit().putString((String)((Button)findViewById(R.id.uik_district_banner_photo)).getTag(), Consts.getAmazonS3Url(t.getDeviceId(), photo)).commit();
		try {
			if(!et_district_number.getText().toString().equals("")){
				getJSON().put((String)et_district_number.getTag(), Integer.valueOf(et_district_number.getText().toString()));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			if(!et_chairman_name.getText().toString().equals("")){
				getJSON().put((String)et_chairman_name.getTag(), et_chairman_name.getText().toString());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			if(!et_secretary_name.getText().toString().equals("")){
				getJSON().put((String)et_secretary_name.getTag(), et_secretary_name.getText().toString());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {	
			if(!et_watchers_count.getText().toString().equals("")){
				getJSON().put((String)et_watchers_count.getTag(), Integer.valueOf(et_watchers_count.getText().toString()));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			//TODO: GPS coordinates!!!
//			if(photo != null){
//				getJSON().put("url", Consts.getAmazonS3Url(t.getDeviceId(), photo));	
//			}
			Intent result = new Intent();
			result.putExtra(Consts.ACTIVITY_JSON_DATA, getJSON().toString());
			this.setResult(RESULT_OK, result);
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
