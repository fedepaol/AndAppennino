package com.whiterabbit.appennino;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockListFragment;
import com.whiterabbit.postman.utils.Constants;

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
        Cursor c = WebcamProviderClient.getWebcam(id, getSherlockActivity());

        if(c.moveToFirst()){
            ((FirstActivity)getSherlockActivity()).onWebcamSelected(
                                                    c.getString(WebcamProvider.WEBCAM_FILENAME_COLUMN_POSITION),
                                                    c.getString(WebcamProvider.WEBCAM_URL_COLUMN_POSITION),
                                                    c.getString(WebcamProvider.WEBCAM_DESCRIPTION_COLUMN_POSITION));
        }else{
            //That would be a surprise..
            Log.d(Constants.LOG_TAG, "Location not found!!");
            throw new IllegalArgumentException("Invalid location id:" + id);
        }
    }


    public WebcamListAdapter getAdapter(){
        return mAdapter;
    }




}
