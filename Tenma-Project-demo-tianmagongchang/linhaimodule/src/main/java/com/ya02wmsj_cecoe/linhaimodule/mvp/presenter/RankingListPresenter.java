package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.RankingEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.RankingListEntity;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

public class RankingListPresenter extends AListPresenter<IListView, RankingEntity> {
    private boolean mbOrginaze;

    public RankingListPresenter(IListView view, boolean orginaze) {
        super(view);
        mbOrginaze = orginaze;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        if (mbOrginaze) {
            addRx2Destroy(new RxSubscriber<RankingListEntity>(Api.organRank(getPage() + "", PAGE_SIZE + "")) {
                @Override
                protected void _onNext(RankingListEntity o) {
                    if (o != null) {
                        loadSuccessful(o.getList());
                    }
                }
            });
        } else {
            addRx2Destroy(new RxSubscriber<RankingListEntity>(Api.getVolunteerRank(getPage() + "", PAGE_SIZE + "")) {
                @Override
                protected void _onNext(RankingListEntity o) {
                    if (o != null) {
                        loadSuccessful(o.getList());
                    }
                }
            });
        }
    }
}
