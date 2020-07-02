package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizationDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OrganizationDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

/**
 * Created by BenyChan on 2019-07-31.
 */
public class OrganizationDetailPresenter extends OrganizationDetailContract.Presenter {
    private String mType, mId;

    public OrganizationDetailPresenter(OrganizationDetailContract.View view, String type, String id) {
        super(view);
        mType = type;
        mId = id;
    }

    @Override
    public void getOrganDetail() {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<OrganizationDetailEntity>(Api.getOrganDetail(mType, mId)) {
            @Override
            protected void _onNext(OrganizationDetailEntity o) {
                mView.dismissDialog();
                if (null != o) {
                    mView.updateView(o);
                }
            }

            @Override
            protected void _onError(String code) {
                mView.dismissDialog();
                super._onError(code);
            }
        });
    }
}
