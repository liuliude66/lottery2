package com.forum.lot.component.ui.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.forum.lot.R;
import com.forum.lot.adapter.LotteryHotGridAdapter;
import com.forum.lot.component.ui.base.BaseFragment;
import com.forum.lot.model.LotteryHotModel;
import com.forum.lot.view.grid.BorderGridView;
import com.forum.lot.view.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

/**
 * 格子型的首页 on 2018/1/24.
 */

public class HomeGridFragment extends BaseFragment {

    private MarqueeView mNoticeMqv;//公告通知栏
    private ListView mLuckyLView;//中奖列表

    private BorderGridView mBorderGridView;//热门彩种
    private LotteryHotGridAdapter mLotteryHotGridAdapter;//热门彩种适配器
    private List<LotteryHotModel> mLotteryHotModels;//热门彩种 渲染用数据

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_grid, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    protected void initView() {
        Activity activity = getActivity();
        mNoticeMqv = activity.findViewById(R.id.mqv_notice);
        mNoticeMqv.setText(getString(R.string.marque_text));
        mNoticeMqv.start();
        mLuckyLView = activity.findViewById(R.id.lv_lot_lucky);

        mBorderGridView = activity.findViewById(R.id.bgv_hot_lottery);
        mBorderGridView.setHorizontalBorderColor(Color.GRAY);
        mBorderGridView.setVerticalBorderColor(Color.GRAY);

        mLotteryHotModels = new ArrayList<>();
        mockLotteryHotModelData();
        mLotteryHotGridAdapter = new LotteryHotGridAdapter(getContext(), mLotteryHotModels);
        mBorderGridView.setAdapter(mLotteryHotGridAdapter);
    }


    private void mockLotteryHotModelData() {
        for (int i = 0; i < 16; i++) {
            LotteryHotModel item = new LotteryHotModel();
            item.lotteryIcon = R.mipmap.lottey_ah11x5;
            item.lotteryCountdown = "00:00:03";
            item.lotteryName = "安徽11选5";
            mLotteryHotModels.add(item);
        }
        LotteryHotModel item = new LotteryHotModel();
        item.lotteryIcon = R.mipmap.lottery_more;
        item.lotteryCountdown = "--:--:--";
        item.lotteryName = "更多";
        mLotteryHotModels.add(item);
    }
}
