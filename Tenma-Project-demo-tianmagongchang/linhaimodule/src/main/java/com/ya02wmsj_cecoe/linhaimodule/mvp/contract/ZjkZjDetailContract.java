package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.ZjkZjDetailEntity;

public interface ZjkZjDetailContract {
    interface View extends IView {
        void updateView(ZjkZjDetailEntity entity);
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void queryExpertDetail(String id);
    }
}
