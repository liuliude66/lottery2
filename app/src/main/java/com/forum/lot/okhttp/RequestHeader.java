package com.forum.lot.okhttp;

/**
 * 网络请求头
 */

public class RequestHeader {

    public String sessionID = "64318d51e4f1c34e5fd82bb3158c15e1";   //"sessionid":"64318d51e4f1c34e5fd82bb3158c15e1"
    public String xRequestedWith = "64318d51e4f1c34e5fd82bb3158"; // 设置为 手机的唯一标识符号 IMEI
    public String userAgent = "SSC/2.0.0 (Android; " + android.os.Build.MODEL + ")";    //user-agent -- 机型
    public String contentType = "application/json;charset:utf-8";

    private static RequestHeader mInstance;

    private RequestHeader() {

    }

    public static RequestHeader getInstance() {
        if (mInstance == null) {
            synchronized (OkHttpClientManager.class) {
                if (mInstance == null) {
                    mInstance = new RequestHeader();
                }
            }
        }
        return mInstance;
    }
}
