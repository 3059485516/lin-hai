package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OrginazeContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;


public class OrginazePresenter extends OrginazeContract.Presenter {

    public OrginazePresenter(OrginazeContract.View view) {
        super(view);
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getZyhOrganList(mView.getSearchStr(), getPage() + "", PAGE_SIZE + ""));
    }

    public void joinOrginize(String depId) {
        addRx2Destroy(new RxSubscriber<Object>(Api.joinOrginize(depId)) {
            @Override
            protected void _onNext(Object o) {
                mView.toast("加入成功");
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
            }
        });
    }
}
