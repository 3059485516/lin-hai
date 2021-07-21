package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;

/**
 * Created by BenyChan on 2019-07-16.
 */
public interface NodeContentContract {
    interface View extends IListView {
    }

    abstract class Presenter extends AListPresenter<View, NodeContent> {
        public Presenter(View view) {
            super(view);
        }
        public abstract String getNodeId();

        public abstract void clickContent(String type);
    }
}
