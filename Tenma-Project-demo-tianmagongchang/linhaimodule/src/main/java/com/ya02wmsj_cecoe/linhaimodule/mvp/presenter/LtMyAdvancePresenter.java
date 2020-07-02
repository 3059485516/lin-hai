package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtMyAdvanceEntity;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

public class LtMyAdvancePresenter extends AListPresenter<IListView, LtMyAdvanceEntity> {
    public LtMyAdvancePresenter(IListView view) {
        super(view);
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getUserCAAdvanceList(getPage() + "", PAGE_SIZE + ""));
    }
}
