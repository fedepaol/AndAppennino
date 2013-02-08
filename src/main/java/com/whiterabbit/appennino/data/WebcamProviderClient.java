/**********************************************************************************************************************************************************************
****** AUTO GENERATED FILE BY ANDROID SQLITE HELPER SCRIPT BY FEDERICO PAOLINELLI. ANY CHANGE WILL BE WIPED OUT IF THE SCRIPT IS PROCESSED AGAIN. *******
**********************************************************************************************************************************************************************/
package com.whiterabbit.appennino.data;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.Date;

public class WebcamProviderClient{

	// -------------- WEBCAM HELPERS ------------------
    public static Uri addWebcam(String Resort, String Url, String Description, Date LastUpdate, Long Favourite, Context c){
     ContentValues contentValues = new ContentValues();
       contentValues.put(WebcamProvider.WEBCAM_RESORT_COLUMN, Resort);
       contentValues.put(WebcamProvider.WEBCAM_URL_COLUMN, Url);
       contentValues.put(WebcamProvider.WEBCAM_DESCRIPTION_COLUMN, Description);
       contentValues.put(WebcamProvider.WEBCAM_LASTUPDATE_COLUMN, LastUpdate.getTime());
       contentValues.put(WebcamProvider.WEBCAM_FAVOURITE_COLUMN, Favourite);
    	ContentResolver cr = c.getContentResolver();
    	return cr.insert(WebcamProvider.WEBCAM_URI, contentValues);
    }

    public static int removeWebcam(long rowIndex, Context c){
       ContentResolver cr = c.getContentResolver();
        Uri rowAddress = ContentUris.withAppendedId(WebcamProvider.WEBCAM_URI, rowIndex);
        return cr.delete(rowAddress, null, null);
    }

    public static int removeAllWebcam(Context c){
       ContentResolver cr = c.getContentResolver();
        return cr.delete(WebcamProvider.WEBCAM_URI, null, null);
    }

    public static Cursor getAllWebcam(Context c){
    	ContentResolver cr = c.getContentResolver();
        String[] result_columns = new String[] {
        	WebcamProvider.ROW_ID,
    		WebcamProvider.WEBCAM_RESORT_COLUMN,
    		WebcamProvider.WEBCAM_URL_COLUMN,
    		WebcamProvider.WEBCAM_DESCRIPTION_COLUMN,
    		WebcamProvider.WEBCAM_LASTUPDATE_COLUMN,
    		WebcamProvider.WEBCAM_FAVOURITE_COLUMN  }; 
    
        String where = null;    
        String whereArgs[] = null;
        String order = null;
    
        Cursor resultCursor = cr.query(WebcamProvider.WEBCAM_URI, result_columns, where, whereArgs, order);
        return resultCursor;
    }

    public static Cursor getWebcam(long rowId, Context c){
    	ContentResolver cr = c.getContentResolver();
        String[] result_columns = new String[] {
        	WebcamProvider.ROW_ID,
    		WebcamProvider.WEBCAM_RESORT_COLUMN,
    		WebcamProvider.WEBCAM_URL_COLUMN,
    		WebcamProvider.WEBCAM_DESCRIPTION_COLUMN,
    		WebcamProvider.WEBCAM_LASTUPDATE_COLUMN,
    		WebcamProvider.WEBCAM_FAVOURITE_COLUMN  };
    
        Uri rowAddress = ContentUris.withAppendedId(WebcamProvider.WEBCAM_URI, rowId);
    
        String where = null;    
        String whereArgs[] = null;
        String order = null;
    
        Cursor resultCursor = cr.query(rowAddress, result_columns, where, whereArgs, order);
        return resultCursor;
    }

    public static int updateWebcam(long rowId, String Resort, String Url, String Description, Date LastUpdate, Long Favourite, Context c){
     ContentValues contentValues = new ContentValues();
       contentValues.put(WebcamProvider.WEBCAM_RESORT_COLUMN, Resort);
       contentValues.put(WebcamProvider.WEBCAM_URL_COLUMN, Url);
       contentValues.put(WebcamProvider.WEBCAM_DESCRIPTION_COLUMN, Description);
       contentValues.put(WebcamProvider.WEBCAM_LASTUPDATE_COLUMN, LastUpdate.getTime());
       contentValues.put(WebcamProvider.WEBCAM_FAVOURITE_COLUMN, Favourite);
    
        Uri rowURI = ContentUris.withAppendedId(WebcamProvider.WEBCAM_URI, rowId); 
    
        String where = null;
        String whereArgs[] = null;
    
        ContentResolver cr = c.getContentResolver();
        int updatedRowCount = cr.update(rowURI, contentValues, where, whereArgs);
        return updatedRowCount;
    }

	// -------------- RESORTWEATHER HELPERS ------------------
    public static Uri addResortWeather(String Resort, String Description, String Temperature, String Wind, String Visibility, String IconUrl, Long WeatherCode, Context c){
     ContentValues contentValues = new ContentValues();
       contentValues.put(WebcamProvider.RESORTWEATHER_RESORT_COLUMN, Resort);
       contentValues.put(WebcamProvider.RESORTWEATHER_DESCRIPTION_COLUMN, Description);
       contentValues.put(WebcamProvider.RESORTWEATHER_TEMPERATURE_COLUMN, Temperature);
       contentValues.put(WebcamProvider.RESORTWEATHER_WIND_COLUMN, Wind);
       contentValues.put(WebcamProvider.RESORTWEATHER_VISIBILITY_COLUMN, Visibility);
       contentValues.put(WebcamProvider.RESORTWEATHER_ICONURL_COLUMN, IconUrl);
       contentValues.put(WebcamProvider.RESORTWEATHER_WEATHERCODE_COLUMN, WeatherCode);
    	ContentResolver cr = c.getContentResolver();
    	return cr.insert(WebcamProvider.RESORTWEATHER_URI, contentValues);
    }

    public static int removeResortWeather(long rowIndex, Context c){
       ContentResolver cr = c.getContentResolver();
        Uri rowAddress = ContentUris.withAppendedId(WebcamProvider.RESORTWEATHER_URI, rowIndex);
        return cr.delete(rowAddress, null, null);
    }

    public static int removeAllResortWeather(Context c){
       ContentResolver cr = c.getContentResolver();
        return cr.delete(WebcamProvider.RESORTWEATHER_URI, null, null);
    }

    public static Cursor getAllResortWeather(Context c){
    	ContentResolver cr = c.getContentResolver();
        String[] result_columns = new String[] {
        	WebcamProvider.ROW_ID,
    		WebcamProvider.RESORTWEATHER_RESORT_COLUMN,
    		WebcamProvider.RESORTWEATHER_DESCRIPTION_COLUMN,
    		WebcamProvider.RESORTWEATHER_TEMPERATURE_COLUMN,
    		WebcamProvider.RESORTWEATHER_WIND_COLUMN,
    		WebcamProvider.RESORTWEATHER_VISIBILITY_COLUMN,
    		WebcamProvider.RESORTWEATHER_ICONURL_COLUMN,
    		WebcamProvider.RESORTWEATHER_WEATHERCODE_COLUMN  }; 
    
        String where = null;    
        String whereArgs[] = null;
        String order = null;
    
        Cursor resultCursor = cr.query(WebcamProvider.RESORTWEATHER_URI, result_columns, where, whereArgs, order);
        return resultCursor;
    }

    public static Cursor getResortWeather(long rowId, Context c){
    	ContentResolver cr = c.getContentResolver();
        String[] result_columns = new String[] {
        	WebcamProvider.ROW_ID,
    		WebcamProvider.RESORTWEATHER_RESORT_COLUMN,
    		WebcamProvider.RESORTWEATHER_DESCRIPTION_COLUMN,
    		WebcamProvider.RESORTWEATHER_TEMPERATURE_COLUMN,
    		WebcamProvider.RESORTWEATHER_WIND_COLUMN,
    		WebcamProvider.RESORTWEATHER_VISIBILITY_COLUMN,
    		WebcamProvider.RESORTWEATHER_ICONURL_COLUMN,
    		WebcamProvider.RESORTWEATHER_WEATHERCODE_COLUMN  };
    
        Uri rowAddress = ContentUris.withAppendedId(WebcamProvider.RESORTWEATHER_URI, rowId);
    
        String where = null;    
        String whereArgs[] = null;
        String order = null;
    
        Cursor resultCursor = cr.query(rowAddress, result_columns, where, whereArgs, order);
        return resultCursor;
    }

    public static int updateResortWeather(long rowId, String Resort, String Description, String Temperature, String Wind, String Visibility, String IconUrl, Long WeatherCode, Context c){
     ContentValues contentValues = new ContentValues();
       contentValues.put(WebcamProvider.RESORTWEATHER_RESORT_COLUMN, Resort);
       contentValues.put(WebcamProvider.RESORTWEATHER_DESCRIPTION_COLUMN, Description);
       contentValues.put(WebcamProvider.RESORTWEATHER_TEMPERATURE_COLUMN, Temperature);
       contentValues.put(WebcamProvider.RESORTWEATHER_WIND_COLUMN, Wind);
       contentValues.put(WebcamProvider.RESORTWEATHER_VISIBILITY_COLUMN, Visibility);
       contentValues.put(WebcamProvider.RESORTWEATHER_ICONURL_COLUMN, IconUrl);
       contentValues.put(WebcamProvider.RESORTWEATHER_WEATHERCODE_COLUMN, WeatherCode);
    
        Uri rowURI = ContentUris.withAppendedId(WebcamProvider.RESORTWEATHER_URI, rowId); 
    
        String where = null;
        String whereArgs[] = null;
    
        ContentResolver cr = c.getContentResolver();
        int updatedRowCount = cr.update(rowURI, contentValues, where, whereArgs);
        return updatedRowCount;
    }

}