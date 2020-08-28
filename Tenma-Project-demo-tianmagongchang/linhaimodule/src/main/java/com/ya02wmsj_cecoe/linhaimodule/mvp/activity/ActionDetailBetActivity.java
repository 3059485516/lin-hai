package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.text.Html;
import android.widget.Button;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseWebViewActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.ActionDetailBetEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ActionDetailBetContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ActionDetailBetPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.DateUtil;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.RatioImageView;


public class ActionDetailBetActivity extends BaseWebViewActivity<ActionDetailBetContract.Presenter> implements ActionDetailBetContract.View {
    protected RatioImageView mIvTop;
    protected TextView mTvTitle;
    protected TextView mTvArea;
    protected TextView mTvTime;
    protected TextView mTvZZ;
    protected TextView mTvLXDH;
    protected TextView mTvFZR;
    protected TextView mTvBMRS;
    protected Button mBtn;

    private int mStatus = 0;

    @Override
    protected boolean canOverrideUrlLoading() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_action_detail_bet;
    }

    @Override
    protected void initMVP() {
        mPresenter = new ActionDetailBetPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("活动详情");
        mIvTop = findViewById(R.id.iv_top);
        mTvTitle = findViewById(R.id.tv_title);
        mTvArea = findViewById(R.id.tv_area);
        mTvTime = findViewById(R.id.tv_time);
        mBtn = findViewById(R.id.btn_join);

        mTvZZ = findViewById(R.id.tv_zz);
        mTvLXDH = findViewById(R.id.tv_lxdh);
        mTvFZR = findViewById(R.id.tv_fzr);
        mTvBMRS = findViewById(R.id.tv_bmrs);

        mBtn.setOnClickListener(v -> {
            if (0 == mStatus) {
                mPresenter.signupRecruit(getIntent().getStringExtra(Constant.KEY_STRING_1));
            } else {
                mPresenter.signoutRecruit(getIntent().getStringExtra(Constant.KEY_STRING_1));
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getRecruitDetail(getIntent().getStringExtra(Constant.KEY_STRING_1));
    }

    @Override
    public void updateView(ActionDetailBetEntity entity) {
        mStatus = entity.getIs_signup();
        if (mStatus == 0) {
            mBtn.setText("报名");
        } else {
            mBtn.setText("取消报名");
        }
        ImageManager.getInstance().loadImage(this, entity.getThumb(), R.mipmap.ya02wmsj_cecoe_placeholder, mIvTop);
        mTvTitle.setText(entity.getName());
        mTvArea.setText(entity.getAddress());
        mTvTime.setText(DateUtil.getTimeStr(entity.getAct_start_time()));
        mTvZZ.setText(entity.getDepartment_name());
        mTvLXDH.setText(entity.getContact_phone());
        mTvFZR.setText(entity.getContact());
        setHtml(entity.getContent());

        StringBuilder htmlText = new StringBuilder();
        htmlText.append("人数：<font color='#").append(Integer.toHexString(getResources().getColor(R.color.yl_red)).substring(2)).append("'>").append(entity.getSignup_people()).append("</font>").append(" / ").append(entity.getRecruit_people());

        mTvBMRS.setText(Html.fromHtml(htmlText.toString()));
    }
}
