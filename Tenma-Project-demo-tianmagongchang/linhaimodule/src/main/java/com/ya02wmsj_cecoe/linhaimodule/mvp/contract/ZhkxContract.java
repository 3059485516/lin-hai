package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;

public interface ZhkxContract {
    interface View extends IView {
        void loginChaoxSuc(String url);
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void logToEBook();

        public abstract void clickContent();
    }
}
