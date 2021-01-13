package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseViewPagerActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.FragmentPublishOpinion;

/**
 * Created by BenyChan on 2019-07-19.
 */
public class AppraiseActivity extends BaseViewPagerActivity {
    protected FrameLayout mRootView;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_appraise;
    }

    @Override
    public String[] getTitles() {
        return new String[]{"发表看法"};
    }

    @Override
    public Fragment[] getFragments() {
        return new Fragment[]{new FragmentPublishOpinion()};
    }

    @Override
    protected void initMVP() {
    }

    @Override
    protected void initView() {
        setTitle("线上评议");
        mRootView = findViewById(R.id.rootView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       // super.onActivityResult(requestCode, resultCode, data);
        getAdapter().getCurrentFragment().onActivityResult(requestCode, resultCode, data);
    }
}
