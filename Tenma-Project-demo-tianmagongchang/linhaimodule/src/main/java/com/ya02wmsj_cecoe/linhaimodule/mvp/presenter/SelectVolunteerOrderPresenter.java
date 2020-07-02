package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;


import com.ya02wmsj_cecoe.linhaimodule.bean.VolunteerOrderEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.SelectOrderTypeContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.List;

import me.texy.treeview.TreeNode;

/**
 * Created by BenyChan on 2019-06-19.
 */
public class SelectVolunteerOrderPresenter extends SelectOrderTypeContract.Presenter {
    public SelectVolunteerOrderPresenter(SelectOrderTypeContract.View view) {
        super(view);
    }

    @Override
    public void getTreeNode() {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<List<VolunteerOrderEntity>>(Api.getServiceOrderList()) {
            @Override
            protected void _onNext(List<VolunteerOrderEntity> list) {
                TreeNode treeNode = TreeNode.root();
                createTreeNode(treeNode, list);
                mView.dismissDialog();
                mView.setTreeRoot(treeNode);
            }
        });
    }

    private void createTreeNode(TreeNode treeNode, List<VolunteerOrderEntity> orderTypes) {
        if (orderTypes != null && !orderTypes.isEmpty()) {
            for (VolunteerOrderEntity orderType : orderTypes) {
                TreeNode treeNode1 = new TreeNode(orderType);
                treeNode1.setLevel(Integer.parseInt(orderType.getLevel()));
                treeNode1.setExpanded(true);
                treeNode.addChild(treeNode1);
                createTreeNode(treeNode1, orderType.getChildren());
            }
        }
    }
}
