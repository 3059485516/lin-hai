package com.ya02wmsj_cecoe.linhaimodule.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.ya02wmsj_cecoe.linhaimodule.App;


@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class TDevice {
    private static Boolean _isTablet = null;

    public static float dpToPixel(float dp) {
        return dp * (getDisplayMetrics().densityDpi / 160F);
    }

    private static DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) App.get().getApplication().getApplicationContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics;
    }

    public static float getScreenWidth() {
        return getDisplayMetrics().widthPixels;
    }

    public static boolean isTablet() {
        if (_isTablet == null) {
            boolean flag;
            flag = (0xf & App.get().getApplication().getApplicationContext().getResources().getConfiguration().screenLayout) >= 3;
            _isTablet = flag;
        }
        return _isTablet;
    }

    // 获取手机型号
    public static String getMobileModel() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getVersionName() {
        String name = "";
        try {
            name = App.get().getApplication().getPackageManager().getPackageInfo(App.get().getApplication().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException ex) {
            ex.printStackTrace();
        }
        return name;
    }

    public static String getTdInfo() {
        return getMobileModel() + "~" + getVersionName();
    }

    public static boolean isWifiOpen() {
        boolean isWifiConnect = false;
        ConnectivityManager cm = (ConnectivityManager) App.get().getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        // check the networkInfos numbers
        NetworkInfo[] networkInfos = cm.getAllNetworkInfo();
        for (int i = 0; i < networkInfos.length; i++) {
            if (networkInfos[i].getState() == NetworkInfo.State.CONNECTED) {
                if (networkInfos[i].getType() == ConnectivityManager.TYPE_MOBILE) {
                    isWifiConnect = false;
                }
                if (networkInfos[i].getType() == ConnectivityManager.TYPE_WIFI) {
                    isWifiConnect = true;
                }
            }
        }
        return isWifiConnect;
    }
//
//    public static boolean hasInternet() {
//        boolean flag;
//        if (((ConnectivityManager) App.get().getSystemService("connectivity")).getActiveNetworkInfo() != null)
//            flag = true;
//        else flag = false;
//        return flag;
//    }

    /**
     * 检测当的网络（WLAN、3G/2G）状态
     *
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
}
