package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LiveRecordListAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LiveRecordListPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class LiveRecordListActivity extends BaseListActivity<LiveRecordListPresenter> implements IListView {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new LiveRecordListAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new LiveRecordListPresenter(this, getIntent().getStringExtra(Constant.KEY_STRING_1));
    }

    @Override
    protected void initView() {
        setTitle("录播");
    }
}
