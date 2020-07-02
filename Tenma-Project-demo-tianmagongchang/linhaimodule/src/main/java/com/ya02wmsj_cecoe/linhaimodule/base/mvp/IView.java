package com.ya02wmsj_cecoe.linhaimodule.base.mvp;

import android.content.Intent;
import android.support.annotation.StringRes;


public interface IView {
    void showDialog();

    void showDialog(String message);

    void dismissDialog();

    void toast(String msg);

    void toast(@StringRes int resId);

    void gotoActivity(Intent intent);

    void gotoActivityAndFinish(Intent intent);

    void finishActivity();
}
