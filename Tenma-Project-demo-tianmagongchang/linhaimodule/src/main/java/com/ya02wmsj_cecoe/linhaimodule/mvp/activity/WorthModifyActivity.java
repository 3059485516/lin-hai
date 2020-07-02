package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.app.Fragment;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.WorthModifyContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.WorthModifyPresenter;

/**
 * Created by BenyChan on 2019-08-07.
 */
public class WorthModifyActivity extends BaseViewPagerActivity<WorthModifyContract.Presenter> implements WorthModifyContract.View {

    @Override
    public String[] getTitles() {
        return new String[0];
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[0];
    }

    @Override
    protected void initMVP() {
        mPresenter = new WorthModifyPresenter(this, getIntent().getStringExtra(Constant.KEY_STRING_2));
    }

    @Override
    protected void initView() {
        setTitle(getIntent().getStringExtra(Constant.KEY_STRING_1));
        setViewPagerData(mPresenter.getTitles(), mPresenter.getFragments());
    }

    @Override
    protected void initData() {
        mPresenter.getNodeList();
    }

    @Override
    public void update() {
        getAdapter().notifyDataSetChanged();
    }
}
