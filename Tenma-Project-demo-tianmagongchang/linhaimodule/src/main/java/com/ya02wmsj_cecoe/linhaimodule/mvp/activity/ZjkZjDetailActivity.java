package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.ZjkZjDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ZjkZjDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.ZjkZjDetailPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.DateUtil;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;

import static com.ya02wmsj_cecoe.linhaimodule.utils.DateUtil.FORMAT_YMD;

public class ZjkZjDetailActivity extends BaseActivity<ZjkZjDetailContract.Presenter> implements ZjkZjDetailContract.View {
    private ImageView mIvIcon;
    private TextView mTvName, mTvTitle, mTvContent;
    private YLTextViewGroup mTvBirth, mTvEmail, mTvPhone, mTvYear, mTvField, mTvUnit;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_zjk_zj_detail_activity;
    }

    @Override
    protected void initMVP() {
        mPresenter = new ZjkZjDetailPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("专家详情");
        mIvIcon = findViewById(R.id.iv_icon);
        mTvName = findViewById(R.id.tv_name);
        mTvTitle = findViewById(R.id.tv_title);
        mTvContent = findViewById(R.id.tv_content);
        mTvBirth = findViewById(R.id.tv_birth);
        mTvEmail = findViewById(R.id.tv_email);
        mTvPhone = findViewById(R.id.tv_phone);
        mTvYear = findViewById(R.id.tv_work_year);
        mTvField = findViewById(R.id.tv_work_field);
        mTvUnit = findViewById(R.id.tv_work_unit);
    }

    @Override
    protected void initData() {
        mPresenter.queryExpertDetail(getIntent().getStringExtra(Constant.KEY_STRING_1));
    }

    @Override
    public void updateView(ZjkZjDetailEntity entity) {
        ImageManager.getInstance().loadCircleImage(this, entity.getHeadImg(), R.mipmap.ya02wmsj_cecoe_head, mIvIcon);
        mTvName.setText(entity.getName());
        mTvTitle.setText(entity.getTitle());
        mTvContent.setText("简介：" + entity.getContent());
        mTvBirth.setTextRight(DateUtil.getTimeStr(entity.getBirthdate(), FORMAT_YMD));
        mTvEmail.setTextRight(entity.getEmail());
        mTvPhone.setTextRight(entity.getMobeli());
        mTvYear.setTextRight(entity.getWorkinglife());
        mTvField.setTextRight(entity.getResearchfield());
        mTvUnit.setTextRight(entity.getWorkunit());
    }
}
