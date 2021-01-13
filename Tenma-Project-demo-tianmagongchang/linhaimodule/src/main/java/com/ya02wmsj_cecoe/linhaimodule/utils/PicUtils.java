package com.ya02wmsj_cecoe.linhaimodule.utils;

import android.support.media.ExifInterface;
import android.text.TextUtils;

public class PicUtils {

    public static String getPicTime(String oldPath) {
        String time = "";
        String model = "";
        ExifInterface exifInterface = null;
        try {
            exifInterface = new ExifInterface(oldPath);
            time = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);
            model = exifInterface.getAttribute(ExifInterface.TAG_MODEL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(time)) {
            return "";
        } else {
            return time + "@" + model;
        }
    }
}
