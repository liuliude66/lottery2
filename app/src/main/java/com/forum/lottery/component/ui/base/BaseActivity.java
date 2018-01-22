package com.forum.lottery.component.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.forum.lottery.component.service.HeartBeatService;

/**
 * 所有Activity类的原始基类
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

    //网络通用的通知连接器
    protected Handler mCommonHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            return false;
        }
    });

    @Override
    protected void onPause() {
        super.onPause();
        pauseActivityAction();
    }

    //终止跳转时的操作行为
    protected void pauseActivityAction(){}
}
