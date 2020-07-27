package com.ya02wmsj_cecoe.linhaimodule.adapter.manage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/7/21 3:21 PM
 * desc   : EMPTY
 * ================================================
 */
public class PagerLayoutManager extends LinearLayoutManager implements RecyclerView.OnChildAttachStateChangeListener{
    private final PagerSnapHelper mSnapHelper;
    private OnPageChangedListener mOnPageChangedListener;

    /**
     * 移动方向标记
     */
    private int direction;

    public PagerLayoutManager(Context context) {
        super(context);
        mSnapHelper = new PagerSnapHelper();
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        mSnapHelper.attachToRecyclerView(view);
        view.addOnChildAttachStateChangeListener(this);
    }

    /**
     * 滑动状态改变监听，滑动完毕后进行播放控制
     *
     * @param state 滑动状态
     */
    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);

        if (state == RecyclerView.SCROLL_STATE_IDLE) {
            View view = mSnapHelper.findSnapView(this);
            if (view == null) {
                return;
            }
            int position = getPosition(view);
            if (mOnPageChangedListener != null && getChildCount() == 1) {
                mOnPageChangedListener.onPageSelected(position, position == getItemCount() - 1);
            }
        }

    }
    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        direction = dx;
        return super.scrollHorizontallyBy(dx, recycler, state);
    }
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        direction = dy;
        return super.scrollVerticallyBy(dy, recycler, state);
    }


    @Override
    public void onChildViewAttachedToWindow(@NonNull View view) {
        if (mOnPageChangedListener != null && getChildCount() == 1) {
            mOnPageChangedListener.onPageInitComplete();
        }
    }

    @Override
    public void onChildViewDetachedFromWindow(@NonNull View view) {
        if (mOnPageChangedListener != null) {
            mOnPageChangedListener.onPageRelease(getPosition(view), direction >= 0);
        }
    }

    public void setOnPageChangedListener(OnPageChangedListener mOnPageChangedListener) {
        this.mOnPageChangedListener = mOnPageChangedListener;
    }

    public interface OnPageChangedListener {

        /**
         * 初始化子布局加载完成
         */
        void onPageInitComplete();

        /**
         * 子布局脱离
         *
         * @param position 子布局在RecyclerView位置
         * @param isNext   是否有下一个
         */
        void onPageRelease(int position, boolean isNext);

        /**
         * 子布局附着
         *
         * @param position 子布局在RecyclerView位置
         * @param isLast   是否最后一个
         */
        void onPageSelected(int position, boolean isLast);
    }
}
