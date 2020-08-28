package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.ZhiyuanhuiListEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ActionContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;


public class ActionPresenter extends ActionContract.Presenter {
    public ActionPresenter(ActionContract.View view) {
        super(view);
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        addRx2Destroy(new RxSubscriber<ZhiyuanhuiListEntity>(Api.getRecruitList(mView.getSearchStr(),getPage() + "", PAGE_SIZE + "")) {
            @Override
            protected void _onNext(ZhiyuanhuiListEntity o) {
                if (o != null) {
                    loadSuccessful(o.getList());
                }
            }
        });
    }
}
