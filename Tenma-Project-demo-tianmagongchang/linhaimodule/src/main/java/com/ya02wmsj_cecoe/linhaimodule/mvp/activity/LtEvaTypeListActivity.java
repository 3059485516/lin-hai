package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LtEvaTypeListAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtEvaEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtEvaTypeListContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtEvaTypeListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.EmptyView;

import me.texy.treeview.TreeNode;
import me.texy.treeview.TreeView;

public class LtEvaTypeListActivity extends BaseActivity<LtEvaTypeListPresenter> implements LtEvaTypeListContract.View {
    protected FrameLayout rootView;

    protected EmptyView emptyView;

    private TreeView treeView;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_lt_watcher_list_activity;
    }

    @Override
    protected void initMVP() {
        mPresenter = new LtEvaTypeListPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("测评项");
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
        LtEvaTypeListAdapter watcherAdapter = new LtEvaTypeListAdapter(new LtEvaTypeListAdapter.IClickWatcherListener() {
            @Override
            public void expand(TreeNode treeNode) {
                boolean isSpan = treeNode.isExpanded();
                treeNode.setExpanded(!isSpan);
                treeView.refreshTreeView();
            }

            @Override
            public void gotoDetail(TreeNode treeNode) {
                LtEvaEntity entity = (LtEvaEntity) treeNode.getValue();
                Intent intent = new Intent();
                intent.putExtra(Constant.KEY_BEAN, entity);
                setResult(RESULT_OK, intent);
                finishActivity();
            }
        });
        treeView = new TreeView(treeRoot, this, watcherAdapter);
        View view = treeView.getView();
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootView.addView(view);
    }
}
