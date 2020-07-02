package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.text.TextUtils;

import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizeDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OrganizeDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

/**
 * Created by BenyChan on 2019-07-25.
 */
public class OrganizeDetailPresenter extends OrganizeDetailContract.Presenter {
    private OrganizeDetailEntity mDetailEntity;
    private String mVoranId;

    public OrganizeDetailPresenter(OrganizeDetailContract.View view, String voran_id) {
        super(view);
        mVoranId = voran_id;
    }

    @Override
    public void getVolunteerOrganDetail() {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<OrganizeDetailEntity>(Api.getVolunteerOrganDetail(mVoranId)) {
            @Override
            protected void _onNext(OrganizeDetailEntity entity) {
                if (entity != null) {
                    mDetailEntity = entity;
                    mView.update();
                }
                mView.dismissDialog();
            }

            @Override
            protected void _onError(String code) {
                mView.dismissDialog();
                super._onError(code);
            }
        });
    }

    @Override
    public void applyJoinVolunteerOrgan(String intro) {
        if (TextUtils.isEmpty(intro)) {
            toast("请先填写个人简介");
            return;
        }
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<Object>(Api.applyJoinVolunteerOrgan(mVoranId, intro)) {
            @Override
            protected void _onNext(Object o) {
                mView.dismissDialog();
                toast("提交成功!");
                mView.finishActivity();
            }

            @Override
            protected void _onError(String code) {
                mView.dismissDialog();
                super._onError(code);
            }
        });
    }

    @Override
    public OrganizeDetailEntity getOrganizeDetailEntity() {
        return mDetailEntity;
    }
}
