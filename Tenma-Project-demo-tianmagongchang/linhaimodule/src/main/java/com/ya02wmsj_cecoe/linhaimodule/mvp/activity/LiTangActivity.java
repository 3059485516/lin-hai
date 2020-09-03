package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.app.Fragment;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.FragmentLiTang;

public class LiTangActivity extends BaseViewPagerActivity {
    @Override
    public String[] getTitles() {
        return new String[]{"文化礼堂"};
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[]{FragmentLiTang.start(true, true)};
    }

    @Override
    protected void initMVP() {
    }

    @Override
    protected boolean hasActionBar() {
        return true;
    }

    @Override
    protected void initView() {
        setTitle("文化礼堂");
    }
}
