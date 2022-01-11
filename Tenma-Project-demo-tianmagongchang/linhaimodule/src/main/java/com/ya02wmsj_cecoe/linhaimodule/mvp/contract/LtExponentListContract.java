package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtExpoenentEntity;

public interface LtExponentListContract {
    interface View extends IListView {
        String getSelectDate();
    }

    abstract class Presenter extends AListPresenter<View, LtExpoenentEntity> {
        public Presenter(View view) {
            super(view);
        }
    }
}
