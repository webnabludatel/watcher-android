package org.dvaletin.apps.nabludatel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import org.dvaletin.apps.nabludatel.utils.Consts;
import org.dvaletin.apps.nabludatel.utils.ElectionsDBHelper;
import org.dvaletin.apps.nabludatel.utils.Tumbler;
import org.dvaletin.apps.nabludatel.utils.CheckListItem;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public abstract class ABSNabludatelActivity extends Activity {
	private static final String T = ABSNabludatelActivity.class.getSimpleName();

	protected final ElectionsDBHelper mElectionsDB = new ElectionsDBHelper(this);

	protected SharedPreferences prefs;
	protected ArrayList<Uri> pictureFileUri;
	protected ArrayList<Uri> videoFileUri;
	protected HashMap<File, String> photos;
	protected HashMap<File, String> videos;
	File photoRequestFile;
	File videoRequestFile;
	long mCurrentPollingPlaceId = -1;
	String mCurrentPollingPlaceType;
	HashMap<String, CheckListItem> mCheckList;
	HashMap<String, View> activeViews;
	double lat;
	double lng;
	LocationListener mLocationListener;
	int screenId;
	Intent toReturn;
	private String lastPhotoKey;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		pictureFileUri = new ArrayList<Uri>();
		videoFileUri = new ArrayList<Uri>();

		photos = new HashMap<File, String>();
		videos = new HashMap<File, String>();
		toReturn = new Intent();
		mCheckList = new HashMap<String, CheckListItem>();
		activeViews = new HashMap<String, View>();
		Intent intent = getIntent();
		mCurrentPollingPlaceId = intent.getLongExtra(Consts.PREFS_CURRENT_POLLING_PLACE_ID, -1);
		
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

		
	}

	@Override
	public void onResume() {
		super.onResume();
		if(mCurrentPollingPlaceId > 0){
			mCurrentPollingPlaceType = mElectionsDB.getPollingPlaceType(mCurrentPollingPlaceId);
		}
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
		try{
			locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
		}catch (IllegalArgumentException e){
			try{
				locationManager.requestLocationUpdates(
						LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
			}catch (IllegalArgumentException e1){
				try{
					locationManager.requestLocationUpdates(
						LocationManager.PASSIVE_PROVIDER, 0, 0, mLocationListener);
				}catch (IllegalArgumentException e2){
					e2.printStackTrace();
				}
			}
		}
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
	
	public void onGalleryPhotoClick(View v){
		if(v.getTag() != null)
			startGalleryPhoto(v.getTag().toString());
	}

	private void startGalleryPhoto(String key) {
		this.lastPhotoKey = key;
		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		photoPickerIntent.setType("image/*");
		startActivityForResult(photoPickerIntent, Consts.GALLERY_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	public void onGalleryVideoClick(View v){
		if(v.getTag() != null)
			startGalleryVideo(v.getTag().toString());
	}
	
	private void startGalleryVideo(String key) {
		this.lastPhotoKey = key;
		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		photoPickerIntent.setType("video/*");
		startActivityForResult(photoPickerIntent, Consts.GALLERY_VIDEO_ACTIVITY_REQUEST_CODE);
	}

	public void onMakeVideoClick(View v) {
		if(v.getTag() != null)
			startCameraVideo(v.getTag().toString());
	}

	public void save() {
		fillActiveViews((ViewGroup)(findViewById(android.R.id.content)).getRootView());
		for (Entry<String, View> entry : activeViews.entrySet()){
			if(entry.getValue() instanceof EditText){
				EditText ed = (EditText)entry.getValue();
				String newValue = ed.getText().toString();
				if (!newValue.equals("")) {
					updateCheckListItem(entry.getKey(), newValue, "");
				}
			}
		}
		for (Entry<String, CheckListItem> entry : mCheckList.entrySet()) {
			saveCheckListItem(entry.getValue());
		}
	}

	protected CheckListItem updateCheckListItem(String key, String newValue, String violation) {
		CheckListItem v = mCheckList.get(key);
		if (v == null) {
			v = new CheckListItem(0L, System.currentTimeMillis(), lat, lng,
					key, newValue, mCurrentPollingPlaceId, violation);
			mCheckList.put(key, v);
		} else {
			v.setValue(newValue);
			if (v.isChanged()) {
				v.setCoordinates(lat, lng);
				v.setTimestamp(System.currentTimeMillis());
			}
		}
		return v;
	}

	protected CheckListItem saveCheckListItem(CheckListItem v) {
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
				CheckListItem checkListItem = saveCheckListItem(updateCheckListItem(checkListItemKey, value, ""));
				Set<File> processedFiles = new HashSet<File>();
				Cursor c = mElectionsDB.getMediaItemsByCheckListItemIdAndMediaType(checkListItem.getId(), mediaType);
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
										checkListItem.getId(),
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
								checkListItem.getId(),
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
		
		fillActiveViews((ViewGroup)(findViewById(android.R.id.content)).getRootView());

		Cursor c = mElectionsDB.getCheckListItemsByPollingPlaceIdAndScreenId(this.mCurrentPollingPlaceId, screenId);
		c.moveToFirst();

		for (int i = 0; i < c.getCount(); i++) {
			String key = c
					.getString(ElectionsDBHelper.CHECKLISTITEM_NAME_COLUMN);
			if(activeViews.keySet().contains(key)){
				CheckListItem checkListItem = new CheckListItem(c.getLong(0),
						c.getLong(ElectionsDBHelper.CHECKLISTITEM_TIMESTAMP_COLUMN),
						c.getDouble(ElectionsDBHelper.CHECKLISTITEM_LAT_COLUMN),
						c.getDouble(ElectionsDBHelper.CHECKLISTITEM_LNG_COLUMN),
						c.getString(ElectionsDBHelper.CHECKLISTITEM_NAME_COLUMN),
						c.getString(ElectionsDBHelper.CHECKLISTITEM_VALUE_COLUMN),
						c.getLong(ElectionsDBHelper.CHECKLISTITEM_POLLINGPLACE_COLUMN),
						c.getString(ElectionsDBHelper.CHECKLISTITEM_VIOLATION_COLUMN));
				mCheckList.put(key, checkListItem);

				restoreMediaItems(checkListItem, photos, Consts.PHOTO_FILE);
				TextView tp = (TextView)findViewById(R.id.photos_count);
				if( tp!= null ){
					tp.setText("("+photos.size()+")");
				}
				restoreMediaItems(checkListItem, videos, Consts.VIDEO_FILE);
				TextView tv = (TextView)findViewById(R.id.videos_count);
				if( tv!= null ){
					tv.setText("("+videos.size()+")");
				}
			}
			c.moveToNext();
		}

		restore((ViewGroup) findViewById(android.R.id.content).getRootView(),
				mCheckList, activeViews);
	}

	private void restoreMediaItems(CheckListItem checkListItem, Map<File, String> medias, String mediaType) {
		Cursor c = mElectionsDB.getMediaItemsByCheckListItemIdAndMediaType(checkListItem.getId(), mediaType);
		c.moveToFirst();
		for (int i = 0; i < c.getCount(); i++) {
			long mediaRowId = c.getLong(0);
			String filePath = c.getString(ElectionsDBHelper.MEDIAITEM_FILEPATH_COLUMN);
			long mediaItemServerId = c.getLong(ElectionsDBHelper.MEDIAITEM_SERVER_ID_COLUMN);

			File file = new File(filePath);
			if (file.exists()) {
				medias.put(file, checkListItem.getKey());
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

	protected void restore(ViewGroup v, HashMap<String, CheckListItem> from, HashMap<String, View> to) {
		for (Entry<String, View> entry : to.entrySet()) {
			View view = entry.getValue();
			String key = entry.getKey();
			CheckListItem data = from.get(key);
			restoreView(view, data);
		}
	}

	protected void restoreView(View view, CheckListItem data) {
		if (view instanceof Tumbler) {
			restoreTumbler((Tumbler) view, data);
		}
		if (view instanceof EditText) {
			restoreEditText((EditText) view, data);
		}
	}

	protected void restoreEditText(EditText editText, CheckListItem data) {
		try {
			if (data != null) {
				editText.setText(data.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void restoreTumbler(Tumbler tumbler, CheckListItem data) {
		try {
			if (data != null) {
				tumbler.setTumbler(data.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
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
							updateCheckListItem(seekBar.getTag().toString(),
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
			}else{
				TextView t = (TextView)findViewById(R.id.photos_count);
				if( t!= null ){
					t.setText("("+photos.size()+")");
				}
			}
			break;
		}
		case Consts.CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE: {
			if (resultCode == 0) {
				videos.remove(videoRequestFile);
			}else{
				TextView t = (TextView)findViewById(R.id.videos_count);
				if( t!= null ){
					t.setText("("+videos.size()+")");
				}
			}
			break;
		}
		case Consts.GALLERY_IMAGE_ACTIVITY_REQUEST_CODE: {
			if(resultCode != 0){
				Uri selectedImage = data.getData();
				String[] filePathColumn = {MediaStore.Images.Media.DATA};
				Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String path = cursor.getString(columnIndex);
				photos.put(new File(path), lastPhotoKey);
				TextView t = (TextView)findViewById(R.id.photos_count);
				if( t!= null ){
					t.setText("("+photos.size()+")");
				}
			}
			lastPhotoKey = null;
			break;
		}
		case Consts.GALLERY_VIDEO_ACTIVITY_REQUEST_CODE: {
			if (resultCode != 0){
				Uri selectedImage = data.getData();
				String[] filePathColumn = {MediaStore.Video.Media.DATA};
				Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
				cursor.moveToFirst();
				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String path = cursor.getString(columnIndex);
				videos.put(new File(path), lastPhotoKey);
				TextView t = (TextView)findViewById(R.id.videos_count);
				if( t!= null ){
					t.setText("("+videos.size()+")");
				}
			}
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
		toReturn.putExtra(Consts.PREFS_VIOLATIONS, mCheckList.entrySet().size());
		setResult(RESULT_OK, toReturn);
		super.onBackPressed();
	}

	public void showInfoDialog(int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Инструкции");
		builder.setMessage(getString(id)).setCancelable(false)
				.setPositiveButton("Понятно", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
	public String getDeviceId(){
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId;
		try {
			deviceId = tm.getDeviceId();
			return deviceId;
		}catch (java.lang.RuntimeException e){
			
			if(prefs == null){
				prefs = getSharedPreferences(Consts.PREFS_FILENAME, MODE_PRIVATE);
				
			}
			deviceId = prefs.getString(Consts.PREFS_DEVICE_ID, "");
			if(deviceId.equals("")){
				deviceId = "nogsm"+String.valueOf((long)(Math.random()*100000000L));
				prefs.edit().putString(Consts.PREFS_DEVICE_ID, deviceId).commit();
				Log.d(T, "generated random device id:"+deviceId);
			}
		}
		
		return deviceId;
	}
}
