package com.whiterabbit.appennino.com.whiterabbit.appennino;

import android.app.Application;
import android.os.Environment;
import uk.co.senab.bitmapcache.BitmapLruCache;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: fedepaol
 * Date: 2/3/13
 * Time: 1:09 PM
 */
public class AppenninoApplication extends Application {
    private BitmapLruCache mCache;
    private static AppenninoApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        File cacheLocation = new File(Environment.getExternalStorageDirectory() + "/Android/data/com.whiterabbit.appennino/files/");
        cacheLocation.mkdirs();

        BitmapLruCache.Builder builder = new BitmapLruCache.Builder();
        builder.setMemoryCacheEnabled(true).setMemoryCacheMaxSizeUsingHeapSize();
        builder.setDiskCacheEnabled(true).setDiskCacheLocation(cacheLocation);

        mCache = builder.build();
        mInstance = this;
    }

    public BitmapLruCache getBitmapCache() {
        return mCache;
    }

    public static AppenninoApplication getApplication(){
        return mInstance;
    }


    @Override
    public void onTrimMemory(int level){
        super.onTrimMemory(level);
        if(level>=TRIM_MEMORY_MODERATE){//60
            mCache.trimMemory();
        }else if(level>=TRIM_MEMORY_BACKGROUND){//40
            // TODO
        }
    }
}
