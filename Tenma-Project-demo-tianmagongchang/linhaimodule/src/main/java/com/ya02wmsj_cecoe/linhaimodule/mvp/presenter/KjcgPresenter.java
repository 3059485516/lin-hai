package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.KjcgEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.KjcgListEntity;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

public class KjcgPresenter extends AListPresenter<IListView, KjcgListEntity> {
    public KjcgPresenter(IListView view) {
        super(view);
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        addRx2Destroy(new RxSubscriber<KjcgEntity>(Api.getScientificList(getPage() + "")) {
            @Override
            protected void _onNext(KjcgEntity entity) {
                if (entity == null) {
                    loadFailed();
                } else {
                    if (entity.getNewsList() != null) {
                        loadSuccessful(entity.getNewsList());
                    }
                }
            }

            @Override
            protected void _onError(String code) {
                if ("1".equals(code)) {
                    mView.finishLoadMore(true);
                }
            }
        });
    }
}
