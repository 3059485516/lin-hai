package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.app.Fragment;

import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.LtAppointManageFragment;

public class LtAppointManageActivity extends BaseViewPagerActivity {
    @Override
    public String[] getTitles() {
        return new String[]{"未审核", "已审核"};
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[]{LtAppointManageFragment.create("1"), LtAppointManageFragment.create("2")};
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void initView() {
        setTitle("预约管理");
    }
}
