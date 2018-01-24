package com.forum.lottery.view.grid;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

import com.forum.lottery.R;

/**
 * 自定义带有横线、竖线的格子View on 2018/1/24.
 */

public class BorderGridView extends GridView {
    private static final int FLAG_LEFT = 1 << 0;
    private static final int FLAG_TOP = 1 << 1;
    private static final int FLAG_RIGHT = 1 << 2;
    private static final int FLAG_BOTTOM = 1 << 3;

    private final Paint mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mBorderFlag;
    private int mVerticalSpacing;
    private int mHorizontalSpacing;
    private int mVerticalBorderColor;
    private int mHorizontalBorderColor;

    private static boolean isAPI(int api) {
        return Build.VERSION.SDK_INT >= api;
    }

    public BorderGridView(Context context) {
        this(context, null);
    }

    public BorderGridView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BorderGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setVerticalSpacing(0);
        super.setHorizontalSpacing(0);
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.BorderGridView);
        mBorderFlag = t.getInt(R.styleable.BorderGridView_borderFlag, 0);
        mVerticalBorderColor = t.getColor(R.styleable.BorderGridView_verticalBorderColor, 0);
        mHorizontalBorderColor = t.getColor(R.styleable.BorderGridView_horizontalBorderColor, 0);
        mVerticalSpacing = t.getDimensionPixelSize(R.styleable.BorderGridView_verticalBorderWidth, 0);
        mHorizontalSpacing = t.getDimensionPixelSize(R.styleable.BorderGridView_horizontalBorderWidth, 0);
        t.recycle();
        mBorderPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void setVerticalSpacing(int verticalSpacing) {
        mVerticalSpacing = verticalSpacing;
        invalidate();
    }

    @Override
    public void setHorizontalSpacing(int horizontalSpacing) {
        mHorizontalSpacing = horizontalSpacing;
        invalidate();
    }

    public void setVerticalBorderColor(int verticalBorderColor) {
        this.mVerticalBorderColor = verticalBorderColor;
        invalidate();
    }

    public void setHorizontalBorderColor(int horizontalBorderColor) {
        this.mHorizontalBorderColor = horizontalBorderColor;
        invalidate();
    }

    public void setBorderFlag(int flag) {
        this.mBorderFlag = flag;
        invalidate();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        final int numColumns = getNumColumns();
        if (numColumns < 1) {
            return;
        }
        final int childCount = getChildCount();
        if (childCount <= 0) {
            return;
        }
        final int numRows = (childCount - 1) / numColumns + 1;
        final View leftChild = getChildAt(0);//第一个
        final int borderLeft = leftChild.getLeft();
        final int borderTop = leftChild.getTop();
        final int borderRight;
        final int borderBottom;
        final View rightChild = getChildAt(numColumns - 1);//第一行最右边一个
        if (rightChild == null) {
            borderRight = borderLeft + leftChild.getWidth() * numColumns;
        } else {
            borderRight = rightChild.getRight();
        }
        final View bottomChild = getChildAt(numColumns * (numRows - 1));//最后一行的第一个
        borderBottom = bottomChild.getBottom();
        float startX;
        float startY;
        float stopX;
        float stopY;
        //画竖线
        int strokeWidth = mVerticalSpacing;
        mBorderPaint.setStrokeWidth(strokeWidth);
        mBorderPaint.setColor(mVerticalBorderColor);
        if ((mBorderFlag & FLAG_LEFT) == FLAG_LEFT) {
            startX = borderLeft + strokeWidth / 2;
            startY = borderTop;
            stopX = startX;
            stopY = borderBottom;
            canvas.drawLine(startX, startY, stopX, stopY, mBorderPaint);
        }

        if ((mBorderFlag & FLAG_RIGHT) == FLAG_RIGHT) {
            startX = borderRight - strokeWidth / 2;
            startY = borderTop;
            stopX = startX;
            stopY = borderBottom;
            canvas.drawLine(startX, startY, stopX, stopY, mBorderPaint);
        }
        for (int i = 0; i < numColumns - 1; i++) {
            View childView = getChildAt(i);
            startX = childView.getRight();
            startY = borderTop;
            stopX = startX;
            stopY = borderBottom;
            canvas.drawLine(startX, startY, stopX, stopY, mBorderPaint);
        }

        //画横线
        strokeWidth = mHorizontalSpacing;
        mBorderPaint.setStrokeWidth(strokeWidth);
        mBorderPaint.setColor(mHorizontalBorderColor);
        if ((mBorderFlag & FLAG_TOP) == FLAG_TOP) {
            startX = borderLeft;
            startY = borderTop + strokeWidth / 2;
            stopX = borderRight;
            stopY = startY;
            canvas.drawLine(startX, startY, stopX, stopY, mBorderPaint);
        }
        if ((mBorderFlag & FLAG_BOTTOM) == FLAG_BOTTOM) {
            startX = borderLeft;
            startY = borderBottom - strokeWidth / 2;
            stopX = borderRight;
            stopY = startY;
            canvas.drawLine(startX, startY, stopX, stopY, mBorderPaint);
        }
        for (int i = 0; i < numRows - 1; i++) {
            View childView = getChildAt(i * numColumns);
            startX = borderLeft;
            startY = childView.getBottom();
            stopX = borderRight;
            stopY = startY;
            canvas.drawLine(startX, startY, stopX, stopY, mBorderPaint);
        }
    }
}
