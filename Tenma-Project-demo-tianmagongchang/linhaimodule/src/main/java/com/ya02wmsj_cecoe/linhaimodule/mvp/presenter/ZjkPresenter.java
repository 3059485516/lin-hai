package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.ZjkProfessionEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ZjkContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.List;

public class ZjkPresenter extends ZjkContract.Presenter {
    public ZjkPresenter(ZjkContract.View view) {
        super(view);
    }

    @Override
    public void getDataList() {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<List<ZjkProfessionEntity>>(Api.queryCateList()) {
            @Override
            protected void _onNext(List<ZjkProfessionEntity> list) {
                if (list != null && list.size() > 0) {
                    mView.updateView(list);
                }
            }
        });
    }
}
