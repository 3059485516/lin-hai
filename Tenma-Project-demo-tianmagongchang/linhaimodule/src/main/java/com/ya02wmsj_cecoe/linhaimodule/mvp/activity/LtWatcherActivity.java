package com.ya02wmsj_cecoe.linhaimodule.mvp.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ya02wmsj_cecoe.linhaimodule.R;
import com.ya02wmsj_cecoe.linhaimodule.adapter.LtWatchListAdapter;
import com.ya02wmsj_cecoe.linhaimodule.base.activity.BaseActivity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtWatcherEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtWatcherListContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.presenter.LtWatcherListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.widget.EmptyView;

import me.texy.treeview.TreeNode;
import me.texy.treeview.TreeView;

public class LtWatcherActivity extends BaseActivity<LtWatcherListPresenter> implements LtWatcherListContract.View {
    protected FrameLayout rootView;

    protected EmptyView emptyView;

    private TreeView treeView;

    @Override
    protected int getLayoutId() {
        return R.layout.ya02wmsj_cecoe_lt_watcher_list_activity;
    }

    @Override
    protected void initMVP() {
        mPresenter = new LtWatcherListPresenter(this);
    }

    @Override
    protected void initView() {
        setTitle("视频监控");
        rootView = findViewById(R.id.root);
        emptyView = findViewById(R.id.emptyView);
    }

    @Override
    protected void initData() {
        mPresenter.getCameraList();
    }

    @Override
    public void setTreeRoot(TreeNode treeRoot) {
        rootView.removeAllViews();
        LtWatchListAdapter watcherAdapter = new LtWatchListAdapter(new LtWatchListAdapter.IClickWatcherListener() {
            @Override
            public void expand(TreeNode treeNode) {
                boolean isSpan = treeNode.isExpanded();
                treeNode.setExpanded(!isSpan);
                treeView.refreshTreeView();
            }

            @Override
            public void gotoDetail(TreeNode treeNode) {
                LtWatcherEntity entity = (LtWatcherEntity) treeNode.getValue();
                mPresenter.getCameraUrl(entity.getCode());
            }
        });
        treeView = new TreeView(treeRoot, this, watcherAdapter);
        View view = treeView.getView();
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        rootView.addView(view);
    }
}
