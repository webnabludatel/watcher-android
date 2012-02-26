package org.dvaletin.apps.nabludatel.utils;

import java.io.File;

import org.dvaletin.apps.nabludatel.server.NabludatelCloud;
import org.dvaletin.apps.nabludatel.server.NabludatelCloudRequestTimeTooSkewedException;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class MediaSyncThread implements Runnable {
	private static final String TAG = "MediaSyncThread";
	NabludatelCloud cloudHelper;
	ElectionsDBHelper mElectionsDB;
	String deviceId;
	IMediaSyncCallCallback callback = null;
	

	public void init(Context context, String id) {
		mElectionsDB = new ElectionsDBHelper(context);
		mElectionsDB.open();
		this.deviceId = id;
		cloudHelper = new NabludatelCloud(deviceId);
		callback = new IMediaSyncCallCallback(){

			@Override
			public void onMediaSyncStart() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onMediaSyncFinish() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onMediaSyncProgresUpdate(int progress) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onMediaSyncError(String msg) {
				// TODO Auto-generated method stub
				
			}};
	}
	
	public void setMediaSyncCallback(IMediaSyncCallCallback toCall){
		callback = toCall;
	}

	@Override
	public void run() {
		Log.d(TAG, "started");
		callback.onMediaSyncStart();
		try {
			
			if (deviceId == null) {
				Log.e(TAG, "Device ID is not set, exiting");
				return;
			}
			if (mElectionsDB == null) {
				Log.e(TAG, "ElectionsDBHelper is not set, exiting");
			}
			cloudHelper.authentication();
			if (cloudHelper.isAuthenticated()) {
//				mElectionsDB.open();

				Cursor c = mElectionsDB.getAllMediaItems();
				c.moveToFirst();
				for (int i = 0; i < c.getCount(); i++) {
					callback.onMediaSyncProgresUpdate((i / c.getCount()) * 100);
					long mediaRowId = c.getLong(0);
					String filePath = c.getString(ElectionsDBHelper.MEDIAITEM_FILEPATH_COLUMN);
					String mediaType = c.getString(ElectionsDBHelper.MEDIAITEM_MEDIATYPE_COLUMN);
					long mediaTimeStamp = c.getLong(ElectionsDBHelper.MEDIAITEM_TIMESTAMP_COLUMN);
					long mediaChecklistId = c.getLong(ElectionsDBHelper.MEDIAITEM_CHECKLISTITEM_COLUMN);
					long mediaItemServerId = c.getLong(ElectionsDBHelper.MEDIAITEM_SERVER_STATUS_COLUMN);

					long serverMessageId = mElectionsDB.getCheckListItemServerId(mediaChecklistId);
					String violationName = mElectionsDB.getCheckListItemViolationName(mediaChecklistId);
					if(serverMessageId != -1L && mediaItemServerId == -1L){
						File toSend = new File(filePath);
						if(toSend.exists()){
							mediaItemServerId = cloudHelper.uploadMediaToMessage(serverMessageId, mediaTimeStamp, violationName, toSend, mediaType, mediaRowId, mediaChecklistId);
							Log.d(TAG, "Item sent:" + toSend.getCanonicalPath());
							mElectionsDB.updateMediaItemServerStatus(mediaRowId, mediaItemServerId);
						}else{
							Log.d(TAG, "File "+toSend.getCanonicalPath()+" does not exists, deleting record MediaItem:"+mediaRowId);
							mElectionsDB.removeMediaItem(mediaRowId);
							cloudHelper.setMediaDeletedForMessage(serverMessageId, mediaItemServerId, System.currentTimeMillis(), mediaRowId, mediaChecklistId);
						}
					}
					c.moveToNext();
				}
//				mElectionsDB.close();
			}
		} catch(NabludatelCloudRequestTimeTooSkewedException e){
			e.printStackTrace();
			callback.onMediaSyncError("На вашем телефоне неверно установлено время. Зайдите в настройки и включите автоматическую синхронизацию часов, даже если часовой пояс настроен неверно.");
		}catch (Exception e) {
			e.printStackTrace();
//			callback.onMediaSyncError(TAG+" "+e.getMessage());
		}
		callback.onMediaSyncFinish();
		Log.d(TAG, "finished");
	}
	public interface IMediaSyncCallCallback{
		public void onMediaSyncStart();
		public void onMediaSyncFinish();
		public void onMediaSyncProgresUpdate(int progress);
		public void onMediaSyncError(String msg);
	}
}
