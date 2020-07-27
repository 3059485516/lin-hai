package com.ya02wmsj_cecoe.linhaimodule.base.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.BaseCleanListContract;

/**
 * ================================================
 * author : 尋水的魚
 * date   : 2020/7/21 2:06 PM
 * desc   : EMPTY
 * ================================================
 */
public abstract class BaseCleanListActivity <P extends BaseCleanListContract.Presenter<?,?>> extends BaseActivity<P> implements BaseCleanListContract.View, OnRefreshListener, OnLoadMoreListener {
    protected RecyclerView mRecyclerView;
    protected SmartRefreshLayout mRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_base_clean_list;
    }

    @Override
    protected void initUI() {
        super.initUI();
        mRecyclerView = findViewById(R.id.rv);
        mRefreshLayout = findViewById(R.id.refresh);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setOnLoadMoreListener(this);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableOverScrollDrag(true);
        initRecyclerViewLayoutManager();
    }

    protected void initRecyclerViewLayoutManager(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPresenter.onLoadMore();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPresenter.onRefresh();
    }

    @Override
    public void refreshError() {
        mRefreshLayout.finishRefresh(false);
    }

    @Override
    public void loadMoreError() {
        mRefreshLayout.finishLoadMore(0, false, false);
    }

    @Override
    public void refreshSucceed() {
        mRefreshLayout.finishRefresh(0);
    }

    @Override
    public void loadMoreSucceed() {
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void noMoreData(boolean isRefresh) {
        if(isRefresh){
            mRefreshLayout.finishRefresh();
            mRefreshLayout.setNoMoreData(true);
        }else {
            mRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void upDataList() {
        RecyclerView.Adapter<?> adapter = mPresenter.getAdapter();
        if(adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }
}
