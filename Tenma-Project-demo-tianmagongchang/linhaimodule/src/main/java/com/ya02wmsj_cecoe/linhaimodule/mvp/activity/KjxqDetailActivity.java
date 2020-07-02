package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseWebViewActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.KjxqDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.KjcgDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.KjxqDetailPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.RatioImageView;

public class KjxqDetailActivity extends BaseWebViewActivity<KjxqDetailPresenter> implements KjcgDetailContract.View {
    private RatioImageView mTopImg;
    private TextView mTvTitle, mTvSource, mTvAuthor, mTvTime;

    @Override
    protected boolean canOverrideUrlLoading() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_detail_zhkx;
    }

    @Override
    protected void initMVP() {
        mPresenter = new KjxqDetailPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("科技需求");
        mTopImg = findViewById(R.id.iv_top);
        mTvTitle = findViewById(R.id.tv_title);
        mTvSource = findViewById(R.id.tv_source);
        mTvAuthor = findViewById(R.id.tv_name_phone);
        mTvTime = findViewById(R.id.tv_area_time);
    }

    @Override
    protected void initData() {
        mPresenter.getDetail(getIntent().getStringExtra(Constant.KEY_STRING_1));
    }

    @Override
    public void updateView(Object entity) {
        KjxqDetailEntity detail = (KjxqDetailEntity) entity;
        if (TextUtils.isEmpty(detail.getHeadImage())) {
            mTopImg.setVisibility(View.GONE);
        } else {
            mTopImg.setVisibility(View.VISIBLE);
            ImageManager.getInstance().loadImage(this, detail.getHeadImage(), R.mipmap.ya02wmsj_cecoe_placeholder, mTopImg);
        }
        mTvTitle.setText(detail.getTitle());
        mTvSource.setText(detail.getCreateDate());
        mTvAuthor.setText(detail.getUsername());
        mTvTime.setText(detail.getMobile());
        setHtml(detail.getContent());
    }
}
