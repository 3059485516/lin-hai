package com.ya02wmsj_cecoe.linhaimodule.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int mCount;

    public SpaceItemDecoration(int space, int count) {
        this.space = space;
        this.mCount = count;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = space;
        outRect.bottom = space;
        //由于每行都只有mCount个，所以第一个都是mCount的倍数，把左边距设为0
        if (parent.getChildLayoutPosition(view) % mCount == 0) {
            outRect.left = 0;
        }
    }
}
