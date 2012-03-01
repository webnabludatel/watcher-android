package org.dvaletin.apps.nabludatel.utils;

import android.database.Cursor;
import android.util.Log;
import org.dvaletin.apps.nabludatel.MainWindow;
import org.dvaletin.apps.nabludatel.server.NabludatelCloud;
import org.dvaletin.apps.nabludatel.server.NabludatelCloudRequestTimeTooSkewedException;

import java.io.File;
import java.io.IOException;

public class MediaSyncTask extends SyncTask {
	private static final String T = MediaSyncTask.class.getSimpleName();

	private final MainWindow mainWindow;

	public MediaSyncTask(MainWindow mainWindow, NabludatelCloud cloud, SyncNotification notification) {
		super(mainWindow, cloud, notification);
		this.mainWindow = mainWindow;
	}

	protected void performSync(ElectionsDBHelper db) throws IOException {
		Cursor c = db.getAllMediaItemsNotSynchronizedWithServer();
		try {
			if (c.getCount() > 0) {
				c.moveToFirst();
				for (int i = 0; i < c.getCount(); i++) {
					notification.progress(i + 1, c.getCount());
					long mediaRowId = c.getLong(0);
					String filePath = c.getString(ElectionsDBHelper.MEDIAITEM_FILEPATH_COLUMN);
					String mediaType = c.getString(ElectionsDBHelper.MEDIAITEM_MEDIATYPE_COLUMN);
					long mediaTimeStamp = c.getLong(ElectionsDBHelper.MEDIAITEM_TIMESTAMP_COLUMN);
					long mediaChecklistId = c.getLong(ElectionsDBHelper.MEDIAITEM_CHECKLISTITEM_COLUMN);
					long mediaItemServerId = c.getLong(ElectionsDBHelper.MEDIAITEM_SERVER_ID_COLUMN);

					long serverMessageId = db.getCheckListItemServerId(mediaChecklistId);
					String violationName = db.getCheckListItemName(mediaChecklistId);
					if (serverMessageId != -1L) {
						File toSend = new File(filePath);
						if (toSend.exists()) {
							try {
								mediaItemServerId = cloud.uploadMediaToMessage(serverMessageId, mediaTimeStamp, violationName, toSend, mediaType, mediaRowId, mediaChecklistId);
							} catch (NabludatelCloudRequestTimeTooSkewedException e) {
								mainWindow.showErrorMessage("На вашем телефоне неверно установлено время. " +
										"Зайдите в настройки и включите автоматическую синхронизацию часов, даже если часовой " +
										"пояс настроен неверно.");
							}
							Log.d(T, "Item sent:" + toSend.getCanonicalPath());
							db.updateMediaItemServerId(mediaRowId, mediaItemServerId);
						} else {
							Log.d(T, "File " + toSend.getCanonicalPath() + " does not exists, deleting record MediaItem:" + mediaRowId);
							db.removeMediaItem(mediaRowId);
							if (mediaItemServerId != -1) {
								cloud.setMediaDeletedForMessage(serverMessageId, mediaItemServerId, System.currentTimeMillis(), mediaRowId);
							}
						}
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
