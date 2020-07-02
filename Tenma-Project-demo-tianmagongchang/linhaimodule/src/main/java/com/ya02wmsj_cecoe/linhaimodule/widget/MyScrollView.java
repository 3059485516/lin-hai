package com.ya02wmsj_cecoe.linhaimodule.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import com.socks.library.KLog;

/**
 * @author Yang Shihao
 */

public class MyScrollView extends NestedScrollView {
    private long mDelay = 100;
    private long mLastScrollTime = -1;
    private ScrollStateListener mScrollStateListener;
    private int mTouchSlop;
    private int downX;
    private int downY;


    public void setScrollStateListener(ScrollStateListener scrollStateListener) {
        mScrollStateListener = scrollStateListener;
    }

    private Runnable scrollerTask = new Runnable() {
        @Override
        public void run() {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - mLastScrollTime) > 100) {
                mLastScrollTime = -1;
                if (mScrollStateListener != null) {
                    mScrollStateListener.scrollEnd();
                }
            } else {
                postDelayed(this, mDelay);
            }
        }
    };

    public MyScrollView(@NonNull Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public MyScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) e.getRawX();
                downY = (int) e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) e.getRawY();
                if (Math.abs(moveY - downY) > mTouchSlop) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(e);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        KLog.d("NestedScrollView1111111", (t - oldt) + "");
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollStateListener != null) {
            mScrollStateListener.scrollChanged(t, oldt);
        }

        if (mLastScrollTime == -1) {
            if (mScrollStateListener != null) {
                mScrollStateListener.scrollStart();
                postDelayed(scrollerTask, mDelay);
            }
        }
        // 更新ScrollView的滑动时间
        mLastScrollTime = System.currentTimeMillis();
    }

    public interface ScrollStateListener {
        void scrollStart();

        void scrollEnd();

        void scrollChanged(int t, int oldt);
    }
}
