package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.widget.Button;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseWebViewActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.ActionRecruitEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ActionDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ActionDetailPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;


/**
 * Created by BenyChan on 2019-07-23.
 */
public class ActionDetailActivity extends BaseWebViewActivity<ActionDetailContract.Presenter> implements ActionDetailContract.View {
    protected TextView mTvTitle;

    protected TextView mTvSource;

    protected YLTextViewGroup mTvTime;

    protected YLTextViewGroup mTvAddress;

    protected YLTextViewGroup mTvUser;

  /*  @BindView(R2.id.tv_tel)
    protected YLTextViewGroup mTvTel;*/

    protected Button mBtnApply;

    @Override
    protected boolean canOverrideUrlLoading() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_action_detail;
    }

    @Override
    protected void initMVP() {
        mPresenter = new ActionDetailPresenter(this);
    }

    @Override
    protected void initView() {
        ActionRecruitEntity entity = (ActionRecruitEntity) getIntent().getSerializableExtra(Constant.KEY_BEAN);
        mTvTitle = findViewById(R.id.tv_title);
        mTvSource = findViewById(R.id.tv_source);
        mTvTime = findViewById(R.id.tv_time);
        mTvAddress = findViewById(R.id.tv_address);
        mTvUser = findViewById(R.id.tv_user);
        mBtnApply = findViewById(R.id.btn_apply);
        if (entity != null) {
            mTvTitle.setText(entity.getService_name());
            mTvSource.setText(entity.getStatus());
            mTvTime.setTextRight(entity.getStart_time());
            mTvAddress.setTextRight(entity.getAddress());
            mTvUser.setTextRight(entity.getName());
            setHtml(entity.getContent());
        }
        setTitle("活动详情");
        mBtnApply.setOnClickListener(v -> {
            if (!"志愿者".equals(Config.getInstance().getUserGroupName())) {
                gotoActivity(VolunteerActivity.class);
            } else {
                mPresenter.activitySign(entity.getId());
            }
        });
    }

    @Override
    protected void initData() {

    }
}
