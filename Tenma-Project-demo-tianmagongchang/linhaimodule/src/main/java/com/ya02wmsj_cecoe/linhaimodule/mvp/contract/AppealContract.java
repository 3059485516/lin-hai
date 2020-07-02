package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;

import java.util.Map;

/**
 * Created by BenyChan on 2019-07-27.
 */
public interface AppealContract {
    interface View extends IView {

    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void addContentAppeal(Map<String, Object> map);
    }
}
