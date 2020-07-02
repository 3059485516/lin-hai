package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtEvaMainEntity;

public interface EvaluationContract {
    interface View extends IListView {

    }

    abstract class Presenter extends AListPresenter<View, LtEvaMainEntity> {
        public Presenter(View view) {
            super(view);
        }

    }
}
