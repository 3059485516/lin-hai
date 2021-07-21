package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.app.Fragment;

import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.FragmentVolunteer;

public class ZhiyuanhuiActivity extends BaseViewPagerActivity {

    @Override
    public String[] getTitles() {
        return new String[]{"志愿服务"};
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[]{FragmentVolunteer.start(true, true)};
    }

    @Override
    protected boolean hasActionBar() {
        return true;
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void initView() {
        setTitle("志愿服务");
    }
}
