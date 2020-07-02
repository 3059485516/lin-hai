package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtAppointEntity;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

public class LtAppointListPresenter extends AListPresenter<IListView, LtAppointEntity> {
    private String mFlag;

    public LtAppointListPresenter(IListView view, String flag) {
        super(view);
        mFlag = flag;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getManSourceApplyList(mFlag));
    }
}
