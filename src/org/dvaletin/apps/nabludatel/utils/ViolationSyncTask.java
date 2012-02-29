package org.dvaletin.apps.nabludatel.utils;

import android.database.Cursor;
import org.dvaletin.apps.nabludatel.MainWindow;
import org.dvaletin.apps.nabludatel.server.NabludatelCloud;

public class ViolationSyncTask extends SyncTask {
	public ViolationSyncTask(NabludatelCloud cloud, MainWindow context, SyncNotification notification) {
		super(context, cloud, notification);
	}

	@Override
	protected void performSync(ElectionsDBHelper db) {
		Cursor c = db.getAllCheckListItemsNotSynchronizedWithServer();
		try {
			if (c.getCount() > 0) {
				c.moveToFirst();
				for (int i = 0; i < c.getCount(); i++) {
					notification.progress(i + 1, c.getCount());
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
				notification.finished();
			}
		} finally {
			c.close();
		}
	}
}
