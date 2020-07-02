package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.view.View;

import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.widget.SoonCoverVideo;


public class LiveActivity extends BaseActivity {
    SoonCoverVideo mVideo;

    private String mTitle;
    private String mPlayUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_live_activity;
    }

    @Override
    protected boolean hasActionBar() {
        return false;
    }

    @Override
    protected void initMVP() {

    }

    @Override
    protected boolean fullScreen() {
        return true;
    }

    @Override
    protected void initView() {
        mTitle = getIntent().getStringExtra(Constant.KEY_STRING_1);
        mPlayUrl = getIntent().getStringExtra(Constant.KEY_STRING_2);
        mVideo = findViewById(R.id.cover_video);
        mVideo.getTitleTextView().setText(mTitle);
        mVideo.setUp(mPlayUrl, false, "");
        mVideo.startPlayLogic();

        mVideo.getBackButton().setVisibility(View.GONE);
        mVideo.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });

        //设置全屏按键功能
        mVideo.getFullscreenButton().setVisibility(View.GONE);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }
}
