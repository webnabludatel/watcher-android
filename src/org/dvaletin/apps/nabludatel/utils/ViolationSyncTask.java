package org.dvaletin.apps.nabludatel.utils;

import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import org.dvaletin.apps.nabludatel.server.NabludatelCloud;

public class ViolationSyncTask extends AsyncTask<ElectionsDBHelper, String, String> {
	private static final String T = ViolationSyncTask.class.getSimpleName();

	private final NabludatelCloud cloud;
	private final IViolationSyncCallCallback callback;

	public ViolationSyncTask(NabludatelCloud cloud, IViolationSyncCallCallback callback) {
		this.cloud = cloud;
		this.callback = callback;
	}

	@Override
	protected String doInBackground(ElectionsDBHelper... dbs) {
		if (dbs == null || dbs.length == 0) {
			Log.w(T, "No databases available");
			return null;
		}
		if (callback != null) callback.onViolationSyncStart();
		try {
			if (cloud.tryAuthenticate()) {
				for (ElectionsDBHelper db : dbs) {
					performSync(db);
				}
			}
		} catch (Exception e) {
			Log.w(T, "Synchronization error", e);
		}
		if (callback != null) callback.onViolationSyncFinish();
		return null;
	}

	private void performSync(ElectionsDBHelper db) {
		Cursor c = db.getAllCheckListItemsNotSynchronizedWithServer();
		c.moveToFirst();
		for (int i = 0; i < c.getCount(); i++) {
			if (callback != null) callback.onViolationSyncProgressUpdate((i / c.getCount()) * 100);
			long rowId = c.getLong(0);
			double lat = c.getDouble(ElectionsDBHelper.CHECKLISTITEM_LAT_COLUMN);
			double lng = c.getDouble(ElectionsDBHelper.CHECKLISTITEM_LNG_COLUMN);
			String name = c.getString(ElectionsDBHelper.CHECKLISTITEM_NAME_COLUMN);
			long timestamp = c.getLong(ElectionsDBHelper.CHECKLISTITEM_TIMESTAMP_COLUMN);
			String value = c.getString(ElectionsDBHelper.CHECKLISTITEM_VALUE_COLUMN);
			long pollingPlace = c.getLong(ElectionsDBHelper.CHECKLISTITEM_POLLINGPLACE_COLUMN);
			long serverId = c.getLong(ElectionsDBHelper.CHECKLISTITEM_SERVER_ID_COLUMN);
			if (serverId == -1) {
				serverId = cloud.postNewMessage(name, value, lat, lng, timestamp, rowId, pollingPlace);
			} else {
				serverId = cloud.editMessage(serverId, name, value, lat, lng, timestamp, rowId, pollingPlace);
			}
			if (serverId != -1) {
				db.updateCheckListItemServerId(rowId, serverId);
			}
			c.moveToNext();
		}
	}

	public interface IViolationSyncCallCallback {
		public void onViolationSyncStart();

		public void onViolationSyncFinish();

		public void onViolationSyncProgressUpdate(int progress);

		public void onViolationSyncError(String msg);
	}
}
