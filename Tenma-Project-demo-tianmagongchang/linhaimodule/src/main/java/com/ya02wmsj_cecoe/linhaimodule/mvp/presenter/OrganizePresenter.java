package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OrganizeContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

/**
 * Created by BenyChan on 2019-07-25.
 */
public class OrganizePresenter extends OrganizeContract.Presenter {
    public OrganizePresenter(OrganizeContract.View view) {
        super(view);
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getVolunteerOrganList());
    }
}
