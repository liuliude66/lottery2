package com.forum.lot.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 有关本地网络的相关辅助操作
 */

public class NetWorkUtils {
    /**
     * @param context 上下文环境
     * @return boolean 返回当前的网络状态
     * @description 检测本机的网络是否可用
     */
    public static boolean checkWorkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        NetworkInfo networkinfo = manager.getActiveNetworkInfo();
        if (networkinfo == null || !networkinfo.isAvailable()) {
            return false;
        }
        return true;
    }
}
