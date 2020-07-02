package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.app.Fragment;

import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtStreetEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtChooseLtContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtShowContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtExponentPresenter;

import java.util.List;

public class LtExponentActivity extends BaseViewPagerActivity<LtExponentPresenter> implements LtChooseLtContract.View {
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
        mPresenter = new LtExponentPresenter(this);
        mPresenter.getAllCABranchList();
    }

    @Override
    protected void initView() {
        setTitle("礼堂指数");
    }


    @Override
    public void update(List<LtStreetEntity> list) {
        setViewPagerData(mPresenter.getTitles(), mPresenter.getFragments());
    }
}
