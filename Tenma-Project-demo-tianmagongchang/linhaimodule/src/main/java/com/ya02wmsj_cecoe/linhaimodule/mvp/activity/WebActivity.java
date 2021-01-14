package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseWebViewActivity;

public class WebActivity extends BaseWebViewActivity {

    @Override
    protected void initMVP() {
    }

    @Override
    protected void initView() {
        setTitle(getIntent().getStringExtra(Constant.KEY_STRING_1));
        mWebView.loadUrl(getIntent().getStringExtra(Constant.KEY_STRING_2));
        mWebView.requestFocus();
    }

    @Override
    protected void initData() {
    }

    @Override
    public void onBackClicked() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected boolean canOverrideUrlLoading() {
        return true;
    }
}
