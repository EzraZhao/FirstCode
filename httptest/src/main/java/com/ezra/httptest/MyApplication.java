package com.ezra.httptest;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ezra on 2017/3/5.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
