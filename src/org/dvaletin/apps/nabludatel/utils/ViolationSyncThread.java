package org.dvaletin.apps.nabludatel.utils;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import org.dvaletin.apps.nabludatel.server.NabludatelCloud;

public class ViolationSyncThread implements Runnable {
	
	private static final String TAG = "ViolationSyncThread";
	NabludatelCloud cloudHelper;
	ElectionsDBHelper mElectionsDB;
	String deviceId;
	IViolationSyncCallCallback callback = null;
	
	public void init(Context context, String id){
		mElectionsDB = new ElectionsDBHelper(context);
		mElectionsDB.open();
		this.deviceId = id;
		cloudHelper = new NabludatelCloud(deviceId);
		callback = new IViolationSyncCallCallback() {

			@Override
			public void onViolationSyncStart() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onViolationSyncFinish() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onViolationSyncProgressUpdate(int progress) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onViolationSyncError(String msg) {
				// TODO Auto-generated method stub
				
			}};
	}
	
	public void setViolationSyncCallCallback(IViolationSyncCallCallback toCall){
		callback = toCall;
	}
	
	@Override
	public void run() {
		Log.d(TAG, "started");
		callback.onViolationSyncStart();
		try{
			
			if(deviceId == null){
				Log.e(TAG, "Device ID is not set, exiting");
				return;
			}
			if(mElectionsDB == null){
				Log.e(TAG, "ElectionsDBHelper is not set, exiting");
			}

			cloudHelper.authentication();
			if(cloudHelper.isAuthenticated()){
//				mElectionsDB.open();
				Cursor c = mElectionsDB.getAllCheckListItemsNotSynchronizedWithServer();
				c.moveToFirst();
				for(int i=0; i< c.getCount(); i++){
					callback.onViolationSyncProgressUpdate((i / c.getCount()) * 100);
					long rowId = c.getLong(0);
					double lat = c.getDouble(ElectionsDBHelper.CHECKLISTITEM_LAT_COLUMN);
					double lng = c.getDouble(ElectionsDBHelper.CHECKLISTITEM_LNG_COLUMN);
					String name = c.getString(ElectionsDBHelper.CHECKLISTITEM_NAME_COLUMN);
					long timestamp = c.getLong(ElectionsDBHelper.CHECKLISTITEM_TIMESTAMP_COLUMN);
					String value = c.getString(ElectionsDBHelper.CHECKLISTITEM_VALUE_COLUMN);
					long pollingPlace = c.getLong(ElectionsDBHelper.CHECKLISTITEM_POLLINGPLACE_COLUMN);
					String violation = c.getString(ElectionsDBHelper.CHECKLISTITEM_VIOLATION_COLUMN);
					long serverId = c.getLong(ElectionsDBHelper.CHECKLISTITEM_SERVER_ID_COLUMN);
					if(serverId == -1){
						serverId = cloudHelper.postNewMessage(name, value, lat, lng, timestamp, rowId, pollingPlace);
					} else {
						serverId = cloudHelper.editMessage(serverId, name, value, lat, lng, timestamp, rowId, pollingPlace);
					}
					Log.d(TAG, "violation sent "+violation);
					if(serverId != -1){
						mElectionsDB.updateCheckListItemServerId(rowId, serverId);
					}
					c.moveToNext();
				}
//				mElectionsDB.close();
			}else{
				cloudHelper.authentication();
			}
		}catch(Exception e){
//			callback.onViolationSyncError(TAG+" "+e.getMessage());
			e.printStackTrace();
		}
		callback.onViolationSyncFinish();
		Log.d(TAG, "finished");
	}
	public interface IViolationSyncCallCallback{
		public void onViolationSyncStart();
		public void onViolationSyncFinish();
		public void onViolationSyncProgressUpdate(int progress);
		public void onViolationSyncError(String msg);
	}
}
