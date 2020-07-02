package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.AppraiseListContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;


/**
 * Created by BenyChan on 2019-08-01.
 */
public class AppraiseListPresenter extends AppraiseListContract.Presenter {
    private String mRegionCode;

    public AppraiseListPresenter(AppraiseListContract.View view, String regionCode) {
        super(view);
        mRegionCode = regionCode;
    }

    public void setRegionCode(String code) {
        mRegionCode = code;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getContentList(mRegionCode, "n","n", "34", getPage() + "", PAGE_SIZE + ""));
    }

}
