package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;

/**
 * Created by BenyChan on 2019-08-01.
 */
public interface AppraiseListContract {
    interface View extends IListView {
    }

    abstract class Presenter extends AListPresenter<View, NodeContent> {
        public Presenter(View view) {
            super(view);
        }
    }
}
