package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.EduCheckContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

/**
 * 教育查询
 */
public class EduCheckPresenter extends EduCheckContract.Presenter {

    public EduCheckPresenter(EduCheckContract.View view) {
        super(view);
    }

    @Override
    public void clickContent() {
        addRx2Destroy(new RxSubscriber<String>(Api.clickedContent("教育服务")) {
            @Override
            protected void _onNext(String str) {
            }

            @Override
            protected void _onError(String code) {
            }
        });
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getEduLinkList(getPage() + "", PAGE_SIZE + ""));
    }
}
