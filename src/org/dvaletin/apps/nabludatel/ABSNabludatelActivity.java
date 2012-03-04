package org.dvaletin.apps.nabludatel;

import android.R;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import org.dvaletin.apps.nabludatel.utils.*;

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

	private String lastButtonKey;

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
		mCurrentPollingPlaceId = intent.getLongExtra(
				Consts.PREFS_CURRENT_POLLING_PLACE_ID, -1);

		screenId = intent.getIntExtra(Consts.PREFS_LAYOUT_ID, -1);
		lat = intent.getDoubleExtra(Consts.PREFS_LATITUDE, 0.0d);
		lng = intent.getDoubleExtra(Consts.PREFS_LONGITUDE, 0.0d);
		String title = intent.getStringExtra(Consts.PREFS_TITLE);
		if (title != null) {
			setTitle(title);
		}
		if (screenId != -1) {
			this.setContentView(screenId);
		}
		prefs = this.getSharedPreferences(Consts.PREFS_FILENAME, MODE_PRIVATE);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (mCurrentPollingPlaceId > 0) {
			mCurrentPollingPlaceType = mElectionsDB
					.getPollingPlaceType(mCurrentPollingPlaceId);
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
		try {
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
		} catch (IllegalArgumentException e) {
			try {
				locationManager.requestLocationUpdates(
						LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
			} catch (IllegalArgumentException e1) {
				try {
					locationManager.requestLocationUpdates(
							LocationManager.PASSIVE_PROVIDER, 0, 0,
							mLocationListener);
				} catch (IllegalArgumentException e2) {
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

	public void save() {
		fillActiveViews((ViewGroup) (findViewById(android.R.id.content))
				.getRootView());
		for (Entry<String, View> entry : activeViews.entrySet()) {
			if (entry.getValue() instanceof EditText) {
				EditText ed = (EditText) entry.getValue();
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

	protected CheckListItem updateCheckListItem(String key, String newValue,
			String violation) {
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
		if (files.size() > 0) {
			HashMap<String, Integer> itemsCache = new HashMap<String, Integer>();
			for (Entry<File, String> entry : files.entrySet()) {
				String key = entry.getValue();
				if (key != null) {
					Integer items = itemsCache.get(key);
					itemsCache.put(key, (items != null ? items : 0) + 1);
				} else {
					files.remove(entry.getKey());
				}
			}

			for (Entry<String, Integer> entry : itemsCache.entrySet()) {
				String checkListItemKey = entry.getKey();
				String value = String.valueOf(entry.getValue());
				CheckListItem checkListItem = saveCheckListItem(updateCheckListItem(
						checkListItemKey, value, ""));
				Set<File> processedFiles = new HashSet<File>();
				Cursor c = mElectionsDB.getMediaItemsByCheckListItemIdAndMediaType(checkListItem.getId(), mediaType);
				try {
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
				} finally {
					c.close();
				}

				for (Entry<File, String> fe : files.entrySet()) {
					File file = fe.getKey();
					if (checkListItemKey.equals(fe.getValue())
							&& !processedFiles.contains(file)) {
						// Create new media item
						mElectionsDB.addMediaItem(file.getAbsolutePath(),
								mediaType, "", file.lastModified(),
								checkListItem.getId(), mCurrentPollingPlaceId);
					}
				}
			}
		}
	}

	public void savePhotos() {
		saveMediaItems(Consts.PHOTO_FILE, photos);
	}

	public void saveVideos() {
		saveMediaItems(Consts.VIDEO_FILE, videos);
	}

	public void fillActiveViews(ViewGroup v) {
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

	public List<View> findViewsByTag(ViewGroup v, String tag){
		List<View> views = new ArrayList<View>();
		if (tag != null) {
			for (int i = 0; i < v.getChildCount(); i++) {
				View child = v.getChildAt(i);
				if (child instanceof ViewGroup) {
					views.addAll(findViewsByTag((ViewGroup) child, tag));
				}

				if (tag.equals(child.getTag())) {
					views.add(child);
				}
			}
		}
		return views;
	}

	public void restore() {
		fillActiveViews((ViewGroup)(findViewById(android.R.id.content)).getRootView());

		restoreCheckListItemsFromDb(this.mCurrentPollingPlaceId);

		restore((ViewGroup) findViewById(android.R.id.content).getRootView(),
				mCheckList, activeViews);
	}

	protected void restoreCheckListItemsFromDb(long pollingPlaceId) {
		Cursor c = mElectionsDB.getCheckListItemsByPollingPlaceIdAndScreenId(pollingPlaceId, screenId);
		try {
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
					restoreMediaItems(checkListItem, videos, Consts.VIDEO_FILE);
				}
				c.moveToNext();
			}
		} finally {
			c.close();
		}
	}

	private void restoreMediaItems(CheckListItem checkListItem, Map<File, String> medias, String mediaType) {
		Cursor c = mElectionsDB.getMediaItemsByCheckListItemIdAndMediaType(checkListItem.getId(), mediaType);
		try {
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
		} finally {
			c.close();
		}
		setCurrentButtonKeysCount(medias.values());
	}

	protected void restore(ViewGroup v, HashMap<String, CheckListItem> from,
			HashMap<String, View> to) {
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

	protected void showPhotoSelector(final Button button) {
		showButtonSelector(button, new CharSequence[]{"Снять фото", "Выбрать в альбомах", "Отменить"},
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						if (item == 0) {
							startCameraPhoto();
						} else if (item == 1) {
							startGalleryPhoto();
						}
					}
				});
	}

	protected void showVideoSelector(final Button button) {
		showButtonSelector(button, new CharSequence[]{"Снять видео", "Выбрать в альбомах", "Отменить"},
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						if (item == 0) {
							startCameraVideo();
						} else if (item == 1) {
							startGalleryVideo();
						}
					}
				});
	}

	protected void showButtonSelector(final Button button, final CharSequence[] items, final DialogInterface.OnClickListener onClickListener) {
		if (button.getTag() != null) {
			lastButtonKey = button.getTag().toString();
			runOnUiThread(new Runnable() {
				public void run() {
					final AlertDialog.Builder builder = new AlertDialog.Builder(ABSNabludatelActivity.this);
					builder.setTitle(getTextWithoutCount(button));
					builder.setItems(items, onClickListener);
					AlertDialog alert = builder.create();
					alert.show();
				}
			});
		}
	}

	private String getTextWithoutCount(Button button) {
		return button.getText().toString().replaceAll("\\(\\d+\\)$", "").trim();
	}

	public void onMakePhotoClick(View v) {
		showPhotoSelector((Button) v);
	}

	public void onMakeVideoClick(View v) {
		showVideoSelector((Button) v);
	}

	private void startGalleryPhoto() {
		Intent in = new Intent(Intent.ACTION_PICK);
		in.setType("image/*");
		startActivityForResult(in, Consts.GALLERY_IMAGE_ACTIVITY_REQUEST_CODE);
	}

	private void startGalleryVideo() {
		Intent in = new Intent(Intent.ACTION_PICK);
		in.setType("video/*");
		startActivityForResult(in, Consts.GALLERY_VIDEO_ACTIVITY_REQUEST_CODE);
	}

	protected void startCameraPhoto() {
		photoRequestFile = startCamera(
				Consts.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE,
				Consts.MEDIA_TYPE_IMAGE, MediaStore.ACTION_IMAGE_CAPTURE);
		if (photoRequestFile != null)
			photos.put(photoRequestFile, lastButtonKey);
		else {
			this.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(ABSNabludatelActivity.this,
							"Не могу запустить камеру...", Toast.LENGTH_LONG);
				}
			});
		}
	}

	protected void startCameraVideo() {
		videoRequestFile = startCamera(
				Consts.CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE,
				Consts.MEDIA_TYPE_VIDEO, MediaStore.ACTION_VIDEO_CAPTURE);
		if (videoRequestFile != null)
			videos.put(videoRequestFile, lastButtonKey);
		else {
			this.runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					Toast.makeText(ABSNabludatelActivity.this,
							"Не могу запустить камеру...", Toast.LENGTH_LONG);
				}
			});
		}
	}

	protected File startCamera(int resultCode, int mediaType,
			String captureAction) {
		try {
			Intent intent = new Intent(captureAction);
			Uri media = getOutputMediaFileUri(mediaType);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, media);

			startActivityForResult(intent, resultCode);
			return new File(media.getPath());
		} catch (Exception e) {
			return null;
		}
	}

	/** Create a file Uri for saving an image or video */
	protected static Uri getOutputMediaFileUri(int type) {
		File mediaFile = getOutputMediaFile(type);
		if (mediaFile != null)
			return Uri.fromFile(mediaFile);
		else
			return null;
	}

	/** Create a File for saving an image or video */
	protected static File getOutputMediaFile(int type) {
		// To be safe, you should check that the SDCard is mounted
		// using Environment.getExternalStorageState() before doing this.
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// We can read and write the media
			File mediaStorageDir = new File(
					Environment
							.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
					"Nabludatel");
			// This location works best if you want the created images to be
			// shared
			// between applications and persist after your app has been
			// uninstalled.

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
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			// We can only read the media
			return null;
		} else {
			// Something else is wrong. It may be one of many other states, but
			// all we need
			// to know is we can neither read nor write
			return null;
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {
		case Consts.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE: {
			if (resultCode == 0) {
				// The user has cancelled image capture
				photos.remove(photoRequestFile);
			} else {
				Toast.makeText(ABSNabludatelActivity.this, "Фото добавлено",
						Toast.LENGTH_LONG);
			}
			setCurrentButtonKeysCount(photos.values());
			break;
		}
		case Consts.CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE: {
			if (resultCode == 0) {
				videos.remove(videoRequestFile);
			} else {
				Toast.makeText(ABSNabludatelActivity.this, "Видео добавлено",
						Toast.LENGTH_LONG);
			}
			setCurrentButtonKeysCount(videos.values());
			break;
		}
		case Consts.GALLERY_IMAGE_ACTIVITY_REQUEST_CODE: {
			if (resultCode != 0) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = {MediaStore.Images.Media.DATA};
				Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
				try {
					cursor.moveToFirst();
					int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
					String path = cursor.getString(columnIndex);
					photos.put(new File(path), lastButtonKey);
				} finally {
					cursor.close();
				}
			}
			setCurrentButtonKeysCount(photos.values());
			break;
		}
		case Consts.GALLERY_VIDEO_ACTIVITY_REQUEST_CODE: {
			if (resultCode != 0) {
				Uri selectedImage = data.getData();
				String[] filePathColumn = {MediaStore.Video.Media.DATA};
				Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
				try {
					cursor.moveToFirst();
					int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
					String path = cursor.getString(columnIndex);
					videos.put(new File(path), lastButtonKey);
				} finally {
					cursor.close();
				}
			}
			setCurrentButtonKeysCount(videos.values());
			break;
		}
		}
		lastButtonKey = null;
	}

	private void setCurrentButtonKeysCount(Collection<String> keys) {
		Map<String, Integer> counts = new HashMap<String, Integer>();
		for (String key : keys) {
			Integer count = counts.get(key);
			if (count == null) {
				count = 0;
			}
			counts.put(key, count + 1);
		}
		ViewGroup root = (ViewGroup) (findViewById(R.id.content)).getRootView();
		for (String key : counts.keySet()) {
			List<View> views = findViewsByTag(root, key);
			for (View view : views) {
				if (view instanceof Button) {
					Button button = (Button) view;
					String textWithoutCount = getTextWithoutCount(button);
					Integer count = counts.get(key);
					if (count != null && count > 0) {
						button.setText(textWithoutCount + " (" + count + ")");
					} else {
						button.setText(textWithoutCount);
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
		setResult(RESULT_OK, toReturn);
		super.onBackPressed();
	}

	public void showInfoDialog(int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Инструкции");
		builder.setMessage(getString(id))
				.setCancelable(false)
				.setPositiveButton("Понятно",
						new DialogInterface.OnClickListener() {
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
		} catch (java.lang.RuntimeException e) {

			if (prefs == null) {
				prefs = getSharedPreferences(Consts.PREFS_FILENAME,
						MODE_PRIVATE);

			}
			deviceId = prefs.getString(Consts.PREFS_DEVICE_ID, "");
			if (deviceId.equals("")) {
				deviceId = "nogsm"
						+ String.valueOf((long) (Math.random() * 100000000L));
				prefs.edit().putString(Consts.PREFS_DEVICE_ID, deviceId)
						.commit();
				Log.d(T, "generated random device id:" + deviceId);
			}
		}

		return deviceId;
	}

}
