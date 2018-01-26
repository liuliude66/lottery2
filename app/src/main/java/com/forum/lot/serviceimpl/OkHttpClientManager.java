package com.forum.lot.serviceimpl;

import android.os.Handler;
import android.os.Looper;

import com.forum.lot.okhttp.RequestHeader;
import com.forum.lot.utils.ParameterUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * OkHttp的封装管理器
 */

public class OkHttpClientManager {

    private static OkHttpClientManager mOkHttpClientManager;
    private OkHttpClient okHttpClient;
    private Handler handler;

    private OkHttpClientManager() {
        okHttpClient = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
    }

    public static OkHttpClientManager getInstance() {
        if (mOkHttpClientManager == null) {
            synchronized (OkHttpClientManager.class) {
                if (mOkHttpClientManager == null) {
                    mOkHttpClientManager = new OkHttpClientManager();
                }
            }
        }
        return mOkHttpClientManager;
    }

    /***
     * 对外接口 Get 请求
     * @param url string 请求网址
     * @param httpCallBack HttpCallBack 异步回调通知接口
     **/
    public void asyncGet(String url, HttpCallBack httpCallBack) {
        RequestHeader header = RequestHeader.getInstance();
        Request request = new Request.Builder().url(url)
                .addHeader("content-type", header.contentType)
                .addHeader("user-agent", header.userAgent)
                .addHeader("x-requested-with", header.xRequestedWith)
                .addHeader("sessionid", header.sessionID).build();
        okHttpClient.newCall(request).enqueue(new StringCallBack(request, httpCallBack));
    }

    /**
     * 对外接口 Post 请求
     *
     * @param url          string 请求网址
     * @param httpCallBack HttpCallBack 异步回调通知接口
     * @param body         String 请求参数
     **/
    public void asyncPost(String url, String body, HttpCallBack httpCallBack) {
        String formatUrl = ParameterUtils.DOMAIN.NORMAL_DOMAIN + url;
        RequestHeader header = RequestHeader.getInstance();
        Request request = new Request.Builder().url(formatUrl)
                .addHeader("content-type", header.contentType)
                .addHeader("user-agent", header.userAgent)
                .addHeader("x-requested-with", header.xRequestedWith)
                .addHeader("sessionid", header.sessionID)
                .method("POST", RequestBody.create(MediaType.parse("application/json; charset=utf-8"), body))
                .build();
        okHttpClient.newCall(request).enqueue(new StringCallBack(request, httpCallBack));
    }

    class StringCallBack implements Callback {

        private HttpCallBack httpCallBack;
        private Request request;

        public StringCallBack(Request request, HttpCallBack httpCallBack) {
            this.request = request;
            this.httpCallBack = httpCallBack;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            final IOException fe = e;
            if (httpCallBack != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpCallBack.onError(request, fe);
                    }
                });
            }
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            final String result = response.body().string();
            if (httpCallBack != null) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        httpCallBack.onSuccess(request, result);
                    }
                });
            }
        }
    }

    public interface HttpCallBack {
        //错误情况下的回调通知
        void onError(Request request, IOException e);

        //成功情况下的回调通知
        void onSuccess(Request request, String result);
    }
}
