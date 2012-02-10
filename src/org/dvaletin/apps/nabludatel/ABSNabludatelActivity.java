package org.dvaletin.apps.nabludatel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dvaletin.apps.nabludatel.utils.Consts;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

public abstract class ABSNabludatelActivity extends Activity {
	protected SharedPreferences prefs;
	protected Uri pictureFileUri;
	protected File photo;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		prefs = this.getPreferences(MODE_PRIVATE);
	}
	
	protected File startNarusheniyePhoto() {

		// File pictureFile = takePicture(R.layout.post_narushenije);
		File pictureFile = startCameraPhoto();
		return pictureFile;
	}

	protected File startCameraPhoto() {
		// create Intent to take a picture and return control to the calling
		// application
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		pictureFileUri = getOutputMediaFileUri(Consts.MEDIA_TYPE_IMAGE); // create
																			// a
																			// file
																			// to
																			// save
																			// the
																			// image
		intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureFileUri); // set the
																	// image
																	// file name

		// start the image capture Intent
		startActivityForResult(intent,
				Consts.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		return new File(pictureFileUri.getPath());
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
}
