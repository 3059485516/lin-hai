package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;

import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;

/**
 * Created by BenyChan on 2019-07-23.
 */
public interface BindPhoneContract {
    interface View extends IView {
        void setCodeEnable(boolean enable);     //设置验证码是否可以获取

        void setCountDownText(String text);     //设置验证码倒计时
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void getSMSCode(String phone);

        public abstract void bindPhone(String phone, String code);
    }
}
