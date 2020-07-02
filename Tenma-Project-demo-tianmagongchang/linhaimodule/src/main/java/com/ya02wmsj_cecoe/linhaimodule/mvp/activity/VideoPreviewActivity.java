package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.VideoView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.widget.loading.AVLoadingIndicatorView;


/**
 * 视频播放
 */
public class VideoPreviewActivity extends BaseActivity
        implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener {

    protected VideoView mVideoView;

    protected ImageView mIvPlay;

    protected AVLoadingIndicatorView mProgress;

    @Override
    protected void onStop() {
        super.onStop();
        if (mVideoView.isPlaying()) {
            mVideoView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        mVideoView.stopPlayback();
        super.onDestroy();
    }

    @Override
    protected boolean fullScreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_video_preview;
    }

    @Override
    protected void initMVP() {
    }

    @Override
    protected void initView() {
        mVideoView = findViewById(R.id.video);
        mIvPlay = findViewById(R.id.iv_play);
        mProgress = findViewById(R.id.progress);
        mIvPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoView.start();
                mIvPlay.setVisibility(View.GONE);
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        }
        String path = intent.getStringExtra(Constant.KEY_STRING_1);
        if (TextUtils.isEmpty(path)) {
            return;
        }
        mVideoView.setOnPreparedListener(this);
        mVideoView.setOnErrorListener(this);
        mVideoView.setOnCompletionListener(this);
        mVideoView.setVideoPath(path);
        mVideoView.start();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mVideoView.start();
        mIvPlay.setVisibility(View.GONE);
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        mIvPlay.setVisibility(View.VISIBLE);
        return true;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mIvPlay.setVisibility(View.VISIBLE);
    }
}
