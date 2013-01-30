/**********************************************************************************************************************************************************************
****** AUTO GENERATED FILE BY ANDROID SQLITE HELPER SCRIPT BY FEDERICO PAOLINELLI. ANY CHANGE WILL BE WIPED OUT IF THE SCRIPT IS PROCESSED AGAIN. *******
**********************************************************************************************************************************************************************/
package com.whiterabbit.appennino;


import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.Date;

public class WebcamProviderClient{

	// -------------- WEBCAM HELPERS ------------------
    public static Uri addWebcam(String Resort, String Url, String Description, Date LastUpdate, Long Favourite, String FileName, Context c){
     ContentValues contentValues = new ContentValues();
       contentValues.put(WebcamProvider.WEBCAM_RESORT_COLUMN, Resort);
       contentValues.put(WebcamProvider.WEBCAM_URL_COLUMN, Url);
       contentValues.put(WebcamProvider.WEBCAM_DESCRIPTION_COLUMN, Description);
       contentValues.put(WebcamProvider.WEBCAM_LASTUPDATE_COLUMN, LastUpdate.getTime());
       contentValues.put(WebcamProvider.WEBCAM_FAVOURITE_COLUMN, Favourite);
       contentValues.put(WebcamProvider.WEBCAM_FILENAME_COLUMN, FileName);
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
    		WebcamProvider.WEBCAM_FAVOURITE_COLUMN,
    		WebcamProvider.WEBCAM_FILENAME_COLUMN  }; 
    
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
    		WebcamProvider.WEBCAM_FAVOURITE_COLUMN,
    		WebcamProvider.WEBCAM_FILENAME_COLUMN  };
    
        Uri rowAddress = ContentUris.withAppendedId(WebcamProvider.WEBCAM_URI, rowId);
    
        String where = null;    
        String whereArgs[] = null;
        String order = null;
    
        Cursor resultCursor = cr.query(rowAddress, result_columns, where, whereArgs, order);
        return resultCursor;
    }

    public static int updateWebcam(long rowId, String Resort, String Url, String Description, Date LastUpdate, Long Favourite, String FileName, Context c){
     ContentValues contentValues = new ContentValues();
       contentValues.put(WebcamProvider.WEBCAM_RESORT_COLUMN, Resort);
       contentValues.put(WebcamProvider.WEBCAM_URL_COLUMN, Url);
       contentValues.put(WebcamProvider.WEBCAM_DESCRIPTION_COLUMN, Description);
       contentValues.put(WebcamProvider.WEBCAM_LASTUPDATE_COLUMN, LastUpdate.getTime());
       contentValues.put(WebcamProvider.WEBCAM_FAVOURITE_COLUMN, Favourite);
       contentValues.put(WebcamProvider.WEBCAM_FILENAME_COLUMN, FileName);
    
        Uri rowURI = ContentUris.withAppendedId(WebcamProvider.WEBCAM_URI, rowId); 
    
        String where = null;
        String whereArgs[] = null;
    
        ContentResolver cr = c.getContentResolver();
        int updatedRowCount = cr.update(rowURI, contentValues, where, whereArgs);
        return updatedRowCount;
    }

}