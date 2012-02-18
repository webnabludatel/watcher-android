/**********************************************************************************************************************************************************************
****** AUTO GENERATED FILE BY ANDROID SQLITE HELPER SCRIPT BY FEDERICO PAOLINELLI. ANY CHANGE WILL BE WIPED OUT IF THE SCRIPT IS PROCESSED AGAIN. *******
**********************************************************************************************************************************************************************/
package org.dvaletin.apps.nabludatel.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import java.util.Date;

public class MediaItemSQLHelper{
    
    private static final String TAG = "MediaItemSQLHelper";

    private static final String DATABASE_NAME = "dbhelperDb.db";
    private static final int DATABASE_VERSION = 1;


    // Variable to hold the database instance
    protected SQLiteDatabase mDb;
    // Context of the application using the database.
    private final Context mContext;
    // Database open/upgrade helper
    private MyDbHelper mDbHelper;
    
    public MediaItemSQLHelper(Context context) {
        mContext = context;
        mDbHelper = new MyDbHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public MediaItemSQLHelper open() throws SQLException { 
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
                                                     
    public void close() {
        mDb.close();
    }

	// -------------- MEDIAITEM DEFINITIONS ------------

	public static final String MEDIAITEM_TABLE = "MediaItem";
	public static final String MEDIAITEM_FILEPATH_KEY = "filepath";
	protected static final int MEDIAITEM_FILEPATH_COLUMN = 1;
	public static final String MEDIAITEM_MEDIATYPE_KEY = "mediatype";
	protected static final int MEDIAITEM_MEDIATYPE_COLUMN = 2;
	public static final String MEDIAITEM_SERVERURL_KEY = "serverurl";
	protected static final int MEDIAITEM_SERVERURL_COLUMN = 3;
	public static final String MEDIAITEM_TIMESTAMP_KEY = "timestamp";
	protected static final int MEDIAITEM_TIMESTAMP_COLUMN = 4;
	public static final String MEDIAITEM_CHECKLISTITEM_KEY = "checklistitem";
	protected static final int MEDIAITEM_CHECKLISTITEM_COLUMN = 5;
	public static final String MEDIAITEM_POLLINGPLACE_KEY = "pollingplace";
	protected static final int MEDIAITEM_POLLINGPLACE_COLUMN = 6;
	public static final String MEDIAITEM_ROW_ID = "_id";



	// -------- TABLES CREATION ----------

	// MediaItem CREATION 
	private static final String DATABASE_MEDIAITEM_CREATE = "create table " + MEDIAITEM_TABLE + " (" + 
				 MEDIAITEM_ROW_ID + " integer primary key autoincrement" + ", " + 
				 MEDIAITEM_FILEPATH_KEY + " text  " + ", " + 
				 MEDIAITEM_MEDIATYPE_KEY + " text  " + ", " + 
				 MEDIAITEM_SERVERURL_KEY + " text  " + ", " + 
				 MEDIAITEM_TIMESTAMP_KEY + " integer  " + ", " + 
				 MEDIAITEM_CHECKLISTITEM_KEY + " integer  " + ", " + 
				 MEDIAITEM_POLLINGPLACE_KEY + " integer  " + ");";




	// -------------- MEDIAITEM HELPERS ------------------
	public long addMediaItem(String filepath, String mediatype, String serverurl, Integer timestamp, Integer checklistitem, Integer pollingplace)
	{
		ContentValues contentValues = new ContentValues();
		contentValues.put(MEDIAITEM_FILEPATH_KEY, filepath);
		contentValues.put(MEDIAITEM_MEDIATYPE_KEY, mediatype);
		contentValues.put(MEDIAITEM_SERVERURL_KEY, serverurl);
		contentValues.put(MEDIAITEM_TIMESTAMP_KEY, timestamp);
		contentValues.put(MEDIAITEM_CHECKLISTITEM_KEY, checklistitem);
		contentValues.put(MEDIAITEM_POLLINGPLACE_KEY, pollingplace);
		return mDb.insert(MEDIAITEM_TABLE, null, contentValues);
	
	}

	public long updateMediaItem(long rowIndex, String filepath, String mediatype, String serverurl, Integer timestamp, Integer checklistitem, Integer pollingplace)
	{
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

	public boolean removeMediaItem(Long rowIndex)
	{
		return mDb.delete(MEDIAITEM_TABLE, MEDIAITEM_ROW_ID + " = " + rowIndex, null) > 0;
	}

	public boolean removeAllMediaItem()
	{
		return mDb.delete(MEDIAITEM_TABLE, null, null) > 0;
	}

	public Cursor getAllMediaItem()
	{
		return mDb.query(MEDIAITEM_TABLE, new String[] {
					MEDIAITEM_ROW_ID,
					MEDIAITEM_FILEPATH_KEY,
					MEDIAITEM_MEDIATYPE_KEY,
					MEDIAITEM_SERVERURL_KEY,
					MEDIAITEM_TIMESTAMP_KEY,
					MEDIAITEM_CHECKLISTITEM_KEY,
					MEDIAITEM_POLLINGPLACE_KEY}, null, null, null, null, null);
	}

	public Cursor getMediaItem(long rowIndex)
	{
		Cursor res = mDb.query(MEDIAITEM_TABLE, new String[] {
					MEDIAITEM_ROW_ID,
					MEDIAITEM_FILEPATH_KEY,
					MEDIAITEM_MEDIATYPE_KEY,
					MEDIAITEM_SERVERURL_KEY,
					MEDIAITEM_TIMESTAMP_KEY,
					MEDIAITEM_CHECKLISTITEM_KEY,
					MEDIAITEM_POLLINGPLACE_KEY}, MEDIAITEM_ROW_ID + " = " + rowIndex, null, null, null, null);
		if(res != null){
			res.moveToFirst();
		}
		return res;
	}




    private static class MyDbHelper extends SQLiteOpenHelper {
    
        public MyDbHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Called when no database exists in disk and the helper class needs
        // to create a new one. 
        @Override
        public void onCreate(SQLiteDatabase db) {      
            db.execSQL(DATABASE_MEDIAITEM_CREATE);
			
        }

        // Called when there is a database version mismatch meaning that the version
        // of the database on disk needs to be upgraded to the current version.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Log the version upgrade.
            Log.w(TAG, "Upgrading from version " + 
                        oldVersion + " to " +
                        newVersion + ", which will destroy all old data");
            
            // Upgrade the existing database to conform to the new version. Multiple 
            // previous versions can be handled by comparing _oldVersion and _newVersion
            // values.

            // The simplest case is to drop the old table and create a new one.
            db.execSQL("DROP TABLE IF EXISTS " + MEDIAITEM_TABLE + ";");
			
            // Create a new one.
            onCreate(db);
        }
    }
     
    /** Dummy object to allow class to compile */
}