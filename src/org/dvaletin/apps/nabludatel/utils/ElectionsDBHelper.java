package org.dvaletin.apps.nabludatel.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ElectionsDBHelper {

	private static final String TAG = "Elections.DB";
	protected static final String DATABASE_NAME = "Elections.DB";
	protected static final int DATABASE_VERSION = 7;
	protected SQLiteDatabase mDb;
	protected final Context mContext;
	protected MyDbHelper mDbHelper;

	public ElectionsDBHelper(Context context) {
		mContext = context;
		mDbHelper = new MyDbHelper(mContext, DATABASE_NAME, null,
				DATABASE_VERSION);
	}

	public ElectionsDBHelper open() throws SQLException {
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDb.close();
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
	public static final String POLLINGPLACE_CHAIRMAN_KEY = "chairman";
	public static final int POLLINGPLACE_CHAIRMAN_COLUMN = 1;
	public static final String POLLINGPLACE_LAT_KEY = "lat";
	public static final int POLLINGPLACE_LAT_COLUMN = 2;
	public static final String POLLINGPLACE_LNG_KEY = "lng";
	public static final int POLLINGPLACE_LNG_COLUMN = 3;
	public static final String POLLINGPLACE_NAME_KEY = "name";
	public static final int POLLINGPLACE_NAME_COLUMN = 4;
	public static final String POLLINGPLACE_SERVER_NUMBER_KEY = "number";
	public static final int POLLINGPLACE_SERVER_NUMBER_COLUMN = 5;
	public static final String POLLINGPLACE_SECRETARY_KEY = "secretary";
	public static final int POLLINGPLACE_SECRETARY_COLUMN = 6;
	public static final String POLLINGPLACE_TIMESTAMP_KEY = "timestamp";
	public static final int POLLINGPLACE_TIMESTAMP_COLUMN = 7;
	public static final String POLLINGPLACE_TOTALOBSERVERS_KEY = "totalobservers";
	public static final int POLLINGPLACE_TOTALOBSERVERS_COLUMN = 8;
	public static final String POLLINGPLACE_TYPE_KEY = "type";
	public static final int POLLINGPLACE_TYPE_COLUMN = 9;
	public static final String POLLINGPLACE_ROW_ID = "_id";
	protected static final String DATABASE_POLLINGPLACE_CREATE = "create table "
			+ POLLINGPLACE_TABLE
			+ " ("
			+ POLLINGPLACE_ROW_ID
			+ " integer primary key autoincrement"
			+ ", "
			+ POLLINGPLACE_CHAIRMAN_KEY + " text  "	+ ", "
			+ POLLINGPLACE_LAT_KEY	+ " float  "	+ ", "
			+ POLLINGPLACE_LNG_KEY	+ " float  "	+ ", "
			+ POLLINGPLACE_NAME_KEY	+ " text  "		+ ", "
			+ POLLINGPLACE_SERVER_NUMBER_KEY	+ " integer  "	+ ", "
			+ POLLINGPLACE_SECRETARY_KEY+ " text  "	+ ", "
			+ POLLINGPLACE_TIMESTAMP_KEY+ " long  "	+ ", "
			+ POLLINGPLACE_TOTALOBSERVERS_KEY+ " integer  "	+ ", "
			+ POLLINGPLACE_TYPE_KEY + " text  " + ");";
	
	
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
		contentValues.put(CHECKLISTITEM_SERVER_ID_KEY, (long)-1);
		contentValues.put(CHECKLISTITEM_SERVER_STATUS_KEY, (long)-1);
		return mDb.insert(CHECKLISTITEM_TABLE, null, contentValues);

	}

	public long addCheckListItem(Violation value, int screen_id) {
		return addCheckListItem(
				value.getLat(), 
				value.getLng(), 
				value.getKey(), 
				value.getTimestamp(),
				value.getValue(), 
				value.getDistrict(),
				value.getViolation(),
				screen_id
		);
		
	}
	
	public long updateCheckListItem(long rowIndex, Float lat, Float lng,
			String name, Integer timestamp, String value, long pollingplace, String violation) {
		String where = CHECKLISTITEM_ROW_ID + " = " + rowIndex;
		ContentValues contentValues = new ContentValues();
		contentValues.put(CHECKLISTITEM_LAT_KEY, lat);
		contentValues.put(CHECKLISTITEM_LNG_KEY, lng);
		contentValues.put(CHECKLISTITEM_NAME_KEY, name);
		contentValues.put(CHECKLISTITEM_TIMESTAMP_KEY, timestamp);
		contentValues.put(CHECKLISTITEM_VALUE_KEY, value);
		contentValues.put(CHECKLISTITEM_POLLINGPLACE_KEY, pollingplace);
		contentValues.put(CHECKLISTITEM_VIOLATION_KEY, violation);
		return mDb.update(CHECKLISTITEM_TABLE, contentValues, where, null);

	}

	public boolean removeCheckListItem(Long rowIndex) {
		return mDb.delete(CHECKLISTITEM_TABLE, CHECKLISTITEM_ROW_ID + " = "
				+ rowIndex, null) > 0;
	}

	public boolean removeAllCheckListItems() {
		return mDb.delete(CHECKLISTITEM_TABLE, null, null) > 0;
	}

	public Cursor getAllCheckListItemsNotSynchronizedWithServer() {
		return mDb.query(CHECKLISTITEM_TABLE, new String[] {
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
		return mDb.query(CHECKLISTITEM_TABLE, new String[] {
				CHECKLISTITEM_ROW_ID, CHECKLISTITEM_VIOLATION_KEY},
				CHECKLISTITEM_POLLINGPLACE_KEY + " = " + pollingPlaceId + " AND "
						+ CHECKLISTITEM_VALUE_KEY + " = 'false'",
				null, null, null, null);
	}

	public Cursor getAllCheckListItemsByElectionsDistrictIdAndScreenId(
			long pollingPlaceId, int screen_id) {
		return mDb.query(CHECKLISTITEM_TABLE, new String[] {
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
		Cursor res = mDb.query(CHECKLISTITEM_TABLE, new String[] {
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
	
	public Cursor getCheckListItemViolation(long rowIndex) {
		Cursor res = mDb.query(CHECKLISTITEM_TABLE, new String[] {
				CHECKLISTITEM_VIOLATION_KEY	
		}, CHECKLISTITEM_ROW_ID + " = " + rowIndex, null, null, null, null);
		if (res != null){
			res.moveToFirst();
		}
		return res;
	}
	
	public String getCheckListItemViolationName(long rowIndex) {
		Cursor res = mDb.query(CHECKLISTITEM_TABLE, new String[] {
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
		return mDb.update(CHECKLISTITEM_TABLE, contentValues, where, null);
	}

	public long addPollingPlace(String chairman, double lat, double lng,
			String name, long number, String secretary, long time,
			int totalobservers, String type) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(POLLINGPLACE_CHAIRMAN_KEY, chairman);
		contentValues.put(POLLINGPLACE_LAT_KEY, lat);
		contentValues.put(POLLINGPLACE_LNG_KEY, lng);
		contentValues.put(POLLINGPLACE_NAME_KEY, name);
		contentValues.put(POLLINGPLACE_SERVER_NUMBER_KEY, number);
		contentValues.put(POLLINGPLACE_SECRETARY_KEY, secretary);
		contentValues.put(POLLINGPLACE_TIMESTAMP_KEY, time);
		contentValues.put(POLLINGPLACE_TOTALOBSERVERS_KEY, totalobservers);
		contentValues.put(POLLINGPLACE_TYPE_KEY, type);
		return mDb.insert(POLLINGPLACE_TABLE, null, contentValues);

	}

	public long updatePollingPlace(long rowIndex, String chairman, Float lat,
			Float lng, String name, Integer number, String secretary,
			long timestamp, Integer totalobservers, String type) {
		String where = POLLINGPLACE_ROW_ID + " = " + rowIndex;
		ContentValues contentValues = new ContentValues();
		contentValues.put(POLLINGPLACE_CHAIRMAN_KEY, chairman);
		contentValues.put(POLLINGPLACE_LAT_KEY, lat);
		contentValues.put(POLLINGPLACE_LNG_KEY, lng);
		contentValues.put(POLLINGPLACE_NAME_KEY, name);
		contentValues.put(POLLINGPLACE_SERVER_NUMBER_KEY, number);
		contentValues.put(POLLINGPLACE_SECRETARY_KEY, secretary);
		contentValues.put(POLLINGPLACE_TIMESTAMP_KEY, timestamp);
		contentValues.put(POLLINGPLACE_TOTALOBSERVERS_KEY, totalobservers);
		contentValues.put(POLLINGPLACE_TYPE_KEY, type);
		return mDb.update(POLLINGPLACE_TABLE, contentValues, where, null);

	}

	public boolean removePollingPlace(Long rowIndex) {
		return mDb.delete(POLLINGPLACE_TABLE, POLLINGPLACE_ROW_ID + " = "
				+ rowIndex, null) > 0;
	}

	public boolean removeAllPollingPlace() {
		return mDb.delete(POLLINGPLACE_TABLE, null, null) > 0;
	}

	public Cursor getAllPollingPlaces() {
		return mDb.query(POLLINGPLACE_TABLE, new String[] {
				POLLINGPLACE_ROW_ID, POLLINGPLACE_CHAIRMAN_KEY,
				POLLINGPLACE_LAT_KEY, POLLINGPLACE_LNG_KEY,
				POLLINGPLACE_NAME_KEY, POLLINGPLACE_SERVER_NUMBER_KEY,
				POLLINGPLACE_SECRETARY_KEY, POLLINGPLACE_TIMESTAMP_KEY,
				POLLINGPLACE_TOTALOBSERVERS_KEY, POLLINGPLACE_TYPE_KEY }, null,
				null, null, null, null);
	}

	public Cursor getPollingPlace(long rowIndex) {
		Cursor res = mDb.query(POLLINGPLACE_TABLE, new String[] {
				POLLINGPLACE_ROW_ID, POLLINGPLACE_CHAIRMAN_KEY,
				POLLINGPLACE_LAT_KEY, POLLINGPLACE_LNG_KEY,
				POLLINGPLACE_NAME_KEY, POLLINGPLACE_SERVER_NUMBER_KEY,
				POLLINGPLACE_SECRETARY_KEY, POLLINGPLACE_TIMESTAMP_KEY,
				POLLINGPLACE_TOTALOBSERVERS_KEY, POLLINGPLACE_TYPE_KEY },
				POLLINGPLACE_ROW_ID + " = " + rowIndex, null, null, null, null);
		if (res != null) {
			res.moveToFirst();
		}
		return res;
	}

	public Cursor getPollingPlaceByNumber(long district_id) {
		Cursor res = mDb.query(POLLINGPLACE_TABLE, new String[] {
				POLLINGPLACE_ROW_ID, POLLINGPLACE_CHAIRMAN_KEY,
				POLLINGPLACE_LAT_KEY, POLLINGPLACE_LNG_KEY,
				POLLINGPLACE_NAME_KEY, POLLINGPLACE_SERVER_NUMBER_KEY,
				POLLINGPLACE_SECRETARY_KEY, POLLINGPLACE_TIMESTAMP_KEY,
				POLLINGPLACE_TOTALOBSERVERS_KEY, POLLINGPLACE_TYPE_KEY },
				POLLINGPLACE_ROW_ID + " = " + district_id, null,
				null, null, null);
		if (res != null) {
			res.moveToFirst();
		}
		return res;
	}

	public Cursor getPollingPlaceNumbers() {
		Cursor res = mDb.query(POLLINGPLACE_TABLE, new String[] {
				POLLINGPLACE_ROW_ID, POLLINGPLACE_NAME_KEY, }, null, null,
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
		contentValues.put(MEDIAITEM_SERVER_STATUS_KEY, (long) -1);
		contentValues.put(MEDIAITEM_SERVER_ID_KEY, (long) -1);
		return mDb.insert(MEDIAITEM_TABLE, null, contentValues);

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
		return mDb.update(MEDIAITEM_TABLE, contentValues, where, null);

	}
	
	public long updateMediaItemServerId(long rowIndex, long mediaServerId){
		String where = MEDIAITEM_ROW_ID + " = " + rowIndex;
		ContentValues contentValues = new ContentValues();
		contentValues.put(MEDIAITEM_SERVER_ID_KEY, mediaServerId);
		contentValues.put(MEDIAITEM_SERVER_STATUS_KEY, 1);
		return mDb.update(MEDIAITEM_TABLE, contentValues, where, null);
	}
	
	public long updateMediaItemUrl(long rowIndex, String serverurl){
		String where = MEDIAITEM_ROW_ID + " = " + rowIndex;
		ContentValues contentValues = new ContentValues();
		contentValues.put(MEDIAITEM_SERVERURL_KEY, serverurl);
		return mDb.update(MEDIAITEM_TABLE, contentValues, where, null);
	}

	public boolean removeMediaItem(Long rowIndex) {
		return mDb.delete(MEDIAITEM_TABLE, MEDIAITEM_ROW_ID + " = " + rowIndex,
				null) > 0;
	}

	public boolean removeAllMediaItem() {
		return mDb.delete(MEDIAITEM_TABLE, null, null) > 0;
	}

	public Cursor getAllMediaItemsNotSynchronizedWithServer() {
		return mDb.query(MEDIAITEM_TABLE, new String[] { MEDIAITEM_ROW_ID,
				MEDIAITEM_FILEPATH_KEY, MEDIAITEM_MEDIATYPE_KEY,
				MEDIAITEM_SERVERURL_KEY, MEDIAITEM_TIMESTAMP_KEY,
				MEDIAITEM_CHECKLISTITEM_KEY, MEDIAITEM_POLLINGPLACE_KEY,
				MEDIAITEM_SERVER_STATUS_KEY, MEDIAITEM_SERVER_ID_KEY},
				MEDIAITEM_SERVER_STATUS_KEY + "<> 1 OR "
				+ MEDIAITEM_SERVER_ID_KEY + " = -1",
				null, null, null, null);
	}

	public Cursor getMediaItem(long rowIndex) {
		Cursor res = mDb.query(MEDIAITEM_TABLE, new String[] {
				MEDIAITEM_ROW_ID, MEDIAITEM_FILEPATH_KEY,
				MEDIAITEM_MEDIATYPE_KEY, MEDIAITEM_SERVERURL_KEY,
				MEDIAITEM_TIMESTAMP_KEY, MEDIAITEM_CHECKLISTITEM_KEY,
				MEDIAITEM_POLLINGPLACE_KEY,
				MEDIAITEM_SERVER_STATUS_KEY, MEDIAITEM_SERVER_ID_KEY},
				MEDIAITEM_ROW_ID + " = " + rowIndex,
				null, null, null, null);
		if (res != null) {
			res.moveToFirst();
		}
		return res;
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

	public long getPollingPlaceServerIdByNumber(long pollingPlace) {
		Cursor res = this.getPollingPlaceByNumber(pollingPlace);
		if(res != null){
			if(res.getCount() > 0){
				return res.getLong(POLLINGPLACE_SERVER_NUMBER_COLUMN);
			}
		}
		return -1L;
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
}
