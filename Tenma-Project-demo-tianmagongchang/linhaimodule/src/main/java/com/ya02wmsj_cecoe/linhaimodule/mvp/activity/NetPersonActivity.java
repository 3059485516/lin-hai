package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NetPersonAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.NodeContentBetPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.Arrays;


public class NetPersonActivity extends BaseListActivity<AListPresenter> implements IListView {
    RecyclerView mRvNode;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_cloud_class;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new NetPersonAdapter(mContext, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new NodeContentBetPresenter(this, "67");
    }

    @Override
    protected void initView() {
        setTitle("网络惠民");
        setDefaultItemDecoration();
        mRvNode = findViewById(R.id.rv_node);
        mRvNode.setLayoutManager(new GridLayoutManager(this, 5));
        NodeAdapter nodeAdapter = new NodeAdapter(this, Arrays.asList(
                new Node("我要咨询", R.mipmap.ya02wmsj_cecoe_wyzx + "", true),
                new Node("社情收集", R.mipmap.ya02wmsj_cecoe_sqsj + "", true),
                new Node("发现非遗", R.mipmap.ya02wmsj_cecoe_discover + "", true),
                new Node("电子图书馆", R.mipmap.ya02wmsj_cecoe_book + "", true),
                new Node("体育馆地图", R.mipmap.ya02wmsj_cecoe_ditu + "", true)));
        mRvNode.setAdapter(nodeAdapter);
    }
}
