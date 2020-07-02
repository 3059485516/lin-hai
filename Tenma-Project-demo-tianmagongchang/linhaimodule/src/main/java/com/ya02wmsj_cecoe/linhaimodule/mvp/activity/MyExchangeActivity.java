package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.app.Fragment;

import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.MyExchangeFragment;

public class MyExchangeActivity extends BaseViewPagerActivity {
    @Override
    public String[] getTitles() {
        return new String[]{"待领取", "已领取", "已退还"};       //订单状态，0-待领取，1-已领取，2已退还
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[]{MyExchangeFragment.create("0"), MyExchangeFragment.create("1"), MyExchangeFragment.create("2")};
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected void initView() {
        setTitle("我的兑换");
    }
}
