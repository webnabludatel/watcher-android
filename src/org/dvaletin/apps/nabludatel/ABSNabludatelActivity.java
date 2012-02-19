package org.dvaletin.apps.nabludatel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.dvaletin.apps.nabludatel.utils.Consts;
import org.dvaletin.apps.nabludatel.utils.GenericSQLHelper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public abstract class ABSNabludatelActivity extends Activity {
	protected SharedPreferences prefs;
	protected ArrayList<Uri> pictureFileUri;
	protected ArrayList<Uri> videoFileUri;
	protected ArrayList<File> photo;
	protected ArrayList<File> video;
	JSONObject json = new JSONObject();
	long mCurrentElectionsDistrict = -1;
	GenericSQLHelper mElectionsDB;
	GenericSQLHelper mMediaDB;
	double lat;
	double lng;
	LocationListener mLocationListener;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		Intent intent = getIntent();
		mCurrentElectionsDistrict = intent.getLongExtra(Consts.PREFS_ELECTIONS_DISRICT, -1);
		prefs = this.getPreferences(MODE_PRIVATE);
		mElectionsDB = new GenericSQLHelper(this);
		
		pictureFileUri = new ArrayList<Uri>();
		videoFileUri = new ArrayList<Uri>();
		
		photo = new ArrayList<File>();
		video = new ArrayList<File>();
	}
	
	@Override
	public void onResume(){
		super.onResume();
		mElectionsDB.open();
		setCallbacks((ViewGroup)(findViewById(android.R.id.content).getRootView()));
		restore();
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		mLocationListener = new LocationListener(){
			public void onStatusChanged(String provider, int status, Bundle extras) {}

		    public void onProviderEnabled(String provider) {}

		    public void onProviderDisabled(String provider) {}

			@Override
			public void onLocationChanged(Location location) {
				lat = location.getLatitude();
				lng = location.getLongitude();	
			}
		};
		
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);

		
	}
	
	@Override
	public void onPause(){
		super.onPause();
		LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		locationManager.removeUpdates(mLocationListener);
		mElectionsDB.close();
	}
	
	public void onMakePhotoClick(View v){
		startNarusheniyePhoto();
	}
	
	public void onMakeVideoClick(View v){
		startNarusheniyeVideo();
	}
	
	public void restore(){
		
		if(mCurrentElectionsDistrict == -1) return;
		Cursor c = mElectionsDB.getAllCheckListItemsByElectionsDistrictId(this.mCurrentElectionsDistrict);
		c.moveToFirst();
		HashMap<String, String> mRestoreHashMap = new HashMap<String,String>();
		for(int i=0; i < c.getCount(); i++){
			
			
			mRestoreHashMap.put(c.getString(GenericSQLHelper.CHECKLISTITEM_NAME_COLUMN), 
					c.getString(GenericSQLHelper.CHECKLISTITEM_VALUE_COLUMN));
			c.moveToNext();
		}
		restore((ViewGroup)findViewById(android.R.id.content).getRootView(), mRestoreHashMap);
	}
	
	public void restore(ViewGroup v, HashMap<String, String> from){
		for(int i=0; i< v.getChildCount(); i++){
			if (v.getChildAt(i) instanceof ViewGroup) {
				restore((ViewGroup) (v.getChildAt(i)), from);
			}

			if (v.getChildAt(i) instanceof SeekBar) {
				try{
					String data = from.get(v.getChildAt(i).getTag().toString());
					if(data != null){
						SeekBar bar = (SeekBar)v.getChildAt(i);
						if(data.equals(String.valueOf(true))){
							bar.setProgress(Consts.SEEKBAR_TRUE);
						}
						if(data.equals(String.valueOf(false))){
							bar.setProgress(Consts.SEEKBAR_FALSE);
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public void setCallbacks(ViewGroup v) {
		for (int i = 0; i < v.getChildCount(); i++) {
			if (v.getChildAt(i) instanceof ViewGroup) {
				setCallbacks((ViewGroup) (v.getChildAt(i)));
			}

			if (v.getChildAt(i) instanceof SeekBar) {
				SeekBar bar = (SeekBar) v.getChildAt(i);
				bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
					int mInitialProgress;
					boolean mIsProgressChanged = false;
					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						if (fromUser) {
							mIsProgressChanged = true;
							
						}
						switch(progress){
						case Consts.SEEKBAR_TRUE:{
							seekBar.setBackgroundDrawable(ABSNabludatelActivity.this.getResources().getDrawable(R.drawable.for_frontend_15));
							break;
						}
						case Consts.SEEKBAR_FALSE:{
							seekBar.setBackgroundDrawable(ABSNabludatelActivity.this.getResources().getDrawable(R.drawable.for_frontend_11));
							break;
						}
						default:{
							seekBar.setBackgroundDrawable(ABSNabludatelActivity.this.getResources().getDrawable(R.drawable.for_frontend_04));
						}
						}

					}

					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {
						this.mInitialProgress = seekBar.getProgress();
						mIsProgressChanged = false;
					}

					@Override
					public void onStopTrackingTouch(SeekBar seekBar) {
						int progress = seekBar.getProgress();
						if(mIsProgressChanged && progress != mInitialProgress) {
							
							if (progress == Consts.SEEKBAR_TRUE) {
								ABSNabludatelActivity.this.mElectionsDB
										.addCheckListItem(lat, lng, seekBar
												.getTag().toString(), System
												.currentTimeMillis(), String
												.valueOf(true),
												mCurrentElectionsDistrict);
								// TODO: implement background color change
								
							}
							if (progress == Consts.SEEKBAR_FALSE) {
								ABSNabludatelActivity.this.mElectionsDB
										.addCheckListItem(lat, lng, seekBar
												.getTag().toString(), System
												.currentTimeMillis(), String
												.valueOf(false),
												mCurrentElectionsDistrict);
							}
							if (progress != Consts.SEEKBAR_TRUE && progress != Consts.SEEKBAR_FALSE){
								seekBar.setProgress(mInitialProgress);
							}
						}
					}

				});
			}

		}
	}
	
	public void restoreDataFromJSON(ViewGroup v, JSONObject from){
		Object tag;
		for(int i=0; i<v.getChildCount(); i++){
			tag = v.getChildAt(i).getTag();
			if(tag != null){
				String tagString = tag.toString();
				if(v.getChildAt(i) instanceof SeekBar){
					try {
						boolean value = from.getBoolean(tagString);
						if(value){
							((SeekBar)v.getChildAt(i)).setProgress(Consts.SEEKBAR_TRUE);
						}else{
							((SeekBar)v.getChildAt(i)).setProgress(Consts.SEEKBAR_FALSE);
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(v.getChildAt(i) instanceof EditText){
					EditText ed = (EditText) v.getChildAt(i);
					if(ed.getInputType() == android.text.InputType.TYPE_CLASS_NUMBER){
						try {
							int value = from.getInt(tagString);
							ed.setText(Integer.valueOf(value));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						try {
							String value = from.getString(tagString);
							ed.setText(value);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				if(v.getChildAt(i) instanceof ViewGroup)
				{
					restoreDataFromJSON((ViewGroup)v.getChildAt(i), from);
				}
			}
		}
	}
	public JSONObject getViewGroupJSON(ViewGroup v){
		Object tag;
		if(json == null){
			json = new JSONObject();
		}
		for(int i=0; i< v.getChildCount(); i++){
			tag = v.getChildAt(i).getTag();
			if(tag != null){
				
				if(v.getChildAt(i) instanceof SeekBar){
					SeekBar bar = (SeekBar)v.getChildAt(i);
					try {
						int progress = bar.getProgress();
						if(progress == Consts.SEEKBAR_FALSE){
							json.put(tag.toString(), false);
						}
						if(progress == Consts.SEEKBAR_TRUE){
							json.put(tag.toString(), true);
						}
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(v.getChildAt(i) instanceof EditText){
					EditText ed = (EditText) v.getChildAt(i);
					if(!ed.getText().toString().equals("")){
						if(ed.getInputType() == android.text.InputType.TYPE_CLASS_NUMBER){
							try{
								String value = ed.getText().toString();
								json.put(tag.toString(), Integer.valueOf(value));
							} catch (JSONException e){
								e.printStackTrace();
							}
						}else{
							try {
								json.put(tag.toString(), ed.getText().toString());
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
			if(v.getChildAt(i) instanceof ViewGroup){
				getViewGroupJSON((ViewGroup)v.getChildAt(i));
			}
		}
		return json;
	}
	
	public Intent save(){
		
		Intent result = new Intent();
		result.putExtra(Consts.ACTIVITY_JSON_DATA, makeJSON((ViewGroup)findViewById(android.R.id.content).getRootView()).toString());
		return result;
	}
	
	public JSONObject makeJSON(ViewGroup v){
		getViewGroupJSON(v);
		if(photo != null && photo.size() > 0){
			JSONArray photos = new JSONArray();
			for(int i=0; i<photo.size(); i++){
				photos.put(photo.get(i).getAbsolutePath());
			}
			try {
				json.put(Consts.PHOTO_FILE, photos);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(video != null && video.size() > 0){
			JSONArray videos = new JSONArray();
			for(int i=0; i<video.size(); i++){
				videos.put(video.get(i).getAbsolutePath());
			}
			try {
				json.put(Consts.VIDEO_FILE, videos);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return json;
	}
	
	protected void startNarusheniyePhoto() {

		// File pictureFile = takePicture(R.layout.post_narushenije);
		File pictureFile = startCameraPhoto();
		photo.add(pictureFile);
	}
	
	protected void startNarusheniyeVideo(){
		File videoFile = startCameraVideo();
		video.add(videoFile);
	}

	protected File startCameraPhoto() {
		// create Intent to take a picture and return control to the calling
		// application
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Uri toAdd = getOutputMediaFileUri(Consts.MEDIA_TYPE_IMAGE);
		pictureFileUri.add(toAdd); // create
																			// a
																			// file
																			// to
																			// save
																			// the
																			// image
		intent.putExtra(MediaStore.EXTRA_OUTPUT, toAdd); // set the
																	// image
																	// file name

		// start the image capture Intent
		startActivityForResult(intent,
				Consts.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		return new File(pictureFileUri.get(pictureFileUri.size()-1).getPath());
	}

	protected File startCameraVideo() {
		// create Intent to take a picture and return control to the calling
		// application
		Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
		Uri toAdd = getOutputMediaFileUri(Consts.MEDIA_TYPE_VIDEO);
		videoFileUri.add(toAdd); // create
																			// a
																			// file
																			// to
																			// save
																			// the
																			// image
		intent.putExtra(MediaStore.EXTRA_OUTPUT, toAdd); // set the
																	// image
																	// file name

		// start the image capture Intent
		startActivityForResult(intent,
				Consts.CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
		return new File(pictureFileUri.get(pictureFileUri.size()-1).getPath());
	}
	
	/** Create a file Uri for saving an image or video */
	protected static Uri getOutputMediaFileUri(int type) {
		return Uri.fromFile(getOutputMediaFile(type));
	}

	/** Create a File for saving an image or video */
	protected static File getOutputMediaFile(int type) {
		// To be safe, you should check that the SDCard is mounted
		// using Environment.getExternalStorageState() before doing this.

		File mediaStorageDir = new File(
				Environment
						.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
				"Nabludatel");
		// This location works best if you want the created images to be shared
		// between applications and persist after your app has been uninstalled.

		// Create the storage directory if it does not exist
		if (!mediaStorageDir.exists()) {
			if (!mediaStorageDir.mkdirs()) {
				Log.d("Nabludatel", "failed to create directory");
				return null;
			}
		}

		// Create a media file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(new Date());
		File mediaFile;
		if (type == Consts.MEDIA_TYPE_IMAGE) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "IMG_" + timeStamp + ".jpg");
		} else if (type == Consts.MEDIA_TYPE_VIDEO) {
			mediaFile = new File(mediaStorageDir.getPath() + File.separator
					+ "VID_" + timeStamp + ".mp4");
		} else {
			return null;
		}

		return mediaFile;
	}
	
	public JSONObject getJSON(){
		return json;
	}
	
	public void onBackButtonPress(View v){
		this.finish();
	}
	
	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

		switch(requestCode){
		case Consts.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:{
			if(resultCode == 0){
				// The user has cancelled image capture
				if(photo.size() > 0){
					photo.remove(photo.size()-1);
				}
			}
		}
		case Consts.CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE:{
			if(resultCode == 0){
				if(video.size() > 0){
					video.remove(video.size()-1);
				}
			}
			break;
		}
		}
	}
}
