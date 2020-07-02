package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.AListPresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IListView;
import com.ya02wmsj_cecoe.linhaimodule.bean.ExchangeEntitly;

import java.util.Map;

public interface ExchangeFragmentContract {
    interface View extends IListView {
        void exchangeCompleted();
    }

    abstract class Presenter extends AListPresenter<View, ExchangeEntitly> {

        public Presenter(View view) {
            super(view);
        }

        public abstract void exchange(Map<String, Object> map);
    }
}
