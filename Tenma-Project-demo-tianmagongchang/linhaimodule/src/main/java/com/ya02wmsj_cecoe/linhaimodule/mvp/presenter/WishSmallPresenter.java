package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.luck.picture.lib.entity.LocalMedia;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.WishSmallContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by BenyChan on 2019-08-14.
 */
public class WishSmallPresenter extends WishSmallContract.Presenter {
    private List<LocalMedia> mImageList = new ArrayList<>();

    public WishSmallPresenter(WishSmallContract.View view) {
        super(view);
    }

    @Override
    public void addTinyWish(Map<String, Object> map) {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<Object>(Api.addTinyWish(map, mImageList)) {
            @Override
            protected void _onNext(Object o) {
                mView.dismissDialog();
                toast("提交成功");
                mView.finishActivity();
            }
        });
    }

    @Override
    public List<LocalMedia> getImageList() {
        return mImageList;
    }
}
