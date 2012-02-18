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

public class PollingPlaceSQLHelper{
    
    private static final String TAG = "PollingPlaceSQLHelper";

    private static final String DATABASE_NAME = "dbhelperDb.db";
    private static final int DATABASE_VERSION = 1;


    // Variable to hold the database instance
    protected SQLiteDatabase mDb;
    // Context of the application using the database.
    private final Context mContext;
    // Database open/upgrade helper
    private MyDbHelper mDbHelper;
    
    public PollingPlaceSQLHelper(Context context) {
        mContext = context;
        mDbHelper = new MyDbHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    public PollingPlaceSQLHelper open() throws SQLException { 
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
                                                     
    public void close() {
        mDb.close();
    }

	// -------------- POLLINGPLACE DEFINITIONS ------------

	public static final String POLLINGPLACE_TABLE = "PollingPlace";
	public static final String POLLINGPLACE_CHAIRMAN_KEY = "chairman";
	protected static final int POLLINGPLACE_CHAIRMAN_COLUMN = 1;
	public static final String POLLINGPLACE_LAT_KEY = "lat";
	protected static final int POLLINGPLACE_LAT_COLUMN = 2;
	public static final String POLLINGPLACE_LNG_KEY = "lng";
	protected static final int POLLINGPLACE_LNG_COLUMN = 3;
	public static final String POLLINGPLACE_NAME_KEY = "name";
	protected static final int POLLINGPLACE_NAME_COLUMN = 4;
	public static final String POLLINGPLACE_NUMBER_KEY = "number";
	protected static final int POLLINGPLACE_NUMBER_COLUMN = 5;
	public static final String POLLINGPLACE_SECRETARY_KEY = "secretary";
	protected static final int POLLINGPLACE_SECRETARY_COLUMN = 6;
	public static final String POLLINGPLACE_TIMESTAMP_KEY = "timestamp";
	protected static final int POLLINGPLACE_TIMESTAMP_COLUMN = 7;
	public static final String POLLINGPLACE_TOTALOBSERVERS_KEY = "totalobservers";
	protected static final int POLLINGPLACE_TOTALOBSERVERS_COLUMN = 8;
	public static final String POLLINGPLACE_TYPE_KEY = "type";
	protected static final int POLLINGPLACE_TYPE_COLUMN = 9;
	public static final String POLLINGPLACE_ROW_ID = "_id";



	// -------- TABLES CREATION ----------

	// PollingPlace CREATION 
	private static final String DATABASE_POLLINGPLACE_CREATE = "create table " + POLLINGPLACE_TABLE + " (" + 
				 POLLINGPLACE_ROW_ID + " integer primary key autoincrement" + ", " + 
				 POLLINGPLACE_CHAIRMAN_KEY + " text  " + ", " + 
				 POLLINGPLACE_LAT_KEY + " float  " + ", " + 
				 POLLINGPLACE_LNG_KEY + " float  " + ", " + 
				 POLLINGPLACE_NAME_KEY + " text  " + ", " + 
				 POLLINGPLACE_NUMBER_KEY + " integer  " + ", " + 
				 POLLINGPLACE_SECRETARY_KEY + " text  " + ", " + 
				 POLLINGPLACE_TIMESTAMP_KEY + " long  " + ", " + 
				 POLLINGPLACE_TOTALOBSERVERS_KEY + " integer  " + ", " + 
				 POLLINGPLACE_TYPE_KEY + " text  " + ");";




	// -------------- POLLINGPLACE HELPERS ------------------
	public long addPollingPlace(String chairman, Float lat, Float lng, String name, Integer number, String secretary, long time, Integer totalobservers, String type)
	{
		ContentValues contentValues = new ContentValues();
		contentValues.put(POLLINGPLACE_CHAIRMAN_KEY, chairman);
		contentValues.put(POLLINGPLACE_LAT_KEY, lat);
		contentValues.put(POLLINGPLACE_LNG_KEY, lng);
		contentValues.put(POLLINGPLACE_NAME_KEY, name);
		contentValues.put(POLLINGPLACE_NUMBER_KEY, number);
		contentValues.put(POLLINGPLACE_SECRETARY_KEY, secretary);
		contentValues.put(POLLINGPLACE_TIMESTAMP_KEY, time);
		contentValues.put(POLLINGPLACE_TOTALOBSERVERS_KEY, totalobservers);
		contentValues.put(POLLINGPLACE_TYPE_KEY, type);
		return mDb.insert(POLLINGPLACE_TABLE, null, contentValues);
	
	}

	public long updatePollingPlace(long rowIndex, String chairman, Float lat, Float lng, String name, Integer number, String secretary, long timestamp, Integer totalobservers, String type)
	{
		String where = POLLINGPLACE_ROW_ID + " = " + rowIndex;
		ContentValues contentValues = new ContentValues();
		contentValues.put(POLLINGPLACE_CHAIRMAN_KEY, chairman);
		contentValues.put(POLLINGPLACE_LAT_KEY, lat);
		contentValues.put(POLLINGPLACE_LNG_KEY, lng);
		contentValues.put(POLLINGPLACE_NAME_KEY, name);
		contentValues.put(POLLINGPLACE_NUMBER_KEY, number);
		contentValues.put(POLLINGPLACE_SECRETARY_KEY, secretary);
		contentValues.put(POLLINGPLACE_TIMESTAMP_KEY, timestamp);
		contentValues.put(POLLINGPLACE_TOTALOBSERVERS_KEY, totalobservers);
		contentValues.put(POLLINGPLACE_TYPE_KEY, type);
		return mDb.update(POLLINGPLACE_TABLE, contentValues, where, null);
	
	}

	public boolean removePollingPlace(Long rowIndex)
	{
		return mDb.delete(POLLINGPLACE_TABLE, POLLINGPLACE_ROW_ID + " = " + rowIndex, null) > 0;
	}

	public boolean removeAllPollingPlace()
	{
		return mDb.delete(POLLINGPLACE_TABLE, null, null) > 0;
	}

	public Cursor getAllPollingPlaces()
	{
		return mDb.query(POLLINGPLACE_TABLE, new String[] {
					POLLINGPLACE_ROW_ID,
					POLLINGPLACE_CHAIRMAN_KEY,
					POLLINGPLACE_LAT_KEY,
					POLLINGPLACE_LNG_KEY,
					POLLINGPLACE_NAME_KEY,
					POLLINGPLACE_NUMBER_KEY,
					POLLINGPLACE_SECRETARY_KEY,
					POLLINGPLACE_TIMESTAMP_KEY,
					POLLINGPLACE_TOTALOBSERVERS_KEY,
					POLLINGPLACE_TYPE_KEY}, null, null, null, null, null);
	}

	public Cursor getPollingPlace(long rowIndex)
	{
		Cursor res = mDb.query(POLLINGPLACE_TABLE, new String[] {
					POLLINGPLACE_ROW_ID,
					POLLINGPLACE_CHAIRMAN_KEY,
					POLLINGPLACE_LAT_KEY,
					POLLINGPLACE_LNG_KEY,
					POLLINGPLACE_NAME_KEY,
					POLLINGPLACE_NUMBER_KEY,
					POLLINGPLACE_SECRETARY_KEY,
					POLLINGPLACE_TIMESTAMP_KEY,
					POLLINGPLACE_TOTALOBSERVERS_KEY,
					POLLINGPLACE_TYPE_KEY}, POLLINGPLACE_ROW_ID + " = " + rowIndex, null, null, null, null);
		if(res != null){
			res.moveToFirst();
		}
		return res;
	}
	
	public Cursor getPollingPlaceByNumber(int pollingPlaceNumber){
		Cursor res = mDb.query(POLLINGPLACE_TABLE, new String[] {
				POLLINGPLACE_ROW_ID,
				POLLINGPLACE_CHAIRMAN_KEY,
				POLLINGPLACE_LAT_KEY,
				POLLINGPLACE_LNG_KEY,
				POLLINGPLACE_NAME_KEY,
				POLLINGPLACE_NUMBER_KEY,
				POLLINGPLACE_SECRETARY_KEY,
				POLLINGPLACE_TIMESTAMP_KEY,
				POLLINGPLACE_TOTALOBSERVERS_KEY,
				POLLINGPLACE_TYPE_KEY}, POLLINGPLACE_NUMBER_KEY + " = " + pollingPlaceNumber, null, null, null, null);
		if(res != null){
			res.moveToFirst();
		}
		return res;
	}

	public Cursor getPollingPlaceNumbers(){
		Cursor res = mDb.query(POLLINGPLACE_TABLE, new String[] {
				POLLINGPLACE_ROW_ID,
				POLLINGPLACE_NUMBER_KEY,},
				null, null, null, null, null);
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
            db.execSQL(DATABASE_POLLINGPLACE_CREATE);
			
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
            db.execSQL("DROP TABLE IF EXISTS " + POLLINGPLACE_TABLE + ";");
			
            // Create a new one.
            onCreate(db);
        }
    }
     
    /** Dummy object to allow class to compile */
}