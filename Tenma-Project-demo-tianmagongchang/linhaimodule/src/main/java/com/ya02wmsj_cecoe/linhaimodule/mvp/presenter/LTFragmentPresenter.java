package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.LtMarkEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.Node;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.BaseAreaListFragmentContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LTFragmentPresenter extends BaseAreaListFragmentPresenter {
    private List<String> mBannerImageUrls = new ArrayList<>();

    public LTFragmentPresenter(BaseAreaListFragmentContract.View view) {
        super(view);
        getBanner(RegionManager.getInstance().getCurrentCountyCode());
        getMarkList();
    }

    public List<String> getDefaultImgUrls() {
        return new ArrayList<>(Arrays.asList(
                "http://47.99.86.101:8030//uploads/ya02wmsj_cecoe/pic/20200sdasdf304/1583300367_143738534.jpeg"
        ));
    }

    public List<String> getBannerImageList() {
        return mBannerImageUrls;
    }

    public void getBanner(String region_code) {
        addRx2Destroy(new RxSubscriber<List<NodeContent>>(Api.getLtIndexTop(region_code)) {
            @Override
            protected void _onNext(List<NodeContent> list) {
                if (list != null) {
                    mBannerImageUrls.clear();
                    getBannerDataList().clear();
                    getBannerDataList().addAll(list);
                    for (NodeContent nodeContent : list) {
                        mBannerImageUrls.add(nodeContent.getIcon_path());
                        mView.updataBanner();
                    }
                }
            }
        });
    }

    @Override
    public void getPageData(boolean isRefresh) {
        super.getPageData(isRefresh);
        setDataSource(Api.getLtContentList(getPage() + "", PAGE_SIZE + "", getMarkId()));
    }

    @Override
    public void getNodeList() {
        super.getNodeList();
        addRx2Destroy(new RxSubscriber<List<Node>>(Api.getNodeList("17")) {
            @Override
            protected void _onNext(List<Node> list) {
                if (list != null) {
                    getNodeData().clear();
                    getNodeData().addAll(list);
                    mView.updateNodeList();
                }
            }
        });
    }

    @Override
    protected void getMarkList() {
        addRx2Destroy(new RxSubscriber<List<LtMarkEntity>>(Api.getContentMarks()) {
            @Override
            protected void _onNext(List<LtMarkEntity> list) {
                if (list != null) {
                    getMarkDataList().clear();
                    getMarkDataList().addAll(list);
                    mView.updateMarkList();
                }
            }
        });
    }
}
