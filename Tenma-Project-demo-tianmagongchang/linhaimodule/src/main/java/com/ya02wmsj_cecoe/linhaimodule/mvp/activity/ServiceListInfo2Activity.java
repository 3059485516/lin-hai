package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ServiceInfoList2Adapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ServiceInfoListPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class ServiceListInfo2Activity extends BaseListActivity<AListPresenter> implements IListView {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new ServiceInfoList2Adapter(this, mPresenter.getDataList());
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