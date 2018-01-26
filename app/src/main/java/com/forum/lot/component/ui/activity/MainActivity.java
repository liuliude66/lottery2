package com.forum.lot.component.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.forum.lot.R;
import com.forum.lot.adapter.MainFragmentPagerAdapter;
import com.forum.lot.component.ui.base.BaseAbUIActivity;
import com.forum.lot.component.ui.fragments.HomeGridFragment;
import com.forum.lot.component.ui.fragments.LotteryGridFragment;
import com.forum.lot.component.ui.fragments.LotteryListFragment;
import com.forum.lot.component.ui.fragments.MatchFragment;
import com.forum.lot.component.ui.fragments.PersonFragment;
import com.forum.lot.utils.LogUtils;
import com.forum.lot.utils.PixelUtils;
import com.forum.lot.view.layout.SingleLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseAbUIActivity {

    private List<Fragment> mFragments;
    private ViewPager mFragmentsViewPager;
    private MainFragmentPagerAdapter mFragmentPagerAdapter;

    private FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction = null;
    private Fragment mCurrentPrimaryItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initFragments();

        initBaseConfig();
    }

    private void initFragments(){
        mFragments = new ArrayList<>();
        mFragments.add(new HomeGridFragment());
        mFragments.add(new LotteryGridFragment());
        mFragments.add(new MatchFragment());
        mFragments.add(new LotteryListFragment());
        mFragments.add(new PersonFragment());

        mFragmentManager = getSupportFragmentManager();
        mFragmentPagerAdapter = new MainFragmentPagerAdapter(mFragmentManager, mFragments);
        mFragmentsViewPager.setAdapter(mFragmentPagerAdapter);
        mFragmentsViewPager.setCurrentItem(0);
        mCurTransaction = mFragmentManager.beginTransaction();
        mCurTransaction.commit();

        mFragmentsViewPager.addOnPageChangeListener(new FragmentPageChangeListener());
    }

    /**
     * 初始化基本配置
     **/
    private void initBaseConfig() {
        new Thread(new TimeRunnable()).start();
        startNetworkRegisterReceiver();
    }

    @Override
    protected void executeNetworkStatusChange(boolean status) {
        Toast.makeText(this, "now the net is " + status, Toast.LENGTH_SHORT).show();
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

    @Override
    protected void initView() {
        mFragmentsViewPager = findViewById(R.id.fg_container);
    }

    private final class FragmentPageChangeListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            LogUtils.debug("message--------------->onPageScrolled (position, positionOffset, positionOffsetPixels)-->(" + position + "," + positionOffset + "," + positionOffsetPixels + ")");
        }

        @Override
        public void onPageSelected(int position) {
            LogUtils.debug("message--------------->onPageSelected position-->(" + position + ")");
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            LogUtils.debug("message--------------->onPageSelected state-->(" + state + ")");
        }
    }

    private SingleLayout mSingleLayout;
    private final class FSubViewClickListener implements SingleLayout.SubViewClickListener {
        @Override
        public void onClick(View view) {
            Toast.makeText(MainActivity.this, ((Button) view).getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void mockSingleLayout(){
        mSingleLayout = findViewById(R.id.sl_dynamic);
        mSingleLayout.setSubViewClickListener(new FSubViewClickListener());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(PixelUtils.dip2px(MainActivity.this, 100), PixelUtils.dip2px(MainActivity.this, 45));
        int margin = PixelUtils.dip2px(MainActivity.this, 15);
        lp.setMargins(margin, 10, margin, 10);
        for (int i = 0; i < 8; i++) {
            Button btn = new Button(mSingleLayout.getContext());
            btn.setBackgroundResource(R.drawable.btn_shape_sel);
            btn.setText(getString(R.string.test_btn));
            btn.setGravity(Gravity.CENTER);
            mSingleLayout.addView(btn, lp);
        }
    }
}
