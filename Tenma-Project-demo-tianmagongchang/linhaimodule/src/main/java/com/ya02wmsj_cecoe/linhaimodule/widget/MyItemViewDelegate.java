package com.ya02wmsj_cecoe.linhaimodule.widget;

import com.zhy.adapter.recyclerview.base.ItemViewDelegate;

public abstract class MyItemViewDelegate<T> implements ItemViewDelegate<T> {
    private int mLayoutId;
    public MyItemViewDelegate(int layoutId) {
        mLayoutId = layoutId;
    }

    @Override
    public int getItemViewLayoutId() {
        return mLayoutId;
    }
}

