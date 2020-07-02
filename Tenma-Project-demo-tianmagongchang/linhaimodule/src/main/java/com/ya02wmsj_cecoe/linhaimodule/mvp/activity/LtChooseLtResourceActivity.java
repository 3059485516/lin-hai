package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LtChooseLtResouceAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtChooseResoucePresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class LtChooseLtResourceActivity extends BaseListActivity<AListPresenter> implements IListView {

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new LtChooseLtResouceAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new LtChooseResoucePresenter(this, getIntent().getStringExtra(Constant.KEY_STRING_1));
    }

    @Override
    protected void initView() {
        setTitle("选择资源");
//        setLayoutManager(new GridLayoutManager(mContext, 2));
        setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        setLoadMoreEnabled(false);
    }
}
