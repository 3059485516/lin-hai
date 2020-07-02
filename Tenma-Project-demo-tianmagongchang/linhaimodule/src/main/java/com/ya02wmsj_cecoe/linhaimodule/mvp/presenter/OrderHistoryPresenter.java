package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OrderHistoryContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

/**
 * Created by BenyChan on 2019-08-02.
 */
public class OrderHistoryPresenter extends OrderHistoryContract.Presenter {
    public OrderHistoryPresenter(OrderHistoryContract.View view) {
        super(view);
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getMyOrderList());
    }
}
