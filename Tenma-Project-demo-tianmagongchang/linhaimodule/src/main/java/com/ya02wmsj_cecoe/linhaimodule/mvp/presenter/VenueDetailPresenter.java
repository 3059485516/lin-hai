package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.VenueDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.VenueDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

/**
 * Created by BenyChan on 2019-07-26.
 */
public class VenueDetailPresenter extends VenueDetailContract.Presenter {
    private String mVenueId;

    public VenueDetailPresenter(VenueDetailContract.View view, String id) {
        super(view);
        mVenueId = id;
    }

    @Override
    public void getStadiaDetail() {
        addRx2Destroy(new RxSubscriber<VenueDetailEntity>(Api.getStadiaDetail(mVenueId)) {
            @Override
            protected void _onNext(VenueDetailEntity entity) {
                if (entity != null) {
                    mView.update(entity);
                }
            }
        });
    }
}
