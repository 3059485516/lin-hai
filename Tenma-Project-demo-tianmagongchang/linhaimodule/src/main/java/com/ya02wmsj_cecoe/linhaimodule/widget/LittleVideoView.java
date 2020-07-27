package com.ya02wmsj_cecoe.linhaimodule.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.ya02wmsj_cecoe.linhaimodule.R;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/7/21 1:45 PM
 * desc   : EMPTY
 * ================================================
 */
public class LittleVideoView extends StandardGSYVideoPlayer {
    private ImageView ivThumb;

    public LittleVideoView(Context context, Boolean fullFlag) {
        super(context, fullFlag);
        init();
    }

    public LittleVideoView(Context context) {
        super(context);
        init();
    }

    public LittleVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    public int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_empty_control_video;
    }

    private void init() {
        ivThumb = findViewById(R.id.thumbImage);
        /*mTextureViewContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getGSYVideoManager().isPlaying()) {
                    getGSYVideoManager().pause();
                    mStartButton.setVisibility(View.VISIBLE);
                } else {
                    getGSYVideoManager().start();
                    mStartButton.setVisibility(View.GONE);
                }
            }
        });*/
    }

    public ImageView getIvThumb() {
        return ivThumb;
    }

    /**下方两个重载方法，在播放开始前不屏蔽封面*/
    @Override
    public void onSurfaceUpdated(Surface surface) {
        super.onSurfaceUpdated(surface);
        if (mThumbImageViewLayout != null && mThumbImageViewLayout.getVisibility() == VISIBLE) {
            mThumbImageViewLayout.setVisibility(INVISIBLE);
        }
    }

    @Override
    protected void setViewShowState(View view, int visibility) {
        if (view == mThumbImageViewLayout && visibility != VISIBLE) {
            return;
        }
        super.setViewShowState(view, visibility);
    }
}
