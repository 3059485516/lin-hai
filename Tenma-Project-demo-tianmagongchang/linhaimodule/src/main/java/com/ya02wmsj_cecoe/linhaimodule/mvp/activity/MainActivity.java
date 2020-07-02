package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.luck.picture.lib.permissions.RxPermissions;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.tenma.ventures.tools.change_activity.BackPressListener;
import com.ya02wmsj_cecoe.linhaimodule.App;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.FragmentFive;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.FragmentFourBet;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.FragmentLiTang;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.FragmentOne;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.FragmentVolunteer;
import com.ya02wmsj_cecoe.linhaimodule.utils.PermissionsUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.MainTabView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;


/**
 * 首页
 */
public class MainActivity extends BaseActivity implements MainTabView.TabClickListener, BackPressListener, MainTabView.ClearUnreadListener {
    MainTabView mTabOne;

    MainTabView mTabTwo;

    MainTabView mTabThree;

    MainTabView mTabFour;

    MainTabView mTabFive;

    private FragmentManager mFragmentManager;
    private MainTabView mCurrentTab;
    private boolean mExit = false;


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_main;
    }

    @Override
    protected boolean hasActionBar() {
        return false;
    }

    @Override
    protected void initMVP() {
        App.get().setApplication(this.getApplication());
    }

    @Override
    protected void initView() {
        mTabOne = findViewById(R.id.tab_one);
        mTabTwo = findViewById(R.id.tab_two);
        mTabThree = findViewById(R.id.tab_three);
        mTabFour = findViewById(R.id.tab_four);
        mTabFive = findViewById(R.id.tab_five);


        mTabOne.bindData(R.drawable.ya02wmsj_cecoe_selector_one, "首页", new FragmentOne(), true, this, null);
        mTabTwo.bindData(R.drawable.ya02wmsj_cecoe_selector_two, "志愿服务", new FragmentVolunteer(), false, this, null);
        mTabThree.bindData(R.drawable.ya02wmsj_cecoe_selector_three, "文化礼堂", new FragmentLiTang(), false, this, this);
        mTabFour.bindData(R.drawable.ya02wmsj_cecoe_selector_four, "我的评议", new FragmentFourBet(), false, this, null);
        mTabFive.bindData(R.drawable.ya02wmsj_cecoe_selector_five, "网络社区", new FragmentFive(), false, this, null);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().add(R.id.fl_container, mTabOne.getFragment()).show(mTabOne.getFragment()).commitAllowingStateLoss();
        mCurrentTab = mTabOne;
    }


    @Override
    protected void initData() {
        getPermissions();
    }

    /**
     * 清空消息
     */
    @Override
    public void clearUnread() {
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

    @SuppressLint("CheckResult")
    @Override
    public void onBackPressed() {
        if (mExit) {
            finishActivity();
//            AppManager.getInstance().exit();
        } else {
            mExit = true;
            toast("再按返回键退出");
            Flowable.timer(3, TimeUnit.SECONDS).subscribe(aLong -> mExit = false);
        }
    }

    @Override
    public boolean onBackPress() {
        return true;
    }

    @SuppressLint("CheckResult")
    private void getPermissions() {
        new RxPermissions(this).requestEach(PermissionsUtils.APP).subscribe(permission -> {
            if (permission.granted && permission.name.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            }
        }, throwable -> {
        });
    }

}