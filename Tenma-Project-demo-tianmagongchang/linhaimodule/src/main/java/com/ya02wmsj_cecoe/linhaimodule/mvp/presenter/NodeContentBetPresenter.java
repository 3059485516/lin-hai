package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.NodeContentBetContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;

public class NodeContentBetPresenter extends NodeContentBetContract.Presenter {
    private String mRegionCode = RegionManager.getInstance().getCurrentCountyCode();
    private String mNodeId;

    public NodeContentBetPresenter(NodeContentBetContract.View view, String nodeId) {
        super(view);
        mNodeId = nodeId;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getContentList(mRegionCode, "", "n", mNodeId, getPage() + "", PAGE_SIZE + ""));
    }


    @Override
    public void clickContent(String type) {
        addRx2Destroy(new RxSubscriber<String>(Api.clickedContent(type)) {
            @Override
            protected void _onNext(String str) {
            }

            @Override
            protected void _onError(String code) {
            }
        });
    }
}
