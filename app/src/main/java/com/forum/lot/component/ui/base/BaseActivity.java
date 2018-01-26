package com.forum.lot.component.ui.base;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;

import com.forum.lot.component.broadcast.NetBroadcastReceiver;
import com.forum.lot.component.service.HeartBeatService;

/**
 * 所有Activity类的原始基类
 */

public abstract class BaseActivity extends FragmentActivity implements NetBroadcastReceiver.NetEvent {

    public static final String NetworkKey = "NetworkKey";
    public static final int NetworkStatusCode = 0x000001;

    private NetBroadcastReceiver mNetworkBroadcastReceiver;

    /**
     * 通用通知连接器
     */
    protected Handler mCommonHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            return false;
        }
    });

    /**
     * 通知网络状态变化的行为变更
     */
    private void notifyNetworkStatusChange(boolean status) {
        executeNetworkStatusChange(status);
    }

    /**
     * 注册网络状态的广播
     */
    protected void startNetworkRegisterReceiver() {
        if (mNetworkBroadcastReceiver == null) {
            mNetworkBroadcastReceiver = new NetBroadcastReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(mNetworkBroadcastReceiver, filter);
            mNetworkBroadcastReceiver.setNetEvent(this);
        }
    }

    /**
     * 执行网络状态变化的行为变更行为
     */
    protected void executeNetworkStatusChange(boolean status) {

    }

    /**
     * 启动心跳监听程序
     */
    protected void startHearBeatService() {
        startService(new Intent(this, HeartBeatService.class));
    }

    /**
     * 终止心跳监听程序
     */
    protected void stopHeartBeatService() {
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

    /**
     * 保存其状态，避免activity因系统回收导致返回该界面时，无法“恢复原始状态”
     */
    protected void saveInstanceState() {

    }

    /**
     * 终止跳转时的操作行为
     */
    protected void pauseActivityAction() {

    }

    /**
     * 结束本activity时的销毁资源的行为
     */
    protected void destroyCurActivityResources() {
        //终止所有的计时器
        terminalAllTimer();
        //终止所有的自定义服务
        terminalAllSelfService();
        //终止广播
        unregisterReceivers();
    }

    /**
     * 结束所有的时间计时器
     */
    protected void terminalAllTimer() {

    }

    /**
     * 终止所有的自定义服务
     */
    protected void terminalAllSelfService() {
        stopHeartBeatService();
    }

    /**
     * 终止所有的自定义广播器
     */
    protected void unregisterReceivers() {

    }

    /**
     * @description 网络变化时候的回调通知
     * @param status boolean 网络当前状态
     */
    public void onNetChange(boolean status) {
        notifyNetworkStatusChange(status);
    }

}
