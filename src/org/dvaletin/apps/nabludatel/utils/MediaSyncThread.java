package org.dvaletin.apps.nabludatel.utils;

import java.io.File;

import org.dvaletin.apps.nabludatel.MainWindow;
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
					callback.onMediaSyncProgresUpdate((int)( (i/c.getCount())*100 ));
					long mediaRowId = c.getLong(0);
					String filePath = c.getString(mElectionsDB.MEDIAITEM_FILEPATH_COLUMN);
					String mediaType = c.getString(mElectionsDB.MEDIAITEM_MEDIATYPE_COLUMN);
					String mediaURL = c.getString(mElectionsDB.MEDIAITEM_SERVERURL_COLUMN);
					long mediaTimeStamp = c.getLong(mElectionsDB.MEDIAITEM_TIMESTAMP_COLUMN);
					long mediaChecklistId = c.getLong(mElectionsDB.MEDIAITEM_CHECKLISTITEM_COLUMN);
					long mediaPollingPlace = c.getLong(mElectionsDB.CHECKLISTITEM_POLLINGPLACE_COLUMN);
					long mediaServerStatus = c.getLong(mElectionsDB.MEDIAITEM_SERVER_STATUS_COLUMN);
					
					long serverMessageId = mElectionsDB.getCheckListItemServerId(mediaChecklistId);
//					long serverPollingPlaceId = mElectionsDB.getPollingPlaceServerIdByNumber(mediaPollingPlace);
					if(serverMessageId != -1L && mediaServerStatus == -1L){
						long mediaServerId = cloudHelper.uploadMediaToMessage(serverMessageId, new File(filePath), mediaType);
						mElectionsDB.updateMediaItemServerStatus(mediaRowId, mediaServerId);
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
	}
	public interface IMediaSyncCallCallback{
		public void onMediaSyncStart();
		public void onMediaSyncFinish();
		public void onMediaSyncProgresUpdate(int progress);
		public void onMediaSyncError(String msg);
	}
}
