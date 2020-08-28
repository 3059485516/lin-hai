package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.OrginazeListEntity;

public interface OrginazeContract {
    interface View extends IListView {
        String getSearchStr();
    }

    abstract class Presenter extends AListPresenter<View, OrginazeListEntity> {
        public Presenter(View view) {
            super(view);
        }
    }
}
