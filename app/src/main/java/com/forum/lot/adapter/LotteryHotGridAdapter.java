package com.forum.lot.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.forum.lot.R;
import com.forum.lot.model.LotteryHotModel;

import java.util.List;

/**
 * 首页中的热门彩种适配器 2018/1/25.
 */

public class LotteryHotGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<LotteryHotModel> mLotteryHotModelList;

    public LotteryHotGridAdapter(Context context, List<LotteryHotModel> lotteryHotModelList) {
        this.mContext = context;
        this.mLotteryHotModelList = lotteryHotModelList;
    }

    @Override
    public int getCount() {
        return mLotteryHotModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return mLotteryHotModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_hot_grid, null);
            holder.iconImv = view.findViewById(R.id.imv_hot_icon);
            holder.lotteryNameTv = view.findViewById(R.id.tv_hot_name);
            holder.lotteryCountdownTv = view.findViewById(R.id.tv_hot_countdown);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        LotteryHotModel item = mLotteryHotModelList.get(position);
        if (item != null){
            holder.iconImv.setBackgroundResource(item.lotteryIcon);
            holder.lotteryNameTv.setText(item.lotteryName);
            holder.lotteryCountdownTv.setText(item.lotteryCountdown);
        }
        return view;
    }

    private final class ViewHolder {
        ImageView iconImv;
        TextView lotteryNameTv, lotteryCountdownTv;
    }
}
