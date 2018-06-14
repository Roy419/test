package com.example.testapp;

import android.app.Application;

import com.example.framework.net.NoHttpConfig;

/**
 * Created by Administrator on 2018/6/13.
 */

public class Myapp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NoHttpConfig.init(this);
    }
}
