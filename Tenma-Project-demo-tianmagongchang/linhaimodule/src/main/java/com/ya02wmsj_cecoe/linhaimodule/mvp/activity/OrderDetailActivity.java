package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.view.View;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseWebViewActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrderDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.VolunteerOrderEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OrderDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.OrderDetailPresenter;


/**
 * Created by BenyChan on 2019-07-27.
 */
public class OrderDetailActivity extends BaseWebViewActivity<OrderDetailContract.Presenter> implements OrderDetailContract.View {
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
        return R.layout.ya02wmsj_cecoe_activity_order_detail;
    }

    @Override
    protected void initMVP() {
        mPresenter = new OrderDetailPresenter(this, getIntent().getStringExtra(Constant.KEY_STRING_1));
    }

    @Override
    protected void initView() {
        setTitle("志愿服务详情");
        mTvTitle = findViewById(R.id.tv_title);
        mTvSource = findViewById(R.id.tv_source);
        mTvNamePhone = findViewById(R.id.tv_name_phone);
        mTvAreaTime = findViewById(R.id.tv_area_time);
        findViewById(R.id.btn_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点单
                mPresenter.addOrder();
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getServiceOrderDetail();
    }


    @Override
    public void update(OrderDetailEntity o) {
        mTvTitle.setText(o.getName());
        mTvSource.setText(o.getType());
        setHtml(o.getDesc().trim());
        mTvNamePhone.setText(o.getService_scope());
        mTvAreaTime.setText(o.getService_time());
    }
}
