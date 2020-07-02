package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ZjkFragmentContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

public class ZjkFragmentPresenter extends ZjkFragmentContract.Presenter {
    public ZjkFragmentPresenter(ZjkFragmentContract.View view) {
        super(view);
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.queryExpertList(getId(), getPage() + ""));
    }
}
