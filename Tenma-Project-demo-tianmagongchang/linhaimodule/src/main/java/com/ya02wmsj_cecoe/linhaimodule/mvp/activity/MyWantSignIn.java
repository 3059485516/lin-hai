package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.annotation.SuppressLint;
import android.view.KeyEvent;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseWebViewActivity;


public class MyWantSignIn extends BaseWebViewActivity {
    private String returnHtmlUrl = "https://lhwmsj.hzyltx.com/application/ya02wmsj_cecoe/h5/h5LinkApp.html";

    @Override
    protected boolean canOverrideUrlLoading() {
        return false;
    }

    @Override
    protected void initMVP() {
    }

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    @Override
    protected void initUI() {
        if (mToolbarLayout != null) {
            mToolbarLayout.setOnClickListener(v -> onBackClicked(), v -> onMenuClicked());
        }
        if (mIvBack != null) {
            mIvBack.setOnClickListener(v -> onBackClicked());
        }
        mWebView = findViewById(R.id.webView);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView() {
        setTitle("我要签到");
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (returnHtmlUrl.equals(url)){
                    finishActivity();
                    return false;
                }else {
                    view.loadUrl(url);
                    return super.shouldOverrideUrlLoading(view, url);
                }
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            //配置权限（同样在WebChromeClient中实现）
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }
        });
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initData() {
        StringBuilder stringBuilder = new StringBuilder("https://appapi.zyh365.com/quick-sign/index.html?token=");
        stringBuilder.append(Config.getInstance().getVolunteerToken());
        stringBuilder.append("&mobile_unique=18267839955&redirectUri=");
        stringBuilder.append(returnHtmlUrl);
        stringBuilder.append("&title=%E5%BF%97%E6%84%BF%E6%B1%87&comment=0");
        mWebView.loadUrl(stringBuilder.toString());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            // 返回前一个页面
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
