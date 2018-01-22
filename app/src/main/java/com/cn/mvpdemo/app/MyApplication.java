package com.cn.mvpdemo.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by Nicky on 2017/12/11.
 */

public class MyApplication extends Application{


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
