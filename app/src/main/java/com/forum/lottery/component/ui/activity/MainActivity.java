package com.forum.lottery.component.ui.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.forum.lottery.R;
import com.forum.lottery.component.ui.base.BaseActivity;
import com.forum.lottery.utils.PixelUtils;
import com.forum.lottery.view.layout.SingleLayout;

public class MainActivity extends BaseActivity {

    private SingleLayout mSingleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new TimeRunnable()).start();

        initView();
    }

    private final class TimeRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                long curt = System.currentTimeMillis();
                //LogUtils.warn("message--------------->current time--->" + curt);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initView() {
        mSingleLayout = findViewById(R.id.sl_dynamic);
        mSingleLayout.setSubViewClickListener(new FSubViewClickListener());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(PixelUtils.dip2px(MainActivity.this, 100), PixelUtils.dip2px(MainActivity.this, 45));
        int margin = PixelUtils.dip2px(MainActivity.this, 15);
        lp.setMargins(margin, 10, margin, 10);
        for (int i = 0; i < 8; i++) {
            Button btn = new Button(mSingleLayout.getContext());
            btn.setBackgroundResource(R.drawable.btn_shape_sel);
            btn.setText(getString(R.string.test_btn) + i);
            btn.setGravity(Gravity.CENTER);
            //btn.setLayoutDirection();
            //btn.setLayoutParams(lp);
            mSingleLayout.addView(btn, lp);
        }
    }

    private final class FSubViewClickListener implements SingleLayout.SubViewClickListener {
        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, ((Button) view).getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
