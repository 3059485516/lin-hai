package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.MyExchangeListEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.MyExchangeContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

public class MyExchangePresenter extends MyExchangeContract.Presenter {
    private String mStatus;

    public MyExchangePresenter(MyExchangeContract.View view, String status) {
        super(view);
        mStatus = status;
    }

    @Override
    public void refund(String orderNo, String desc) {
        addRx2Destroy(new RxSubscriber<Object>(Api.refund(orderNo, desc)) {
            @Override
            protected void _onNext(Object o) {
                mView.returnCompleted();
            }
        });
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        addRx2Destroy(new RxSubscriber<MyExchangeListEntity>(Api.getExchangeList(mStatus, getPage() + "", PAGE_SIZE + "")) {
            @Override
            protected void _onNext(MyExchangeListEntity entity) {
                if (entity != null) {
                    loadSuccessful(entity.getRows());
                }
            }
        });
    }
}
