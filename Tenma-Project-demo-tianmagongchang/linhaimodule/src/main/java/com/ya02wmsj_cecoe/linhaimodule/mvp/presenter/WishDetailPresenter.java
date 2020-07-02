package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.WishDetailEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.WishDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.Map;

/**
 * Created by BenyChan on 2019-08-15.
 */
public class WishDetailPresenter extends WishDetailContract.Presenter {
    private String mWishId;

    public WishDetailPresenter(WishDetailContract.View view, String id) {
        super(view);
        mWishId = id;
    }

    @Override
    public void getWishDetail() {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<WishDetailEntity>(Api.getWishDetail(mWishId)) {
            @Override
            protected void _onNext(WishDetailEntity entity) {
                mView.dismissDialog();
                if (entity != null) {
                    mView.updateDetail(entity);
                }
            }
        });
    }

    @Override
    public void applyWish(Map<String, Object> map) {
        addRx2Destroy(new RxSubscriber<Object>(Api.claimTinyWish(map)) {
            @Override
            protected void _onNext(Object o) {
                toast("认领成功！");
                mView.finishActivity();
            }
        });
    }
}
