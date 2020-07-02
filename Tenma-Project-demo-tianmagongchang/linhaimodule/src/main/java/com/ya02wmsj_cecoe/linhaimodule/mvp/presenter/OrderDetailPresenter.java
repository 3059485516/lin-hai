package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.content.Intent;

import com.ya02wmsj_cecoe.linhaimodule.bean.OrderDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.BindRegionActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OrderDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.HttpCode;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;


/**
 * Created by BenyChan on 2019-07-27.
 */
public class OrderDetailPresenter extends OrderDetailContract.Presenter {
    private String mServiceId;

    public OrderDetailPresenter(OrderDetailContract.View view, String service_id) {
        super(view);
        mServiceId = service_id;
    }

    @Override
    public void getServiceOrderDetail() {
        addRx2Destroy(new RxSubscriber<OrderDetailEntity>(Api.getServiceOrderDetail(mServiceId)) {
            @Override
            protected void _onNext(OrderDetailEntity o) {
                mView.update(o);
            }
        });
    }

    @Override
    public void addOrder() {
        addRx2Destroy(new RxSubscriber<Object>(Api.addOrder(mServiceId)) {
            @Override
            protected void _onNext(Object o) {
                toast("点单成功");
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
                if (HttpCode.CODE_20012.getCode().equals(code)) {
                    mView.gotoActivity(new Intent(mContext, BindRegionActivity.class));
                }
            }
        });
    }
}
