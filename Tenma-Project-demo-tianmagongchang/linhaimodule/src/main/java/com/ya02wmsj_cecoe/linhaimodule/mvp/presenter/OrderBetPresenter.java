package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

public class OrderBetPresenter extends AListPresenter<IListView, Node> {
    private String id, type;
    public OrderBetPresenter(IListView view, String id, String type) {
        super(view);
        this.id = id;
        this.type = type;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getServiceOrder(id, type));
    }
}
