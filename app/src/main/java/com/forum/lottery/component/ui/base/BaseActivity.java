package com.forum.lottery.component.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.forum.lottery.component.service.HeartBeatService;

/**
 * 所有Activity类的初始基类
 */

public abstract class BaseActivity extends Activity {

    //网络检测的心跳包
    protected Handler mNetWorkHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            return false;
        }
    });

    //启动心跳监听程序
    protected void startHearBeatService(){
        startService(new Intent(this, HeartBeatService.class));
    }


}
