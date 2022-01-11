package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtExponentListContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

public class LtExponentListPresenter extends LtExponentListContract.Presenter {
    private String code;

    public LtExponentListPresenter(LtExponentListContract.View view, String code) {
        super(view);
        this.code = code;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getCAEvaList(code,mView.getSelectDate()));
    }
}
