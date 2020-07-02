package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.ActionRecruitEntity;

/**
 * Created by BenyChan on 2019-07-23.
 */
public interface ActionRecruitContract {
    interface View extends IListView {

    }

    abstract class Presenter extends AListPresenter<View, ActionRecruitEntity> {
        public Presenter(View view) {
            super(view);
        }
    }
}
