package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.LtEntitiy;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtStreetEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtChooseLtContract2;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.List;

public class LtChooseLtPresenter2 extends LtChooseLtContract2.Presenter {
    public LtChooseLtPresenter2(LtChooseLtContract2.View view) {
        super(view);
    }

    @Override
    public void getArea() {
        addRx2Destroy(new RxSubscriber<List<LtStreetEntity>>(Api.getCABranchList()) {
            @Override
            protected void _onNext(List<LtStreetEntity> list) {
                if (list != null) {
                    getAreaList().clear();
                    getAreaList().addAll(list);
                    getAreaList().get(0).setSelected(true); //默认第一个为选中状态
                    mView.updateArea();
                    getLt(list.get(0).getCode());
                }
            }
        });
    }

    @Override
    public void getLt(String code) {
        addRx2Destroy(new RxSubscriber<List<LtEntitiy>>(Api.getCAList(code)) {
            @Override
            protected void _onNext(List<LtEntitiy> list) {
                if (list != null) {
                    getLtList().clear();
                    getLtList().addAll(list);
                    mView.updateLt();
                }
            }
        });
    }
}
