package com.ya02wmsj_cecoe.linhaimodule.base.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.FragmentWithTitleAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.EmptyView;

import java.util.Arrays;
import java.util.List;



public abstract class BaseViewPagerActivity<P extends APresenter> extends BaseActivity<P> {

    protected TabLayout mTabLayout;

    protected ViewPager mViewPager;

    EmptyView mEmptyView;

    private FragmentWithTitleAdapter mPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_view_pager;
    }

    @Override
    protected boolean hasActionBar() {
        return true;
    }

    @Override
    protected final void initUI() {
        super.initUI();
        mTabLayout = findViewById(R.id.tab);
        mViewPager = findViewById(R.id.vp);
        mEmptyView = findViewById(R.id.empty);
        mTabLayout.setupWithViewPager(mViewPager);
        if (mEmptyView != null) {
            mEmptyView.setText("暂无数据,点击重新加载");
            mEmptyView.setOnClickListener(v -> onEmptyViewClicked());
        }
    }

    @Override
    protected void initData() {
        setViewPagerData(getTitles(), getFragments());
    }


    protected void onEmptyViewClicked() {
    }

    protected int getCurrentItem() {
        return mViewPager.getCurrentItem();
    }

    protected void setPageLimit(int limit) {
        mViewPager.setOffscreenPageLimit(limit);
    }

    public void setViewPagerData(String[] titles, Fragment[] fragments) {
        if (titles == null || fragments == null) {
            return;
        }
        setViewPagerData(Arrays.asList(titles), Arrays.asList(fragments));
    }

    public void setViewPagerData(List<String> titles, List<Fragment> fragments) {
        if (titles == null || fragments == null) {
            return;
        }
        int fragmentSize = fragments.size();
        int titleSize = titles.size();
        if (fragmentSize != titleSize) {
            return;
        }
        if (mEmptyView != null) {
            mEmptyView.hide();
        }
        mPagerAdapter = new FragmentWithTitleAdapter(getSupportFragmentManager(), titles, fragments);
        mViewPager.setAdapter(mPagerAdapter);
        if (fragmentSize == 1) {
            mTabLayout.setVisibility(View.GONE);
        } else if (fragmentSize < 5) {
            int titleLength = 0;
            for (String str : titles) {
                titleLength = titleLength + str.length();
            }
            if (titleLength > 17) {
                mTabLayout.setVisibility(View.VISIBLE);
                mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
            } else {
                mTabLayout.setVisibility(View.VISIBLE);
                mTabLayout.setTabMode(TabLayout.MODE_FIXED);
            }
        } else {
            mTabLayout.setVisibility(View.VISIBLE);
            mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    public abstract String[] getTitles();

    public abstract Fragment[] getFragments();

    protected FragmentWithTitleAdapter getAdapter() {
        return mPagerAdapter;
    }
}
