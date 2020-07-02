package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.app.Fragment;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.NodeContentFragment;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.SportVenueFragment;

/**
 * Created by BenyChan on 2019-07-26.
 */
public class SportActivity extends BaseViewPagerActivity {
    private String mNodeId;

    @Override
    public String[] getTitles() {
        return new String[]{"体育资讯", "场馆信息"};
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[]{NodeContentFragment.start(Constant.DEFAULT_REGION_CODE, mNodeId), new SportVenueFragment()};
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void initView() {
        setTitle(getIntent().getStringExtra(Constant.KEY_STRING_1));
        mNodeId = getIntent().getStringExtra(Constant.KEY_STRING_2);
    }
}
