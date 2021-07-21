package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeContentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.NodeContentContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.NodeContentPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

/**
 * Created by BenyChan on 2019-07-16.
 */
public class NodeContentActivity extends BaseListActivity<NodeContentContract.Presenter> implements NodeContentContract.View {

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        NodeContentAdapter nodeContentAdapter = new NodeContentAdapter(this, mPresenter.getDataList());
        nodeContentAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                String nodeId = mPresenter.getNodeId();
                if ("41".equals(nodeId) || "42".equals(nodeId)) {
                    mPresenter.clickContent("理论云学堂");
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        return nodeContentAdapter;
    }

    @Override
    protected void initMVP() {
        mPresenter = new NodeContentPresenter(this, getIntent().getStringExtra(Constant.KEY_STRING_2), Constant.DEFAULT_REGION_CODE);
    }

    @Override
    protected void initView() {
        setTitle(getIntent().getStringExtra(Constant.KEY_STRING_1));
        setDefaultItemDecoration();
    }
}
