package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.content.Intent;

import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtWatcherEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtWatcherPlayEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.LiveActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtWatcherListContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.List;

import me.texy.treeview.TreeNode;

public class LtWatcherListPresenter extends LtWatcherListContract.Presenter {

    public LtWatcherListPresenter(LtWatcherListContract.View view) {
        super(view);
    }

    @Override
    public void getCameraList() {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<List<LtWatcherEntity>>(Api.getCameraList()) {
            @Override
            protected void _onNext(List<LtWatcherEntity> list) {
                if (list != null) {
                    TreeNode treeNode = TreeNode.root();
                    createTreeNode(treeNode, list);
                    mView.dismissDialog();
                    mView.setTreeRoot(treeNode);
                }
            }
        });
    }

    @Override
    public void getCameraUrl(String code) {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<LtWatcherPlayEntity>(Api.getCameraUrl(code)) {
            @Override
            protected void _onNext(LtWatcherPlayEntity entity) {
                mView.dismissDialog();
                Intent intent = new Intent(mContext, LiveActivity.class);
                intent.putExtra(Constant.KEY_STRING_1, entity.getTitle());
                intent.putExtra(Constant.KEY_STRING_2, entity.getPlay_url().getAndroidWebcamUrl());
                mView.gotoActivity(intent);
            }
        });
    }

    private void createTreeNode(TreeNode treeNode, List<LtWatcherEntity> orderTypes) {
        if (orderTypes != null && !orderTypes.isEmpty()) {
            for (LtWatcherEntity orderType : orderTypes) {
                TreeNode treeNode1 = new TreeNode(orderType);
                treeNode1.setLevel(orderType.getLevel());
                treeNode1.setExpanded(true);
                treeNode.addChild(treeNode1);
                createTreeNode(treeNode1, orderType.getChildren());
            }
        }
    }
}
