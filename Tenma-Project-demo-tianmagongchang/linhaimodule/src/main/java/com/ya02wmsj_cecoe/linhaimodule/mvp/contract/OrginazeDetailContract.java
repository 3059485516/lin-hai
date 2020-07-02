package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrginazeDetailEntity;

public interface OrginazeDetailContract {
    interface View extends IView {
        void updateView(OrginazeDetailEntity entity);
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void joinOrginize(String depId);

        public abstract void quitOrginize(String depId);

        public abstract void getOrginazeDetail(String depId);
    }
}
