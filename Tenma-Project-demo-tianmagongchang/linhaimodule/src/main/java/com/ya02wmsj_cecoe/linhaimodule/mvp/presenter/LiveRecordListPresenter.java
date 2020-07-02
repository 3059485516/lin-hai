package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LiveRecordEntity;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

public class LiveRecordListPresenter extends AListPresenter<IListView, LiveRecordEntity> {
    private String mCid;

    public LiveRecordListPresenter(IListView view, String cid) {
        super(view);
        mCid = cid;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getLiveVideolist(mCid));
    }
}
