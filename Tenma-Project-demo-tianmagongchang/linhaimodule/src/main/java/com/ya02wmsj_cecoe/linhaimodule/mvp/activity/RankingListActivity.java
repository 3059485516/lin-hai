package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.app.Fragment;

import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.RankingListFragment;

public class RankingListActivity extends BaseViewPagerActivity {
    @Override
    public String[] getTitles() {
        return new String[]{"组织时长", "志愿者时长"};
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[]{RankingListFragment.start(true), RankingListFragment.start(false)};
    }

    @Override
    protected void initMVP() {
        setTitle("我要看排名");
    }

    @Override
    protected void initView() {

    }
}
