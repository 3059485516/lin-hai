package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import com.ya02wmsj_cecoe.linhaimodule.adapter.OrderHistoryAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OrderHistoryContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.OrderHistoryPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

/**
 * Created by BenyChan on 2019-08-02.
 */
public class OrderHistoryActivity extends BaseListActivity<OrderHistoryContract.Presenter> implements OrderHistoryContract.View {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new OrderHistoryAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new OrderHistoryPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("点单历史");
        setDefaultItemDecoration();
        setLoadMoreEnabled(false);
    }
}
