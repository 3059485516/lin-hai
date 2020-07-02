package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.support.v4.app.Fragment;

import com.ya02wmsj_cecoe.linhaimodule.bean.LtStreetEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.LtShowContract;
import com.ya02wmsj_cecoe.linhaimodule.mvp.fragment.LtShowFragment;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.ArrayList;
import java.util.List;

public class LtShowPresenter extends LtShowContract.Presenter {
    private List<String> mTitleList = new ArrayList<>();
    private List<Fragment> mFragmentList = new ArrayList<>();

    public LtShowPresenter(LtShowContract.View view) {
        super(view);
    }

    @Override
    public void getCABranchList() {
        addRx2Destroy(new RxSubscriber<List<LtStreetEntity>>(Api.getCABranchList()) {
            @Override
            protected void _onNext(List<LtStreetEntity> list) {
                if (list != null) {
                    mTitleList.clear();
                    mFragmentList.clear();
                    for (LtStreetEntity entity : list) {
                        mTitleList.add(entity.getRegion_name());
                        mFragmentList.add(LtShowFragment.create(entity.getCode()));
                    }
                    mView.updateView();
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
