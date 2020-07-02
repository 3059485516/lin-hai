package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ServiceInfoListAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ServiceInfoListPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class ServiceListInfoActivity extends BaseListActivity<AListPresenter> implements IListView {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new ServiceInfoListAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new ServiceInfoListPresenter(this, getIntent().getStringExtra(Constant.KEY_STRING_2), this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        setTitle(getIntent().getStringExtra(Constant.KEY_STRING_1));
        setDefaultItemDecoration();
    }
}
