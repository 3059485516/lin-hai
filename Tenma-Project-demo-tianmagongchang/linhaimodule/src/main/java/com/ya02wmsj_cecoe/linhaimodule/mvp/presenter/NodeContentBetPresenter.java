package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;

public class NodeContentBetPresenter extends AListPresenter<IListView, NodeContent> {
    private String mNodeId;
    private String mRegionCode = RegionManager.getInstance().getCurrentCountyCode();

    public NodeContentBetPresenter(IListView view, String nodeId) {
        super(view);
        mNodeId = nodeId;
    }

    public void setRegionCode(String region) {
        mRegionCode = region;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getContentList(mRegionCode, "", "n", mNodeId, getPage() + "", PAGE_SIZE + ""));
    }
}
