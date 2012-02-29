package org.dvaletin.apps.nabludatel.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import org.dvaletin.apps.nabludatel.R;

/**
 * @author Alexey Efimov
 */
public class SyncNotification extends Notification {

	private final Context context;
	private final int id;

	private final NotificationManager manager;
	private final String title;

	public SyncNotification(int id, Context context, String title) {
		super();
		this.context = context;

		this.id = id;
		this.title = title;

		manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Intent intent = new Intent(context, context.getClass());
		contentIntent = PendingIntent.getActivity(context, 0, intent, 0);
	}

	public void progress(int current, int count) {
		icon = android.R.drawable.stat_sys_upload;
		tickerText = title + (current < count ? " (" + current + " из " + count + ")" : "");
		sendNotification();
	}

	public void finished() {
		icon = android.R.drawable.stat_sys_upload_done;
		tickerText = title;
		sendNotification();
	}

	public void failed() {
		icon = android.R.drawable.stat_sys_warning;
		tickerText = title;
		sendNotification();
	}

	private void sendNotification() {
		when = System.currentTimeMillis();
		setLatestEventInfo(context,
				context.getString(R.string.app_name),
				title, contentIntent);
		manager.notify(id, this);
	}

	public void remove() {
		manager.cancel(id);
	}
}
