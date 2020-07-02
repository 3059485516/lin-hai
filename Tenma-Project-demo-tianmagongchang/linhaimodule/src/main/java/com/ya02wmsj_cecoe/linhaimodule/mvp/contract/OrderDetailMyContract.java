package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;

public interface OrderDetailMyContract {
    interface View extends IView {
        void updateView(Object obj);
    }

    abstract class Presenter extends APresenter<View> {

        public Presenter(View view) {
            super(view);
        }

        public abstract void getMyOrderDetail(String c_id);
    }
}
