package org.dvaletin.apps.nabludatel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dvaletin.apps.nabludatel.server.NabludatelCloud;
import org.dvaletin.apps.nabludatel.utils.Consts;
import org.dvaletin.apps.nabludatel.utils.ElectionsDBHelper;
import org.dvaletin.apps.nabludatel.utils.Violation;
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
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import org.dvaletin.apps.nabludatel.utils.Tumbler;

public abstract class ABSNabludatelActivity extends Activity {
	protected SharedPreferences prefs;
	protected ArrayList<Uri> pictureFileUri;
	protected ArrayList<Uri> videoFileUri;
	protected HashMap<File, String> photo;
	protected HashMap<File, String> video;
	File photoRequestFile;
	File videoRequestFile;
	JSONObject json = new JSONObject();
	long mCurrentElectionsDistrict = -1;
	ElectionsDBHelper mElectionsDB;
	HashMap<String, Violation> myState;
	HashMap<String, View> activeViews;
	double lat;
	double lng;
	LocationListener mLocationListener;
	int screenId;
	Intent toReturn;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		mCurrentElectionsDistrict = intent.getLongExtra(
				Consts.PREFS_ELECTIONS_DISRICT, -1);
		screenId = intent.getIntExtra(Consts.PREFS_LAYOUT_ID, -1);
		lat = intent.getDoubleExtra(Consts.PREFS_LATITUDE, 0.0d);
		lng = intent.getDoubleExtra(Consts.PREFS_LONGITUDE, 0.0d);
		String title = intent.getStringExtra(Consts.PREFS_TITLE);
		if(title != null){
			setTitle(title);
		}
		if(screenId != -1){
			this.setContentView(screenId);
		}
		prefs = this.getPreferences(MODE_PRIVATE);
		mElectionsDB = new ElectionsDBHelper(this);

		pictureFileUri = new ArrayList<Uri>();
		videoFileUri = new ArrayList<Uri>();

		photo = new HashMap<File, String>();
		video = new HashMap<File, String>();
		toReturn = new Intent();
		
		
	}

	@Override
	public void onResume() {
		super.onResume();
		mElectionsDB.open();
		setCallbacks((ViewGroup) (findViewById(android.R.id.content)
				.getRootView()));
		restore();
		LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		// Define a listener that responds to location updates
		mLocationListener = new LocationListener() {
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}

			@Override
			public void onLocationChanged(Location location) {
				lat = location.getLatitude();
				lng = location.getLongitude();
			}
		};

		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);

	}

	@Override
	public void onPause() {
		LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		locationManager.removeUpdates(mLocationListener);

		mElectionsDB.close();
		super.onPause();
	}

	public void onMakePhotoClick(View v) {
		if(v.getTag() != null)
			startNarusheniyePhoto(v.getTag().toString());
	}

	public void onMakeVideoClick(View v) {
		if(v.getTag() != null)
			startNarusheniyeVideo(v.getTag().toString());
	}

	public void save() {
		for(Entry<String, View> entry : activeViews.entrySet()){
			if(entry.getValue() instanceof EditText){
				EditText ed = (EditText)entry.getValue();

				if(!ed.getText().toString().equals("")){
					myState.put(entry.getKey(), new Violation(
							lat, 
							lng, 
							ed.getTag().toString(), 
							System.currentTimeMillis(),
							ed.getText().toString(),
							mCurrentElectionsDistrict,
							""
					));
				}
			}
		}
		for (Entry<String, Violation> entry : myState.entrySet()) {
			mElectionsDB.addCheckListItem(entry.getValue(), screenId);
		}
	}

	public void savePhotos(){

		int numOfPhotos = photo.size();
		if(numOfPhotos > 0){
			HashMap<String, Integer> photoItemsCache = new HashMap<String, Integer>();
			Iterator i = photo.entrySet().iterator();
			while(i.hasNext()){
				Map.Entry entry = (Map.Entry) i.next();
				String key = (String)entry.getValue();
				int items = 0;
				try{
					items = photoItemsCache.get(key);
				}catch (Exception e){
					e.printStackTrace();
				}
				items++;
				photoItemsCache.put((String)entry.getValue(), items);
			}
			
			i = photoItemsCache.entrySet().iterator();
			while(i.hasNext()){
				Map.Entry entry = (Map.Entry) i.next();
				String key = (String)entry.getKey();
				long violationId = mElectionsDB.addCheckListItem(
						lat,
						lng,
						(String)entry.getKey(),
						System.currentTimeMillis(),
						String.valueOf((Integer)entry.getValue()),
						mCurrentElectionsDistrict,
						"",
						screenId
						);
				Iterator j = photo.entrySet().iterator();
				while(j.hasNext()){
					Map.Entry photoEntry = (Map.Entry)j.next();
					String checklist_item = (String) photoEntry.getValue();
					if(checklist_item.equals(key)){
						File photoFile = (File) photoEntry.getKey();
						mElectionsDB.addMediaItem(
								photoFile.getAbsolutePath(),
								"photo",
								"",
								System.currentTimeMillis(),
								violationId,
								mCurrentElectionsDistrict
								);
					}
				}
			}
		}
	}
	
	public void saveVideos(){

		int numOfvideos = video.size();
		if(numOfvideos > 0){
			HashMap<String, Integer> videoItemsCache = new HashMap<String, Integer>();
			Iterator i = video.entrySet().iterator();
			while(i.hasNext()){
				Map.Entry entry = (Map.Entry) i.next();
				String key = (String)entry.getValue();
				int items = 0;
				try{
					items = videoItemsCache.get(key);
				}catch (Exception e){
					e.printStackTrace();
				}
				items++;
				videoItemsCache.put((String)entry.getValue(), items);
			}
			
			i = videoItemsCache.entrySet().iterator();
			while(i.hasNext()){
				Map.Entry entry = (Map.Entry) i.next();
				String key = (String)entry.getKey();
				long violationId = mElectionsDB.addCheckListItem(
						lat,
						lng,
						(String)entry.getKey(),
						System.currentTimeMillis(),
						String.valueOf((Integer)entry.getValue()),
						mCurrentElectionsDistrict,
						"",
						screenId
						);
				Iterator j = video.entrySet().iterator();
				while(j.hasNext()){
					Map.Entry videoEntry = (Map.Entry)j.next();
					String checklist_item = (String) videoEntry.getValue();
					if(checklist_item.equals(key)){
						File videoFile = (File) videoEntry.getKey();
						mElectionsDB.addMediaItem(
								videoFile.getAbsolutePath(),
								"video",
								"",
								System.currentTimeMillis(),
								violationId,
								mCurrentElectionsDistrict
								);
					}
				}
			}
		}
	}
	
	public void fillActiveViews(ViewGroup v){
		for (int i = 0; i < v.getChildCount(); i++) {
			if (v.getChildAt(i) instanceof ViewGroup) {
				fillActiveViews((ViewGroup) (v.getChildAt(i)));
			}

			if (v.getChildAt(i).getTag() != null) {
				try {
					String tag = v.getChildAt(i).getTag().toString();
					activeViews.put(tag, v.getChildAt(i));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void restore() {
		myState = new HashMap<String, Violation>();
		activeViews = new HashMap<String, View>();
		fillActiveViews((ViewGroup)(findViewById(android.R.id.content)).getRootView());
		if (mCurrentElectionsDistrict == -1)
			return;
		Cursor c = mElectionsDB
				.getAllCheckListItemsByElectionsDistrictIdAndScreenId(this.mCurrentElectionsDistrict, screenId);
		c.moveToFirst();

		for (int i = 0; i < c.getCount(); i++) {
			String key = c
					.getString(ElectionsDBHelper.CHECKLISTITEM_NAME_COLUMN);
			if(activeViews.keySet().contains(key)){
				myState.put(
					key,
					new Violation(
							c.getDouble(ElectionsDBHelper.CHECKLISTITEM_LAT_COLUMN),
							c.getDouble(ElectionsDBHelper.CHECKLISTITEM_LNG_COLUMN),
							c.getString(ElectionsDBHelper.CHECKLISTITEM_NAME_COLUMN),
							c.getLong(ElectionsDBHelper.CHECKLISTITEM_TIMESTAMP_COLUMN),
							c.getString(ElectionsDBHelper.CHECKLISTITEM_VALUE_COLUMN),
							c.getLong(ElectionsDBHelper.CHECKLISTITEM_POLLINGPLACE_COLUMN),
							c.getString(ElectionsDBHelper.CHECKLISTITEM_VIOLATION_COLUMN)));
			}
			c.moveToNext();
		}
		restore((ViewGroup) findViewById(android.R.id.content).getRootView(),
				myState, activeViews);
	}

	public void restore(ViewGroup v, HashMap<String, Violation> from, HashMap<String, View> to) {
		Iterator i =   to.entrySet().iterator();
		while(i.hasNext()){
			Map.Entry entry = (Map.Entry)i.next();
			if (entry.getValue() instanceof Tumbler) {
				try {
					Violation data = from.get(entry.getKey().toString());
					if (data != null) {
						Tumbler bar = (Tumbler) entry.getValue();
						bar.setTumbler(data.getValue());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(entry.getValue() instanceof EditText){
				try {
					Violation data = from.get(entry.getKey().toString());
					if(data != null) {
						EditText ed = (EditText) entry.getValue();
						ed.setText(data.getValue());
					}
				}catch (Exception e){
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

			if (v.getChildAt(i) instanceof Tumbler) {
				Tumbler bar = (Tumbler) v.getChildAt(i);
				bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
					int mInitialProgress;
					boolean mIsProgressChanged = false;

					@Override
					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						if (fromUser) {
							mIsProgressChanged = true;

						}
						if (((Tumbler) seekBar).getTumblerValue()
								.equals("true")) {
							seekBar.setBackgroundDrawable(ABSNabludatelActivity.this
									.getResources().getDrawable(
											R.drawable.for_frontend_15));
						} else if (((Tumbler) seekBar).getTumblerValue()
								.equals("false")) {
							seekBar.setBackgroundDrawable(ABSNabludatelActivity.this
									.getResources().getDrawable(
											R.drawable.for_frontend_11));
						} else {
							seekBar.setBackgroundDrawable(ABSNabludatelActivity.this
									.getResources().getDrawable(
											R.drawable.for_frontend_04));
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
						if (mIsProgressChanged && progress != mInitialProgress) {
							String key = seekBar.getTag().toString();
							Violation v = ABSNabludatelActivity.this.myState
									.get(key);
							if (v == null) {
								ABSNabludatelActivity.this.myState.put(
										key,
										new Violation(lat, lng, seekBar
												.getTag().toString(), System
												.currentTimeMillis(),
												((Tumbler) seekBar)
														.getTumblerValue(),
												mCurrentElectionsDistrict,
												((Tumbler) seekBar)
														.getViolation()));
							} else {
								v.setValue(((Tumbler) seekBar)
										.getTumblerValue());
								v.setCoordinates(lat, lng);
								v.setTimeStamp(System.currentTimeMillis());
							}
							// ABSNabludatelActivity.this.mElectionsDB
							// .addCheckListItem(
							// lat,
							// lng,
							// seekBar.getTag().toString(),
							// System.currentTimeMillis(),
							// ((Tumbler) seekBar).getTumblerValue(),
							// mCurrentElectionsDistrict,
							// ((Tumbler) seekBar).getViolation()
							// );

						}
					}

				});
			}

		}
	}

	protected void startNarusheniyePhoto(String key) {

		// File pictureFile = takePicture(R.layout.post_narushenije);
		photoRequestFile = startCameraPhoto();
		
		photo.put(photoRequestFile, key);
	}

	protected void startNarusheniyeVideo(String key) {
		videoRequestFile = startCameraVideo();
		video.put(videoRequestFile, key);
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
		return new File(pictureFileUri.get(pictureFileUri.size() - 1).getPath());
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
		return new File(videoFileUri.get(videoFileUri.size() - 1).getPath());
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


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {
		case Consts.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE: {
			if (resultCode == 0) {
				// The user has cancelled image capture
				if (photo.size() > 0) {
					photo.remove(photo.size() - 1);
				}
			}
			break;
		}
		case Consts.CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE: {
			if (resultCode == 0) {
				if (video.size() > 0) {
					video.remove(video.size() - 1);
				}
			}
			break;
		}
		}
	}
	
	public Intent getReturnIntent(){
		return toReturn;
	}
	
	@Override
	public void onBackPressed() {
		save();
		savePhotos();
		saveVideos();
		toReturn.putExtra(Consts.PREFS_VIOLATIONS, myState.entrySet().size());
		setResult(RESULT_OK, toReturn);
		super.onBackPressed();
	}

}
