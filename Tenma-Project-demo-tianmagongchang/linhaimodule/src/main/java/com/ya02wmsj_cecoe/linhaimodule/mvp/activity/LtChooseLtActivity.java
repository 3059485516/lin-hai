package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.app.Fragment;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtStreetEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtChooseLtContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtChooseLtPresenter;
import java.util.List;

public class LtChooseLtActivity extends BaseViewPagerActivity<LtChooseLtContract.Presenter> implements LtChooseLtContract.View {
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
        mPresenter = new LtChooseLtPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("选择礼堂");
        setPageLimit(3);
    }

    @Override
    protected void initData() {
        mPresenter.getAllCABranchList();
    }

    @Override
    public void update(List<LtStreetEntity> list) {
        setViewPagerData(mPresenter.getTitles(), mPresenter.getFragments());
    }
}
