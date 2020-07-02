package com.ya02wmsj_cecoe.linhaimodule.base.fragment;

import android.annotation.TargetApi;
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


/**
 * @author Yang Shihao
 */
public abstract class BaseViewPagerFragment<P extends APresenter> extends BaseFragment<P> {

    protected TabLayout mTabLayout;
    protected ViewPager mViewPager;
    EmptyView mEmptyView;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_fragment_view_pager;
    }

    @Override
    protected final void initUI() {
        super.initUI();
        mTabLayout = mRootView.findViewById(R.id.tab);
        mViewPager = mRootView.findViewById(R.id.vp);
        mEmptyView = mRootView.findViewById(R.id.empty);
        mTabLayout.setupWithViewPager(mViewPager);
        if (mEmptyView != null) {
            mEmptyView.setText("暂无数据");
        }
    }

    @Override
    protected void initData() {
        setViewPagerData(getTitles(), getFragments());
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

    protected int getCurrentItem() {
        return mViewPager.getCurrentItem();
    }

    @TargetApi(17)
    public void setViewPagerData(List<String> titles, List<Fragment> fragments) {
        if (titles == null || fragments == null) {
            return;
        }

        int fragmentSize = fragments.size();
        int titleSize = titles.size();
        if (fragmentSize == 0 || titleSize == 0 || fragmentSize != titleSize) {
            return;
        }

        if (mEmptyView != null) {
            mEmptyView.hide();
        }
        mViewPager.setAdapter(new FragmentWithTitleAdapter(getChildFragmentManager(), titles, fragments));
        if (fragmentSize == 1) {
            mTabLayout.setVisibility(View.GONE);
        } else if (fragmentSize < 5) {
            mTabLayout.setVisibility(View.VISIBLE);
            mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            mTabLayout.setVisibility(View.VISIBLE);
            mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    public abstract String[] getTitles();

    public abstract Fragment[] getFragments();
}
