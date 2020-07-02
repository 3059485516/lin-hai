package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.luck.picture.lib.entity.LocalMedia;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LtAdvancePresenter extends APresenter<IView> {
    private List<LocalMedia> mImages = new ArrayList<>();

    public LtAdvancePresenter(IView view) {
        super(view);
    }

    public void addCAAdvance(Map<String, Object> map) {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<Object>(Api.addCAAdvance(map, mImages)) {
            @Override
            protected void _onNext(Object o) {
                mView.dismissDialog();
                toast("提交成功");
                mView.finishActivity();
            }
        });
    }

    public List<LocalMedia> getImageList() {
        return mImages;
    }
}
