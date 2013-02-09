package com.whiterabbit.appennino.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import uk.co.senab.bitmapcache.CacheableImageView;

public class AspectRatioImageView extends CacheableImageView {

    public AspectRatioImageView(Context context) {
        super(context);
    }

    public AspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        Drawable d = getDrawable();
        if(d != null){
            int height = width * d.getIntrinsicHeight() / d.getIntrinsicWidth();
            setMeasuredDimension(width, height);
        }else{
            setMeasuredDimension(0, 0);
        }
    }
}


