package com.coolweather.smtq;

import android.content.Context;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;


/**
 * Created by liyu on 2016/11/2.
 */

public class App extends LitePalApplication {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        LitePal.initialize(this);
        if (!BuildConfig.DEBUG) {
            AppExceptionHandler.getInstance().setCrashHanler(this);
        }

    }

    public static Context getContext() {
        return mContext;
    }

}
