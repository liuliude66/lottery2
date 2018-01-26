package com.forum.lot.component.ui.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.forum.lot.utils.ParameterUtils;

/**
 * 第二层常用的基本操作.
 */

public abstract class BaseAbUIActivity extends BaseActivity {

    //初始化控件
    protected abstract void initView();

    //UI沟通handler
    protected final Handler mBridgeHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.getData().getInt(ParameterUtils.CODES.BRIDGE_CODE)){
                case ParameterUtils.CODES.OBTAIN_SESSION_SUCCESS_CODE:
                    obtainSessionSuccessAction();
                    break;
                case ParameterUtils.CODES.OBTAIN_SESSION_FAILURE_CODE:
                    obtainSessionFailureAction();
                    break;
                case ParameterUtils.CODES.OBTAIN_SESSION_MESSAGE_CODE:
                    obtainMessageAction(message.getData().getString(ParameterUtils.CODES.MESSAGE_CODE));
                    break;
                case ParameterUtils.CODES.NO_JSON_CODE:
                    noReturnValueNotJsonAction();
                    break;
            }
            return false;
        }
    });

    protected void obtainSessionSuccessAction(){}
    protected void obtainSessionFailureAction(){}
    protected void obtainMessageAction(String message){}
    protected void noReturnValueNotJsonAction(){}

    protected BaseClickListener mBaseClickListener = new BaseClickListener();

    private final class BaseClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){

            }
        }
    }

    protected void sendMessageToHandler(int code){
        Message msg = mBridgeHandler.obtainMessage();
        Bundle data = msg.getData();
        data.putInt(ParameterUtils.CODES.BRIDGE_CODE, code);
        msg.setData(data);
        mBridgeHandler.sendMessage(msg);
    }

    protected void sendMessageToHandler(int code, String message){
        Message msg = mBridgeHandler.obtainMessage();
        Bundle data = msg.getData();
        data.putInt(ParameterUtils.CODES.BRIDGE_CODE, code);
        data.putString(ParameterUtils.CODES.MESSAGE_CODE, message);
        msg.setData(data);
        mBridgeHandler.sendMessage(msg);
    }
}
