package com.ya02wmsj_cecoe.linhaimodule.base.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.VideoAllCallBack;
import com.shuyu.gsyvideoplayer.utils.GSYVideoType;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.manage.PagerLayoutManager;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.LittleVideoView;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/7/21 3:02 PM
 * desc   : EMPTY
 * ================================================
 */
public abstract class BaseLittleVideoActivity<P extends APresenter<?>> extends BaseActivity<P> implements PagerLayoutManager.OnPageChangedListener, VideoAllCallBack {
    protected LittleVideoView mVideoView;
    protected GSYVideoOptionBuilder mGsySmallVideoHelperBuilder;
    protected boolean isLoopPlay = false;
    protected ProgressBar mProgressBar;
    protected RecyclerView mRecyclerView;
    protected PagerLayoutManager mPagerLayoutManager;
    protected int mCurrentPosition;
    private ImageView mIvBack;
    protected TextView mRightText;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_base_little_video;
    }

    @Override
    protected boolean hasActionBar() {
        return false;
    }

    protected void onRightTextViewClicked(){
    }

    @Override
    protected void initUI() {
        super.initUI();
        mIvBack = findViewById(R.id.iv_back);
        mRightText = findViewById(R.id.tv_rightText);
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRightTextViewClicked();
            }
        });

        mProgressBar = findViewById(R.id.pb);
        mRecyclerView = findViewById(R.id.rv);
        initVideo();
        mPagerLayoutManager = new PagerLayoutManager(this);
        mPagerLayoutManager.setOnPageChangedListener(this);
        mRecyclerView.setLayoutManager(mPagerLayoutManager);
    }

    /**
     * 初始化播放器内容，采用了GSY播放器
     */
    private void initVideo() {
        mVideoView = new LittleVideoView(this);
        GSYVideoType.setShowType(GSYVideoType.SCREEN_TYPE_DEFAULT);
        mGsySmallVideoHelperBuilder = new GSYVideoOptionBuilder();
        mGsySmallVideoHelperBuilder
                .setLooping(isLoopPlay)
                .setCacheWithPlay(true)
                .setIsTouchWiget(false)
                .setVideoAllCallBack(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mVideoView != null)
            mVideoView.onVideoResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mVideoView != null)
            mVideoView.onVideoPause();
    }

    @Override
    protected void onDestroy() {
        if (mVideoView != null)
            mVideoView.release();
        super.onDestroy();
    }

    //------------------- {@link VideoAllCallBack} -------------------

    //开始加载，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onStartPrepared(String url, Object... objects) {

    }

    //加载成功，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onPrepared(String url, Object... objects) {
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                BaseViewHolder viewHolder = (BaseViewHolder) mRvLittleVideo.findViewHolderForLayoutPosition(mCurrentPosition);
                if (viewHolder != null) {
                    ImageView mVideoThumb = viewHolder.getView(R.id.iv_thumb_item);
                    if (mVideoThumb != null) {
                        mVideoThumb.setVisibility(View.INVISIBLE);
                    }
                }
            }
        }, 100);*/
    }

    //点击了开始按键播放，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onClickStartIcon(String url, Object... objects) {
    }

    //点击了错误状态下的开始按键，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onClickStartError(String url, Object... objects) {
    }

    //点击了播放状态下的开始按键--->停止，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onClickStop(String url, Object... objects) {
    }

    //点击了全屏播放状态下的开始按键--->停止，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onClickStopFullscreen(String url, Object... objects) {
    }

    //点击了暂停状态下的开始按键--->播放，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onClickResume(String url, Object... objects) {
    }

    //点击了全屏暂停状态下的开始按键--->播放，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onClickResumeFullscreen(String url, Object... objects) {
    }

    //点击了空白弹出seekbar，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onClickSeekbar(String url, Object... objects) {
    }

    //点击了全屏的seekbar，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onClickSeekbarFullscreen(String url, Object... objects) {
    }

    //播放完了，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onAutoComplete(String url, Object... objects) {
       /* if (!isLoopPlay) {
            if (mCurrentPosition + 1 < mLittleVideoAdapter.getItemCount()) {
                mRvLittleVideo.smoothScrollToPosition(mCurrentPosition + 1);
            }
        }*/
    }

    //进去全屏，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onEnterFullscreen(String url, Object... objects) {
    }

    //退出全屏，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onQuitFullscreen(String url, Object... objects) {
    }

    //进入小窗口，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onQuitSmallWidget(String url, Object... objects) {
    }

    //退出小窗口，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onEnterSmallWidget(String url, Object... objects) {
    }

    //触摸调整声音，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onTouchScreenSeekVolume(String url, Object... objects) {
    }

    //触摸调整进度，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onTouchScreenSeekPosition(String url, Object... objects) {
    }

    //触摸调整亮度，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onTouchScreenSeekLight(String url, Object... objects) {
    }

    //播放错误，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onPlayError(String url, Object... objects) {
    }

    //点击了空白区域开始播放，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onClickStartThumb(String url, Object... objects) {
    }

    //点击了播放中的空白区域，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onClickBlank(String url, Object... objects) {
    }

    //点击了全屏播放中的空白区域，objects[0]是title，object[1]是当前所处播放器（全屏或非全屏）
    @Override
    public void onClickBlankFullscreen(String url, Object... objects) {
    }
}
