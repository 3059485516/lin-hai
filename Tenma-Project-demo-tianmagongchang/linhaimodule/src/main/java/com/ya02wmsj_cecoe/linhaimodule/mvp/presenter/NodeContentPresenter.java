package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.NodeContentContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;

/**
 * Created by BenyChan on 2019-07-16.
 */
public class NodeContentPresenter extends NodeContentContract.Presenter {
    private String mNodeId;
    private String mRegionCode;

    public NodeContentPresenter(NodeContentContract.View view, String node_id, String region_code) {
        super(view);
        mNodeId = node_id;
        mRegionCode = region_code;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getContentList(mRegionCode, "","n", mNodeId, getPage() + "", PAGE_SIZE + ""));
    }
}
