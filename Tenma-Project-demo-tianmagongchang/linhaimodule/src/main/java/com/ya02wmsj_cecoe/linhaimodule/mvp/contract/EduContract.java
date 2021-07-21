package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;

/**
 * 教育服务
 */
public interface EduContract {
    interface View extends IView {
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }
        public abstract void clickContent();
    }
}
