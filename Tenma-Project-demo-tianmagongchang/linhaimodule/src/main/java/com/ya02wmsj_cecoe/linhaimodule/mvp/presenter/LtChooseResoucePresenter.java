package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtResouceEntity;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

public class LtChooseResoucePresenter extends AListPresenter<IListView, LtResouceEntity> {
    private String mCode;

    public LtChooseResoucePresenter(IListView view, String code) {
        super(view);
        mCode = code;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getCASourceList(mCode));
    }
}
