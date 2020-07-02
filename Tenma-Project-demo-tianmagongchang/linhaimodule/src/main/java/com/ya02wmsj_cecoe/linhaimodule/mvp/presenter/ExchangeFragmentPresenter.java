package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.ExchangeListEntity;
import com.ya02wmsj_cecoe.linhaimodule.event.ExchangeResult;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ExchangeFragmentContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxBus;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.Map;

public class ExchangeFragmentPresenter extends ExchangeFragmentContract.Presenter {
    private String mPrice;

    public ExchangeFragmentPresenter(ExchangeFragmentContract.View view, String price) {
        super(view);
        mPrice = price;
    }

    @Override
    public void exchange(Map<String, Object> map) {
        addRx2Destroy(new RxSubscriber<Object>(Api.payment(map)) {
            @Override
            protected void _onNext(Object o) {
                mView.toast("兑换成功");
                RxBus.getInstance().send(new ExchangeResult());
            }
        });
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        addRx2Destroy(new RxSubscriber<ExchangeListEntity>(Api.getGoodsList(mPrice, getPage() + "", PAGE_SIZE + "")) {
            @Override
            protected void _onNext(ExchangeListEntity entitly) {
                if (entitly != null && entitly.getRows() != null) {
                    loadSuccessful(entitly.getRows());
                }
            }
        });
    }
}
