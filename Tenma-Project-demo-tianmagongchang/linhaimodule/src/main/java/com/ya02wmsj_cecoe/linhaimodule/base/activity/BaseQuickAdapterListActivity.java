package com.ya02wmsj_cecoe.linhaimodule.base.activity;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.BaseQuickAdapterPresenter;

import java.util.List;

/**
 * Created by 寻水的鱼 on 2019-09-16.
 * adapter 只能继承于BaseQuickAdapter
 */
public abstract class BaseQuickAdapterListActivity<P extends BaseQuickAdapterPresenter<?,?,?>> extends BaseCleanListActivity<P> {

    @Override
    protected void initUI() {
        super.initUI();
        mPresenter.getAdapter().bindToRecyclerView(mRecyclerView);
    }
}
