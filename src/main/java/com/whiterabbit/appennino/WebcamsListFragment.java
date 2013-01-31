package com.whiterabbit.appennino;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockListFragment;

public class WebcamsListFragment extends SherlockListFragment{
    private WebcamListAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.webcam_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new WebcamListAdapter(getActivity(), null);
        getListView().setAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ((FirstActivity)getSherlockActivity()).onWebcamSelected("aaa");
    }


    public WebcamListAdapter getAdapter(){
        return mAdapter;
    }




}
