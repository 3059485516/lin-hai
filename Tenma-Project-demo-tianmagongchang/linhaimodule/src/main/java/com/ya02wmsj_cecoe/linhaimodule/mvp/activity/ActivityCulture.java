package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.AdvanceTopAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeContentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.CultureContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.CulturePresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.ItemDecorationVertical;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;


/**
 * Created by BenyChan on 2019-07-16.
 */
public class ActivityCulture extends BaseListActivity<CultureContract.Presenter> implements CultureContract.View {
    protected RecyclerView mRvTop;

    private AdvanceTopAdapter mTopAdapter;

    @Override
    protected boolean hasActionBar() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_culture;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new NodeContentAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new CulturePresenter(this, getIntent().getStringExtra(Constant.KEY_STRING_2));
    }

    @Override
    protected void initView() {
        mRvTop = findViewById(R.id.rv_node);
        setTitle(getIntent().getStringExtra(Constant.KEY_STRING_1));
        setDefaultItemDecoration();
        mRvTop.setLayoutManager(new LinearLayoutManager(this));
        mRvTop.addItemDecoration(new ItemDecorationVertical(ContextCompat.getColor(this, R.color.yl_devide_line), 1, 0, 0));
        mTopAdapter = new AdvanceTopAdapter(this, mPresenter.getTopList());
        mRvTop.setAdapter(mTopAdapter);
    }

    @Override
    public void updateTopList() {
        mTopAdapter.notifyDataSetChanged();
    }
}
