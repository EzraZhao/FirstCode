package com.ezra.litepaltest;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.ActionBar;

import org.litepal.LitePalApplication;

/**
 * Created by Ezra on 2017/3/5.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
        LitePalApplication.initialize(context);
    }

    public static Context getContext() {
        return context;
    }
}
