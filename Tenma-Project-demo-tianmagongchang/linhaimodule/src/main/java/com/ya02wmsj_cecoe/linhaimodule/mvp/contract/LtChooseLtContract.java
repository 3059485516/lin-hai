package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import android.support.v4.app.Fragment;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.LtStreetEntity;

import java.util.ArrayList;
import java.util.List;

public interface LtChooseLtContract {
    interface View extends IView {
        void update(List<LtStreetEntity> list);
    }

    abstract class Presenter extends APresenter<View> {
        private List<String> mTitle = new ArrayList<>();
        private List<Fragment> mFragment = new ArrayList<>();

        public Presenter(View view) {
            super(view);
        }

        public abstract void getAllCABranchList();

        public List<String> getTitles() {
            return mTitle;
        }

        public List<Fragment> getFragments() {
            return mFragment;
        }
    }
}
