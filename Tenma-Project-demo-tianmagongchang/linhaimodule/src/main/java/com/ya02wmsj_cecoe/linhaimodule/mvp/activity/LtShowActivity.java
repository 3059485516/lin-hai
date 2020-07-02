package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.app.Fragment;

import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtShowContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.LtShowFragment;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtShowPresenter;


public class LtShowActivity extends BaseViewPagerActivity<LtShowPresenter> implements LtShowContract.View {
    @Override
    public String[] getTitles() {
//        return new String[]{"古城街道", "余杭街道", "和睦街道", "小和山街道"};
        return new String[0];
    }

    @Override
    public Fragment[] getFragments() {
//        return new Fragment[]{LtShowFragment.create(), LtShowFragment.create(), LtShowFragment.create(), LtShowFragment.create()};
        return new Fragment[0];
    }

    @Override
    protected void initMVP() {
        mPresenter = new LtShowPresenter(this);
        mPresenter.getCABranchList();
    }

    @Override
    protected void initView() {
        setTitle("礼堂展示");
        setPageLimit(3);
    }

    @Override
    public void updateView() {
        setViewPagerData(mPresenter.getTitles(), mPresenter.getFragments());
    }
}
