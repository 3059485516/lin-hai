package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;

/**
 * Created by BenyChan on 2019-07-23.
 */
public interface VolunteerContract {
    interface View extends IView {

    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void applyForVolunteer(String name, String address, String intro);
    }
}
