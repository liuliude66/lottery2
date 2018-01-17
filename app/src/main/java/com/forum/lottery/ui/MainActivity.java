package com.forum.lottery.ui;

import android.os.Bundle;

import com.forum.lottery.R;
import com.forum.lottery.ui.base.BaseActivity;
import com.forum.lottery.utils.LogUtils;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtils.i("888time1--t1-t1->t1");
        long t1 = System.currentTimeMillis();

        LogUtils.i("666time1--t1-t1->t1" + t1);
        LogUtils.i("699time1--t1-t1->t1" + t1);
        LogUtils.i("123dev-time1--t1-t1->t1" + t1);
        LogUtils.i("dev-23dev-time1--t1-t1->t1" + t1);
    }
}
