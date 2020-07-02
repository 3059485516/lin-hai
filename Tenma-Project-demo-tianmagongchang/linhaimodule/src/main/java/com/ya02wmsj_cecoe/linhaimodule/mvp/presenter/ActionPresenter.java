package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.ZhiyuanhuiEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.ZhiyuanhuiListEntity;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

public class ActionPresenter extends AListPresenter<IListView, ZhiyuanhuiEntity> {
    public ActionPresenter(IListView view) {
        super(view);
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
//        setDataSource(Api.getRecruitList(getPage() + "", PAGE_SIZE + ""));
        addRx2Destroy(new RxSubscriber<ZhiyuanhuiListEntity>(Api.getRecruitList(getPage() + "", PAGE_SIZE + "")) {
            @Override
            protected void _onNext(ZhiyuanhuiListEntity o) {
                if (o != null) {
                    loadSuccessful(o.getList());
                }
            }
        });
    }
}
