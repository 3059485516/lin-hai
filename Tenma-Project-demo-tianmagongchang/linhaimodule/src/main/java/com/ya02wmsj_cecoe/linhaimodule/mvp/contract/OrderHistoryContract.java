package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrderHistoryEntity;

/**
 * Created by BenyChan on 2019-08-02.
 */
public interface OrderHistoryContract {
    interface View extends IListView {

    }

    abstract class Presenter extends AListPresenter<View, OrderHistoryEntity> {
        public Presenter(View view) {
            super(view);
        }
    }
}
