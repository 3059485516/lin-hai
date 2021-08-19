package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.CategoryTypeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.CategoryTypeEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.SelectOrderTypeContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.CategorySelectPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.EmptyView;

import me.texy.treeview.TreeNode;
import me.texy.treeview.TreeView;

/**
 * Created by BenyChan on 2019-07-30.
 */
public class CategorySelectActivity extends BaseActivity<SelectOrderTypeContract.Presenter> implements SelectOrderTypeContract.View {
    protected FrameLayout rootView;
    protected EmptyView emptyView;
    private TreeView treeView;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_select_order;
    }

    @Override
    protected void initMVP() {
        mPresenter = new CategorySelectPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("诉求类型");
        rootView = findViewById(R.id.root);
        emptyView = findViewById(R.id.emptyView);
    }

    @Override
    protected void initData() {
        mPresenter.getTreeNode();
    }

    @Override
    public void setTreeRoot(TreeNode treeRoot) {
        rootView.removeAllViews();
        CategoryTypeAdapter orderAdapter = new CategoryTypeAdapter();
        orderAdapter.setListener(treeNode -> {
            boolean isSpan = treeNode.isExpanded();
            treeNode.setExpanded(!isSpan);
            treeView.refreshTreeView();
            CategoryTypeEntity orderType = (CategoryTypeEntity) treeNode.getValue();
            if (orderType.getList() == null || orderType.getList().size() == 0) {
                Intent intent = new Intent();
                intent.putExtra(Constant.KEY_BEAN, orderType);
                setResult(RESULT_OK, intent);
                finishActivity();
            }
        });
        treeView = new TreeView(treeRoot, this, orderAdapter);
        View view = treeView.getView();
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootView.addView(view);
    }
}
