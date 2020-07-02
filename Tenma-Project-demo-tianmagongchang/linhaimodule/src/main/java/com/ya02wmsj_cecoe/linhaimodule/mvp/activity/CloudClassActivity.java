package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeContentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.bean.UserOperateEvent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.NodeContentBetPresenter;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxBus;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.Arrays;

import io.reactivex.functions.Consumer;

public class CloudClassActivity extends BaseListActivity<NodeContentBetPresenter> implements IListView {
    RecyclerView mRvNode;

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
        mPresenter.getRxManager2Destroy().add(RxBus.getInstance().register(UserOperateEvent.class).subscribe(new Consumer<UserOperateEvent>() {
            @Override
            public void accept(UserOperateEvent userOperateEvent) throws Exception {
                for (NodeContent content : mPresenter.getDataList()) {
                    if (content.getId().equals(userOperateEvent.getId())) {
                        content.setThumb_num(userOperateEvent.getLikeNum());
                        break;
                    }
                }
                updateList();
            }
        }));
    }
}
