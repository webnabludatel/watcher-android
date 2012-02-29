package org.dvaletin.apps.nabludatel.utils;

import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import org.dvaletin.apps.nabludatel.server.NabludatelCloud;

import java.util.concurrent.locks.ReentrantLock;

public class ViolationSyncTask extends AsyncTask<ElectionsDBHelper, String, String> {
	private static final String T = ViolationSyncTask.class.getSimpleName();

	private static final ReentrantLock lock = new ReentrantLock();

	private final NabludatelCloud cloud;
	private final IViolationSyncCallCallback callback;

	public ViolationSyncTask(NabludatelCloud cloud, IViolationSyncCallCallback callback) {
		this.cloud = cloud;
		this.callback = callback;
	}

	@Override
	protected String doInBackground(ElectionsDBHelper... dbs) {
		if (lock.tryLock()) {
			try {
				if (dbs == null || dbs.length == 0) {
					Log.w(T, "No db available");
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
			} finally {
				lock.unlock();
			}
		} else {
			Log.d(T, "Task already performed in other thread. Ignore");
		}
		return null;
	}

	private void performSync(ElectionsDBHelper db) {
		Cursor c = db.getAllCheckListItemsNotSynchronizedWithServer();
		try {
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
		} finally {
			c.close();
		}
	}

	public interface IViolationSyncCallCallback {
		public void onViolationSyncStart();

		public void onViolationSyncFinish();

		public void onViolationSyncProgressUpdate(int progress);

		public void onViolationSyncError(String msg);
	}
}
