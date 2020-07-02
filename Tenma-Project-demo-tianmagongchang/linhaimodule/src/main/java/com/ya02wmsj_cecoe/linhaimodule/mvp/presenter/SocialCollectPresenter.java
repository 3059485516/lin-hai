package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.luck.picture.lib.entity.LocalMedia;
import com.ya02wmsj_cecoe.linhaimodule.bean.RegionEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.SocialCollectContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SocialCollectPresenter extends SocialCollectContract.Presenter {
    private List<LocalMedia> mImages = new ArrayList<>();

    public SocialCollectPresenter(SocialCollectContract.View view) {
        super(view);
    }

    @Override
    public List<LocalMedia> getImageList() {
        return mImages;
    }

    @Override
    public void getRegionDataCountry() {
        addRx2Destroy(new RxSubscriber<List<RegionEntity>>(Api.getRegionData("county", "")) {
            @Override
            protected void _onNext(List<RegionEntity> list) {
                if (list != null && list.size() > 0) {
                    mView.updateRegionContry(list.get(0));
                }
            }
        });
    }

    @Override
    public void getRegionDataTown(String type, String pid) {
        addRx2Destroy(new RxSubscriber<List<RegionEntity>>(Api.getRegionData(type, pid)) {
            @Override
            protected void _onNext(List<RegionEntity> list) {
                if (list != null && list.size() > 0) {
                    mView.updateTown(list);
                }
            }
        });
    }

    @Override
    public void getRegionDataVillage(String type, String pid) {
        addRx2Destroy(new RxSubscriber<List<RegionEntity>>(Api.getRegionData(type, pid)) {
            @Override
            protected void _onNext(List<RegionEntity> list) {
                if (list != null && list.size() > 0) {
                    mView.updateVillage(list);
                }
            }
        });
    }

    @Override
    public void collectNetInfo(Map<String, Object> map) {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<Object>(Api.collectNetInfo(map, mImages)) {
            @Override
            protected void _onNext(Object o) {
                mView.dismissDialog();
                toast("提交成功");
                mView.uploadComplete();
            }

            @Override
            protected void _onError(String code) {
                mView.dismissDialog();
                super._onError(code);
            }
        });
    }
}
