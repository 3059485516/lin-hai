package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtEntitiy;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

public class LtListPresenter extends AListPresenter<IListView, LtEntitiy> {
    private String mCode;

    public LtListPresenter(IListView view, String code) {
        super(view);
        mCode = code;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getCAList(mCode));
    }
}
