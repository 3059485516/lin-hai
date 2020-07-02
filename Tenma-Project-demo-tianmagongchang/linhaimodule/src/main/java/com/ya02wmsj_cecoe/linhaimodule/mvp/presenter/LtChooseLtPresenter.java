package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.LtStreetEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtChooseLtContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.LtListFragment;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.List;

public class LtChooseLtPresenter extends LtChooseLtContract.Presenter {
    public LtChooseLtPresenter(LtChooseLtContract.View view) {
        super(view);
    }

    @Override
    public void getAllCABranchList() {
        addRx2Destroy(new RxSubscriber<List<LtStreetEntity>>(Api.getAllCABranchList()) {
            @Override
            protected void _onNext(List<LtStreetEntity> list) {
                if (list != null) {
                    getTitles().clear();
                    getFragments().clear();

                    for (LtStreetEntity entity : list) {
                        getTitles().add(entity.getRegion_name());
                        getFragments().add(LtListFragment.create(entity.getCode()));
                    }
                    mView.update(list);
                }
            }
        });
    }
}
