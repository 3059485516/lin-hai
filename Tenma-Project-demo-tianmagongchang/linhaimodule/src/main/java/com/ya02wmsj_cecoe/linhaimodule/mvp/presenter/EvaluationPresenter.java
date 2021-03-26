package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.EvaluationContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

public class EvaluationPresenter extends EvaluationContract.Presenter {
    public EvaluationPresenter(EvaluationContract.View view) {
        super(view);
    }


    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getMyEvaApplies(getPage() + "", PAGE_SIZE + ""));
    }
}
