package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tenma.ventures.share.event.ShellEvent;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.FragmentWithTitleAdapter;
import com.ya02wmsj_cecoe.linhaimodule.bean.UpdateRegionMyEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.BaseAreaFragmentPresenter;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxBus;
import com.ya02wmsj_cecoe.linhaimodule.utils.DisplayUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.EmptyView;
import com.ya02wmsj_cecoe.linhaimodule.widget.ToolbarLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

/**
 * 网络社区
 * Created by BenyChan on 2019-07-16.
 */
public class FragmentFive extends BaseAreaFragment {
    protected ToolbarLayout mToolbar;
    protected TabLayout mTabLayout;
    protected ViewPager mViewPager;
    EmptyView mEmptyView;

    private FragmentWithTitleAdapter mPagerAdapter;

    @Override
    protected void updateRegion() {
        mToolbar.setMenuText(RegionManager.getInstance().getCurrentVillageName());
        RxBus.getInstance().send(new UpdateRegionMyEntity());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_fragment_four_bet;
    }

    @Override
    protected void initMVP() {
        mPresenter = new BaseAreaFragmentPresenter(this);
    }

    @Override
    protected void initView() {
        mToolbar = mRootView.findViewById(R.id.toolbar);
        mTabLayout = mRootView.findViewById(R.id.tab);
        mViewPager = mRootView.findViewById(R.id.vp);
        mEmptyView = mRootView.findViewById(R.id.empty);

        mToolbar.setTitle("网络社区");
        TextView tv_menu = mToolbar.getMenuTextView();
        Drawable drawable = ContextCompat.getDrawable(mActivity, R.mipmap.ya02wmsj_cecoe_arrow_down);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv_menu.setCompoundDrawables(null, null, drawable, null);
        tv_menu.setCompoundDrawablePadding(DisplayUtils.dip2px(mActivity, 5));
        mToolbar.setMenuText(RegionManager.getInstance().getCurrentTownName());
        mToolbar.showBack();
        mToolbar.setOnClickListener(v -> {
            ShellEvent shellEvent = new ShellEvent();
            shellEvent.setType(0);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("page", 0);
            Gson gson = new Gson();
            shellEvent.setParams(gson.toJson(jsonObject));
            EventBus.getDefault().post(shellEvent);
        }, v -> {
            // 选择区域
            showRegionPickerDialog(mToolbar.getMenuTextView());
        });
        mTabLayout.setupWithViewPager(mViewPager);
        if (mEmptyView != null) {
            mEmptyView.setText("暂无数据,点击重新加载");
        }
    }

    @Override
    protected void initData() {
        super.initData();
        setViewPagerData(Arrays.asList("秀一秀", "帮一帮", "说一说", "更多服务"), Arrays.asList(NodeContentBetFragment.start("21"), NodeContentBetFragment.start("22"), NodeContentBetFragment.start("23"), new MoreServiceFragment()));
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
        mPagerAdapter = new FragmentWithTitleAdapter(getChildFragmentManager(), titles, fragments);
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

    @Override
    public void updateOnlineList() {
    }
}
