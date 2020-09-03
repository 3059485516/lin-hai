package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.ProcessAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.EventDetail;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.EventDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.EventDetailPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.YLTextViewGroup;

/**
 * Created by BenyChan on 2019-07-30.
 */
public class EventDetailActivity extends BaseActivity<EventDetailContract.Presenter> implements EventDetailContract.View {
    protected YLTextViewGroup mTvTitle;
    protected TextView mTvDesc;
    protected TextView mTvReasonTop;
    protected TextView mTvReason;
    protected RecyclerView mRvProcess;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_event_detail;
    }

    @Override
    protected void initMVP() {
        mPresenter = new EventDetailPresenter(this, getIntent().getStringExtra(Constant.KEY_STRING_1));
    }

    @Override
    protected void initView() {
        setTitle("诉求详情");
        mTvTitle = findViewById(R.id.tv_title);
        mTvDesc = findViewById(R.id.tv_desc);
        mTvReasonTop = findViewById(R.id.tv_reason_top);
        mTvReason = findViewById(R.id.tv_reason);
        mRvProcess = findViewById(R.id.rv_process);
        mRvProcess.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        mPresenter.getEventDetail();
    }

    @Override
    public void updateDetail(EventDetail entity) {
        mTvTitle.setTextRight(entity.getTitle());
        mTvDesc.setText(entity.getDesc());
        if (!TextUtils.isEmpty(entity.getReject_reason())) {
            mTvReasonTop.setVisibility(View.VISIBLE);
            mTvReason.setVisibility(View.VISIBLE);
            mTvReason.setText(entity.getReject_reason());
        }
        if (entity.getProcess_info() != null) {
            ProcessAdapter adapter = new ProcessAdapter(this, entity.getProcess_info());
            mRvProcess.setAdapter(adapter);
        }
    }
}
