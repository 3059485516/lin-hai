package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.content.Intent;

import com.luck.picture.lib.entity.LocalMedia;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.BindRegionActivity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.PublishOpinionContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.HttpCode;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by BenyChan on 2019-07-22.
 */
public class PublishOpinionPresenter extends PublishOpinionContract.Presenter {
    private List<LocalMedia> mImages = new ArrayList<>();

    public PublishOpinionPresenter(PublishOpinionContract.View view) {
        super(view);
    }

    @Override
    public List<LocalMedia> getImageList() {
        return mImages;
    }

    @Override
    public void commit(Map<String, Object> map) {
        mView.showDialog("正在提交请稍候..");
        addRx2Destroy(new RxSubscriber<Object>(Api.addContent(map, mImages)) {
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
                if (HttpCode.CODE_20012.getCode().equals(code)) {
                    mView.gotoActivity(new Intent(mContext, BindRegionActivity.class));
                }
            }
        });
    }
}
