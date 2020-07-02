package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.TalentEntity;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

public class TalentPresenter extends AListPresenter<IListView, TalentEntity> {
    public TalentPresenter(IListView view) {
        super(view);
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getTalentList(getPage() + "", PAGE_SIZE + ""));
    }
}
