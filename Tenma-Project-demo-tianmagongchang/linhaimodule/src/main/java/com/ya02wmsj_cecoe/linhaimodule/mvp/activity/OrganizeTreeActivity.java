package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.OrganizeTreeAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizeTreeEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.SelectOrderTypeContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.OrganizeTreePresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.EmptyView;

import me.texy.treeview.TreeNode;
import me.texy.treeview.TreeView;

/**
 * Created by BenyChan on 2019-07-31.
 */
public class OrganizeTreeActivity extends BaseActivity<SelectOrderTypeContract.Presenter> implements SelectOrderTypeContract.View {
    protected FrameLayout rootView;

    protected EmptyView emptyView;

    protected TextView mTvSearch;

    private TreeView treeView;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_activity_organize_tree;
    }

    @Override
    protected void initMVP() {
        mPresenter = new OrganizeTreePresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("组织结构");
        rootView = findViewById(R.id.root);
        emptyView = findViewById(R.id.emptyView);
        mTvSearch = findViewById(R.id.tv_search);
        mTvSearch.setOnClickListener(v -> gotoActivity(SearchOrganizeActivity.class));
    }

    @Override
    protected void initData() {
        mPresenter.getTreeNode();
    }

    @Override
    public void setTreeRoot(TreeNode treeRoot) {
        rootView.removeAllViews();
        OrganizeTreeAdapter orderAdapter = new OrganizeTreeAdapter();
        orderAdapter.setListener(new OrganizeTreeAdapter.IOrganizeItemClickOption() {
            @Override
            public void expand(TreeNode treeNode) {
                boolean isSpan = treeNode.isExpanded();
                treeNode.setExpanded(!isSpan);
                treeView.refreshTreeView();
            }

            @Override
            public void gotoDetail(TreeNode treeNode) {
                //  查看详情
                OrganizeTreeEntity entity = (OrganizeTreeEntity) treeNode.getValue();
                Intent intent = new Intent(mContext, OrganizationDetailActivity.class);
                intent.putExtra(Constant.KEY_BEAN, entity);
                mContext.startActivity(intent);
            }
        });
        treeView = new TreeView(treeRoot, this, orderAdapter);
        View view = treeView.getView();
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootView.addView(view);
    }
}
