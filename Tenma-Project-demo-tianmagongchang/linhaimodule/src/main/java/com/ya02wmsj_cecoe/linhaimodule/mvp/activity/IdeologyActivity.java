package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.NodeContentAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseListActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.NodeContentContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.NodeContentPresenter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;


/**
 * Created by BenyChan on 2019-07-16.
 */
public class IdeologyActivity extends BaseListActivity<NodeContentContract.Presenter>
        implements NodeContentContract.View {
    protected RecyclerView mRvNode;

    @Override
    protected boolean hasActionBar() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_ideology;
    }

    @Override
    protected MultiItemTypeAdapter getAdapter() {
        return new NodeContentAdapter(this, mPresenter.getDataList());
    }

    @Override
    protected void initMVP() {
        mPresenter = new NodeContentPresenter(this, getIntent().getStringExtra(Constant.KEY_STRING_2), Constant.DEFAULT_REGION_CODE);
    }

    @Override
    protected void initView() {
        mRvNode = findViewById(R.id.rv_node);
        setTitle(getIntent().getStringExtra(Constant.KEY_STRING_1));
        setDefaultItemDecoration();

        mRvNode.setLayoutManager(new GridLayoutManager(this, 4));
//        mRvNode.setAdapter(new NodeAdapter(this, Arrays.asList(
//                new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_ideology_1, "学堂直播", 0, null),
//                new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_ideology_2, "学习时代", 0, null),
//                new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_ideology_3, "做视频", 0, null),
//                new MainNodeEntity(R.mipmap.ya02wmsj_cecoe_ideology_4, "学习强国", 0, null)
//        )));
    }
}
