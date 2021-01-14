package com.ya02wmsj_cecoe.linhaimodule.base.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.other.MJavascriptInterface;

/**
 * WebView 基础页面
 *
 * @param <P>
 */
public abstract class BaseWebViewActivity<P extends APresenter> extends BaseActivity<P> {
    protected WebView mWebView;

    protected abstract boolean canOverrideUrlLoading();

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_base_web_view;
    }

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    @Override
    protected void initUI() {
        super.initUI();
        mWebView = findViewById(R.id.webView);
        mWebView.setClickable(false);
        mWebView.setFocusable(true);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.addJavascriptInterface(new MJavascriptInterface(mContext), "imagelistener");
        WebSettings settings = mWebView.getSettings();
        settings.setDefaultFontSize(16);
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        settings.setDisplayZoomControls(false);
        settings.setUseWideViewPort(false);
        settings.setBuiltInZoomControls(false);
        settings.setAllowFileAccess(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        settings.setLoadWithOverviewMode(true);
        settings.setBlockNetworkImage(false);
        settings.setPluginState(WebSettings.PluginState.ON);

        // 清缓存和记录，缓存引起的白屏
        mWebView.clearCache(true);
        mWebView.clearHistory();
        settings.setDomStorageEnabled(true);//开启本地DOM存储
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (canOverrideUrlLoading()) return super.shouldOverrideUrlLoading(view, url);
                if (url != null) {//自定义跳转
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
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
                int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                // 重新测量
                mWebView.measure(w, h);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
    }

    protected void setHtml(String content) {
        String curContent = Constant.HTML_START_WITH_CLICK + content + Constant.HTML_END;
        mWebView.loadDataWithBaseURL(null, curContent, "text/html", "utf-8", null);
    }
}
