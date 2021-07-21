package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.content.ContextCompat;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.TalentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.TalentPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

/**
 * 达人库
 */
public class TalentActivity extends BaseListActivity<AListPresenter> implements IListView {

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new TalentAdapter(mContext, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new TalentPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("达人库");
        setBackgroundColor(ContextCompat.getColor(this, R.color.yl_graybg));
    }
}
