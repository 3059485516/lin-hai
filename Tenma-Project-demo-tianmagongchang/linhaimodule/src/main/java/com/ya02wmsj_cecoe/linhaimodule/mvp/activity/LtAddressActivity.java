package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;


import java.net.URLEncoder;

public class LtAddressActivity extends BaseActivity {
    protected BridgeWebView mWebView;


    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_web_bridge;
    }

    @Override
    protected void initMVP() {

    }

    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility", "JavascriptInterface"})
    @Override
    @JavascriptInterface
    protected void initView() {
        setTitle("礼堂地图");

        mWebView = findViewById(R.id.webView);
        mWebView.setClickable(false);
        mWebView.setFocusable(true);
        mWebView.setFocusableInTouchMode(true);
        mWebView.setHorizontalScrollBarEnabled(false);
        // 清缓存和记录，缓存引起的白屏
        mWebView.clearCache(true);
        mWebView.clearHistory();
        mWebView.addJavascriptInterface(this, "android");

        WebSettings settings = mWebView.getSettings();
        settings.setDefaultFontSize(16);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        settings.setDisplayZoomControls(false);
        settings.setBuiltInZoomControls(false);
        settings.setAllowFileAccess(true);
        settings.setBlockNetworkImage(false);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setDomStorageEnabled(true);//开启本地DOM存储
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showDialog();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dismissDialog();
            }
        });

//        mWebView.loadUrl("http://hzyltx.zhfucun.com/xsd-h5/index.html#/ltMap");
        mWebView.loadUrl("https://lhwhlt.hzyltx.com:8443/application/ya02lhwhlt_wdhaw/camap/index.html");

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @JavascriptInterface
    public void gotoLtResourceActivity(String id) {
        Log.i("Handler-result-test = ", id);
        Intent intent = new Intent(mContext, LtDetailActivity.class);
        intent.putExtra(Constant.KEY_STRING_1, id);
        intent.putExtra(Constant.KEY_STRING_2, "礼堂详情");
        mContext.startActivity(intent);
    }
}
