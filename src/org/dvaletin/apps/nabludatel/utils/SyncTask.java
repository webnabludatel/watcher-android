package org.dvaletin.apps.nabludatel.utils;

import android.content.Context;
import android.util.Log;
import org.dvaletin.apps.nabludatel.server.NabludatelCloud;

import java.io.IOException;

/**
 * @author Alexey Efimov
 */
public abstract class SyncTask implements Runnable {
	private static final String T = SyncTask.class.getSimpleName();

	protected final ElectionsDBHelper db;
	protected final NabludatelCloud cloud;
	protected final SyncNotification notification;

	public SyncTask(Context context, NabludatelCloud cloud, SyncNotification notification) {
		this.db = new ElectionsDBHelper(context);
		this.cloud = cloud;
		this.notification = notification;
	}

	@Override
	public void run() {
		notification.remove();
		try {
			if (cloud.tryAuthenticate()) {
				performSync(db);
			}
		} catch (Exception e) {
			notification.failed();
			Log.w(T, "Synchronization error", e);
		}
	}

	protected abstract void performSync(ElectionsDBHelper db) throws IOException;

	public void dispose() {
		db.close();
		notification.remove();
	}
}
