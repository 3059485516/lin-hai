package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.app.Fragment;

import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.MyWishFragment;

/**
 * Created by BenyChan on 2019-08-15.
 */
public class MyWishActivity extends BaseViewPagerActivity {
    @Override
    public String[] getTitles() {
        return new String[]{"我发起", "我认领"};
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[]{MyWishFragment.start(0), MyWishFragment.start(1)};
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void initView() {
        setTitle("我的微心愿");
    }
}
