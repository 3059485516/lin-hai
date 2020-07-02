package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.VenueDetailEntity;

/**
 * Created by BenyChan on 2019-07-26.
 */
public interface VenueDetailContract {
    interface View extends IView {
        void update(VenueDetailEntity entity);
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void getStadiaDetail();
    }
}
