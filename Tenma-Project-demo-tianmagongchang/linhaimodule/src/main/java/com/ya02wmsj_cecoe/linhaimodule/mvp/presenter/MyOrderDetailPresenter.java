package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OrderDetailMyContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

public class MyOrderDetailPresenter extends OrderDetailMyContract.Presenter {
    public MyOrderDetailPresenter(OrderDetailMyContract.View view) {
        super(view);
    }

    @Override
    public void getMyOrderDetail(String c_id) {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<Object>(Api.getOrderActivityDetail(c_id)) {
            @Override
            protected void _onNext(Object o) {
                if (o != null) {
                    mView.updateView(o);
                }
                mView.dismissDialog();
            }
        });
    }
}
