package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseWebViewActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.KjcgDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.KjcgDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.KjcgDetailPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.RatioImageView;


public class KjcgDetailActivity extends BaseWebViewActivity<KjcgDetailPresenter> implements KjcgDetailContract.View {
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
        mPresenter = new KjcgDetailPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("科研成果");
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
        KjcgDetailEntity kjcgDetailEntity = (KjcgDetailEntity) entity;
        if (TextUtils.isEmpty(kjcgDetailEntity.getImage())) {
            mTopImg.setVisibility(View.GONE);
        } else {
            mTopImg.setVisibility(View.VISIBLE);
            ImageManager.getInstance().loadImage(this, kjcgDetailEntity.getImage(), R.mipmap.ya02wmsj_cecoe_placeholder, mTopImg);
        }
        mTvTitle.setText(kjcgDetailEntity.getTitle());
        mTvSource.setText(kjcgDetailEntity.getPlace());
        mTvAuthor.setText(kjcgDetailEntity.getAuthors());
        mTvTime.setText(kjcgDetailEntity.getPhone());
        setHtml(kjcgDetailEntity.getContent());
    }
}
