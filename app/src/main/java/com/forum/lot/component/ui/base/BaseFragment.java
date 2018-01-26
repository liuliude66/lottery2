package com.forum.lot.component.ui.base;

import android.support.v4.app.Fragment;

/**
 * 所有Activity类的原始基类
 */

public abstract class BaseFragment extends Fragment {

    protected OnNotifyActivityListener mOnNotifyActivityListener;
    //用于通知activity的的监听器，即再fragment中做操作时，应当通知其宿主activity的行为
    public interface OnNotifyActivityListener {
        void executeNotify(int flag, String message, Object o);
    }

    public void setOnNotifyActivityListener(OnNotifyActivityListener onNotifyActivityListener){
        this.mOnNotifyActivityListener = onNotifyActivityListener;
    }

    protected void initView(){};

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
