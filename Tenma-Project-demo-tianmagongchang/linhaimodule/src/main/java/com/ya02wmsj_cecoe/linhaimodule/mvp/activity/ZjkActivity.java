package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.app.Fragment;

import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.ZjkProfessionEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ZjkContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.ZjkFragment;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ZjkPresenter;

import java.util.ArrayList;
import java.util.List;

public class ZjkActivity extends BaseViewPagerActivity<ZjkPresenter> implements ZjkContract.View {
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
        mPresenter = new ZjkPresenter(this);
        mPresenter.getDataList();
    }

    @Override
    protected void initView() {
        setTitle("专家库");
    }

    @Override
    public void updateView(List<ZjkProfessionEntity> date) {
        List<String> titles = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        for (ZjkProfessionEntity entity : date) {
            titles.add(entity.getName());
            fragments.add(ZjkFragment.create(entity.getCateList()));
        }
        setViewPagerData(titles, fragments);
        dismissDialog();
    }
}
