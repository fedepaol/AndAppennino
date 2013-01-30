package com.whiterabbit.appennino;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockListFragment;
import com.whiterabbit.R;

public class WebcamsListFragment extends SherlockListFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.webcam_list, container, false);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ((FirstActivity)getSherlockActivity()).onWebcamSelected("aaa");
    }




}
