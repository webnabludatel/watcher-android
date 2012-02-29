package org.dvaletin.apps.nabludatel.utils;

import android.database.Cursor;
import android.util.Log;
import org.dvaletin.apps.nabludatel.server.NabludatelCloud;
import org.dvaletin.apps.nabludatel.server.NabludatelCloudRequestTimeTooSkewedException;

import java.io.File;
import java.io.IOException;

public class MediaSyncTask implements Runnable {
	private static final String T = MediaSyncTask.class.getSimpleName();

	private final NabludatelCloud cloud;
	private final IMediaSyncCallCallback callback;
	private final ElectionsDBHelper db;

	public MediaSyncTask(ElectionsDBHelper db, NabludatelCloud cloud, IMediaSyncCallCallback callback) {
		this.db = db;
		this.cloud = cloud;
		this.callback = callback;
	}

	@Override
	public void run() {
		if (callback != null) callback.onMediaSyncStart();
		try {
			if (cloud.tryAuthenticate()) {
				performSync(db);
			}
		} catch (NabludatelCloudRequestTimeTooSkewedException e) {
			Log.w(T, "Time too skewed on client, this deny S3 uploading");
			if (callback != null) callback.onMediaSyncError("На вашем телефоне неверно установлено время. " +
					"Зайдите в настройки и включите автоматическую синхронизацию часов, даже если часовой " +
					"пояс настроен неверно.");
		} catch (Exception e) {
			Log.w(T, "Media synchronization error", e);
		}
		if (callback != null) callback.onMediaSyncFinish();
	}

	private void performSync(ElectionsDBHelper db) throws NabludatelCloudRequestTimeTooSkewedException, IOException {
		Cursor c = db.getAllMediaItemsNotSynchronizedWithServer();
		try {
			c.moveToFirst();
			for (int i = 0; i < c.getCount(); i++) {
				if (callback != null) callback.onMediaSyncProgresUpdate((i / c.getCount()) * 100);
				long mediaRowId = c.getLong(0);
				String filePath = c.getString(ElectionsDBHelper.MEDIAITEM_FILEPATH_COLUMN);
				String mediaType = c.getString(ElectionsDBHelper.MEDIAITEM_MEDIATYPE_COLUMN);
				long mediaTimeStamp = c.getLong(ElectionsDBHelper.MEDIAITEM_TIMESTAMP_COLUMN);
				long mediaChecklistId = c.getLong(ElectionsDBHelper.MEDIAITEM_CHECKLISTITEM_COLUMN);
				long mediaItemServerId = c.getLong(ElectionsDBHelper.MEDIAITEM_SERVER_ID_COLUMN);

				long serverMessageId = db.getCheckListItemServerId(mediaChecklistId);
				String violationName = db.getCheckListItemViolationName(mediaChecklistId);
				if (serverMessageId != -1L) {
					File toSend = new File(filePath);
					if (toSend.exists()) {
						mediaItemServerId = cloud.uploadMediaToMessage(serverMessageId, mediaTimeStamp, violationName, toSend, mediaType, mediaRowId, mediaChecklistId);
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
		} finally {
			c.close();
		}
	}

	public interface IMediaSyncCallCallback {
		public void onMediaSyncStart();

		public void onMediaSyncFinish();

		public void onMediaSyncProgresUpdate(int progress);

		public void onMediaSyncError(String msg);
	}
}
