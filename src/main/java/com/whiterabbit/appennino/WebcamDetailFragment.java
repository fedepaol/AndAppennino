package com.whiterabbit.appennino;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: fedepaol
 * Date: 1/31/13
 * Time: 11:59 PM
 */
public class WebcamDetailFragment extends Fragment {
    private TextView mLocationName;
    private String mFilename;
    private LoadTaks mLoader;


    private class LoadTaks extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            File path = WebcamDetailFragment.this.getActivity().getExternalFilesDir(null);
            File image = new File(path, strings[0]);

            try {
                InputStream s = new FileInputStream(image);
                Bitmap bitmap = BitmapFactory.decodeStream(s);
                return bitmap;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mLoader = new LoadTaks();
        return inflater.inflate(R.layout.webcam_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLocationName = (TextView) view.findViewById(R.id.webcamdetail_location);

    }

    public void update(String location, String filename){
        mLocationName.setText(location);
        mFilename = filename;
        mLoader.execute(filename);

    }


}
