package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.annotation.SuppressLint;
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

import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

public class WebBridgeActivity extends BaseActivity {
    public static final String BRIDGE_HANDLE_NAME = "sendToken";
    protected BridgeWebView mWebView;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_web_bridge;
    }

    @Override
    protected void initMVP() {
    }

    @Override
    protected boolean hasActionBar() {
        return false;
    }

    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility", "JavascriptInterface"})
    @Override
    protected void initView() {
        mWebView = findViewById(R.id.webView);
        mWebView.setClickable(false);
        mWebView.setFocusable(true);
        mWebView.setFocusableInTouchMode(true);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.addJavascriptInterface(this, BRIDGE_HANDLE_NAME);
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
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
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

        mWebView.setDefaultHandler(new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.i("Handler-result = ", data);
            }
        });

        mWebView.registerHandler(BRIDGE_HANDLE_NAME, new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                Log.i("Handler-result = ", data);
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String desc = jsonObject.optString("token");
                    //保存志愿汇token
                    Config.getInstance().setVolunteerToken(desc);
                    finishActivity();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        String url = "https://m2.zyh365.com/"
                + "webproject/usercenter/login"
                + "?type=lhxsd&openid="
                + Config.getInstance().getUser().getUuid()
                + "&callbackurl="
                + toURLEncoded(Constant.getBaseUrl() + "application/ya02wmsj_cecoe/h5/enter.html");
        mWebView.loadUrl(url);
    }

    @Override
    protected void initData() {
    }

    private String toURLEncoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            return "";
        }
        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return "";
    }

    @JavascriptInterface
    public void refreshToken(String str) {
        //保存志愿汇token
        Config.getInstance().setVolunteerToken(str);
        finishActivity();
    }
}
