package com.forum.lottery.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * xml 暂存器
 */

public class SharedPreferenceUtils {

    private static final String SharedPreferXml = "LotCasio";

    public static final String NETWORK_STATUS = "NETWORK_STATUS";

    public static void putBoolean(Context context, String key, boolean value){
        SharedPreferences sp = context.getSharedPreferences(SharedPreferXml, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue){
        SharedPreferences sp = context.getSharedPreferences(SharedPreferXml, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static void putInfo(Context context, String key, String value){
        SharedPreferences sp = context.getSharedPreferences(SharedPreferXml, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    public static String getInfo(Context context, String key, String defValue){
        SharedPreferences sp = context.getSharedPreferences(SharedPreferXml, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static void putLong(Context context, String key, long value){
        SharedPreferences sp = context.getSharedPreferences(SharedPreferXml, Context.MODE_PRIVATE);
        sp.edit().putLong(key, value).apply();
    }

    public static long getLong(Context context, String key, long defValue){
        SharedPreferences sp = context.getSharedPreferences(SharedPreferXml, Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }
}
