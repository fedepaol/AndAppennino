package com.whiterabbit.appennino;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.whiterabbit.appennino.actions.MeteoDownloadAction;
import com.whiterabbit.appennino.data.WebcamProvider;
import com.whiterabbit.appennino.utils.PrefUtils;
import com.whiterabbit.postman.ServerInteractionHelper;
import com.whiterabbit.postman.ServerInteractionResponseInterface;
import com.whiterabbit.postman.exceptions.SendingCommandException;

import java.util.Date;

public class FirstActivity extends SherlockFragmentActivity implements ActionBar.OnNavigationListener, LoaderManager.LoaderCallbacks<Cursor>,ServerInteractionResponseInterface {

    private static final String METEO_REQUEST = "Meteo";
    private String[] mResorts;
    private WebcamsListFragment mWebcamList;
    private MeteoFragment mMeteoFragment;
    private int mResort;
    private ServerInteractionHelper mServer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mResort = PrefUtils.getSavedResort(this);
        setupNavigation();
        setContentView(R.layout.main);
        mWebcamList = (WebcamsListFragment) getSupportFragmentManager().findFragmentById(R.id.webcams_fragment);
        mMeteoFragment= (MeteoFragment) getSupportFragmentManager().findFragmentById(R.id.meteo_fragment);
        mServer = ServerInteractionHelper.getInstance(this);

        getSupportLoaderManager().initLoader(0, null, this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mServer.registerEventListener(this, this);
        getMeteoResort();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mServer.unregisterEventListener(this, this);
    }


    private void getMeteoResort(){
        // A little twisted here, gotta think about overriding sendRestAction with array of actions

        if(!mServer.isRequestAlreadyPending(METEO_REQUEST) && !meteoRequestDoneRecently()){
            MeteoDownloadAction[] actions = new MeteoDownloadAction[mResorts.length - 1];
            MeteoDownloadAction firstAction = new MeteoDownloadAction(mResorts[0]);
            for(int i = 1; i < mResorts.length; i++){
                actions[i-1] = new MeteoDownloadAction(mResorts[i]);
                i++;
            }
            try {
                mServer.sendRestAction(this, METEO_REQUEST, firstAction, actions);
            } catch (SendingCommandException e) {
                // TODO
            }
        }
    }



    private void setupNavigation(){
        mResorts = getResources().getStringArray(R.array.resorts);
        Context c = getSupportActionBar().getThemedContext();
        ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(c, R.array.resorts, R.layout.sherlock_spinner_item);
        list.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setListNavigationCallbacks(list, this);
        getSupportActionBar().setSelectedNavigationItem(mResort);
        getSupportActionBar().setTitle("AndAppennino    "); // To get some space

    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        mResort = itemPosition;
        getSupportLoaderManager().restartLoader(0, null, this);
        PrefUtils.setSelectedResort(mResort, this); // could be done on onstop only
        mMeteoFragment.update(mResorts[mResort]);
        getMeteoResort();
        return true;
    }


    public void onWebcamSelected(long lastUpdate, String url, String description, long id){
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra(DetailActivity.URL, url);
        i.putExtra(DetailActivity.TITLE, description);
        i.putExtra(DetailActivity.LAST_UPDATE, lastUpdate);
        i.putExtra(DetailActivity.WEBCAM_ID, id);
        startActivity(i);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = null;
        String where = null;
        String sortOrder = null;

        where = "Resort=?";
        String[] whereArgs = new String[]{mResorts[mResort]};

        // Query URI
        Uri queryUri = WebcamProvider.WEBCAM_URI;

        return new android.support.v4.content.CursorLoader(FirstActivity.this, queryUri, projection, where, whereArgs, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        mWebcamList.getAdapter().swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {
        mWebcamList.getAdapter().swapCursor(null);
    }

    @Override
    public void onServerResult(String result, String requestId) {
        if(requestId.equals(METEO_REQUEST)){
            mMeteoFragment.update(mResorts[mResort]);
        }
    }

    @Override
    public void onServerError(String result, String requestId) {
        Toast toast = Toast.makeText(this, getString(R.string.weather_error), Toast.LENGTH_SHORT);
        toast.show();
    }

    private boolean meteoRequestDoneRecently(){
        long lastUpdate = PrefUtils.getMeteoLastUpdate(this);
        Date d = new Date();
        if((d.getTime() - lastUpdate) > 1000 * 60 * 30){    // 30 minutes
            return false;
        }else{
            return true;
        }
    }

}



