package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizeDetailEntity;

/**
 * Created by BenyChan on 2019-07-25.
 */
public interface OrganizeDetailContract {
    interface View extends IView {
        void update();
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void getVolunteerOrganDetail();

        public abstract void applyJoinVolunteerOrgan(String intro);

        public abstract OrganizeDetailEntity getOrganizeDetailEntity();
    }
}
