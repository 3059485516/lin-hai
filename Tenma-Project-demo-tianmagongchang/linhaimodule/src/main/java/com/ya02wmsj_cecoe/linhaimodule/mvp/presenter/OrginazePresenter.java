package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

public class OrginazePresenter extends AListPresenter {
    public OrginazePresenter(IListView view) {
        super(view);
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getZyhOrganList("", getPage() + "", PAGE_SIZE + ""));
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
