package com.ya02wmsj_cecoe.linhaimodule.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

public class T {
    private static Toast toast;
    private static boolean isShow = true;

    public static void showShort(Context context, CharSequence message) {
        show(context, message, Toast.LENGTH_SHORT);
    }

    public static void showShort(Context context, @StringRes int resId) {
        show(context, resId, Toast.LENGTH_SHORT);
    }

    public static void showLong(Context context, CharSequence message) {
        show(context, message, Toast.LENGTH_LONG);
    }

    public static void showLong(Context context, @StringRes int resId) {
        show(context, resId, Toast.LENGTH_LONG);
    }

    @SuppressLint("ShowToast")
    private static void show(Context context, CharSequence message, int duration) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context.getApplicationContext(), message, duration);
            } else {
                toast.setText(message);
                toast.setDuration(duration);
            }
            toast.show();
        }
    }

    @SuppressLint("ShowToast")
    private static void show(Context context, int resId, int duration) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context.getApplicationContext(), resId, duration);
            } else {
                toast.setText(resId);
                toast.setDuration(duration);
            }
            toast.show();
        }
    }
}