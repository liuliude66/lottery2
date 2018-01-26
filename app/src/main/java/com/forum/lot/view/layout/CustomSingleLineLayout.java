package com.forum.lot.view.layout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

/**
 * 自定义单行，水平放置 的空间容器,根据大小自动切换
 */

public class CustomSingleLineLayout extends LinearLayout {

    private List<View> mSubViews;
    private int mCurrentSubViewPosition = 0;
    private String mCurrentSubViewName = "none";
    private OnClickListener mOnClickListener;

    public CustomSingleLineLayout(Context context) {
        super(context);
    }

    public CustomSingleLineLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSingleLineLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

    }
}
