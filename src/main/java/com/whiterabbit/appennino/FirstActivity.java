package com.whiterabbit.appennino;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.widget.ArrayAdapter;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.whiterabbit.appennino.com.whiterabbit.appennino.data.WebcamProvider;
import com.whiterabbit.appennino.utils.PrefUtils;

public class FirstActivity extends SherlockFragmentActivity implements ActionBar.OnNavigationListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static String TAG = "AndAppennino";
    private String[] mResorts;
    private WebcamsListFragment mWebcamList;
    private int mResort;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mResort = PrefUtils.getSavedResort(this);
        setupNavigation();
        setContentView(R.layout.main);
        mWebcamList = (WebcamsListFragment) getSupportFragmentManager().findFragmentById(R.id.webcams_fragment);

    }


    private void setupNavigation(){
        mResorts = getResources().getStringArray(R.array.resorts);
        Context c = getSupportActionBar().getThemedContext();
        ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(c, R.array.resorts, R.layout.sherlock_spinner_item);
        list.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setListNavigationCallbacks(list, this);
        getSupportActionBar().setSelectedNavigationItem(mResort);

    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        mResort = itemPosition;
        getSupportLoaderManager().restartLoader(0, null, this);
        PrefUtils.setSelectedResort(mResort, this); // could be done on onstop only
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

        String[] whereArgs = null;
        if(mResort > 0){
            where = "Resort=?";
            whereArgs = new String[]{mResorts[mResort]};
        }

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
}



