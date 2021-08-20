package com.ya02wmsj_cecoe.linhaimodule.mvp.fragment;

import android.text.TextUtils;
import com.tenma.ventures.bean.utils.TMSharedPUtil;

/**
 * 临海新时代（解决初始化跳转登录问题）
 */
public class LinHaiNewTimesFragment extends FragmentMain {

    /**
     * 是否初始化完成
     */
    private boolean isInitComplete = false;

    @Override
    public void onResume() {
        super.onResume();
        setUserVisibleHint(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        setUserVisibleHint(false);
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isLogin()) {
            if (!isInitComplete) {
                initMVP();
                initView();
                initData();
            }
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (isLogin()) {
            super.onHiddenChanged(hidden);
        }
    }

    @Override
    protected void initMVP() {
        if (isLogin()) {
            super.initMVP();
        }
    }

    @Override
    protected void initView() {
        if (isLogin()) {
            super.initView();
        }
    }

    @Override
    protected void initData() {
        if (isLogin()) {
            super.initData();
            isInitComplete = true;
        }
    }

    private boolean isLogin() {
        return !TextUtils.isEmpty(TMSharedPUtil.getTMToken(getActivity()));
    }
}
