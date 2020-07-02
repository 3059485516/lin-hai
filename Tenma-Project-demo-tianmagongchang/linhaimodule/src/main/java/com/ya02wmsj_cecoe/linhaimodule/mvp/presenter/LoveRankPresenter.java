package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LoveRankContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

/**
 * Created by BenyChan on 2019-07-26.
 */
public class LoveRankPresenter extends LoveRankContract.Presenter {
    public LoveRankPresenter(LoveRankContract.View view) {
        super(view);
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getVolunteerServeList());
    }
}
