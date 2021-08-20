package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.luck.picture.lib.permissions.RxPermissions;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.tenma.ventures.share.event.TablayoutEvent;
import com.tenma.ventures.tools.change_activity.BackPressListener;
import com.ya02wmsj_cecoe.linhaimodule.App;
import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.fragment.BaseFragment;
import com.ya02wmsj_cecoe.linhaimodule.utils.PermissionsUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.MainTabView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by BenyChan on 2019-07-17.
 */
public class FragmentMain extends BaseFragment implements MainTabView.TabClickListener, BackPressListener, MainTabView.ClearUnreadListener {
    protected MainTabView mTabOne;
    protected MainTabView mTabTwo;
    protected MainTabView mTabThree;
    protected MainTabView mTabFour;
    protected MainTabView mTabFive;

    private FragmentManager mFragmentManager;
    private MainTabView mCurrentTab;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_main;
    }

    @Override
    protected void initMVP() {
        App.get().setApplication(mActivity.getApplication());
        App.get().initTMUser();
    }

    @Override
    protected void initView() {
        mTabOne = mRootView.findViewById(R.id.tab_one);
        mTabTwo = mRootView.findViewById(R.id.tab_two);
        mTabThree = mRootView.findViewById(R.id.tab_three);
        mTabFour = mRootView.findViewById(R.id.tab_four);
        mTabFive = mRootView.findViewById(R.id.tab_five);

        mTabOne.bindData(R.drawable.ya02wmsj_cecoe_selector_one, "首页", new FragmentOne(), true, this, null);
        mTabTwo.bindData(R.drawable.ya02wmsj_cecoe_selector_two, "志愿服务", new FragmentVolunteer(), false, this, null);
        mTabThree.bindData(R.drawable.ya02wmsj_cecoe_selector_three, "文化礼堂", new FragmentLiTang(), false, this, this);
        mTabFour.bindData(R.drawable.ya02wmsj_cecoe_selector_four, "我的评议", new FragmentFourBet(), false, this, null);
        mTabFive.bindData(R.drawable.ya02wmsj_cecoe_selector_five, "网络社区", new FragmentFive(), false, this, null);
        mFragmentManager = getChildFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.fl_container, mTabOne.getFragment()).show(mTabOne.getFragment()).commitAllowingStateLoss();
        mCurrentTab = mTabOne;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        TablayoutEvent tablayoutEvent = new TablayoutEvent();
        if (isVisibleToUser) {
            tablayoutEvent.setHide(true);
        } else {
            tablayoutEvent.setHide(false);
        }
        EventBus.getDefault().post(tablayoutEvent);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        TablayoutEvent tablayoutEvent = new TablayoutEvent();
        if (hidden) {
            tablayoutEvent.setHide(false);
        } else {
            tablayoutEvent.setHide(true);
        }
        EventBus.getDefault().post(tablayoutEvent);
    }

    @Override
    protected void initData() {
        getPermissions();
    }

    @SuppressLint("CheckResult")
    private void getPermissions() {
        new RxPermissions(mActivity).requestEach(PermissionsUtils.APP).subscribe(permission -> {
            if (permission.granted && permission.name.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            }
        }, throwable -> {
        });
    }

    @Override
    public void tabClick(MainTabView mainTab) {
        showFragment(mainTab);
    }


    private void showFragment(MainTabView indexTab) {
        GSYVideoManager.releaseAllVideos();
        if (indexTab == mCurrentTab) {
            return;
        }
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.hide(mCurrentTab.getFragment());
        mCurrentTab.setSelected(false);
        Fragment showFragment = indexTab.getFragment();
        if (showFragment.isAdded()) {
            fragmentTransaction.show(showFragment);
        } else {
            fragmentTransaction.add(R.id.fl_container, showFragment).show(showFragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
        indexTab.setSelected(true);
        mCurrentTab = indexTab;
    }

    @Override
    public void clearUnread() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Config.getInstance().destroy();
    }
}
