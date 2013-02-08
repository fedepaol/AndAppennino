/**********************************************************************************************************************************************************************
****** AUTO GENERATED FILE BY ANDROID SQLITE HELPER SCRIPT BY FEDERICO PAOLINELLI. ANY CHANGE WILL BE WIPED OUT IF THE SCRIPT IS PROCESSED AGAIN. *******
**********************************************************************************************************************************************************************/
package com.whiterabbit.appennino.data;


import android.content.*;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class WebcamProvider extends ContentProvider {
    private static final String DATABASE_NAME = "dbFileDb.db";
    private static final int DATABASE_VERSION = 10;
    private static final String TAG = "WebcamProvider";


    // -------------- URIS ------------

    public static final Uri WEBCAM_URI = Uri.parse("content://com.whiterabbit.appennino/webcam");
    public static final Uri RESORTWEATHER_URI = Uri.parse("content://com.whiterabbit.appennino/resortweather");


    public static final String ROW_ID = "_id";

    // -------------- WEBCAM DEFINITIONS ------------

    public static final String WEBCAM_TABLE = "Webcam";
    public static final String WEBCAM_RESORT_COLUMN = "Resort";
    public static final int WEBCAM_RESORT_COLUMN_POSITION = 1;
    public static final String WEBCAM_URL_COLUMN = "Url";
    public static final int WEBCAM_URL_COLUMN_POSITION = 2;
    public static final String WEBCAM_DESCRIPTION_COLUMN = "Description";
    public static final int WEBCAM_DESCRIPTION_COLUMN_POSITION = 3;
    public static final String WEBCAM_LASTUPDATE_COLUMN = "LastUpdate";
    public static final int WEBCAM_LASTUPDATE_COLUMN_POSITION = 4;
    public static final String WEBCAM_FAVOURITE_COLUMN = "Favourite";
    public static final int WEBCAM_FAVOURITE_COLUMN_POSITION = 5;

    private static final int ALLWEBCAM= 1;
    private static final int SINGLE_WEBCAM= 2;



    // -------------- RESORTWEATHER DEFINITIONS ------------

    public static final String RESORTWEATHER_TABLE = "ResortWeather";
    public static final String RESORTWEATHER_RESORT_COLUMN = "Resort";
    public static final int RESORTWEATHER_RESORT_COLUMN_POSITION = 1;
    public static final String RESORTWEATHER_DESCRIPTION_COLUMN = "Description";
    public static final int RESORTWEATHER_DESCRIPTION_COLUMN_POSITION = 2;
    public static final String RESORTWEATHER_TEMPERATURE_COLUMN = "Temperature";
    public static final int RESORTWEATHER_TEMPERATURE_COLUMN_POSITION = 3;
    public static final String RESORTWEATHER_WIND_COLUMN = "Wind";
    public static final int RESORTWEATHER_WIND_COLUMN_POSITION = 4;
    public static final String RESORTWEATHER_VISIBILITY_COLUMN = "Visibility";
    public static final int RESORTWEATHER_VISIBILITY_COLUMN_POSITION = 5;
    public static final String RESORTWEATHER_ICONURL_COLUMN = "IconUrl";
    public static final int RESORTWEATHER_ICONURL_COLUMN_POSITION = 6;
    public static final String RESORTWEATHER_WEATHERCODE_COLUMN = "WeatherCode";
    public static final int RESORTWEATHER_WEATHERCODE_COLUMN_POSITION = 7;

    private static final int ALLRESORTWEATHER= 3;
    private static final int SINGLE_RESORTWEATHER= 4;



    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.whiterabbit.appennino", "webcam", ALLWEBCAM);
        uriMatcher.addURI("com.whiterabbit.appennino", "webcam/#", SINGLE_WEBCAM);
        uriMatcher.addURI("com.whiterabbit.appennino", "resortweather", ALLRESORTWEATHER);
        uriMatcher.addURI("com.whiterabbit.appennino", "resortweather/#", SINGLE_RESORTWEATHER);
    }

    // -------- TABLES CREATION ----------

    // Webcam CREATION 
    private static final String DATABASE_WEBCAM_CREATE = "create table " + WEBCAM_TABLE + " (" + 
				 ROW_ID + " integer primary key autoincrement" + ", " + 
				 WEBCAM_RESORT_COLUMN + " text  " + ", " + 
				 WEBCAM_URL_COLUMN + " text  " + ", " + 
				 WEBCAM_DESCRIPTION_COLUMN + " text  " + ", " + 
				 WEBCAM_LASTUPDATE_COLUMN + " integer  " + ", " + 
				 WEBCAM_FAVOURITE_COLUMN + " integer  " + ");";


    // ResortWeather CREATION 
    private static final String DATABASE_RESORTWEATHER_CREATE = "create table " + RESORTWEATHER_TABLE + " (" + 
				 ROW_ID + " integer primary key autoincrement" + ", " + 
				 RESORTWEATHER_RESORT_COLUMN + " text  " + ", " + 
				 RESORTWEATHER_DESCRIPTION_COLUMN + " text  " + ", " + 
				 RESORTWEATHER_TEMPERATURE_COLUMN + " text  " + ", " + 
				 RESORTWEATHER_WIND_COLUMN + " text  " + ", " + 
				 RESORTWEATHER_VISIBILITY_COLUMN + " text  " + ", " + 
				 RESORTWEATHER_ICONURL_COLUMN + " text  " + ", " + 
				 RESORTWEATHER_WEATHERCODE_COLUMN + " integer  " + ");";



    private MyDbHelper myOpenHelper;

    @Override
    public boolean onCreate() {
        myOpenHelper = new MyDbHelper(getContext(), DATABASE_NAME, null, DATABASE_VERSION);
        return true;
    }

    /**
    * Returns the right table name for the given uri
    * @param uri
    * @return
    */
    private String getTableNameFromUri(Uri uri){
        switch (uriMatcher.match(uri)) {
            case ALLWEBCAM:
            case SINGLE_WEBCAM:
                return WEBCAM_TABLE;
            case ALLRESORTWEATHER:
            case SINGLE_RESORTWEATHER:
                return RESORTWEATHER_TABLE;
            default: break;
        }

           return null;
    }

	/**
    * Returns the parent uri for the given uri
    * @param uri
    * @return
    */
    private Uri getContentUriFromUri(Uri uri){
        switch (uriMatcher.match(uri)) {
            case ALLWEBCAM:
            case SINGLE_WEBCAM:
                return WEBCAM_URI;
            case ALLRESORTWEATHER:
            case SINGLE_RESORTWEATHER:
                return RESORTWEATHER_URI;
            default: break;
        }

        return null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
        String[] selectionArgs, String sortOrder) {

        // Open thedatabase.
        SQLiteDatabase db;
        try {
            db = myOpenHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = myOpenHelper.getReadableDatabase();
        }

        // Replace these with valid SQL statements if necessary.
        String groupBy = null;
        String having = null;

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // If this is a row query, limit the result set to the passed in row.
        switch (uriMatcher.match(uri)) {
            case SINGLE_WEBCAM:
            case SINGLE_RESORTWEATHER:
                String rowID = uri.getPathSegments().get(1);
                queryBuilder.appendWhere(ROW_ID + "=" + rowID);
            default: break;
        }

        // Specify the table on which to perform the query. This can
        // be a specific table or a join as required.
        queryBuilder.setTables(getTableNameFromUri(uri));

        // Execute the query.
        Cursor cursor = queryBuilder.query(db, projection, selection,
                    selectionArgs, groupBy, having, sortOrder);
            cursor.setNotificationUri(getContext().getContentResolver(), uri);

        // Return the result Cursor.
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        // Return a string that identifies the MIME type
        // for a Content Provider URI
        switch (uriMatcher.match(uri)) {
            case ALLWEBCAM:
                return "vnd.android.cursor.dir/vnd.com.whiterabbit.appennino.webcam";
            case SINGLE_WEBCAM:
                return "vnd.android.cursor.dir/vnd.com.whiterabbit.appennino.webcam";
            case ALLRESORTWEATHER:
                return "vnd.android.cursor.dir/vnd.com.whiterabbit.appennino.resortweather";
            case SINGLE_RESORTWEATHER:
                return "vnd.android.cursor.dir/vnd.com.whiterabbit.appennino.resortweather";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
            }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        switch (uriMatcher.match(uri)) {
            case SINGLE_WEBCAM:
            case SINGLE_RESORTWEATHER:
                String rowID = uri.getPathSegments().get(1);
                selection = ROW_ID + "=" + rowID + (!TextUtils.isEmpty(selection) ?  " AND (" + selection + ')' : "");
            default: break;
        }


        if (selection == null)
            selection = "1";

        int deleteCount = db.delete(getTableNameFromUri(uri),
                selection, selectionArgs);

        getContext().getContentResolver().notifyChange(uri, null);

        return deleteCount;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        String nullColumnHack = null;

        long id = db.insert(getTableNameFromUri(uri), nullColumnHack, values);
        if (id > -1) {
            Uri insertedId = ContentUris.withAppendedId(getContentUriFromUri(uri), id);
                                getContext().getContentResolver().notifyChange(insertedId, null);
            getContext().getContentResolver().notifyChange(insertedId, null);
            return insertedId;
        } else {
            return null;
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        // Open a read / write database to support the transaction.
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        // If this is a row URI, limit the deletion to the specified row.
        switch (uriMatcher.match(uri)) {             case SINGLE_WEBCAM:
            case SINGLE_RESORTWEATHER:
                String rowID = uri.getPathSegments().get(1);
                selection = ROW_ID + "=" + rowID + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : "");
            default: break;
        }

        // Perform the update.
        int updateCount = db.update(getTableNameFromUri(uri), values, selection, selectionArgs);

        // Notify any observers of the change in the data set.
        getContext().getContentResolver().notifyChange(uri, null);

        return updateCount;
    }


    private static class MyDbHelper extends SQLiteOpenHelper {
    
        public MyDbHelper(Context context, String name, CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // Called when no database exists in disk and the helper class needs
        // to create a new one.


        private void fillRow(SQLiteDatabase db, ContentValues c, String resort, String url, boolean  favourite, String description){
            c.clear();
            c.put(WEBCAM_RESORT_COLUMN, resort);
            c.put(WEBCAM_URL_COLUMN, url);
            c.put(WEBCAM_LASTUPDATE_COLUMN, 0);
            c.put(WEBCAM_FAVOURITE_COLUMN, favourite);
            c.put(WEBCAM_DESCRIPTION_COLUMN, description);
            db.insert(WEBCAM_TABLE, null, c);
        }


        private void fillTables(SQLiteDatabase db){
            try{
                db.beginTransaction();
                ContentValues contentValues = new ContentValues();
                fillRow(db, contentValues, "Abetone", "http://www.aptabetone.it/abetone/pics/lat001.jpg", false, "Piazzale abetone");
                fillRow(db, contentValues, "Abetone", "http://www.meteo-system.com/stazioni/valdiluce.jpg", false, "Val di luce");
                fillRow(db, contentValues, "Abetone", "http://srv2.realcam.it/live/pub/34-9.jpg", false, "Monte gomito");
                fillRow(db, contentValues, "Abetone", "http://www.albergotosca.it/webcam/abetonealbergotosca.jpg ", false, "Le regine");
                fillRow(db, contentValues, "Abetone", "http://srv2.realcam.it/live/pub/34-3.jpg", false, "Val di luce Albergone");
                fillRow(db, contentValues, "Cimone", "http://www.cimonesci.it/cams/funivia.jpg", false, "Passo del lupo");
                fillRow(db, contentValues, "Cimone", "http://www.cimonesci.it/cams/panorama.jpg", false, "Monte cimone");
                fillRow(db, contentValues, "Cimone", "http://www.cimonesci.it/cams/cimoncino.jpg", false, "Cimoncino");
                fillRow(db, contentValues, "Cimone", "http://www.cimonesci.it/cams/polle.jpg", false, "Le polle");

                db.setTransactionSuccessful();
            } catch (SQLException e) {
            } finally {
                db.endTransaction();
            }
        }


        // Called when no database exists in disk and the helper class needs
        // to create a new one.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_WEBCAM_CREATE);
            db.execSQL(DATABASE_RESORTWEATHER_CREATE);
            fillTables(db);

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
            db.execSQL("DROP TABLE IF EXISTS " + WEBCAM_TABLE + ";");
			db.execSQL("DROP TABLE IF EXISTS " + RESORTWEATHER_TABLE + ";");
			
            // Create a new one.
            onCreate(db);
        }
    }
     
    /** Dummy object to allow class to compile */
}