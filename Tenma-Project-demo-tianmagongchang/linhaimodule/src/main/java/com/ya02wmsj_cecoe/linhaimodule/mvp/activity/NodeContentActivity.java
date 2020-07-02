package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeContentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.NodeContentContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.NodeContentPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

/**
 * Created by BenyChan on 2019-07-16.
 */
public class NodeContentActivity extends BaseListActivity<NodeContentContract.Presenter> implements NodeContentContract.View {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new NodeContentAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new NodeContentPresenter(this, getIntent().getStringExtra(Constant.KEY_STRING_2), Constant.DEFAULT_REGION_CODE);
    }

    @Override
    protected void initView() {
        setTitle(getIntent().getStringExtra(Constant.KEY_STRING_1));
        setDefaultItemDecoration();
    }
}
