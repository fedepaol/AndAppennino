package com.whiterabbit.appennino.com.whiterabbit.appennino.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
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
}
