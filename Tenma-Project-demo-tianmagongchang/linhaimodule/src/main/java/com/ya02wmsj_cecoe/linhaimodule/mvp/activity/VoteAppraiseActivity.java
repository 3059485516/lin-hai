package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.VideoPath;
import com.ya02wmsj_cecoe.linhaimodule.bean.VoteEntity;
import com.ya02wmsj_cecoe.linhaimodule.other.MJavascriptInterface;
import com.ya02wmsj_cecoe.linhaimodule.utils.DisplayUtils;
import com.ya02wmsj_cecoe.linhaimodule.widget.CoverVideo;

/**
 * 评选投票 视频和图文信息
 */
public class VoteAppraiseActivity extends BaseActivity {
    private CoverVideo coverVideo;
    private WebView webView;
    private ImageView iv_back;
    private VoteEntity voteEntity;

    @Override
    protected boolean fullScreen() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_dialog_vote_appraise;
    }

    @Override
    protected void initMVP() {
    }

    @Override
    protected void initView() {
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = DisplayUtils.getScreenWidth(this);
        params.height = (int) (DisplayUtils.getScreenHeight(this) * 0.5);
        window.setAttributes(params);

        voteEntity = (VoteEntity) getIntent().getSerializableExtra(Constant.KEY_BEAN);

        coverVideo = findViewById(R.id.coverView);
        webView = findViewById(R.id.webView);
        iv_back = findViewById(R.id.iv_back);

        iv_back.setOnClickListener(v -> {
            finish();
        });

        webView.setClickable(false);
        webView.setFocusable(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.addJavascriptInterface(new MJavascriptInterface(mContext), "imagelistener");
        WebSettings settings = webView.getSettings();
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
        webView.clearCache(true);
        webView.clearHistory();
        settings.setDomStorageEnabled(true);//开启本地DOM存储
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存
    }

    @Override
    protected void initData() {
        VideoPath video_path = voteEntity.getVideo_path();
        if (video_path != null) {
            webView.setVisibility(View.GONE);
            coverVideo.setVisibility(View.VISIBLE);
            //设置返回键
            coverVideo.getBackButton().setVisibility(View.GONE);
            coverVideo.setUp(video_path.getOrigUrl(), false, "");
            coverVideo.startPlayLogic();
        } else {
            webView.setVisibility(View.VISIBLE);
            coverVideo.setVisibility(View.GONE);
            String content = Constant.HTML_START_WITH_CLICK + voteEntity.getContent() + Constant.HTML_END;
            webView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (coverVideo != null) {
            coverVideo.release();
            coverVideo = null;
        }
    }
}
