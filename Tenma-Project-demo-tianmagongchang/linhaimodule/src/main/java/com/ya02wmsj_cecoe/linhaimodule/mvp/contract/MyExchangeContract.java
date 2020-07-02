package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.MyExchangeEntity;

public interface MyExchangeContract {
    interface View extends IListView {
        void returnCompleted();
    }

    abstract class Presenter extends AListPresenter<View, MyExchangeEntity> {

        public Presenter(View view) {
            super(view);
        }

        public abstract void refund(String orderNo, String desc);
    }
}
