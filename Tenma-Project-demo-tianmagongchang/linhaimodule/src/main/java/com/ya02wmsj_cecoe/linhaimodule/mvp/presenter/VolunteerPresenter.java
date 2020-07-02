package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.VolunteerContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

/**
 * Created by BenyChan on 2019-07-23.
 */
public class VolunteerPresenter extends VolunteerContract.Presenter {
    public VolunteerPresenter(VolunteerContract.View view) {
        super(view);
    }

    @Override
    public void applyForVolunteer(String name, String address, String intro) {
        addRx2Destroy(new RxSubscriber<Object>(Api.applyForVolunteer(name, address, intro)) {
            @Override
            protected void _onNext(Object o) {
                toast("提交成功！");
                Config.getInstance().getUser().setGroup_name("志愿者");
                mView.finishActivity();
            }
        });
    }
}
