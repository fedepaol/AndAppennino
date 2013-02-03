package com.whiterabbit.appennino;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.whiterabbit.appennino.com.whiterabbit.appennino.data.WebcamProvider;

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
        TextView description = (TextView) view.findViewById(R.id.webcam_elem_description);
        TextView lastUpdate = (TextView) view.findViewById(R.id.webcam_elem_last_update);

        description.setText(cursor.getString(WebcamProvider.WEBCAM_DESCRIPTION_COLUMN_POSITION));
        long lUpdate = cursor.getLong(WebcamProvider.WEBCAM_LASTUPDATE_COLUMN_POSITION);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return mInflater.inflate(R.layout.webcam_list_elem, parent, false);
    }

}
