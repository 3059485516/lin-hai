package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizationDetailEntity;

/**
 * Created by BenyChan on 2019-07-31.
 */
public interface OrganizationDetailContract {
    interface View extends IView {
        void updateView(OrganizationDetailEntity obj);
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void getOrganDetail();
    }
}
