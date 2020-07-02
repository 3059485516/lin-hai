package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.LtEvaEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtEvaTypeListContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.List;

import me.texy.treeview.TreeNode;

public class LtEvaTypeListPresenter extends LtEvaTypeListContract.Presenter {
    public LtEvaTypeListPresenter(LtEvaTypeListContract.View view) {
        super(view);
    }

    @Override
    public void getTreeNode() {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<List<LtEvaEntity>>(Api.getEvaRules()) {
            @Override
            protected void _onNext(List<LtEvaEntity> list) {
                TreeNode treeNode = TreeNode.root();
                createTreeNode(treeNode, list);
                mView.dismissDialog();
                mView.setTreeRoot(treeNode);
            }

            @Override
            protected void _onError(String code) {
                mView.dismissDialog();
                super._onError(code);
            }
        });
    }

    private void createTreeNode(TreeNode treeNode, List<LtEvaEntity> orderTypes) {
        if (orderTypes != null && !orderTypes.isEmpty()) {
            for (LtEvaEntity orderType : orderTypes) {
                TreeNode treeNode1 = new TreeNode(orderType);
                treeNode1.setLevel(orderType.getLevel());
                treeNode1.setExpanded(true);
                treeNode.addChild(treeNode1);
                createTreeNode(treeNode1, orderType.getChildren());
            }
        }
    }
}
