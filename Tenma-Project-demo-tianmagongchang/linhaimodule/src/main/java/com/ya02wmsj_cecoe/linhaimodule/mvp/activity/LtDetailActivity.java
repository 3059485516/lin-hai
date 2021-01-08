package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LtDetailResourceAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LtNodeContentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtDetailPresenter;
import com.ya02wmsj_cecoe.linhaimodule.utils.ImageManager;
import com.ya02wmsj_cecoe.linhaimodule.widget.ItemDecorationVertical;
import com.ya02wmsj_cecoe.linhaimodule.widget.RatioImageView;

public class LtDetailActivity extends BaseActivity<LtDetailPresenter> implements LtDetailContract.View {
    private RatioImageView mIvTop;
    private TextView mTvCaName, mTvCaDesc;

    private RecyclerView mRvResource, mRvPublish;

    private LtDetailResourceAdapter mResourceAdapter;
    private LtNodeContentAdapter mPublishAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_lt_detail_activity;
    }

    @Override
    protected void initMVP() {
        mPresenter = new LtDetailPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle(getIntent().getStringExtra(Constant.KEY_STRING_2));
        //setMenuText("预约使用");
        mIvTop = findViewById(R.id.iv_top);
        mTvCaName = findViewById(R.id.tv_ca_name);
        mTvCaDesc = findViewById(R.id.tv_ca_desc);
        mRvResource = findViewById(R.id.rv_resource);
        mRvResource.setLayoutManager(new LinearLayoutManager(this));
        mRvPublish = findViewById(R.id.rv_publish);
        mRvPublish.setLayoutManager(new LinearLayoutManager(this));
        int dimension = (int) getResources().getDimension(R.dimen.yl_list_horizontal_margin);
        mRvPublish.addItemDecoration(new ItemDecorationVertical(ContextCompat.getColor(this, R.color.yl_devide_line), 1, dimension, dimension));

        mResourceAdapter = new LtDetailResourceAdapter(this, mPresenter.getSourceList());
        mRvResource.setAdapter(mResourceAdapter);

        mPublishAdapter = new LtNodeContentAdapter(this, mPresenter.getCommentList());
        mRvPublish.setAdapter(mPublishAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.getCAInfo(getIntent().getStringExtra(Constant.KEY_STRING_1));
    }

    @Override
    public void onMenuClicked() {
        Intent intent = new Intent(this, LtChooseLtResourceActivity.class);
        intent.putExtra(Constant.KEY_STRING_1, getIntent().getStringExtra(Constant.KEY_STRING_1));
        gotoActivity(intent);
    }

    @Override
    public void updateView(LtDetailEntity obj) {
        ImageManager.getInstance().loadImage(this, obj.getCa_info().getPic(), R.mipmap.ya02wmsj_cecoe_placeholder, mIvTop);
        mTvCaName.setText(obj.getCa_info().getName());
        mTvCaDesc.setText(Html.fromHtml(obj.getCa_info().getDesc()));
        mResourceAdapter.notifyDataSetChanged();
        mPublishAdapter.notifyDataSetChanged();
    }
}
