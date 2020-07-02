package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;

import me.texy.treeview.TreeNode;

public interface LtWatcherListContract {
    interface View extends IView {
        void setTreeRoot(TreeNode treeRoot);
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void getCameraList();

        public abstract void getCameraUrl(String code);
    }
}
