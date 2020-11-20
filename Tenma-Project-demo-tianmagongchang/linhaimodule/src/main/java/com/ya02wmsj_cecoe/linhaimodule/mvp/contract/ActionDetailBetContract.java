package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.bean.ActionDetailBetEntity;

public interface ActionDetailBetContract {
    interface View extends IView {
        void updateView(ActionDetailBetEntity entity);
        void updateBnt(int status);
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void getRecruitDetail(String id);

        public abstract void signupRecruit(String id);

        public abstract void signoutRecruit(String id);
    }
}
