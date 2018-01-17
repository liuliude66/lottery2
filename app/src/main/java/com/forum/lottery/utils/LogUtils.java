package com.forum.lottery.utils;

import android.util.Log;

/**
 * Log 打印日志
 */

public class LogUtils {

    private static final String TAG = "Lottery2";
    private static final boolean isDebug = true;

    public static void i(String message){
        if (isDebug){
            Log.i(TAG, message);
        }
    }

    public static void e(String message){
        if (isDebug){
            Log.e(TAG, message);
        }
    }

    public static void w(String message){
        if (isDebug){
            Log.w(TAG, message);
        }
    }
}
