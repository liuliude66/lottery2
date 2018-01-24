package com.forum.lottery.utils;

import android.util.Log;

/**
 * log打印机
 */

public class LogUtils {

    private static final boolean isDebug = true;
    private static final String TAG = "LogUtils";

    public static void debug(String message){
        if (isDebug){
            Log.d(TAG, message);
        }
    }

    public static void info(String message){
        if (isDebug){
            Log.i(TAG, message);
        }
    }

    public static void warn(String message){
        if (isDebug){
            Log.w(TAG, message);
        }
    }

    public static void error(String message){
        if (isDebug){
            Log.e(TAG, message);
        }
    }
}
