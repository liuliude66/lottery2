package com.forum.lot.view.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.forum.lot.utils.LogUtils;

/**
 * 单行布局的容器
 */

public class SingleLayout extends ViewGroup {

    private Context mContext;
    private SubViewClickListener mSubViewClickListener;

    public SingleLayout(Context context) {
        super(context);
        init(context);
    }

    public SingleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SingleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        //setOnTouchListener();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec), getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec));
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        //LogUtils.error("message--------------->onLayout");
        int childMeasureWidth, childMeasureHeight;
        int layoutWidth = 0;    // 容器已经占据的宽度
        int layoutHeight = 0;   // 容器已经占据的宽度
        int maxChildHeight = 0; //一行中子控件最高的高度，用于决定下一行高度应该在目前基础上累加多少
        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            child.setOnClickListener(mCurrentListener);
            childMeasureWidth = child.getMeasuredWidth();
            childMeasureHeight = child.getMeasuredHeight();
            left = layoutWidth;
            right = left + childMeasureWidth;
            top = layoutHeight;
            bottom = top + childMeasureHeight;
            layoutWidth += childMeasureWidth;  //宽度累加
            if (childMeasureHeight > maxChildHeight) {
                maxChildHeight = childMeasureHeight;
            }
            child.layout(left, top, right, bottom);
        }
        mLayoutTotalWidth = right;
    }

    private float currentX, currentY, downX, downY;
    private float deltaX, deltaY;
    private int mLayoutTotalWidth = 0;//所有控件的总长度
    private final int DEFAULT_MARGIN = 5;
    private ClickListener mCurrentListener = new ClickListener();

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                deltaX = 0;
                deltaX = 0;
                LogUtils.info(String.format("message--------------->down(x, y)--(%f, %f)", downX, downY));
                break;
            case MotionEvent.ACTION_MOVE:
                //必须要在MOVE中return才有效果，在这里return后UP事件也会被拦截
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.onInterceptTouchEvent(event);
    }

    //添加 滑动事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                currentX = event.getX();
                currentY = event.getY();
                deltaX = currentX - downX;
                deltaY = currentY - downY;
                downX = currentX;
                downY = currentY;
                resetLayoutPosition();
                LogUtils.info(String.format("message--------------->delta(x, y)--(%f, %f)", deltaX, deltaY));
                break;
            case MotionEvent.ACTION_UP:
                currentX = event.getX();
                currentY = event.getY();
                deltaX = 0;
                deltaX = 0;
                LogUtils.error(String.format("message--------------->up(x, y)--(%f, %f)", currentX, currentY));
                break;
            default:
                break;
        }
        invalidate();
        return true;
    }

    //设置布局的位置
    private void resetLayoutPosition() {
        int count = getChildCount();
        if (mLayoutTotalWidth < getWidth()) {
            return;
        }
        //首先判断，用户是向左滑动还是向右滑动；
        //判断是否已经抵达“顶端”位置，达到顶端位置之后，则禁止用户进行滑动
        if (deltaX > 0) {//右向滑动
            LogUtils.error("message--------------->右向滑动");
            View child = getChildAt(0);
            if (child.getLeft() >= 5) {
                return;
            }
        } else {//左方向滑动,禁止右边的超出边界
            LogUtils.error("message--------------->左向滑动");
            View child = getChildAt(count - 1);
            if (child.getRight() <= getWidth()) {
                return;
            }
        }

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int measuredWidth = child.getMeasuredWidth();
            int left = (int) (child.getLeft() + deltaX);
            int right = left + measuredWidth;
            String format = "message--------------->current(left, right, measuredWidth, currentWidth)--(%d, %d, %d)";
            LogUtils.warn(String.format(format, left, right, measuredWidth));
            child.layout(left, child.getTop(), right, child.getBottom());
            //lastPosition = right;
        }
    }

    private final class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (mSubViewClickListener != null) {
                mSubViewClickListener.onClick(view);
            }
        }
    }

    public void setSubViewClickListener(SubViewClickListener subViewClickListener) {
        this.mSubViewClickListener = subViewClickListener;
    }

    public interface SubViewClickListener {
        void onClick(View view);
    }

}
