package com.forum.lottery.application;

import android.app.Application;

import java.util.TimeZone;

/**
 * Created by DELL on 2018/1/17.
 */

public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TimeZone chinaTimeZone = TimeZone.getTimeZone("GMT+8");
        TimeZone.setDefault(chinaTimeZone);
    }

}
