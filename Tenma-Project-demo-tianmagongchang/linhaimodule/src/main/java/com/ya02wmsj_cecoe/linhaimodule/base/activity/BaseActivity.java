package com.ya02wmsj_cecoe.linhaimodule.base.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tenma.ventures.base.TMActivity;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.AppManager;
import com.ya02wmsj_cecoe.linhaimodule.utils.StatusBarUtils;
import com.ya02wmsj_cecoe.linhaimodule.utils.T;
import com.ya02wmsj_cecoe.linhaimodule.widget.ToolbarLayout;



public abstract class BaseActivity<P extends APresenter> extends TMActivity implements DialogInterface.OnCancelListener {
    protected ToolbarLayout mToolbarLayout;
    ImageView mIvBack;

    protected P mPresenter;
    protected Activity mContext;
    protected ProgressDialog mDialog;
    protected boolean mDestroy = false;
    protected Bundle mSavedInstanceState;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedInstanceState = savedInstanceState;
        if (fullScreen()) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(getLayoutId());
        } else if (isTranslucent()) {
            setContentView(getLayoutId());
            StatusBarUtils.setTranslucentForImageView(this, 112, $(needOffsetViewId()));
        } else if (!hasActionBar()) {
            setContentView(getLayoutId());
            StatusBarUtils.setColor(this, ContextCompat.getColor(this, R.color.yl_theme_color));
        } else {
            setContentView(R.layout.ya02wmsj_cecoe_activity_base);
            createUI();
            StatusBarUtils.setColor(this, ContextCompat.getColor(this, R.color.yl_theme_color));
        }


        mToolbarLayout = findViewById(R.id.base_toolbar);
        mIvBack = findViewById(R.id.base_iv_back);
        AppManager.getInstance().pushActivity(this);
        mContext = this;
        initMVP();
        if (mPresenter != null) {
            mPresenter.mContext = this;
        }
        initUI();
        initView();
        initData();
        mDestroy = false;
    }

    @Override
    protected void onStop() {
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
    protected void onDestroy() {
        super.onDestroy();
        mDestroy = true;
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
        AppManager.getInstance().popActivity(this);
    }

    private void createUI() {
        LinearLayout activity = $(R.id.activity_base);
        View.inflate(this, getLayoutId(), activity);
    }

    protected void initUI() {
        if (mToolbarLayout != null) {
            mToolbarLayout.setOnClickListener(v -> onBackClicked(), v -> onMenuClicked());
        }

        if (mIvBack != null) {
            mIvBack.setOnClickListener(v -> onBackClicked());
        }
    }

    protected <T extends View> T $(@IdRes int resId) {
        return (T) super.findViewById(resId);
    }

    protected <T extends View> T $(View layoutView, @IdRes int resId) {
        return (T) layoutView.findViewById(resId);
    }

    protected abstract @LayoutRes
    int getLayoutId();

    protected abstract void initMVP();

    protected abstract void initView();

    protected abstract void initData();

    protected boolean fullScreen() {
        return false;
    }

    protected boolean hasActionBar() {
        return true;
    }


    /**
     * 是否是半透明状态栏
     */
    protected boolean isTranslucent() {
        return false;
    }

    /**
     * 半透明状态栏时,需要向下偏移的View
     */
    protected @IdRes
    int needOffsetViewId() {
        return 0;
    }


    /**
     * ToolbarLayout的操作
     */

    public void setTitle(String text) {
        if (mToolbarLayout != null) {
            mToolbarLayout.setTitle(text);
        }
    }

    public void showBack() {
        if (mToolbarLayout != null) {
            mToolbarLayout.showBack();
        }
    }

    public void hideBack() {
        if (mToolbarLayout != null) {
            mToolbarLayout.hideBack();
        }
    }

    public void showTextMenu() {
        if (mToolbarLayout != null) {
            mToolbarLayout.showTextMenu();
        }
    }

    public void hideTextMenu() {
        if (mToolbarLayout != null) {
            mToolbarLayout.hideTextMenu();
        }
    }

    public void setMenuText(String text) {
        if (mToolbarLayout != null) {
            mToolbarLayout.setMenuText(text);
        }
    }

    public void showIconMenu() {
        if (mToolbarLayout != null) {
            mToolbarLayout.showIconMenu();
        }
    }

    public void hideIconMenu() {
        if (mToolbarLayout != null) {
            mToolbarLayout.hideIconMenu();
        }
    }

    public void setMenuIcon(@DrawableRes int resId) {
        if (mToolbarLayout != null) {
            mToolbarLayout.setMenuIcon(resId);
        }
    }

    public void onBackClicked() {
        finish();
    }

    public void onMenuClicked() {

    }

    /**
     * 加载对话框------------------------------------------------------------------------------------
     */
    public void showDialog() {
        showDialog("正在加载...");
    }

    public void showDialog(String message) {
        if (mDialog == null) {
            mDialog = new ProgressDialog(this);
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
        T.showShort(this, msg);
    }

    public void toast(@StringRes int resId) {
        T.showShort(this, resId);
    }

    /**
     * Activity跳转-----------------------------------------------------------------------------------
     */

    public void gotoActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    public void gotoActivity(Intent intent) {
        startActivity(intent);
    }

    public void gotoActivityAndFinish(Intent intent) {
        startActivity(intent);
        finish();
    }

    public void finishActivity() {
        finish();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
    }

    protected boolean viewVisible(View view) {

        if (view != null && view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
            return true;
        }

        return false;
    }

    protected boolean viewGone(View view) {

        if (view != null && view.getVisibility() != View.GONE) {
            view.setVisibility(View.GONE);
            return true;
        }

        return false;
    }
}
