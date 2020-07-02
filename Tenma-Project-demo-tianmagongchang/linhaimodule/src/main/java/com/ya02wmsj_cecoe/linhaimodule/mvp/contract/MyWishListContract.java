package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.WishListEntity;

/**
 * Created by BenyChan on 2019-08-15.
 */
public interface MyWishListContract {
    interface View extends IListView {

    }

    abstract class Presenter extends AListPresenter<View, WishListEntity> {
        public Presenter(View view) {
            super(view);
        }
    }
}
