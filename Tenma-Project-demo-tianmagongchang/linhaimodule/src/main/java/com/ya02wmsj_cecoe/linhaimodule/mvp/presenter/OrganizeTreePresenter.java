package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizeTreeEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.SelectOrderTypeContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.List;

import me.texy.treeview.TreeNode;

/**
 * Created by BenyChan on 2019-07-31.
 */
public class OrganizeTreePresenter extends SelectOrderTypeContract.Presenter {
    public OrganizeTreePresenter(SelectOrderTypeContract.View view) {
        super(view);
    }

    @Override
    public void getTreeNode() {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<List<OrganizeTreeEntity>>(Api.getOrganTree()) {
            @Override
            protected void _onNext(List<OrganizeTreeEntity> list) {
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

    private void createTreeNode(TreeNode treeNode, List<OrganizeTreeEntity> orderTypes) {
        if (orderTypes != null && !orderTypes.isEmpty()) {
            for (OrganizeTreeEntity orderType : orderTypes) {
                TreeNode treeNode1 = new TreeNode(orderType);
                treeNode1.setLevel(Integer.parseInt(orderType.getLevel()));
                treeNode1.setExpanded(true);
                treeNode.addChild(treeNode1);
                createTreeNode(treeNode1, orderType.getList());
            }
        }
    }
}
