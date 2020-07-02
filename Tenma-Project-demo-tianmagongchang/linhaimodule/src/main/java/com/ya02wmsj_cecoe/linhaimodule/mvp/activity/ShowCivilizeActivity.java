package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeContentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.NodeContentContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.NodeContentPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class ShowCivilizeActivity extends BaseListActivity<NodeContentContract.Presenter> implements NodeContentContract.View {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new NodeContentAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new NodeContentPresenter(this, "30", RegionManager.getInstance().getCurrentCountyCode());
    }

    @Override
    protected void initView() {
        setTitle("我要秀文明");
        setDefaultItemDecoration();
    }
}
