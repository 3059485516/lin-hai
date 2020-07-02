package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizationSubEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.SeachOrganizeContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.List;

/**
 * Created by BenyChan on 2019-08-01.
 */
public class SearchOrganizePresenter extends SeachOrganizeContract.Presenter {
    public SearchOrganizePresenter(SeachOrganizeContract.View view) {
        super(view);
    }

    @Override
    public void search(String name) {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<List<OrganizationSubEntity>>(Api.searchOrgan(name)) {
            @Override
            protected void _onNext(List<OrganizationSubEntity> list) {
                mView.dismissDialog();
                if (list != null) {
                    loadSuccessful(list);
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
