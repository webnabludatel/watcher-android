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

public class CheckListItemSQLHelper{
    
    private static final String TAG = "CheckListItemSQLHelper";

    private static final String DATABASE_NAME = "dbhelperDb.db";
    private static final int DATABASE_VERSION = 1;


    // Variable to hold the database instance
    protected SQLiteDatabase mDb;
    // Context of the application using the database.
    private final Context mContext;
    // Database open/upgrade helper
    private MyDbHelper mDbHelper;
    
    public CheckListItemSQLHelper(Context context) {
        mContext = context;
        mDbHelper = new MyDbHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public CheckListItemSQLHelper open() throws SQLException { 
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
                                                     
    public void close() {
        mDb.close();
    }

	// -------------- CHECKLISTITEM DEFINITIONS ------------

	public static final String CHECKLISTITEM_TABLE = "CheckListItem";
	public static final String CHECKLISTITEM_LAT_KEY = "lat";
	protected static final int CHECKLISTITEM_LAT_COLUMN = 1;
	public static final String CHECKLISTITEM_LNG_KEY = "lng";
	protected static final int CHECKLISTITEM_LNG_COLUMN = 2;
	public static final String CHECKLISTITEM_NAME_KEY = "name";
	protected static final int CHECKLISTITEM_NAME_COLUMN = 3;
	public static final String CHECKLISTITEM_TIMESTAMP_KEY = "timestamp";
	protected static final int CHECKLISTITEM_TIMESTAMP_COLUMN = 4;
	public static final String CHECKLISTITEM_VALUE_KEY = "value";
	protected static final int CHECKLISTITEM_VALUE_COLUMN = 5;
	public static final String CHECKLISTITEM_POLLINGPLACE_KEY = "pollingplace";
	protected static final int CHECKLISTITEM_POLLINGPLACE_COLUMN = 6;
	public static final String CHECKLISTITEM_ROW_ID = "_id";



	// -------- TABLES CREATION ----------

	// CheckListItem CREATION 
	private static final String DATABASE_CHECKLISTITEM_CREATE = "create table " + CHECKLISTITEM_TABLE + " (" + 
				 CHECKLISTITEM_ROW_ID + " integer primary key autoincrement" + ", " + 
				 CHECKLISTITEM_LAT_KEY + " float  " + ", " + 
				 CHECKLISTITEM_LNG_KEY + " float  " + ", " + 
				 CHECKLISTITEM_NAME_KEY + " text  " + ", " + 
				 CHECKLISTITEM_TIMESTAMP_KEY + " integer  " + ", " + 
				 CHECKLISTITEM_VALUE_KEY + " text  " + ", " + 
				 CHECKLISTITEM_POLLINGPLACE_KEY + " integer  " + ");";




	// -------------- CHECKLISTITEM HELPERS ------------------
	public long addCheckListItem(Float lat, Float lng, String name, Integer timestamp, String value, Integer pollingplace)
	{
		ContentValues contentValues = new ContentValues();
		contentValues.put(CHECKLISTITEM_LAT_KEY, lat);
		contentValues.put(CHECKLISTITEM_LNG_KEY, lng);
		contentValues.put(CHECKLISTITEM_NAME_KEY, name);
		contentValues.put(CHECKLISTITEM_TIMESTAMP_KEY, timestamp);
		contentValues.put(CHECKLISTITEM_VALUE_KEY, value);
		contentValues.put(CHECKLISTITEM_POLLINGPLACE_KEY, pollingplace);
		return mDb.insert(CHECKLISTITEM_TABLE, null, contentValues);
	
	}

	public long updateCheckListItem(long rowIndex, Float lat, Float lng, String name, Integer timestamp, String value, Integer pollingplace)
	{
		String where = CHECKLISTITEM_ROW_ID + " = " + rowIndex;
		ContentValues contentValues = new ContentValues();
		contentValues.put(CHECKLISTITEM_LAT_KEY, lat);
		contentValues.put(CHECKLISTITEM_LNG_KEY, lng);
		contentValues.put(CHECKLISTITEM_NAME_KEY, name);
		contentValues.put(CHECKLISTITEM_TIMESTAMP_KEY, timestamp);
		contentValues.put(CHECKLISTITEM_VALUE_KEY, value);
		contentValues.put(CHECKLISTITEM_POLLINGPLACE_KEY, pollingplace);
		return mDb.update(CHECKLISTITEM_TABLE, contentValues, where, null);
	
	}

	public boolean removeCheckListItem(Long rowIndex)
	{
		return mDb.delete(CHECKLISTITEM_TABLE, CHECKLISTITEM_ROW_ID + " = " + rowIndex, null) > 0;
	}

	public boolean removeAllCheckListItem()
	{
		return mDb.delete(CHECKLISTITEM_TABLE, null, null) > 0;
	}

	public Cursor getAllCheckListItem()
	{
		return mDb.query(CHECKLISTITEM_TABLE, new String[] {
					CHECKLISTITEM_ROW_ID,
					CHECKLISTITEM_LAT_KEY,
					CHECKLISTITEM_LNG_KEY,
					CHECKLISTITEM_NAME_KEY,
					CHECKLISTITEM_TIMESTAMP_KEY,
					CHECKLISTITEM_VALUE_KEY,
					CHECKLISTITEM_POLLINGPLACE_KEY}, null, null, null, null, null);
	}

	public Cursor getCheckListItem(long rowIndex)
	{
		Cursor res = mDb.query(CHECKLISTITEM_TABLE, new String[] {
					CHECKLISTITEM_ROW_ID,
					CHECKLISTITEM_LAT_KEY,
					CHECKLISTITEM_LNG_KEY,
					CHECKLISTITEM_NAME_KEY,
					CHECKLISTITEM_TIMESTAMP_KEY,
					CHECKLISTITEM_VALUE_KEY,
					CHECKLISTITEM_POLLINGPLACE_KEY}, CHECKLISTITEM_ROW_ID + " = " + rowIndex, null, null, null, null);
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
            db.execSQL(DATABASE_CHECKLISTITEM_CREATE);
			
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
            db.execSQL("DROP TABLE IF EXISTS " + CHECKLISTITEM_TABLE + ";");
			
            // Create a new one.
            onCreate(db);
        }
    }
     
    /** Dummy object to allow class to compile */
}