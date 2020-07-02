package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.content.ContextCompat;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LtMyAdvanceAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtMyAdvancePresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class LtMyAdvanceActivity extends BaseListActivity<LtMyAdvancePresenter> implements IListView {
    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new LtMyAdvanceAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new LtMyAdvancePresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("我的建议");
        setBackgroundColor(ContextCompat.getColor(mContext, R.color.yl_graybg));
    }
}
