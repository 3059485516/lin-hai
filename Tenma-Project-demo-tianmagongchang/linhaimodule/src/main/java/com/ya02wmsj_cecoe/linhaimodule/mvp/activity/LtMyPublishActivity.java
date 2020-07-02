package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.content.ContextCompat;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LtMyPublishAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtMyPublishPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;


public class LtMyPublishActivity extends BaseListActivity<LtMyPublishPresenter> implements IListView {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new LtMyPublishAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new LtMyPublishPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("我发布的");
        setBackgroundColor(ContextCompat.getColor(this, R.color.yl_graybg));
    }
}
