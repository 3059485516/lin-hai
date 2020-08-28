package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.app.Fragment;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.ActionFragment;


public class DoVolunteerActivity extends BaseViewPagerActivity {
    @Override
    public String[] getTitles() {
        return new String[]{"活动"/*, "组织"*/};
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[]{new ActionFragment()/*, new OrginazeFragment()*/};
    }

    @Override
    protected void initMVP() {
    }

    @Override
    protected void initView() {
        setTitle("我要做志愿");
        setMenuText("我的报名");
    }

    @Override
    public void onMenuClicked() {
        gotoActivity(ZhiyuanhuiMyListActivity.class);
    }
}
