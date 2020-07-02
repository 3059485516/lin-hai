package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;

import me.texy.treeview.TreeNode;

public interface LtEvaTypeListContract {
    interface View extends IView {
        void setTreeRoot(TreeNode treeRoot);
    }

    abstract class Presenter extends APresenter<LtEvaTypeListContract.View> {

        public Presenter(LtEvaTypeListContract.View view) {
            super(view);
        }

        public abstract void getTreeNode();
    }
}
