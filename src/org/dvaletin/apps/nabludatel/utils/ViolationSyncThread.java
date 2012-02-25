package org.dvaletin.apps.nabludatel.utils;

import org.dvaletin.apps.nabludatel.server.NabludatelCloud;

import android.database.Cursor;
import android.util.Log;

public class ViolationSyncThread implements Runnable {
	
	private static final String TAG = "ViolationSyncThread";
	NabludatelCloud cloudHelper;
	ElectionsDBHelper mElectionsDB;
	String deviceId;
	
	public void init(ElectionsDBHelper dbHelper, String id){
		mElectionsDB = dbHelper;
		this.deviceId = id;
	}
	@Override
	public void run() {
		try{
			if(deviceId == null){
				Log.e(TAG, "Device ID is not set, exiting");
				return;
			}
			if(mElectionsDB == null){
				Log.e(TAG, "ElectionsDBHelper is not set, exiting");
			}
			cloudHelper = new NabludatelCloud(deviceId);
			cloudHelper.authentication();
			if(cloudHelper.isAuthenticated()){
				mElectionsDB.open();
				Cursor c = mElectionsDB.getAllCheckListItems();
				c.moveToFirst();
				for(int i=0; i< c.getCount(); i++){
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
				mElectionsDB.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
