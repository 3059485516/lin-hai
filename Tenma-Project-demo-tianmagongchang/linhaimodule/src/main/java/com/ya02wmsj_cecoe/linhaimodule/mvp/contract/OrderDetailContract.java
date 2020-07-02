package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrderDetailEntity;

/**
 * Created by BenyChan on 2019-07-27.
 */
public interface OrderDetailContract {
    interface View extends IView {
        void update(OrderDetailEntity o);
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void getServiceOrderDetail();

        public abstract void addOrder();
    }
}
