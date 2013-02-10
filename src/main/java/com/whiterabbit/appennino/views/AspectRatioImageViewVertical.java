package com.whiterabbit.appennino.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import uk.co.senab.bitmapcache.CacheableImageView;

public class AspectRatioImageViewVertical extends CacheableImageView {

    public AspectRatioImageViewVertical(Context context) {
        super(context);
    }

    public AspectRatioImageViewVertical(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.getSize(heightMeasureSpec);
        Drawable d = getDrawable();
        if(d != null){
            int width = height * d.getIntrinsicWidth() / d.getIntrinsicHeight();
            setMeasuredDimension(width, height);
        }else{
            setMeasuredDimension(0, 0);
        }
    }
}


