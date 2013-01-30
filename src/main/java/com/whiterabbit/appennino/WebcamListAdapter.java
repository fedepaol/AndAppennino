package com.whiterabbit.appennino;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Adapter class to show webcam list. In case of already loaded image it will show a thumbnail
 */
public class WebcamListAdapter extends CursorAdapter {
    final Context mContext;
    private final LayoutInflater mInflater;

    public WebcamListAdapter(Context context, Cursor c) {
        super(context, c, true);
        mContext = context;
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    protected synchronized void onContentChanged() {
        super.onContentChanged();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Reset the view (in case it was recycled) and prepare for binding


    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // todo inflate
        return null;
    }

}
