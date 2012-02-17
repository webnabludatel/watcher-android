package org.dvaletin.apps.nabludatel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.dvaletin.apps.nabludatel.utils.Consts;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;

public abstract class ABSNabludatelActivity extends Activity {
	protected SharedPreferences prefs;
	protected ArrayList<Uri> pictureFileUri;
	protected ArrayList<Uri> videoFileUri;
	protected ArrayList<File> photo;
	protected ArrayList<File> video;
	JSONObject json = new JSONObject();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		prefs = this.getPreferences(MODE_PRIVATE);
		pictureFileUri = new ArrayList<Uri>();
		videoFileUri = new ArrayList<Uri>();
		
		photo = new ArrayList<File>();
		video = new ArrayList<File>();
	}
	
	public void onMakePhotoClick(View v){
		startNarusheniyePhoto();
	}
	
	public void onMakeVideoClick(View v){
		startNarusheniyeVideo();
	}
	
	public void restore(Intent from){
		try {
			json = new JSONObject(from.getStringExtra(Consts.ACTIVITY_JSON_DATA));
			restoreDataFromJSON((ViewGroup)findViewById(android.R.id.content).getRootView(), json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e){
			e.printStackTrace();
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
