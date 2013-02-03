package com.whiterabbit.appennino;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragment;
import com.whiterabbit.appennino.com.whiterabbit.appennino.AppenninoApplication;
import uk.co.senab.bitmapcache.CacheableBitmapDrawable;
import uk.co.senab.bitmapcache.CacheableImageView;

/**
 * Created with IntelliJ IDEA.
 * User: fedepaol
 * Date: 1/31/13
 * Time: 11:59 PM
 */
public class WebcamDetailFragment extends SherlockFragment{
    private TextView mLocationName;
    private CacheableImageView mWebcamImage;
    private AppenninoApplication mApplication;


    private class LoadTask extends AsyncTask<String, Void, CacheableBitmapDrawable> {

        @Override
        protected CacheableBitmapDrawable doInBackground(String... strings) {
            return mApplication.getBitmapCache().get(strings[0]);
        }

        @Override
        protected void onPostExecute(CacheableBitmapDrawable bitmap) {
            if(bitmap != null){
                mWebcamImage.setImageDrawable(bitmap);
            }else{
                // TODO Set default
            }
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mApplication = AppenninoApplication.getApplication();
        return inflater.inflate(R.layout.webcam_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLocationName = (TextView) view.findViewById(R.id.webcamdetail_location);
        mWebcamImage = (CacheableImageView) view.findViewById(R.id.webcamdetail_image);
    }

    public void update(String location, String url){
        mLocationName.setText(location);
        LoadTask loader = new LoadTask();
        loader.execute(url);
    }


}
