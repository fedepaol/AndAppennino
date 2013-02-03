package com.whiterabbit.appennino;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragment;

/**
 * Created with IntelliJ IDEA.
 * User: fedepaol
 * Date: 2/3/13
 * Time: 8:17 PM
 */
public class MeteoFragment extends SherlockFragment{
    private TextView mDescription;
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

        mDescription = (TextView) view.findViewById(R.id.weather_description);
        mTemperature = (TextView) view.findViewById(R.id.weather_temperature);
        mWind = (TextView) view.findViewById(R.id.weather_wind);
        mVisibility = (TextView) view.findViewById(R.id.weather_visibility);
    }

    public void update(String resortName){
    }

}
