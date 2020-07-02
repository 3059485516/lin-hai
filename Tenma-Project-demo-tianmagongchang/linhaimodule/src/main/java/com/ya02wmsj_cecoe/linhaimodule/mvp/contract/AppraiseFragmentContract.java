package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;

/**
 * Created by BenyChan on 2019-07-29.
 */
public interface AppraiseFragmentContract {
    interface View extends IListView {
        void showActionThemeDialog();
    }

    abstract class Presenter extends AListPresenter<View, AppraiseEntity> {
        public Presenter(View view) {
            super(view);
        }
    }
}
