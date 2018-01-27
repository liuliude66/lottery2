package com.forum.lot.component.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.forum.lot.R;
import com.forum.lot.component.ui.base.BaseAbUIActivity;
import com.forum.lot.entity.ResultEntity;
import com.forum.lot.okhttp.RequestHeader;
import com.forum.lot.okhttp.OkHttpClientManager;
import com.forum.lot.utils.JsonUtils;
import com.forum.lot.utils.LogUtils;
import com.forum.lot.utils.ParameterUtils;
import com.forum.lot.utils.SharedPreferenceUtils;
import com.forum.lot.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Request;

public class LoginActivity extends BaseAbUIActivity {

    private EditText mUsernameEt, mPasswordEt;
    private String mUsername, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        initBaseConfig();

    }

    private void requestLogin(){
        getSessionValidate();
    }

    private void getSessionValidate(){
        OkHttpClientManager.getInstance().asyncPost(ParameterUtils.URLS.SESSION_URL, "", new OkHttpClientManager.HttpCallBack() {
            @Override
            public void onError(Request request, IOException e) {
                LogUtils.debug("message--------------->" + e.getMessage());
                sendMessageToHandler(ParameterUtils.CODES.OBTAIN_SESSION_FAILURE_CODE);
            }

            @Override
            public void onSuccess(Request request, String result) {
                LogUtils.debug("message--------------->result" + result);
                Gson gson = new Gson();
                ResultEntity<JsonObject> entity = gson.fromJson(result, new TypeToken<ResultEntity<JsonObject>>(){}.getType());
                LogUtils.debug("message--------------->entity.code-->" + entity.code);
                if (entity.code == 0){
                    JsonObject data = entity.data;
                    if (!JsonUtils.isJson(data.toString())){
                        sendMessageToHandler(ParameterUtils.CODES.NO_JSON_CODE);
                        return;
                    }
                    LogUtils.debug("message--------------->data-->" + data);
                    RequestHeader.getInstance().sessionID = data.get("sessionid").getAsString();
                    long expiryTime = data.get("expiryTime").getAsLong();
                    sendMessageToHandler(ParameterUtils.CODES.OBTAIN_SESSION_SUCCESS_CODE);
                } else {
                    sendMessageToHandler(ParameterUtils.CODES.OBTAIN_SESSION_MESSAGE_CODE, entity.msg);
                }
            }
        });
    }

    @Override
    protected void obtainSessionFailureAction() {

    }

    @Override
    protected void obtainSessionSuccessAction() {
        try {
            //执行登录操作
            JSONObject obj = new JSONObject();
            obj.put("account", "lilei006");
            obj.put("password", "000000");
            String parameter = obj.toString();
            OkHttpClientManager.getInstance().asyncPost(ParameterUtils.URLS.LOGIN_URL, parameter, new OkHttpClientManager.HttpCallBack() {
                @Override
                public void onError(Request request, IOException e) {
                    LogUtils.debug("message--------------->" + e.getMessage());
                    sendMessageToHandler(ParameterUtils.CODES.LOGIN_FAILURE_CODE);
                }

                @Override
                public void onSuccess(Request request, String result) {
                    LogUtils.debug("message--------------->result" + result);
                    Gson gson = new Gson();
                    ResultEntity<JsonObject> entity = gson.fromJson(result, new TypeToken<ResultEntity<JsonObject>>(){}.getType());
                    if (entity.code == 0){
                        JsonObject data = entity.data;
                        /*if (!JsonUtils.isJson(data.toString())){
                            sendMessageToHandler(ParameterUtils.CODES.NO_JSON_CODE);
                            return;
                        }*/
                        SharedPreferenceUtils.putInfo(getBaseContext(), SharedPreferenceUtils.LOGIN_AUTHOR_USERID, String.valueOf(data.get("userId").getAsInt()));
                        SharedPreferenceUtils.putInfo(getBaseContext(), SharedPreferenceUtils.LOGIN_AUTHOR_USERID, data.get("account").getAsString());
                        sendMessageToHandler(ParameterUtils.CODES.LOGIN_SUCCESS_CODE);
                    } else {
                        sendMessageToHandler(ParameterUtils.CODES.LOGIN_MESSAGE_CODE, entity.msg);
                    }
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void handleLoginSuccess(){
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void obtainMessageAction(String message){

    }

    @Override
    protected void noReturnValueNotJsonAction(){
        ToastUtils.toast(this, "data not json, please try again...");
    }

    /**
     * 初始化基本配置
     **/
    private void initBaseConfig() {
        startNetworkRegisterReceiver();
    }

    @Override
    protected void executeNetworkStatusChange(boolean status) {
        Toast.makeText(this, "now the net is " + status, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void loginAction() {
        requestLogin();
    }

    @Override
    protected void initView() {
        mUsernameEt = findViewById(R.id.et_login_username);
        mPasswordEt = findViewById(R.id.et_login_password);
        findViewById(R.id.btn_login).setOnClickListener(mBaseClickListener);
    }
}
