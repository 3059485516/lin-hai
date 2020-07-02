package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;


import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.WishDetailEntity;

import java.util.Map;

/**
 * Created by BenyChan on 2019-08-15.
 */
public interface WishDetailContract {
    interface View extends IView {
        void updateDetail(WishDetailEntity entity);
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void getWishDetail();

        public abstract void applyWish(Map<String, Object> map);
    }
}
