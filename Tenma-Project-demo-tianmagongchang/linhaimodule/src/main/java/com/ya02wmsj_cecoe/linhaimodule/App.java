package com.ya02wmsj_cecoe.linhaimodule;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.netease.nim.yl.NeteaseApp;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.socks.library.KLog;
import com.ya02wmsj_cecoe.linhaimodule.mvp.activity.MainActivity;

/**
 * Created by BenyChan on 2019-07-09.
 */
public class App {
    @SuppressLint("StaticFieldLeak")
    private Application application;
    private static App instance;

    private App() {
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> new ClassicsHeader(context));
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(context));
    }

    public static App get() {
        if (instance == null) {
            synchronized (App.class) {
                instance = new App();
            }
        }
        return instance;
    }

    public void initTMUser() {
        KLog.init(BuildConfig.DEBUG);
        Config.getInstance();
    }

    public void setApplication(Application application) {
        this.application = application;
//        init();
    }

    public Application getApplication() {
        return application;
    }

    public static void init(Context context) {
        NeteaseApp.neteaseIMInit(context, Constant.getBaseUrl(), MainActivity.class, MainActivity.class);
    }
}
