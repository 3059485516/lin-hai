package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;

public interface ExchangeFuliContract {
    interface View extends IView {
        void updateCoins(String coins);
    }

    abstract class Presenter extends APresenter<View> {

        public Presenter(View view) {
            super(view);
        }

        public abstract void getVolunteerScore();
    }
}
