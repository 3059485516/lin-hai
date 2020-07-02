package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.EduEntity;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

public class EduCheckPresenter extends AListPresenter<IListView, EduEntity> {

    public EduCheckPresenter(IListView view) {
        super(view);
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getEduLinkList(getPage() + "", PAGE_SIZE + ""));
    }
}
