package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import android.support.v4.app.Fragment;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;

import java.util.List;

/**
 * Created by BenyChan on 2019-08-07.
 */
public interface WorthModifyContract {
    interface View extends IView {
        void update();
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void getNodeList();

        public abstract List<String> getTitles();

        public abstract List<Fragment> getFragments();
    }
}
