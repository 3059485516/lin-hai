package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.EduEntity;

/**
 * 教育查询
 */
public interface EduCheckContract {
    interface View extends IListView {
    }

    abstract class Presenter extends AListPresenter<View, EduEntity> {
        public Presenter(View view) {
            super(view);
        }
        public abstract void clickContent();
    }
}
