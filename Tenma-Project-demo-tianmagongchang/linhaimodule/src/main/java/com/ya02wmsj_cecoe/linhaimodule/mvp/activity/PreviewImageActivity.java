package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.PreviewImageAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;

import java.util.List;

public class PreviewImageActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    TextView mTvIndex;

    ViewPager mViewPager;

    TextView mTvTitle;

    private int mTotalSize;
    private boolean mShowTitle = false;
    private List<String> mTitleList;

    @Override
    protected boolean fullScreen() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_preview_image;
    }

    @Override
    protected void initMVP() {
    }

    @Override
    protected void initView() {
        mTvIndex = findViewById(R.id.tv_index);
        mViewPager = findViewById(R.id.vp);
        mTvTitle = findViewById(R.id.tv_title);
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        List images = intent.getStringArrayListExtra(Constant.KEY_BEAN);
        if (images == null || images.size() == 0) {
            return;
        }
        mTotalSize = images.size();
        mTitleList = intent.getStringArrayListExtra(Constant.KEY_BEAN_2);
        int index = intent.getIntExtra(Constant.KEY_INT_1, 0);
        if (mTitleList == null || mTitleList.size() == 0) {
            mTvTitle.setVisibility(View.GONE);
        } else {
            mShowTitle = true;
            mTvTitle.setVisibility(View.VISIBLE);
            showImageDesc(index);
        }
        mViewPager.setAdapter(new PreviewImageAdapter(this, images));
        mViewPager.addOnPageChangeListener(this);
        if (index < mTotalSize) {
            mViewPager.setCurrentItem(index);
            mTvIndex.setText(String.format("%d/%d", (index + 1), mTotalSize));
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onPageSelected(int position) {
        mTvIndex.setText(String.format("%d/%d", position + 1, mTotalSize));
        showImageDesc(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    private void showImageDesc(int position) {
        if (mShowTitle) {
            if (position < mTitleList.size()) {
                mTvTitle.setVisibility(View.VISIBLE);
                mTvTitle.setText(mTitleList.get(position));
            } else {
                mTvTitle.setVisibility(View.GONE);
            }
        }
    }
}
