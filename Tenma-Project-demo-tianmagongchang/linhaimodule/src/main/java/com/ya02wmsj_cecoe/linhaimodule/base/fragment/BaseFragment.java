package com.ya02wmsj_cecoe.linhaimodule.base.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tenma.ventures.base.TMFragment;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.T;



public abstract class BaseFragment<P extends APresenter> extends TMFragment implements DialogInterface.OnCancelListener {
    private static final String TAG = "BaseFragment";
    protected P mPresenter;
    protected Activity mActivity;
    protected View mRootView;
    protected ProgressDialog mDialog;

    private boolean mUIVisible = false;
    private boolean mViewCreated = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mUIVisible = isVisibleToUser;
        if (mUIVisible) {
            lazyLoadData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        mActivity = getActivity();
        initMVP();
        if (mPresenter != null) {
            mPresenter.mContext = mActivity;
        }
        initUI();
        initView();
        if (isLazyData()) {
            mViewCreated = true;
            lazyLoadData();
        } else {
            initData();
        }
        return mRootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    protected void initUI() {
    }

    protected <T extends View> T $(@IdRes int resId) {
        return (T) mRootView.findViewById(resId);
    }

    protected <T extends View> T $(View layoutView, @IdRes int resId) {
        return (T) layoutView.findViewById(resId);
    }

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract void initMVP();

    protected abstract void initView();

    protected abstract void initData();

    protected boolean isLazyData() {
        return false;
    }

    private void lazyLoadData() {
        if (mUIVisible && mViewCreated && isLazyData()) {
            initData();
            mUIVisible = false;
            mViewCreated = false;
        }
    }

    /**
     * 加载对话框------------------------------------------------------------------------------------
     */
    public void showDialog() {
        showDialog("正在加载...");
    }

    public void showDialog(String message) {
        if (mDialog == null) {
            mDialog = new ProgressDialog(mActivity);
            mDialog.setOnCancelListener(this);
        }
        mDialog.setMessage(message);
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    public void dismissDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    /**
     * 吐司-----------------------------------------------------------------------------------------
     */
    public void toast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        T.showShort(mActivity, msg);
    }

    public void toast(@StringRes int resId) {
        T.showShort(mActivity, resId);
    }

    /**
     * Activity跳转----------------------------------------------------------------------------------
     */
    public void gotoActivity(Class<?> cls) {
        mActivity.startActivity(new Intent(mActivity, cls));
    }

    public void gotoActivity(Intent intent) {
        mActivity.startActivity(intent);
    }

    public void gotoActivityAndFinish(Intent intent) {
        startActivity(intent);
        mActivity.finish();
    }

    public void finishActivity() {
        mActivity.finish();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
    }
}