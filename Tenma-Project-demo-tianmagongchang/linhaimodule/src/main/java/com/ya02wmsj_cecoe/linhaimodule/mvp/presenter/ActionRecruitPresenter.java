package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ActionRecruitContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

/**
 * Created by BenyChan on 2019-07-23.
 */
public class ActionRecruitPresenter extends ActionRecruitContract.Presenter {
    public ActionRecruitPresenter(ActionRecruitContract.View view) {
        super(view);
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getOfflineActivityList());
    }
}
