package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.adapter.OrderBetSubAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.OrderBetPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class OrderBetSubActivity extends BaseListActivity<AListPresenter> implements IListView {
    private Node mNode;

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new OrderBetSubAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mNode = (Node) getIntent().getSerializableExtra(Constant.KEY_BEAN);
        mPresenter = new OrderBetPresenter(this, mNode.getId(), "");
    }

    @Override
    protected void initView() {
        setTitle(mNode.getTitle());
        setLoadMoreEnabled(false);
    }
}
