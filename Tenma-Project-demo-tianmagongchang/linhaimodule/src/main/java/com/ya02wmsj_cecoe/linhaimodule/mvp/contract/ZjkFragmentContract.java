package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.ZjkzjEntiey;

public interface ZjkFragmentContract {
    interface View extends IListView {

    }

    abstract class Presenter extends AListPresenter<View, ZjkzjEntiey> {
        private String mId;

        public Presenter(View view) {
            super(view);
        }

        public void setId(String id) {
            mId = id;
        }

        public String getId() {
            return mId;
        }
    }
}
