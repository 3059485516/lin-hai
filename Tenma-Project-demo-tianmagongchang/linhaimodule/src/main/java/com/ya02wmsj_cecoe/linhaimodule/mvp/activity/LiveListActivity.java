package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.adapter.LiveListAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LiveListEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LiveListPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

public class LiveListActivity extends BaseListActivity<LiveListPresenter> implements IListView {

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        LiveListAdapter adapter = new LiveListAdapter(this, mPresenter.getDataList());
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                LiveListEntity entity = mPresenter.getDataList().get(position);
                mPresenter.getLiveStatus(entity);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        return adapter;
    }

    @Override
    protected void initMVP() {
        mPresenter = new LiveListPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("直播");
        setDefaultItemDecoration();
        setLoadMoreEnabled(false);
    }
}
