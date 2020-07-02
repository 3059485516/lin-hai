package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.bean.CategoryTypeEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.SelectOrderTypeContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.List;

import me.texy.treeview.TreeNode;

/**
 * Created by BenyChan on 2019-07-30.
 */
public class CategorySelectPresenter extends SelectOrderTypeContract.Presenter {
    public CategorySelectPresenter(SelectOrderTypeContract.View view) {
        super(view);
    }

    @Override
    public void getTreeNode() {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<List<CategoryTypeEntity>>(Api.getEventCategory(Config.getInstance().getRegionCode())) {
            @Override
            protected void _onNext(List<CategoryTypeEntity> list) {
                TreeNode treeNode = TreeNode.root();
                createTreeNode(treeNode, list);
                mView.dismissDialog();
                mView.setTreeRoot(treeNode);
            }
        });
    }

    private void createTreeNode(TreeNode treeNode, List<CategoryTypeEntity> orderTypes) {
        if (orderTypes != null && !orderTypes.isEmpty()) {
            for (CategoryTypeEntity orderType : orderTypes) {
                TreeNode treeNode1 = new TreeNode(orderType);
                treeNode1.setLevel(Integer.parseInt(orderType.getLevel()));
                treeNode1.setExpanded(true);
                treeNode.addChild(treeNode1);
                createTreeNode(treeNode1, orderType.getList());
            }
        }
    }
}
