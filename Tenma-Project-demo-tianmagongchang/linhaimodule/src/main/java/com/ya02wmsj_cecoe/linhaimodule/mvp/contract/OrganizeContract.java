package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrganizeListEntity;

/**
 * Created by BenyChan on 2019-07-25.
 */
public interface OrganizeContract {
    interface View extends IListView {

    }

    abstract class Presenter extends AListPresenter<View, OrganizeListEntity> {
        public Presenter(View view) {
            super(view);
        }
    }
}
