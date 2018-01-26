package com.forum.lot.component.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.forum.lot.utils.NetWorkUtils;

/**
 * 网络状态监听广播器
 **/
public class NetBroadcastReceiver extends BroadcastReceiver {

    private NetEvent netEvent;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            //检查网络状态的类型
            boolean networkState = NetWorkUtils.checkWorkAvailable(context);
            if (netEvent != null) {// 接口回传网络状态的类型
                netEvent.onNetChange(networkState);
            }
        }
    }

    public void setNetEvent(NetEvent netEvent) {
        this.netEvent = netEvent;
    }

    /**
     * 网络状态通知回调接口
     */
    public interface NetEvent {
        void onNetChange(boolean status);
    }
}
