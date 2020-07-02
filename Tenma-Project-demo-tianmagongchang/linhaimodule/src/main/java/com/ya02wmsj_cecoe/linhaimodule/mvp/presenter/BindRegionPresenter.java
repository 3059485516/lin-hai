package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.bean.RegionEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.BindRegionContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.List;

/**
 * Created by BenyChan on 2019-07-27.
 */
public class BindRegionPresenter extends BindRegionContract.Presenter {
    public BindRegionPresenter(BindRegionContract.View view) {
        super(view);
    }

    @Override
    public void bindArea(String region_code) {
        addRx2Destroy(new RxSubscriber<Object>(Api.bindRegion(region_code)) {
            @Override
            protected void _onNext(Object o) {
                toast("绑定成功");
                Config.getInstance().getUser().setRegion_code(region_code);
                mView.finishActivity();
            }
        });
    }

    @Override
    public void getRegionData1() {
        addRx2Destroy(new RxSubscriber<List<RegionEntity>>(Api.getRegionData("county", "")) {
            @Override
            protected void _onNext(List<RegionEntity> list) {
                if (list != null && list.size() > 0) {
                    mView.updateRegion1(list.get(0));
                }
            }
        });
    }

    @Override
    public void getRegionData2(String type, String pid) {
        addRx2Destroy(new RxSubscriber<List<RegionEntity>>(Api.getRegionData(type, pid)) {
            @Override
            protected void _onNext(List<RegionEntity> list) {
                if (list != null && list.size() > 0) {
                    mView.updateRegion2(list);
                }
            }
        });
    }

    @Override
    public void getRegionData3(String type, String pid) {
        addRx2Destroy(new RxSubscriber<List<RegionEntity>>(Api.getRegionData(type, pid)) {
            @Override
            protected void _onNext(List<RegionEntity> list) {
                if (list != null && list.size() > 0) {
                    mView.updateRegion3(list);
                }
            }
        });
    }
}
