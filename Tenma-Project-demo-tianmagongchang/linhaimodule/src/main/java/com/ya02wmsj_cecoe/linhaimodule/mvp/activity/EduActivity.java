package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.EduContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.NodeContentFragment;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.EduPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;

/**
 * 教育服务
 */
public class EduActivity extends BaseViewPagerActivity<EduContract.Presenter> implements EduContract.View {

    @Override
    public String[] getTitles() {
        return new String[]{"教育资讯", "教育视频"};
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[]{NodeContentFragment.start(RegionManager.getInstance().getCurrentCountyCode(), "36"),
                NodeContentFragment.start(RegionManager.getInstance().getCurrentCountyCode(),"37")};
    }

    @Override
    protected void initMVP() {
        mPresenter = new EduPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("教育服务");
        setMenuText("教育查询");
        mPresenter.clickContent();
    }

    @Override
    public void onMenuClicked() {
        startActivity(new Intent(this, EduCheckActivity.class));
    }
}
