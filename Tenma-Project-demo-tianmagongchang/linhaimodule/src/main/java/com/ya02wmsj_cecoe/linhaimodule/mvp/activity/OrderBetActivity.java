package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.OrderBetPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.StaggeredDividerItemDecoration;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class OrderBetActivity extends BaseListActivity<AListPresenter> implements IListView {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new NodeAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new OrderBetPresenter(this, "", "");
    }

    @Override
    protected void initView() {
        setBackgroundColor(Color.WHITE);
        setTitle("我要点服务");
        setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        setItemDecoration(new StaggeredDividerItemDecoration(this, 10, 4));
        setMenuText("我的服务");
        setLoadMoreEnabled(false);
    }

    @Override
    public void onMenuClicked() {
        gotoActivity(OrderHistoryActivity.class);
    }
}
