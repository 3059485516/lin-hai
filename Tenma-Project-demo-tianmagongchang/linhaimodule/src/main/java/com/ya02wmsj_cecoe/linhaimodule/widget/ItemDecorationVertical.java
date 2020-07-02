package com.ya02wmsj_cecoe.linhaimodule.widget;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemDecorationVertical extends RecyclerView.ItemDecoration {
    private Drawable mDivider;
    private int mOffsetLeft = 0;
    private int mOffsetRight = 0;
    private int mOffsetBottom = 0;

    public ItemDecorationVertical(int lineColor, int lineWidth, int offsetLeft, int offsetRight) {
        mDivider = new ColorDrawable(lineColor);
        mOffsetBottom = lineWidth;
        mOffsetLeft = offsetLeft;
        mOffsetRight = offsetRight;
    }

    public ItemDecorationVertical(Drawable drawable, int lineWidth, int offsetLeft, int offsetRight) {
        mDivider = drawable;
        mOffsetBottom = lineWidth;
        mOffsetLeft = offsetLeft;
        mOffsetRight = offsetRight;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getPaddingLeft() + mOffsetLeft;
        final int right = parent.getWidth() - parent.getPaddingRight() - mOffsetRight;
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mOffsetBottom;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, mOffsetBottom);
    }
}
