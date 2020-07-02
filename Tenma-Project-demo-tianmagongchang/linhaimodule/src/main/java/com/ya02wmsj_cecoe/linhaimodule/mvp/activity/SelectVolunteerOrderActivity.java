package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.VolunteerOrderAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.VolunteerOrderEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.SelectOrderTypeContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.SelectVolunteerOrderPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.EmptyView;

import me.texy.treeview.TreeNode;
import me.texy.treeview.TreeView;

/**
 * Created by BenyChan on 2019-07-27.
 */
public class SelectVolunteerOrderActivity extends BaseActivity<SelectOrderTypeContract.Presenter> implements SelectOrderTypeContract.View {
    protected FrameLayout rootView;

    protected EmptyView emptyView;

    private TreeView treeView;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_select_order;
    }

    @Override
    protected void initMVP() {
        mPresenter = new SelectVolunteerOrderPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("志愿点单");
        setMenuText("我的点单");
        rootView = findViewById(R.id.root);
        emptyView = findViewById(R.id.emptyView);
    }

    @Override
    public void onMenuClicked() {
        gotoActivity(OrderHistoryActivity.class);
    }

    @Override
    protected void initData() {
        mPresenter.getTreeNode();
    }

    @Override
    public void setTreeRoot(TreeNode treeRoot) {
        rootView.removeAllViews();
        VolunteerOrderAdapter orderAdapter = new VolunteerOrderAdapter();
        orderAdapter.setListener(treeNode -> {
            // 点击
            boolean isSpan = treeNode.isExpanded();
            treeNode.setExpanded(!isSpan);
            treeView.refreshTreeView();
            VolunteerOrderEntity orderType = (VolunteerOrderEntity) treeNode.getValue();
            if (orderType.getChildren() == null || orderType.getChildren().size() == 0) {
                // 详情点单页面
                Intent intent = new Intent(mContext, OrderDetailActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, orderType.getId());
                mContext.startActivity(intent);
            }
        });
        treeView = new TreeView(treeRoot, this, orderAdapter);
        View view = treeView.getView();
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootView.addView(view);
    }
}
