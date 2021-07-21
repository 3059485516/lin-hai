package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtMarkEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.NodeContent;
import com.ya02wmsj_cecoe.linhaimodule.bean.RegionEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.BaseAreaListFragmentContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;
import com.ya02wmsj_cecoe.linhaimodule.utils.RegionManager;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseAreaListFragmentPresenter extends BaseAreaListFragmentContract.Presenter {
    private List<AppraiseEntity> mNodeContentList = new ArrayList<>();
    private List<LtMarkEntity> mMarkList = new ArrayList<>();
    private List<NodeContent> mBannerDataList = new ArrayList<>();
    private String mMarkId = "";

    public BaseAreaListFragmentPresenter(BaseAreaListFragmentContract.View view) {
        super(view);
    }

    @Override
    public void getRegionCountyData() {
        addRx2Destroy(new RxSubscriber<List<RegionEntity>>(Api.getRegionData("county", "")) {
            @Override
            protected void _onNext(List<RegionEntity> list) {
                if (list != null && list.size() > 0) {
                    RegionManager.getInstance().setCurrentCounty(list.get(0));
                    getRegionTownData();
                    mView.updateContry();
                }
            }
        });
    }

    @Override
    public void getRegionTownData() {
        addRx2Destroy(new RxSubscriber<List<RegionEntity>>(Api.getRegionData("town", RegionManager.getInstance().getCurrentCounty().getCode())) {
            @Override
            protected void _onNext(List<RegionEntity> list) {
                if (list != null && list.size() > 0) {
                    RegionManager.getInstance().setTownList(list);
                    mView.updateTown();
                    getRegionVillageData();
                }
            }
        });
    }

    @Override
    public void getRegionVillageData() {
        addRx2Destroy(new RxSubscriber<List<RegionEntity>>(Api.getRegionData("village", RegionManager.getInstance().getCurrentTownCode())) {
            @Override
            protected void _onNext(List<RegionEntity> list) {
                if (list != null && list.size() > 0) {
                    RegionManager.getInstance().setVillageList(list);
                    mView.updateVillage();
                }
            }
        });
    }

    @Override
    public void getOnlineActivityList() {
        addRx2Destroy(new RxSubscriber<List<AppraiseEntity>>(Api.getOnlineActivityList(getPage() + "", PAGE_SIZE + "", RegionManager.getInstance().getCurrentCountyCode(), "")) {
            @Override
            protected void _onNext(List<AppraiseEntity> list) {
                if (list != null) {
                    mNodeContentList.clear();
                    if (list.size() <= 1) {
                        mNodeContentList.addAll(list);
                    } else {
                        mNodeContentList.addAll(list.subList(0, 1));
                    }
                    mView.updateOnlineList();
                }
            }
        });
    }

    @Override
    public List<AppraiseEntity> getOnlineList() {
        return mNodeContentList;
    }

    @Override
    public List<String> getDefaultImgUrls() {
        return null;
    }

    @Override
    public void getBanner(String region_code) {
    }

    @Override
    public List<String> getBannerImageList() {
        return null;
    }

    public List<NodeContent> getBannerDataList() {
        return mBannerDataList;
    }

    @Override
    public void getNodeList() {
    }

    public String getMarkId() {
        return mMarkId;
    }

    public void setMarkId(String mark_id) {
        mMarkId = mark_id;
    }

    protected void getMarkList() {
    }

    public List<LtMarkEntity> getMarkDataList() {
        return mMarkList;
    }

    public void clickContent() {
        addRx2Destroy(new RxSubscriber<String>(Api.clickedContent("文化礼堂")) {
            @Override
            protected void _onNext(String str) {
            }

            @Override
            protected void _onError(String code) {
            }
        });
    }
}
