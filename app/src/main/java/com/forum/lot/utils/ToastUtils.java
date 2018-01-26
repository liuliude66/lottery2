package com.forum.lot.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 吐司辅助类 on 2018/1/27.
 */

public class ToastUtils {

    private static Toast mToast;

    public static void toast(Context context, int stringId) {
        if (mToast != null) {
            mToast.cancel();
            mToast.setText(stringId);
            mToast.show();
        } else {
            mToast = Toast.makeText(context, stringId, Toast.LENGTH_SHORT);
            mToast.show();
        }
    }

    public static void toast(Context context, String message) {
        if (mToast != null) {
            mToast.cancel();
            mToast.setText(message);
            mToast.show();
        } else {
            mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            mToast.show();
        }
    }
}
