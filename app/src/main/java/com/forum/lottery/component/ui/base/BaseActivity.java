package com.forum.lottery.component.ui.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.forum.lottery.component.service.HeartBeatService;

/**
 * 所有Activity类的原始基类
 */

public abstract class BaseActivity extends Activity {

    public static final String NetworkKey = "NetworkKey";
    public static final int NetworkStatusCode = 0x000001;

    //网络检测的心跳包
    protected Handler mNetWorkHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.getData().getInt(NetworkKey)){
                case NetworkStatusCode:
                    notifyNetworkStatusChange();
                    break;
            }
            return false;
        }
    });

    //通用通知连接器
    protected Handler mCommonHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            return false;
        }
    });

    //通知网络状态变化的行为变更
    private void notifyNetworkStatusChange(){
        executeNetworkStatusChange();
    }

    //执行网络状态变化的行为变更行为
    protected void executeNetworkStatusChange(){

    }

    //启动心跳监听程序
    protected void startHearBeatService(){
        startService(new Intent(this, HeartBeatService.class));
    }

    protected void stopHeartBeatService(){
        stopService(new Intent(this, HeartBeatService.class));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存其状态，避免activity因系统回收导致返回该界面时，无法“恢复原始状态”
        saveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onPause() {
        super.onPause();
        pauseActivityAction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyCurActivityResources();
    }

    //保存其状态，避免activity因系统回收导致返回该界面时，无法“恢复原始状态”
    protected void saveInstanceState(){

    }

    //终止跳转时的操作行为
    protected void pauseActivityAction(){

    }

    //结束本activity时的销毁资源的行为
    protected void destroyCurActivityResources(){
        //终止所有的计时器
        terminalAllTimer();
        //终止所有的自定义服务
        terminalAllSelfService();
    }

    protected void terminalAllTimer(){}
    protected void terminalAllSelfService(){
        stopHeartBeatService();

    }
}
