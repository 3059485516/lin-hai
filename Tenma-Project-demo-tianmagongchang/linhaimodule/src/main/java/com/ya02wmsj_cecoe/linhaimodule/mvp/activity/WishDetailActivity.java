package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.WishProcessAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.WishDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.WishDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.WishDetailPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.RatioImageView;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLEditTextGroup;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by BenyChan on 2019-08-15.
 */
public class WishDetailActivity extends BaseActivity<WishDetailContract.Presenter> implements WishDetailContract.View {
    protected RatioImageView mIvTop;

    protected TextView mTvTitle;

    protected TextView mTvDesc;

    protected TextView mTvPhone;

    protected RecyclerView mRvProcess;

    protected YLEditTextGroup mTvApplyName;

    protected YLEditTextGroup mTvApplyPhone;

    protected YLEditTextGroup mTvApplyAddr;

    protected Button mBtnApply;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activty_wish_detail;
    }

    @Override
    protected void initMVP() {
        mPresenter = new WishDetailPresenter(this, getIntent().getStringExtra(Constant.KEY_STRING_1));
    }

    @Override
    protected void initView() {
        setTitle("微心愿");
        mIvTop = findViewById(R.id.iv_top);
        mTvTitle = findViewById(R.id.tv_title);
        mTvPhone = findViewById(R.id.tv_phone);
        mTvDesc = findViewById(R.id.tv_desc);
        mRvProcess = findViewById(R.id.rv_process);
        mTvApplyName = findViewById(R.id.tv_apply_name);
        mTvApplyPhone = findViewById(R.id.tv_apply_phone);
        mTvApplyAddr = findViewById(R.id.tv_apply_addr);
        mBtnApply = findViewById(R.id.btn_apply);


        mRvProcess.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.btn_apply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 认领
                if (TextUtils.isEmpty(mTvApplyName.getTextRight())) {
                    toast("请填写申请人姓名");
                    return;
                }
                if (TextUtils.isEmpty(mTvApplyPhone.getTextRight())) {
                    toast("请填写申请人电话");
                    return;
                }
                if (TextUtils.isEmpty(mTvApplyAddr.getTextRight())) {
                    toast("请填写申请人地址");
                    return;
                }
                if (TextUtils.isEmpty(Config.getInstance().getRegionCode())) {
                    toast("请先绑定区域");
                    return;
                }
                Map<String, Object> params = new HashMap<>();
                params.put("wish_id", getIntent().getStringExtra(Constant.KEY_STRING_1));
                params.put("region_code", Config.getInstance().getRegionCode());
                params.put("name", mTvApplyName.getTextRight());
                params.put("phone", mTvApplyPhone.getTextRight());
                params.put("address", mTvApplyAddr.getTextRight());
                mPresenter.applyWish(params);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getWishDetail();
    }

    @Override
    public void onMenuClicked() {
        Intent intent = new Intent(this, FinishWishActivity.class);
        intent.putExtra(Constant.KEY_STRING_1, getIntent().getStringExtra(Constant.KEY_STRING_1));
        gotoActivity(intent);
    }

    @Override
    public void updateDetail(WishDetailEntity entity) {
        if (!TextUtils.isEmpty(entity.getPath())) {
            String url = entity.getIcon_path();
            if (!url.contains("http") && !url.contains("HTTP")) {
                url = Constant.getBaseUrl() + url;
            }
            ImageManager.getInstance().loadImage(this, url, mIvTop);
        }
        mTvTitle.setText(entity.getTitle());
        mTvDesc.setText(entity.getDesc());
        mTvPhone.setText(entity.getName() + "   " + entity.getPhone());
        if (entity.getClaim_info() != null) {
            mTvApplyName.setTextRight(entity.getClaim_info().getName());
            mTvApplyPhone.setTextRight(entity.getClaim_info().getPhone());
            mTvApplyAddr.setTextRight(entity.getClaim_info().getAddress());
            mTvApplyName.setEnableEidt(false);
            mTvApplyPhone.setEnableEidt(false);
            mTvApplyAddr.setEnableEidt(false);
            if (entity.getClaim_info().getUser_id().equals(Config.getInstance().getUser().getUuid())) {
                setMenuText("办结心愿");
            }
        }
        if (entity.getProcess_info() != null) {
            WishProcessAdapter adapter = new WishProcessAdapter(this, entity.getProcess_info());
            mRvProcess.setAdapter(adapter);
        }
        if ("待认领".equals(entity.getStatus())) {
            mBtnApply.setVisibility(View.VISIBLE);
            mTvApplyName.setEnableEidt(true);
            mTvApplyPhone.setEnableEidt(true);
            mTvApplyAddr.setEnableEidt(true);
        } else {
            mBtnApply.setVisibility(View.GONE);
            mTvApplyName.setEnableEidt(false);
            mTvApplyPhone.setEnableEidt(false);
            mTvApplyAddr.setEnableEidt(false);
        }
    }
}
