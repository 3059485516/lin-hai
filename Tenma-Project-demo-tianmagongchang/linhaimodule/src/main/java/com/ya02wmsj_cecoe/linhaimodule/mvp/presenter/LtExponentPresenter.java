package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.support.v4.app.Fragment;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtStreetEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtChooseLtContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.LtExponentFragment;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.ArrayList;
import java.util.List;

public class LtExponentPresenter extends LtChooseLtContract.Presenter {
    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();

    public LtExponentPresenter(LtChooseLtContract.View view) {
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
                        getFragments().add(LtExponentFragment.create(entity.getCode()));
                    }
                    mView.update(list);
                }
            }
        });
    }

    public List<String> getTitles() {
        return mTitleList;
    }

    public List<Fragment> getFragments() {
        return mFragmentList;
    }
}
