package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrginazeDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OrginazeDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.OrginazeDetailPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;


public class OrginazeDetailActivity extends BaseActivity<OrginazeDetailPresenter> implements OrginazeDetailContract.View {
    ImageView mIvIcon;

    YLTextViewGroup mTvName;

    YLTextViewGroup mTvTime;

    YLTextViewGroup mTvPoints;

    YLTextViewGroup mTvRank;

    YLTextViewGroup mTvCount;

    YLTextViewGroup mTvArea;

    YLTextViewGroup mTvJoin;

    YLTextViewGroup mTvMain;

    Button mBtnJoin;

    private int join_status = 0;    //0-未加入， 1-已加入，2-审核中，3-申请未通过

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_orginaze_detail;
    }

    @Override
    protected void initMVP() {
        mPresenter = new OrginazeDetailPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("组织详情");
        mIvIcon = findViewById(R.id.iv_icon);
        mTvName = findViewById(R.id.tv_name);
        mTvTime = findViewById(R.id.tv_time);
        mTvPoints = findViewById(R.id.tv_points);
        mTvRank = findViewById(R.id.tv_rank);
        mTvCount = findViewById(R.id.tv_count);
        mTvArea = findViewById(R.id.tv_area);
        mTvJoin = findViewById(R.id.tv_join);
        mTvMain = findViewById(R.id.tv_main);
        mBtnJoin = findViewById(R.id.btn_join);

        mBtnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (join_status == 0) {
                    mPresenter.joinOrginize(getIntent().getStringExtra(Constant.KEY_STRING_1));
                } else if (join_status == 1) {
                    mPresenter.quitOrginize(getIntent().getStringExtra(Constant.KEY_STRING_1));
                } else {
                    toast("审核中，请勿重复申请");
                }
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getOrginazeDetail(getIntent().getStringExtra(Constant.KEY_STRING_1));
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mPresenter.getOrginazeDetail(getIntent().getStringExtra(Constant.KEY_STRING_1));
    }

    @Override
    public void updateView(OrginazeDetailEntity entity) {
        ImageManager.getInstance().loadCircleImage(this, entity.getAvatar(), R.mipmap.ya02wmsj_cecoe_head, mIvIcon);
        mTvName.setTextRight(entity.getTitle());
        mTvTime.setTextRight(entity.getHours() + "小时");
        mTvPoints.setTextRight(entity.getPoints() + "分");
        mTvCount.setTextRight(entity.getPerson() + "人");
        mTvRank.setTextRight("第" + entity.getRanking() + "名");
        mTvArea.setTextRight(entity.getProvince_name() + entity.getCity_name() + entity.getCounty_name());
        join_status = Integer.parseInt(entity.getIs_signup());
        if ("0".equals(entity.getIs_signup())) {
            mTvJoin.setTextRight("未加入");
            mBtnJoin.setVisibility(View.VISIBLE);
            mBtnJoin.setText("加入组织");
        } else if ("1".equals(entity.getIs_signup())) {
            mTvJoin.setTextRight("已加入");
            mBtnJoin.setVisibility(View.VISIBLE);
            mBtnJoin.setText("退出组织");
        } else {
            mTvJoin.setTextRight("审核中");
            mBtnJoin.setVisibility(View.GONE);
        }
        if ("0".equals(entity.getIs_main())) {
            mTvMain.setTextRight("否");
        } else {
            mTvMain.setTextRight("是");
        }
    }
}
