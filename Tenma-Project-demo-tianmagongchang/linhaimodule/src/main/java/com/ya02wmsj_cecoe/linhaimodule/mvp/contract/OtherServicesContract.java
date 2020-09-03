package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;

/**
 * 我要点服务 -- 其他服务
 */
public interface OtherServicesContract {
    interface View extends IView {
        String getName();
        String getPhone();
        String getRegionCode();
        String getAddress();
        String getContent();
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void submitData();
    }
}
