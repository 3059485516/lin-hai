package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.ZjkProfessionEntity;

import java.util.List;

public interface ZjkContract {
    interface View extends IView {
        void updateView(List<ZjkProfessionEntity> date);
    }

    abstract class Presenter extends APresenter<View> {

        public Presenter(View view) {
            super(view);
        }

        public abstract void getDataList();
    }
}
