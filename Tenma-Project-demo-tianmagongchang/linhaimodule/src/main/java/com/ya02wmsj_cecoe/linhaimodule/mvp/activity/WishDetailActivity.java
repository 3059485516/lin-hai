package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.annotation.SuppressLint;
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

/**
 * Created by BenyChan on 2019-08-15
 */
public class WishDetailActivity extends BaseActivity<WishDetailContract.Presenter> implements WishDetailContract.View {
    protected RatioImageView mIvTop;
    protected TextView mTvTitle;
    protected TextView mTvDesc;
    protected TextView mTvPhone;
    protected RecyclerView mRvProcess;
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
        mBtnApply = findViewById(R.id.btn_apply);
        mRvProcess.setLayoutManager(new LinearLayoutManager(this));
        findViewById(R.id.btn_apply).setOnClickListener(v -> {
            WishDetailDialog wishDetailDialog = new WishDetailDialog(mContext);
            wishDetailDialog.setWishDetailCall(params -> {
                params.put("wish_id", getIntent().getStringExtra(Constant.KEY_STRING_1));
                mPresenter.applyWish(params);
                wishDetailDialog.dismiss();
            });
            wishDetailDialog.show();
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

    @SuppressLint("SetTextI18n")
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
        mTvPhone.setVisibility(View.GONE);
        if (entity.getClaim_info() != null) {
            mTvPhone.setVisibility(View.VISIBLE);
            mTvPhone.setText(entity.getName() + "   " + entity.getPhone());
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
        } else {
            mBtnApply.setVisibility(View.GONE);
        }
    }
}
