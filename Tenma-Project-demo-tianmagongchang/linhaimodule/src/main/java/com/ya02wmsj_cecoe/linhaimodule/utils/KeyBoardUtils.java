package com.ya02wmsj_cecoe.linhaimodule.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;


public class KeyBoardUtils {

    public static void closeSoftInput(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getApplicationWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }
}
