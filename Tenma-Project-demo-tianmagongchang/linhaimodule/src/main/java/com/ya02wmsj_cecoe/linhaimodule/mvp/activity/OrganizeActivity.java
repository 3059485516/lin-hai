package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import com.ya02wmsj_cecoe.linhaimodule.adapter.OrganizeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OrganizeContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.OrganizePresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

/**
 * 加入组织页面
 * Created by BenyChan on 2019-07-24.
 */
public class OrganizeActivity extends BaseListActivity<OrganizeContract.Presenter> implements OrganizeContract.View {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new OrganizeAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new OrganizePresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("加入组织");
        setDefaultItemDecoration();
        setLoadMoreEnabled(false);
    }
}
