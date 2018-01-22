package com.forum.lottery.component.ui.activity;

import android.os.Bundle;

import com.forum.lottery.R;
import com.forum.lottery.component.ui.base.BaseActivity;

import lottery.forum.com.netservicelib.LogUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.i("888time1--t1-t1->t1");
        LogUtils.i("888time1--t1-t1->t1");
        long t1 = System.currentTimeMillis();
    }
}
