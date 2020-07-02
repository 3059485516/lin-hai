package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.luck.picture.lib.entity.LocalMedia;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FinishWishPresenter extends APresenter<IView> {
    private List<LocalMedia> mImages = new ArrayList<>();

    public FinishWishPresenter(IView view) {
        super(view);
    }

    public List<LocalMedia> getImageList() {
        return mImages;
    }

    public void commit(Map<String, Object> map) {
        mView.showDialog("正在提交请稍候..");
        addRx2Destroy(new RxSubscriber<Object>(Api.finishTinyWish(map, mImages)) {
            @Override
            protected void _onNext(Object o) {
                mView.dismissDialog();
                toast("提交成功");
                mView.finishActivity();
            }

            @Override
            protected void _onError(String code) {
                mView.dismissDialog();
                super._onError(code);
            }
        });
    }
}
