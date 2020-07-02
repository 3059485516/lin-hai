package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtMyPublishEntity;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

public class LtMyPublishPresenter extends AListPresenter<IListView, LtMyPublishEntity> {
    public LtMyPublishPresenter(IListView view) {
        super(view);
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getUserConList(getPage() + "", PAGE_SIZE + ""));
    }
}
