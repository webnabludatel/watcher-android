package org.dvaletin.apps.nabludatel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import org.dvaletin.apps.nabludatel.utils.Consts;
import org.dvaletin.apps.nabludatel.utils.ElectionsDBHelper;
import org.dvaletin.apps.nabludatel.utils.Violation;

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
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import org.dvaletin.apps.nabludatel.utils.Tumbler;

public abstract class ABSNabludatelActivity extends Activity {
	protected SharedPreferences prefs;
	protected ArrayList<Uri> pictureFileUri;
	protected ArrayList<Uri> videoFileUri;
	protected HashMap<File, String> photos;
	protected HashMap<File, String> videos;
	File photoRequestFile;
	File videoRequestFile;
	long mCurrentPollingPlaceId = -1;
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
		mCurrentPollingPlaceId = intent.getLongExtra(
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
		prefs = this.getSharedPreferences(Consts.PREFS_FILENAME, MODE_PRIVATE);
		mElectionsDB = new ElectionsDBHelper(this);

		pictureFileUri = new ArrayList<Uri>();
		videoFileUri = new ArrayList<Uri>();

		photos = new HashMap<File, String>();
		videos = new HashMap<File, String>();
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
			startCameraPhoto(v.getTag().toString());
	}

	public void onMakeVideoClick(View v) {
		if(v.getTag() != null)
			startCameraVideo(v.getTag().toString());
	}

	public void save() {
		for (Entry<String, View> entry : activeViews.entrySet()){
			if(entry.getValue() instanceof EditText){
				EditText ed = (EditText)entry.getValue();

				String newValue = ed.getText().toString();
				if (!newValue.equals("")) {
					updateViolationState(entry.getKey(), newValue, "");
				}
			}
		}
		for (Entry<String, Violation> entry : myState.entrySet()) {
			saveViolation(entry.getValue());
		}
	}

	private Violation updateViolationState(String key, String newValue, String violation) {
		Violation v = myState.get(key);
		if (v == null) {
			v = new Violation(0L, System.currentTimeMillis(), lat, lng,
					key, newValue, mCurrentPollingPlaceId, violation);
			myState.put(key, v);
		} else {
			v.setValue(newValue);
			if (v.isChanged()) {
				v.setCoordinates(lat, lng);
				v.setTimestamp(System.currentTimeMillis());
			}
		}
		return v;
	}

	private Violation saveViolation(Violation v) {
		if (v.isChanged() || v.getId() <= 0) {
			if (v.getId() <= 0) {
				v.setId(mElectionsDB.addCheckListItem(v, screenId));
			} else {
				mElectionsDB.updateCheckListItem(v);
			}
			v.setChanged(false);
		}
		return v;
	}

	private void saveMediaItems(String mediaType, Map<File, String> files) {
		if (files.size() > 0){
			HashMap<String, Integer> itemsCache = new HashMap<String, Integer>();
			for (Entry<File, String> entry : files.entrySet()) {
				String key = entry.getValue();
				Integer items = itemsCache.get(key);
				itemsCache.put(entry.getValue(), (items != null ? items : 0) + 1);
			}

			for (Entry<String,Integer> entry : itemsCache.entrySet()){
				String checkListItemKey = entry.getKey();
				String value = String.valueOf(entry.getValue());
				Violation violation = saveViolation(updateViolationState(checkListItemKey, value, ""));
				Set<File> processedFiles = new HashSet<File>();
				Cursor c = mElectionsDB.getMediaItemsByCheckListItemIdAndMediaType(violation.getId(), mediaType);
				c.moveToFirst();
				for (int i = 0; i < c.getCount(); i++) {
					long mediaRowId = c.getLong(0);
					String filePath = c.getString(ElectionsDBHelper.MEDIAITEM_FILEPATH_COLUMN);
					long mediaItemServerId = c.getLong(ElectionsDBHelper.MEDIAITEM_SERVER_ID_COLUMN);
					long timestamp = c.getLong(ElectionsDBHelper.MEDIAITEM_TIMESTAMP_COLUMN);

					boolean found = false;
					for (Entry<File, String> fe : files.entrySet()) {
						File file = fe.getKey();
						if (checkListItemKey.equals(fe.getValue()) &&
								file.getAbsolutePath().equals(filePath)) {
							// Update file
							if (file.lastModified() != timestamp) {
								mElectionsDB.updateMediaItem(
										mediaRowId,
										file.getAbsolutePath(),
										mediaType, "",
										file.lastModified(),
										violation.getId(),
										mCurrentPollingPlaceId
								);
							}
							processedFiles.add(file);
							found = true;
						}
					}
					if (!found) {
						// Remove file (record found in DB, but file does not exists)
						if (mediaItemServerId > 0L) {
							// Only mark record in DB (not really delete it)
							mElectionsDB.resetMediaItemServerStatus(mediaRowId);
						} else {
							// No server records, delete from DB
							mElectionsDB.removeMediaItem(mediaRowId);
						}
					}

					c.moveToNext();
				}

				for (Entry<File, String> fe : files.entrySet()) {
					File file = fe.getKey();
					if (checkListItemKey.equals(fe.getValue()) && !processedFiles.contains(file)) {
						// Create new media item
						mElectionsDB.addMediaItem(
								file.getAbsolutePath(),
								mediaType, "",
								file.lastModified(),
								violation.getId(),
								mCurrentPollingPlaceId
						);
					}
				}
			}
		}
	}

	public void savePhotos(){
		saveMediaItems(Consts.PHOTO_FILE, photos);
	}

	public void saveVideos(){
		saveMediaItems(Consts.VIDEO_FILE, videos);
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

		Cursor c = mElectionsDB.getCheckListItemsByPollingPlaceIdAndScreenId(this.mCurrentPollingPlaceId, screenId);
		c.moveToFirst();

		for (int i = 0; i < c.getCount(); i++) {
			String key = c
					.getString(ElectionsDBHelper.CHECKLISTITEM_NAME_COLUMN);
			if(activeViews.keySet().contains(key)){
				Violation violation = new Violation(c.getLong(0),
						c.getLong(ElectionsDBHelper.CHECKLISTITEM_TIMESTAMP_COLUMN), c.getDouble(ElectionsDBHelper.CHECKLISTITEM_LAT_COLUMN),
						c.getDouble(ElectionsDBHelper.CHECKLISTITEM_LNG_COLUMN),
						c.getString(ElectionsDBHelper.CHECKLISTITEM_NAME_COLUMN),
						c.getString(ElectionsDBHelper.CHECKLISTITEM_VALUE_COLUMN),
						c.getLong(ElectionsDBHelper.CHECKLISTITEM_POLLINGPLACE_COLUMN),
						c.getString(ElectionsDBHelper.CHECKLISTITEM_VIOLATION_COLUMN));
				myState.put(key, violation);

				restoreMediaItems(violation, photos, Consts.PHOTO_FILE);
				restoreMediaItems(violation, videos, Consts.VIDEO_FILE);
			}
			c.moveToNext();
		}

		restore((ViewGroup) findViewById(android.R.id.content).getRootView(),
				myState, activeViews);
	}

	private void restoreMediaItems(Violation violation, Map<File, String> medias, String mediaType) {
		Cursor c = mElectionsDB.getMediaItemsByCheckListItemIdAndMediaType(violation.getId(), mediaType);
		c.moveToFirst();
		for (int i = 0; i < c.getCount(); i++) {
			long mediaRowId = c.getLong(0);
			String filePath = c.getString(ElectionsDBHelper.MEDIAITEM_FILEPATH_COLUMN);
			long mediaItemServerId = c.getLong(ElectionsDBHelper.MEDIAITEM_SERVER_ID_COLUMN);

			File file = new File(filePath);
			if (file.exists()) {
				medias.put(file, violation.getKey());
			} else {
				// Remove file (record found in DB, but file does not exists)
				if (mediaItemServerId > 0L) {
					// Only mark record in DB (not really delete it)
					mElectionsDB.resetMediaItemServerStatus(mediaRowId);
				} else {
					// No server records, delete from DB
					mElectionsDB.removeMediaItem(mediaRowId);
				}
			}

			c.moveToNext();
		}
	}

	public void restore(ViewGroup v, HashMap<String, Violation> from, HashMap<String, View> to) {
		for (Entry<String, View> stringViewEntry : to.entrySet()) {
			Entry entry = (Entry) stringViewEntry;
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
			if (entry.getValue() instanceof EditText) {
				try {
					Violation data = from.get(entry.getKey().toString());
					if (data != null) {
						EditText ed = (EditText) entry.getValue();
						ed.setText(data.getValue());
					}
				} catch (Exception e) {
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
							updateViolationState(seekBar.getTag().toString(),
									((Tumbler) seekBar).getTumblerValue(),
									((Tumbler) seekBar).getViolation());
						}
					}

				});
			}

		}
	}

	protected void startCameraPhoto(String key) {
		photoRequestFile = startCamera(
				Consts.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE,
				Consts.MEDIA_TYPE_IMAGE,
				MediaStore.ACTION_IMAGE_CAPTURE);
		photos.put(photoRequestFile, key);
	}

	protected void startCameraVideo(String key) {
		videoRequestFile = startCamera(Consts.CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE,
				Consts.MEDIA_TYPE_VIDEO,
				MediaStore.ACTION_VIDEO_CAPTURE);
		videos.put(videoRequestFile, key);
	}

	protected File startCamera(int resultCode, int mediaType, String captureAction) {
		Intent intent = new Intent(captureAction);
		Uri media = getOutputMediaFileUri(mediaType);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, media);

		startActivityForResult(intent, resultCode);
		return new File(media.getPath());
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
				Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
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
				photos.remove(photoRequestFile);
			}
			break;
		}
		case Consts.CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE: {
			if (resultCode == 0) {
				videos.remove(videoRequestFile);
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
