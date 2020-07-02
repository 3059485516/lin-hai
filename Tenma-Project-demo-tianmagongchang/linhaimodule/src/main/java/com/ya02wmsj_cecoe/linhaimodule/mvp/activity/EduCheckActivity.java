package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ya02wmsj_cecoe.linhaimodule.adapter.EduCheckAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.EduCheckPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class EduCheckActivity extends BaseListActivity<EduCheckPresenter> implements IListView {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new EduCheckAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new EduCheckPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("教育查询");
        setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setBackgroundColor(Color.WHITE);
    }
}
