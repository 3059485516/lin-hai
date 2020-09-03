package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OnlineCommunityContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;

/**
 * 网络社区
 */
public class OnlineCommunityPresenter extends OnlineCommunityContract.Presenter {
    private String mNodeId;
    private String mRegionCode = RegionManager.getInstance().getCurrentCountyCode();

    public OnlineCommunityPresenter(OnlineCommunityContract.View view, String nodeId) {
        super(view);
        mNodeId = nodeId;
    }

    public void setRegionCode(String region) {
        mRegionCode = region;
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getNetQueueList(mRegionCode, mNodeId, getPage() + "", PAGE_SIZE + ""));
    }
}
