package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.app.Fragment;

import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.NodeContentFragment;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;

public class SmartTechActivity extends BaseViewPagerActivity {
    @Override
    public String[] getTitles() {
        return new String[]{"科技工作", "乡村科普"};
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[]{NodeContentFragment.start(RegionManager.getInstance().getCurrentCountyCode(), "39"),
                NodeContentFragment.start(RegionManager.getInstance().getCurrentCountyCode(), "40")};
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void initView() {
        setTitle("智慧科普");
    }
}
