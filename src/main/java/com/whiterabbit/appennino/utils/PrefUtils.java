package com.whiterabbit.appennino.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;


public class PrefUtils {
	private final static String SELECTED_RESORT = "Resort";
    private final static String METEO_LAST_UPDATE = "MeteoUpdate";
	private final static String PREF_NAME = "com.whiterabbit.appennino";

	public static int getSavedResort(Context c)
	{
		int mode = Activity.MODE_PRIVATE;
		SharedPreferences mySharedPreferences = c.getSharedPreferences(PREF_NAME, mode);		
		return mySharedPreferences.getInt(SELECTED_RESORT, 0);
	}


	public static void setSelectedResort(int resort , Context c)
	{
		int mode = Activity.MODE_PRIVATE;
		SharedPreferences mySharedPreferences = c.getSharedPreferences(PREF_NAME, mode);		
		SharedPreferences.Editor editor = mySharedPreferences.edit();	
		editor.putInt(SELECTED_RESORT, resort);
		editor.commit();

	}

	public static void setMeteoLastUpdate(Context c)
	{
        Date d = new Date();
		int mode = Activity.MODE_PRIVATE;
		SharedPreferences mySharedPreferences = c.getSharedPreferences(PREF_NAME, mode);
		SharedPreferences.Editor editor = mySharedPreferences.edit();
		editor.putLong(METEO_LAST_UPDATE, d.getTime());
		editor.commit();
	}


    public static long getMeteoLastUpdate(Context c)
    {
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences mySharedPreferences = c.getSharedPreferences(PREF_NAME, mode);
        return mySharedPreferences.getLong(METEO_LAST_UPDATE, 0);
    }
}
