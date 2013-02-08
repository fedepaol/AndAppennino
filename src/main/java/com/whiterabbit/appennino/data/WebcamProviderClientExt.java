package com.whiterabbit.appennino.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: fedepaol
 * Date: 2/3/13
 * Time: 10:43 PM
 */
public class WebcamProviderClientExt extends WebcamProviderClient{
    public static int updateWebcamLastUpdate(Long webcamId, Date updateDate, Context c){

            ContentValues contentValues = new ContentValues();
            contentValues.put(WebcamProvider.WEBCAM_LASTUPDATE_COLUMN, updateDate.getTime());
            Uri rowURI = ContentUris.withAppendedId(WebcamProvider.WEBCAM_URI, webcamId);

            String where = null;
            String whereArgs[] = null;

            ContentResolver cr = c.getContentResolver();
            int updatedRowCount = cr.update(rowURI, contentValues, where, whereArgs);
            return updatedRowCount;
    }


    public static Cursor getResortWeather(String resort, Context c){
        ContentResolver cr = c.getContentResolver();
        String[] result_columns = new String[] {
                WebcamProvider.ROW_ID,
                WebcamProvider.RESORTWEATHER_RESORT_COLUMN,
                WebcamProvider.RESORTWEATHER_DESCRIPTION_COLUMN,
                WebcamProvider.RESORTWEATHER_TEMPERATURE_COLUMN,
                WebcamProvider.RESORTWEATHER_WIND_COLUMN,
                WebcamProvider.RESORTWEATHER_VISIBILITY_COLUMN,
                WebcamProvider.RESORTWEATHER_ICONURL_COLUMN  };

        String where = "Resort=?";
        String whereArgs[] = new String[]{resort};
        String order = null;

        Cursor resultCursor = cr.query(WebcamProvider.RESORTWEATHER_URI, result_columns, where, whereArgs, order);
        return resultCursor;
    }
}
