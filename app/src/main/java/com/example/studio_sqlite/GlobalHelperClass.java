package com.example.studio_sqlite;

import android.app.Application;
import android.content.Context;

public class GlobalHelperClass extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }


}
