package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.text.TextUtils;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.OtherServicesContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

/**
 * 我要点服务 -- 其他服务
 */
public class OtherServicesPresenter extends OtherServicesContract.Presenter {
    public OtherServicesPresenter(OtherServicesContract.View view) {
        super(view);
    }

    @Override
    public void submitData() {
        String name = mView.getName();
        if (TextUtils.isEmpty(name)){
            mView.toast("联系人不能为空！");
            return;
        }
        String phone = mView.getPhone();
        if (TextUtils.isEmpty(name)){
            mView.toast("联系电话不能为空！");
            return;
        }
        String regionCode = mView.getRegionCode();
        if (TextUtils.isEmpty(name)){
            mView.toast("村社不能为空！");
            return;
        }
        String address = mView.getAddress();
        if (TextUtils.isEmpty(name)){
            mView.toast("联系地址不能为空！");
            return;
        }
        String content = mView.getContent();
        if (TextUtils.isEmpty(content)){
            mView.toast("服务内容描述不能为空！");
            return;
        }
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<Object>(Api.addOtherOrder(regionCode, name, phone, address, content), mView) {
            @Override
            protected void _onNext(Object o) {
                mView.dismissDialog();
                toast("提交成功");
                mView.finishActivity();
            }

            public void _onError(String msg) {
                mView.dismissDialog();
                toast("提交失败!");
            }
        });
    }
}
