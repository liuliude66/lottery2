package com.forum.lottery.component.ui.dialog;

import android.app.Dialog;
import android.content.Context;

/**
 * 自定义常用的dialog，包含按钮（确定，取消）， on 2018/1/22.
 */

public class CommonDialog extends Dialog {

    public CommonDialog(Context context) {
        super(context);
    }

    public CommonDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
}
