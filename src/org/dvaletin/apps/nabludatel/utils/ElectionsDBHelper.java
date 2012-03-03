package org.dvaletin.apps.nabludatel.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ElectionsDBHelper {

	private static final String TAG = "Elections.DB";
	private static final String DATABASE_NAME = "Elections.DB";
	private static final int DATABASE_VERSION = 9;
	private final MyDbHelper mDbHelper;

	public ElectionsDBHelper(Context context) {
		mDbHelper = new MyDbHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void close() {
		try {
			while (db().isDbLockedByOtherThreads()) {
				Thread.yield();
			}
			mDbHelper.close();
		} catch (Exception e) {
			Log.e(TAG, "Can't close database", e);
		}
	}

	private SQLiteDatabase db() {
		return mDbHelper.getWritableDatabase();
	}

	public static final String CHECKLISTITEM_TABLE = "CheckListItem";
	public static final String CHECKLISTITEM_LAT_KEY = "lat";
	public static final int CHECKLISTITEM_LAT_COLUMN = 1;
	public static final String CHECKLISTITEM_LNG_KEY = "lng";
	public static final int CHECKLISTITEM_LNG_COLUMN = 2;
	public static final String CHECKLISTITEM_NAME_KEY = "name";
	public static final int CHECKLISTITEM_NAME_COLUMN = 3;
	public static final String CHECKLISTITEM_TIMESTAMP_KEY = "timestamp";
	public static final int CHECKLISTITEM_TIMESTAMP_COLUMN = 4;
	public static final String CHECKLISTITEM_VALUE_KEY = "value";
	public static final int CHECKLISTITEM_VALUE_COLUMN = 5;
	public static final String CHECKLISTITEM_POLLINGPLACE_KEY = "pollingplace";
	public static final int CHECKLISTITEM_POLLINGPLACE_COLUMN = 6;
	public static final String CHECKLISTITEM_VIOLATION_KEY = "violation";
	public static final int CHECKLISTITEM_VIOLATION_COLUMN = 7;
	public static final String CHECKLISTITEM_SCREEN_ID_KEY = "screen_id";
	public static final int CHECKLISTITEM_SCREEN_ID_COLUMN = 8;
	public static final String CHECKLISTITEM_SERVER_STATUS_KEY = "serverstatus";
	public static final int CHECKLISTITEM_SERVER_STATUS_COLUMN = 9;
	public static final String CHECKLISTITEM_SERVER_ID_KEY = "server_id";
	public static final int CHECKLISTITEM_SERVER_ID_COLUMN = 10;
	public static final String CHECKLISTITEM_ROW_ID = "_id";
	private static final String DATABASE_CHECKLISTITEM_CREATE = "create table "
			+ CHECKLISTITEM_TABLE + " (" 
			+ CHECKLISTITEM_ROW_ID
			+ " integer primary key autoincrement" + ", "
			+ CHECKLISTITEM_LAT_KEY + " float  " + ", " 
			+ CHECKLISTITEM_LNG_KEY	+ " float  " + ", " 
			+ CHECKLISTITEM_NAME_KEY + " text  " + ", "
			+ CHECKLISTITEM_TIMESTAMP_KEY + " integer  " + ", "
			+ CHECKLISTITEM_VALUE_KEY + " text  " + ", "
			+ CHECKLISTITEM_POLLINGPLACE_KEY + " integer  " + ", "
			+ CHECKLISTITEM_VIOLATION_KEY + " text " + ", "
			+ CHECKLISTITEM_SCREEN_ID_KEY + " integer " + ", "
			+ CHECKLISTITEM_SERVER_STATUS_KEY + " integer " + ", "
			+ CHECKLISTITEM_SERVER_ID_KEY + " integer "
			+ ");";
	public static final String POLLINGPLACE_TABLE = "PollingPlace";
	public static final String POLLINGPLACE_LAT_KEY = "lat";
	public static final int POLLINGPLACE_LAT_COLUMN = 1;
	public static final String POLLINGPLACE_LNG_KEY = "lng";
	public static final int POLLINGPLACE_LNG_COLUMN = 2;
	public static final String POLLINGPLACE_NAME_KEY = "name";
	public static final int POLLINGPLACE_NAME_COLUMN = 3;
	public static final String POLLINGPLACE_TIMESTAMP_KEY = "timestamp";
	public static final int POLLINGPLACE_TIMESTAMP_COLUMN = 4;
	public static final String POLLINGPLACE_CHAIRMAN_KEY = "chairman";
	public static final int POLLINGPLACE_CHAIRMAN_COLUMN = 5;
	public static final String POLLINGPLACE_SECRETARY_KEY = "secretary";
	public static final int POLLINGPLACE_SECRETARY_COLUMN = 6;
	public static final String POLLINGPLACE_REGION_ID_KEY = "region_id";
	public static final int POLLINGPLACE_REGION_ID_COLUMN = 7;
	public static final String POLLINGPLACE_TYPE_KEY = "type";
	public static final int POLLINGPLACE_TYPE_COLUMN = 8;
	public static final String POLLINGPLACE_SERVER_ID_KEY = "server_id";
	public static final int POLLINGPLACE_SERVER_ID_COLUMN = 9;
	public static final String POLLINGPLACE_ROW_ID = "_id";
	protected static final String DATABASE_POLLINGPLACE_CREATE = "create table "
			+ POLLINGPLACE_TABLE
			+ " ("
			+ POLLINGPLACE_ROW_ID
			+ " integer primary key autoincrement"
			+ ", "
			+ POLLINGPLACE_LAT_KEY	+ " float  "	+ ", "
			+ POLLINGPLACE_LNG_KEY	+ " float  "	+ ", "
			+ POLLINGPLACE_NAME_KEY	+ " text  "		+ ", "
			+ POLLINGPLACE_TIMESTAMP_KEY+ " long  "	+ ", "
			+ POLLINGPLACE_CHAIRMAN_KEY + " text  "	+ ", "
			+ POLLINGPLACE_SECRETARY_KEY+ " text  "	+ ", "
			+ POLLINGPLACE_REGION_ID_KEY + " integer  "	+ ", "
			+ POLLINGPLACE_TYPE_KEY + " text  " + ", "
			+ POLLINGPLACE_SERVER_ID_KEY + " integer  "	+ ");";
	
	
	public static final String MEDIAITEM_TABLE = "MediaItem";
	public static final String MEDIAITEM_FILEPATH_KEY = "filepath";
	public static final int MEDIAITEM_FILEPATH_COLUMN = 1;
	public static final String MEDIAITEM_MEDIATYPE_KEY = "mediatype";
	public static final int MEDIAITEM_MEDIATYPE_COLUMN = 2;
	public static final String MEDIAITEM_SERVERURL_KEY = "serverurl";
	public static final int MEDIAITEM_SERVERURL_COLUMN = 3;
	public static final String MEDIAITEM_TIMESTAMP_KEY = "timestamp";
	public static final int MEDIAITEM_TIMESTAMP_COLUMN = 4;
	public static final String MEDIAITEM_CHECKLISTITEM_KEY = "checklistitem";
	public static final int MEDIAITEM_CHECKLISTITEM_COLUMN = 5;
	public static final String MEDIAITEM_POLLINGPLACE_KEY = "pollingplace";
	public static final int MEDIAITEM_POLLINGPLACE_COLUMN = 6;
	public static final String MEDIAITEM_SERVER_STATUS_KEY = "serverstatus";
	public static final int MEDIAITEM_SERVER_STATUS_COLUMN = 7;
	public static final String MEDIAITEM_SERVER_ID_KEY = "server_id";
	public static final int MEDIAITEM_SERVER_ID_COLUMN = 7;
	public static final String MEDIAITEM_ROW_ID = "_id";
	protected static final String DATABASE_MEDIAITEM_CREATE = "create table "
			+ MEDIAITEM_TABLE + " (" + MEDIAITEM_ROW_ID
			+ " integer primary key autoincrement" + ", "
			+ MEDIAITEM_FILEPATH_KEY + " text  " + ", "
			+ MEDIAITEM_MEDIATYPE_KEY + " text  " + ", "
			+ MEDIAITEM_SERVERURL_KEY + " text  " + ", "
			+ MEDIAITEM_TIMESTAMP_KEY + " integer  " + ", "
			+ MEDIAITEM_CHECKLISTITEM_KEY + " integer  " + ", "
			+ MEDIAITEM_POLLINGPLACE_KEY + " integer  " + ", "
			+ MEDIAITEM_SERVER_STATUS_KEY + " integer " + ", "
			+ MEDIAITEM_SERVER_ID_KEY + " integer "
			+ ");";

	public long addCheckListItem(double lat, double lng, String name, long l,
			String value, long mCurrentElectionsDistrictId, String violation, int screen_id) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(CHECKLISTITEM_LAT_KEY, lat);
		contentValues.put(CHECKLISTITEM_LNG_KEY, lng);
		contentValues.put(CHECKLISTITEM_NAME_KEY, name);
		contentValues.put(CHECKLISTITEM_TIMESTAMP_KEY, l);
		contentValues.put(CHECKLISTITEM_VALUE_KEY, value);
		contentValues.put(CHECKLISTITEM_POLLINGPLACE_KEY, mCurrentElectionsDistrictId);
		contentValues.put(CHECKLISTITEM_VIOLATION_KEY, violation);
		contentValues.put(CHECKLISTITEM_SCREEN_ID_KEY, screen_id );
		contentValues.put(CHECKLISTITEM_SERVER_ID_KEY, -1L);
		contentValues.put(CHECKLISTITEM_SERVER_STATUS_KEY, -1L);
		return db().insert(CHECKLISTITEM_TABLE, null, contentValues);

	}

	public long addCheckListItem(CheckListItem value, int screen_id) {
		return addCheckListItem(
				value.getLat(), 
				value.getLng(), 
				value.getKey(), 
				value.getTimestamp(),
				value.getValue(), 
				value.getPollingPlaceId(),
				value.getViolation(),
				screen_id
		);
		
	}
	
	public long updateCheckListItem(CheckListItem value) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(CHECKLISTITEM_LAT_KEY, value.getLat());
		contentValues.put(CHECKLISTITEM_LNG_KEY, value.getLng());
		contentValues.put(CHECKLISTITEM_NAME_KEY, value.getKey());
		contentValues.put(CHECKLISTITEM_TIMESTAMP_KEY, value.getTimestamp());
		contentValues.put(CHECKLISTITEM_VALUE_KEY, value.getValue());
		contentValues.put(CHECKLISTITEM_POLLINGPLACE_KEY, value.getPollingPlaceId());
		contentValues.put(CHECKLISTITEM_VIOLATION_KEY, value.getViolation());
		contentValues.put(CHECKLISTITEM_SERVER_STATUS_KEY, 0L);
		return db().update(CHECKLISTITEM_TABLE, contentValues, CHECKLISTITEM_ROW_ID + " = " + value.getId(), null);

	}

	public Cursor getAllCheckListItemsNotSynchronizedWithServer() {
		return db().query(CHECKLISTITEM_TABLE, new String[]{
				CHECKLISTITEM_ROW_ID, CHECKLISTITEM_LAT_KEY,
				CHECKLISTITEM_LNG_KEY, CHECKLISTITEM_NAME_KEY,
				CHECKLISTITEM_TIMESTAMP_KEY, CHECKLISTITEM_VALUE_KEY,
				CHECKLISTITEM_POLLINGPLACE_KEY, CHECKLISTITEM_VIOLATION_KEY,
				CHECKLISTITEM_SCREEN_ID_KEY,
				CHECKLISTITEM_SERVER_STATUS_KEY, CHECKLISTITEM_SERVER_ID_KEY},
				CHECKLISTITEM_SERVER_STATUS_KEY + " <> 1 OR "
						+ CHECKLISTITEM_SERVER_ID_KEY + " = -1",
				null, null, null, null);
	}

	public Cursor getViolationsByPollingPlaceId(long pollingPlaceId) {
		return db().query(CHECKLISTITEM_TABLE, new String[]{
				CHECKLISTITEM_ROW_ID, CHECKLISTITEM_VIOLATION_KEY},
				CHECKLISTITEM_POLLINGPLACE_KEY + " = " + pollingPlaceId + " AND "
						+ CHECKLISTITEM_VALUE_KEY + " = 'false'",
				null, null, null, null);
	}
	
	public int getCheckListItemsCountByScreenId(int screenId) {
		Cursor c = db().query(CHECKLISTITEM_TABLE, new String[]{
				CHECKLISTITEM_ROW_ID}, 
				CHECKLISTITEM_VALUE_KEY + " <> " + "'undef' AND " 
//				+ CHECKLISTITEM_VIOLATION_KEY + " <> '' AND "
				+CHECKLISTITEM_SCREEN_ID_KEY + " = " + screenId,
				null, null, null, null 
				);
		if(c == null)
			return 0;
		return c.getCount();
	}

	public Cursor getNoneViolationsByPollingPlaceId(long pollingPlaceId) {
		return db().query(CHECKLISTITEM_TABLE, new String[]{
				CHECKLISTITEM_ROW_ID, CHECKLISTITEM_VIOLATION_KEY},
				CHECKLISTITEM_POLLINGPLACE_KEY + " = " + pollingPlaceId + " AND "
						+ CHECKLISTITEM_VALUE_KEY + " = 'true'",
				null, null, null, null);
	}
	
	public Cursor getCheckListItemsByPollingPlaceIdAndScreenId(
			long pollingPlaceId, int screen_id) {
		return db().query(CHECKLISTITEM_TABLE, new String[]{
				CHECKLISTITEM_ROW_ID, CHECKLISTITEM_LAT_KEY,
				CHECKLISTITEM_LNG_KEY, CHECKLISTITEM_NAME_KEY,
				CHECKLISTITEM_TIMESTAMP_KEY, CHECKLISTITEM_VALUE_KEY,
				CHECKLISTITEM_POLLINGPLACE_KEY, CHECKLISTITEM_VIOLATION_KEY,
				CHECKLISTITEM_SERVER_ID_KEY, CHECKLISTITEM_SCREEN_ID_KEY},
				CHECKLISTITEM_POLLINGPLACE_KEY + " = " + pollingPlaceId + " AND "
						+ CHECKLISTITEM_SCREEN_ID_KEY + " = " + screen_id, // where
				null, null, null, null);
	}

	public Cursor getCheckListItem(long rowIndex) {
		Cursor res = db().query(CHECKLISTITEM_TABLE, new String[]{
				CHECKLISTITEM_ROW_ID, CHECKLISTITEM_LAT_KEY,
				CHECKLISTITEM_LNG_KEY, CHECKLISTITEM_NAME_KEY,
				CHECKLISTITEM_TIMESTAMP_KEY, CHECKLISTITEM_VALUE_KEY,
				CHECKLISTITEM_POLLINGPLACE_KEY, CHECKLISTITEM_VIOLATION_KEY,
				CHECKLISTITEM_SCREEN_ID_KEY,
				CHECKLISTITEM_SERVER_STATUS_KEY, CHECKLISTITEM_SERVER_ID_KEY},
				CHECKLISTITEM_ROW_ID + " = "
						+ rowIndex, null, null, null, null);
		if (res != null) {
			res.moveToFirst();
		}
		return res;
	}

	public String getCheckListItemName(long rowIndex) {
		Cursor res = db().query(CHECKLISTITEM_TABLE, new String[]{
				CHECKLISTITEM_NAME_KEY
		}, CHECKLISTITEM_ROW_ID + " = " + rowIndex, null, null, null, null);
		if (res != null && res.getCount() > 0){
			res.moveToFirst();
			return res.getString(res.getColumnIndex(CHECKLISTITEM_NAME_KEY));
		}
		return null;
	}

	public long updateCheckListItemServerId(long rowIndex, long serverId){
		String where = CHECKLISTITEM_ROW_ID + " = " + rowIndex;
		ContentValues contentValues = new ContentValues();
		contentValues.put(CHECKLISTITEM_SERVER_ID_KEY, serverId);
		contentValues.put(CHECKLISTITEM_SERVER_STATUS_KEY, 1);
		return db().update(CHECKLISTITEM_TABLE, contentValues, where, null);
	}

	public long addPollingPlace(double lat, double lng, String name, long time, String chairman,
								String secretary, int regionId, String type, long serverId) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(POLLINGPLACE_LAT_KEY, lat);
		contentValues.put(POLLINGPLACE_LNG_KEY, lng);
		contentValues.put(POLLINGPLACE_NAME_KEY, name);
		contentValues.put(POLLINGPLACE_TIMESTAMP_KEY, time);
		contentValues.put(POLLINGPLACE_CHAIRMAN_KEY, chairman);
		contentValues.put(POLLINGPLACE_SECRETARY_KEY, secretary);
		contentValues.put(POLLINGPLACE_REGION_ID_KEY, regionId);
		contentValues.put(POLLINGPLACE_TYPE_KEY, type);
		contentValues.put(POLLINGPLACE_SERVER_ID_KEY, serverId);
		return db().insert(POLLINGPLACE_TABLE, null, contentValues);

	}

	public long updatePollingPlace(long rowIndex, double lat, double lng, String name, long timestamp, String chairman,
								   String secretary, int regionId, String type, int serverId) {
		String where = POLLINGPLACE_ROW_ID + " = " + rowIndex;
		ContentValues contentValues = new ContentValues();
		contentValues.put(POLLINGPLACE_LAT_KEY, lat);
		contentValues.put(POLLINGPLACE_LNG_KEY, lng);
		contentValues.put(POLLINGPLACE_NAME_KEY, name);
		contentValues.put(POLLINGPLACE_TIMESTAMP_KEY, timestamp);
		contentValues.put(POLLINGPLACE_CHAIRMAN_KEY, chairman);
		contentValues.put(POLLINGPLACE_SECRETARY_KEY, secretary);
		contentValues.put(POLLINGPLACE_REGION_ID_KEY, regionId);
		contentValues.put(POLLINGPLACE_TYPE_KEY, type);
		contentValues.put(POLLINGPLACE_SERVER_ID_KEY, serverId);
		return db().update(POLLINGPLACE_TABLE, contentValues, where, null);

	}

	public Cursor getPollingPlace(long rowIndex) {
		Cursor res = db().query(POLLINGPLACE_TABLE, new String[]{
				POLLINGPLACE_ROW_ID,
				POLLINGPLACE_LAT_KEY,
				POLLINGPLACE_LNG_KEY,
				POLLINGPLACE_NAME_KEY,
				POLLINGPLACE_TIMESTAMP_KEY,
				POLLINGPLACE_CHAIRMAN_KEY,
				POLLINGPLACE_SECRETARY_KEY,
				POLLINGPLACE_REGION_ID_KEY,
				POLLINGPLACE_TYPE_KEY,
				POLLINGPLACE_SERVER_ID_KEY},
				POLLINGPLACE_ROW_ID + " = " + rowIndex, null, null, null, null);
		if (res != null) {
			res.moveToFirst();
		}
		return res;
	}
	
	public String getPollingPlaceType(long rowIndex){
		Cursor c = getPollingPlace(rowIndex);
		if (c == null || c.getCount() < 1) {
			return null;
		}
		return c.getString(c.getColumnIndex(POLLINGPLACE_TYPE_KEY));
	}

	public Cursor getPollingPlaceByNumber(long district_id) {
		Cursor res = db().query(POLLINGPLACE_TABLE, new String[] {
				POLLINGPLACE_ROW_ID,
				POLLINGPLACE_LAT_KEY,
				POLLINGPLACE_LNG_KEY,
				POLLINGPLACE_NAME_KEY,
				POLLINGPLACE_TIMESTAMP_KEY,
				POLLINGPLACE_CHAIRMAN_KEY,
				POLLINGPLACE_SECRETARY_KEY,
				POLLINGPLACE_REGION_ID_KEY,
				POLLINGPLACE_TYPE_KEY,
				POLLINGPLACE_SERVER_ID_KEY},
				POLLINGPLACE_ROW_ID + " = " + district_id, null,
				null, null, null);
		if (res != null) {
			res.moveToFirst();
		}
		return res;
	}

	public Cursor getPollingPlaceNames() {
		Cursor res = db().query(POLLINGPLACE_TABLE, new String[] {
				POLLINGPLACE_ROW_ID, POLLINGPLACE_NAME_KEY}, null, null,
				null, null, null);
		if (res != null) {
			res.moveToFirst();
		}
		return res;
	}

	public long addMediaItem(String filepath, String mediatype,
			String serverurl, long timestamp, long checklistitem,
			long pollingplace) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(MEDIAITEM_FILEPATH_KEY, filepath);
		contentValues.put(MEDIAITEM_MEDIATYPE_KEY, mediatype);
		contentValues.put(MEDIAITEM_SERVERURL_KEY, serverurl);
		contentValues.put(MEDIAITEM_TIMESTAMP_KEY, timestamp);
		contentValues.put(MEDIAITEM_CHECKLISTITEM_KEY, checklistitem);
		contentValues.put(MEDIAITEM_POLLINGPLACE_KEY, pollingplace);
		contentValues.put(MEDIAITEM_SERVER_STATUS_KEY, -1L);
		contentValues.put(MEDIAITEM_SERVER_ID_KEY, -1L);
		return db().insert(MEDIAITEM_TABLE, null, contentValues);

	}

	public long updateMediaItem(long rowIndex, String filepath,
			String mediatype, String serverurl, long timestamp,
			long checklistitem, long pollingplace) {
		String where = MEDIAITEM_ROW_ID + " = " + rowIndex;
		ContentValues contentValues = new ContentValues();
		contentValues.put(MEDIAITEM_FILEPATH_KEY, filepath);
		contentValues.put(MEDIAITEM_MEDIATYPE_KEY, mediatype);
		contentValues.put(MEDIAITEM_SERVERURL_KEY, serverurl);
		contentValues.put(MEDIAITEM_TIMESTAMP_KEY, timestamp);
		contentValues.put(MEDIAITEM_CHECKLISTITEM_KEY, checklistitem);
		contentValues.put(MEDIAITEM_POLLINGPLACE_KEY, pollingplace);
		contentValues.put(MEDIAITEM_SERVER_STATUS_KEY, 0L);
		return db().update(MEDIAITEM_TABLE, contentValues, where, null);

	}
	
	public long updateMediaItemServerId(long rowIndex, long mediaServerId){
		String where = MEDIAITEM_ROW_ID + " = " + rowIndex;
		ContentValues contentValues = new ContentValues();
		contentValues.put(MEDIAITEM_SERVER_ID_KEY, mediaServerId);
		contentValues.put(MEDIAITEM_SERVER_STATUS_KEY, 1L);
		return db().update(MEDIAITEM_TABLE, contentValues, where, null);
	}

	public long resetMediaItemServerStatus(long rowIndex) {
		String where = MEDIAITEM_ROW_ID + " = " + rowIndex;
		ContentValues contentValues = new ContentValues();
		contentValues.put(MEDIAITEM_SERVER_STATUS_KEY, 0L);
		return db().update(MEDIAITEM_TABLE, contentValues, where, null);
	}

	public boolean removeMediaItem(long rowIndex) {
		return db().delete(MEDIAITEM_TABLE, MEDIAITEM_ROW_ID + " = " + rowIndex,
				null) > 0;
	}

	public Cursor getAllMediaItemsNotSynchronizedWithServer() {
		return db().query(MEDIAITEM_TABLE, new String[]{MEDIAITEM_ROW_ID,
				MEDIAITEM_FILEPATH_KEY, MEDIAITEM_MEDIATYPE_KEY,
				MEDIAITEM_SERVERURL_KEY, MEDIAITEM_TIMESTAMP_KEY,
				MEDIAITEM_CHECKLISTITEM_KEY, MEDIAITEM_POLLINGPLACE_KEY,
				MEDIAITEM_SERVER_STATUS_KEY, MEDIAITEM_SERVER_ID_KEY},
				MEDIAITEM_SERVER_STATUS_KEY + " <> 1 OR "
						+ MEDIAITEM_SERVER_ID_KEY + " = -1",
				null, null, null, null);
	}

	public Cursor getMediaItemsByCheckListItemIdAndMediaType(long checkListItemId, String mediaType) {
		return db().query(MEDIAITEM_TABLE, new String[]{MEDIAITEM_ROW_ID,
				MEDIAITEM_FILEPATH_KEY, MEDIAITEM_MEDIATYPE_KEY,
				MEDIAITEM_SERVERURL_KEY, MEDIAITEM_TIMESTAMP_KEY,
				MEDIAITEM_CHECKLISTITEM_KEY, MEDIAITEM_POLLINGPLACE_KEY,
				MEDIAITEM_SERVER_STATUS_KEY, MEDIAITEM_SERVER_ID_KEY},
				MEDIAITEM_CHECKLISTITEM_KEY + " = " + checkListItemId + " AND "
						+ MEDIAITEM_MEDIATYPE_KEY + " = '" + mediaType + "'",
				null, null, null, null);
	}

	protected static class MyDbHelper extends SQLiteOpenHelper {

		public MyDbHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}

		// Called when no database exists in disk and the helper class needs
		// to create a new one.
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CHECKLISTITEM_CREATE);
			db.execSQL(DATABASE_MEDIAITEM_CREATE);
			db.execSQL(DATABASE_POLLINGPLACE_CREATE);
		}

		// Called when there is a database version mismatch meaning that the
		// version
		// of the database on disk needs to be upgraded to the current version.
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Log the version upgrade.
			Log.w(TAG, "Upgrading from version " + oldVersion + " to "
					+ newVersion + ", which will destroy all old data");

			// Upgrade the existing database to conform to the new version.
			// Multiple
			// previous versions can be handled by comparing _oldVersion and
			// _newVersion
			// values.

			// The simplest case is to drop the old table and create a new one.
			db.execSQL("DROP TABLE IF EXISTS " + CHECKLISTITEM_TABLE + ";");
			db.execSQL("DROP TABLE IF EXISTS " + MEDIAITEM_TABLE + ";");
			db.execSQL("DROP TABLE IF EXISTS " + POLLINGPLACE_TABLE + ";");
			// Create a new one.
			onCreate(db);
		}
	}

	public String getPollingPlaceNameByNumber(long pollingPlace) {
		Cursor res = this.getPollingPlaceByNumber(pollingPlace);
		if(res != null){
			res.moveToFirst();
			if(res.getCount() > 0){
				return res.getString(POLLINGPLACE_NAME_COLUMN);
			}
		}
		return null;
	}

	public long getCheckListItemServerId(long mediaChecklistId) {
		Cursor res = this.getCheckListItem(mediaChecklistId);
		if (res != null) {
			res.moveToFirst();
			return res.getLong(CHECKLISTITEM_SERVER_ID_COLUMN);
		}
		return -1L;
	}

	public long removePollingPlace(long rowIndex) {
		String where = POLLINGPLACE_ROW_ID + " = " + rowIndex;
		ContentValues contentValues = new ContentValues();
		contentValues.put(POLLINGPLACE_TYPE_KEY, "deleted");
		return db().update(POLLINGPLACE_TABLE, contentValues, where, null);
	}

	public Cursor getActivePollingPlaceNames() {
		Cursor res = db().query(POLLINGPLACE_TABLE, new String[] {
				POLLINGPLACE_ROW_ID, POLLINGPLACE_NAME_KEY}, 
				POLLINGPLACE_TYPE_KEY + " <> " + "'deleted'", null,	null, null, null);
		if (res != null) {
			res.moveToFirst();
		}
		return res;
	}
}
