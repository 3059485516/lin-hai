package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppealHistoryEntity;

/**
 * Created by BenyChan on 2019-07-30.
 */
public interface AppealHistoryContract {
    interface View extends IListView {

    }

    abstract class Presenter extends AListPresenter<View, AppealHistoryEntity> {
        public Presenter(View view) {
            super(view);
        }
    }
}
