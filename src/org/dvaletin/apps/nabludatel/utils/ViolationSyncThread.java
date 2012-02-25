package org.dvaletin.apps.nabludatel.utils;

import org.dvaletin.apps.nabludatel.server.NabludatelCloud;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

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
			public void onViolationSyncProgresUpdate(int progress) {
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
				Cursor c = mElectionsDB.getAllCheckListItems();
				c.moveToFirst();
				for(int i=0; i< c.getCount(); i++){
					callback.onViolationSyncProgresUpdate((int)( (i/c.getCount())*100 ));
					long rowId = c.getLong(0);
					double lat = c.getDouble(ElectionsDBHelper.CHECKLISTITEM_LAT_COLUMN);
					double lng = c.getDouble(ElectionsDBHelper.CHECKLISTITEM_LNG_COLUMN);
					String name = c.getString(ElectionsDBHelper.CHECKLISTITEM_NAME_COLUMN);
					long timestamp = c.getLong(ElectionsDBHelper.CHECKLISTITEM_TIMESTAMP_COLUMN);
					String value = c.getString(ElectionsDBHelper.CHECKLISTITEM_VALUE_COLUMN);
					long pollingPlace = c.getLong(ElectionsDBHelper.CHECKLISTITEM_POLLINGPLACE_COLUMN);
					String violation = c.getString(ElectionsDBHelper.CHECKLISTITEM_VIOLATION_COLUMN);
					String pollingPlaceName = mElectionsDB.getPollingPlaceNameByNumber(pollingPlace);
					long server_status = c.getLong(ElectionsDBHelper.CHECKLISTITEM_SERVER_STATUS_COLUMN);
	//				Cursor pollingPlaceNameCursor = mElectionsDB.getPollingPlaceByNumber(pollingPlace);
					if(server_status == -1){
						long serverId = cloudHelper.postNewMessage(name, violation, lat, lng, timestamp);
						if(serverId != -1){
							mElectionsDB.updateCheckListItemServerSync(rowId, serverId);
						}
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
	}
	public interface IViolationSyncCallCallback{
		public void onViolationSyncStart();
		public void onViolationSyncFinish();
		public void onViolationSyncProgresUpdate(int progress);
		public void onViolationSyncError(String msg);
	}
}
