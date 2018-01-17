package com.forum.lottery.ui;

import android.os.Bundle;
import android.util.Log;

import com.forum.lottery.R;
import com.forum.lottery.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        long t1 = System.currentTimeMillis();
        Log.i("MainActivity", "time1---->" + t1);
    }
}
