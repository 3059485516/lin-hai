package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeContentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.bean.UserOperateEvent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.NodeContentBetContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.NodeContentBetPresenter;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxBus;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.Arrays;

/**
 * 理论云学堂、理论云课堂
 */
public class CloudClassActivity extends BaseListActivity<NodeContentBetContract.Presenter> implements NodeContentBetContract.View {
    protected RecyclerView mRvNode;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_cloud_class;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new NodeContentAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new NodeContentBetPresenter(this, getIntent().getStringExtra(Constant.KEY_STRING_1));
    }

    @Override
    protected void initView() {
        setTitle("理论云学堂");
        mRvNode = findViewById(R.id.rv_node);
        setDefaultItemDecoration();
        mRvNode.setLayoutManager(new GridLayoutManager(this, 5));
        NodeAdapter nodeAdapter = new NodeAdapter(this, Arrays.asList(
                new Node("图文", R.mipmap.ya02wmsj_cecoe_platform_4 + "", true),
                new Node("视频", R.mipmap.ya02wmsj_cecoe_sp + "", true),
                new Node("直播", R.mipmap.ya02wmsj_cecoe_zb + "", true),
                new Node("达人库", R.mipmap.ya02wmsj_cecoe_drk + "", true),
                new Node("学习强国", R.mipmap.ya02wmsj_cecoe_xiqg + "", true)));
        mRvNode.setAdapter(nodeAdapter);

        nodeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if (position == 4) {
                    mPresenter.clickContent("学习强国");
                } else {
                    mPresenter.clickContent("理论云学堂");
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        mPresenter.getRxManager2Destroy().add(RxBus.getInstance().register(UserOperateEvent.class).subscribe(userOperateEvent -> {
            for (NodeContent content : mPresenter.getDataList()) {
                if (content.getId().equals(userOperateEvent.getId())) {
                    content.setThumb_num(userOperateEvent.getLikeNum());
                    break;
                }
            }
            updateList();
        }));
        mPresenter.clickContent("理论云学堂");
    }
}
