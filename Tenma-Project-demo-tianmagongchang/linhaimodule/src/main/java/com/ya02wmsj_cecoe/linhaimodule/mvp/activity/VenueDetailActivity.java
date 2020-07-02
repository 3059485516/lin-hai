package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseWebViewActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.VenueDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.VenueEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.VenueDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.VenueDetailPresenter;


/**
 * Created by BenyChan on 2019-07-26.
 */
public class VenueDetailActivity extends BaseWebViewActivity<VenueDetailContract.Presenter> implements VenueDetailContract.View {
    protected TextView mTvTitle;

    protected TextView mTvSource;

    protected TextView mTvNamePhone;

    protected TextView mTvAreaTime;

    @Override
    protected boolean canOverrideUrlLoading() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_venue_detail;
    }

    @Override
    protected void initMVP() {
        VenueEntity entity = (VenueEntity) getIntent().getSerializableExtra(Constant.KEY_BEAN);
        mPresenter = new VenueDetailPresenter(this, entity.getId());
    }

    @Override
    protected void initView() {
        setTitle("场馆详情");
        mTvTitle = findViewById(R.id.tv_title);
        mTvSource = findViewById(R.id.tv_source);
        mTvNamePhone = findViewById(R.id.tv_name_phone);
        mTvAreaTime = findViewById(R.id.tv_area_time);
    }

    @Override
    protected void initData() {
        mPresenter.getStadiaDetail();
    }

    @Override
    public void update(VenueDetailEntity entity) {
        setHtml(entity.getDesc());
        mTvTitle.setText(entity.getName());
        mTvSource.setText("电话：" + entity.getPhone());
        mTvNamePhone.setText(entity.getAddress());
        mTvAreaTime.setText(entity.getCtime());
    }
}
