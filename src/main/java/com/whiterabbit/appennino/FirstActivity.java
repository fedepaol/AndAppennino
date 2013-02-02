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

public class FirstActivity extends SherlockFragmentActivity implements ActionBar.OnNavigationListener, LoaderManager.LoaderCallbacks<Cursor> {

    private static String TAG = "AndAppennino";
    private String[] mResorts;
    private WebcamsListFragment mWebcamList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupNavigation();
        setContentView(R.layout.main);
        getSupportLoaderManager().initLoader(0, null, this);
        mWebcamList = (WebcamsListFragment) getSupportFragmentManager().findFragmentById(R.id.webcams_fragment);
    }


    private void setupNavigation(){
        mResorts = getResources().getStringArray(R.array.resorts);
        Context c = getSupportActionBar().getThemedContext();
        ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(c, R.array.resorts, R.layout.sherlock_spinner_item);
        list.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setListNavigationCallbacks(list, this);

    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        return true;
    }


    public void onWebcamSelected(String fileName, String url){
        Intent i = new Intent(this, DetailView.class);
        startActivity(i);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = null;
        String where = null;
        String[] whereArgs = null;
        String sortOrder = null;

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



