package com.whiterabbit.appennino;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragment;
import com.whiterabbit.appennino.data.WebcamProvider;
import com.whiterabbit.appennino.data.WebcamProviderClientExt;

/**
 * Created with IntelliJ IDEA.
 * User: fedepaol
 * Date: 2/3/13
 * Time: 8:17 PM
 */
public class MeteoFragment extends SherlockFragment{
    private ImageView mImage;
    private TextView mTemperature;
    private TextView mWind;
    private TextView mVisibility;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.weather_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mImage = (ImageView) view.findViewById(R.id.weather_image);
        mTemperature = (TextView) view.findViewById(R.id.weather_temperature);
        mWind = (TextView) view.findViewById(R.id.weather_wind);
        mVisibility = (TextView) view.findViewById(R.id.weather_visibility);
    }

    public void update(String resortName){
        AsyncTask<String, Void, Cursor> t = new AsyncTask<String, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(String... strings) {
                return WebcamProviderClientExt.getResortWeather(strings[0], getActivity());
            }

            @Override
            protected void onPostExecute(Cursor cursor) {
                if(cursor.moveToFirst()){
                    String windString = String.format(getString(R.string.weather_wind_speed),
                                                    cursor.getString(WebcamProvider.RESORTWEATHER_WIND_COLUMN_POSITION));

                    mWind.setText(windString);
                    String temperatureString = cursor.getString(WebcamProvider.RESORTWEATHER_TEMPERATURE_COLUMN_POSITION) +
                            (char) 0x00B0 + " C";

                    mTemperature.setText(temperatureString);

                    String visibilityString = String.format(getString(R.string.weather_visibility),
                                                cursor.getString(WebcamProvider.RESORTWEATHER_VISIBILITY_COLUMN_POSITION));
                    mVisibility.setText(visibilityString);

                    int weatherCode = (int) cursor.getLong(WebcamProvider.RESORTWEATHER_WEATHERCODE_COLUMN_POSITION);
                    mImage.setImageResource(getImageFromWeatherCode(weatherCode));
                }else{
                    mWind.setText("");
                    mTemperature.setText("");
                    mVisibility.setText("");
                }
            }
        };
        t.execute(resortName);
    }


    private int getImageFromWeatherCode(int code){
        switch(code){
            case 395:
            case 332:
                return R.drawable.blowing_snow;
            case 392:
                return R.drawable.snow_shower;
            case 389:
                return R.drawable.t_storm_rain;
            case 386:
                return R.drawable.p_c_rain;
            case 377:
            case 374:
                return R.drawable.freezing_rain;
            case 371:
                return R.drawable.blowing_snow;
            case 368:
                return R.drawable.snow_shower;
            case 365:
            case 362:
                return R.drawable.blizzard;
            case 359:
            case 356:
                return R.drawable.showers;
            case 353:
                return R.drawable.rainy;
            case 350:
                return R.drawable.sleet;
            case 338:
            case 335:
                return R.drawable.p_c_snow;
            case 329:
            case 323:
                return R.drawable.m_c_snow;
            case 326:
                return R.drawable.snow;
            case 320:
            case 317:
                return R.drawable.sleet;
            case 314:
            case 311:
            case 230:
                return R.drawable.freezing_rain;
            case 308:
            case 305:
                return R.drawable.showers;
            case 302:
            case 299:
            case 296:
                return R.drawable.rainy;

            case 293:
                return R.drawable.p_c_rain;
            case 284:
            case 185:
                return R.drawable.drizzle;
            case 281:
            case 266:
            case 263:
                return R.drawable.fair_drizzle;

            case 260:
            case 248:
                return R.drawable.fog;

            case 227:
                return R.drawable.flurries;
            case 200:
                return R.drawable.thunder_storm;

            case 179:
                return R.drawable.p_c_snow;

            case 182:
                return R.drawable.sleet;

            case 176:
                return R.drawable.p_c_rain;

            case 143:
                return R.drawable.fog;
            case 119:
            case 116:
                return R.drawable.cloudy;
            case 122:
                return R.drawable.fair;
            case 113:
                return R.drawable.sunny;
        }
        return 0;
    }

}
