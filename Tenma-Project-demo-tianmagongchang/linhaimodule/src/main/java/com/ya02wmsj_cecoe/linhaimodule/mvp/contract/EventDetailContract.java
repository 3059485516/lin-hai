package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.EventDetail;

/**
 * Created by BenyChan on 2019-07-30.
 */
public interface EventDetailContract {
    interface View extends IView {
        void updateDetail(EventDetail entity);
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void getEventDetail();
    }
}
