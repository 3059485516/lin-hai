package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtExpoenentEntity;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

public class LtExponentListPresenter extends AListPresenter<IListView, LtExpoenentEntity> {
    private String code;

    public LtExponentListPresenter(IListView view, String code) {
        super(view);
        this.code = code;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getCAEvaList(code));
    }
}
