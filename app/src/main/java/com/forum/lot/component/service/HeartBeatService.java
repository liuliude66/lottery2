package com.forum.lot.component.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import com.forum.lot.utils.ParameterUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 心跳监听包
 **/
public class HeartBeatService extends Service {

    private Timer messagesAndNoticesHeartBeatTimer = null;
    private TimerTask messagesAndNoticesHearBeatTimeTask = null;

    private Context mContext;
    private Handler mHandler;

    public HeartBeatService() {

    }

    public HeartBeatService(Context context) {
        this.mContext = context;
    }

    public HeartBeatService(Context context, Handler handler) {
        this.mContext = context;
        this.mHandler = handler;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //开启一个计时器，每次间隔10秒钟请求一次网络数据，主要面对的功能有（通告（普通、维护）、客服消息）
        startMessagesAndNoticesTimerTask();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        terminalMessagesAndNoticesTimerTask();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * 终止计时器操作
     */
    private void terminalMessagesAndNoticesTimerTask() {
        if (messagesAndNoticesHearBeatTimeTask != null) {
            messagesAndNoticesHearBeatTimeTask.cancel();
            messagesAndNoticesHearBeatTimeTask = null;
        }
        if (messagesAndNoticesHeartBeatTimer != null) {
            messagesAndNoticesHeartBeatTimer.cancel();
            messagesAndNoticesHeartBeatTimer = null;
        }
    }

    /**
     * 启动计时器操作
     */
    private void startMessagesAndNoticesTimerTask() {
        if (messagesAndNoticesHeartBeatTimer == null) {
            messagesAndNoticesHeartBeatTimer = new Timer();
        }
        if (messagesAndNoticesHearBeatTimeTask == null) {
            messagesAndNoticesHearBeatTimeTask = new TimerTask() {
                @Override
                public void run() {
                    //执行网络请求（通告（普通、维护）、客服消息）；
                    execNetRequestCommonInfo();
                }
            };
        }
        messagesAndNoticesHeartBeatTimer.schedule(messagesAndNoticesHearBeatTimeTask, ParameterUtils.GlobalConfig.HeatBeatMsgAndNoticesDelay, ParameterUtils.GlobalConfig.HeatBeatMsgAndNoticesInterval);
    }

    /**
     * 执行网络请求（通告（普通、维护）、客服消息）
     */
    private void execNetRequestCommonInfo() {
        String url = ParameterUtils.DOMAIN.NORMAL_DOMAIN + ParameterUtils.URLS.HEART_BEAT_URL;
        //封装okhttp 的服务

    }
}
