package com.ya02wmsj_cecoe.linhaimodule.utils;

import android.app.Activity;

import com.socks.library.KLog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhanglq
 */
public class AppManager {
    private static final String TAG = "AppManager";
    private List<WeakReference<Activity>> mStack = new ArrayList<WeakReference<Activity>>();

    public static AppManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 加入一个Activity到栈中
     */
    public void pushActivity(Activity activity) {
        synchronized (AppManager.class) {
            if (activity == null) {
                return;
            }
            if (mStack == null) {
                mStack = new ArrayList<>();
            }
            WeakReference<Activity> weakReference = new WeakReference<>(activity);
            mStack.add(weakReference);
        }
        KLog.d(TAG, "pushActivity: " + mStack.size());
    }

    /**
     * 移除指定的Activity
     */
    public void popActivity(Activity activity) {
        synchronized (AppManager.class) {
            if (activity == null) {
                return;
            }
            for (WeakReference<Activity> weakReference : mStack) {
                if (weakReference.get() == activity) {
                    mStack.remove(weakReference);
                    if (!activity.isFinishing()) {
                        activity.finish();
                    }
                    break;
                }
            }
        }
        KLog.d(TAG, "popActivity: " + mStack.size());
    }

    /**
     * 移除指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        if (mStack == null || mStack.isEmpty()) {
            return;
        }
        Iterator<WeakReference<Activity>> iterator = mStack.iterator();
        while (iterator.hasNext()) {
            WeakReference<Activity> weakReference = iterator.next();
            Activity activity = weakReference.get();
            if (activity.getClass().equals(cls)) {
                iterator.remove();
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
    }

    /**
     * 移除指定类名的Activity之外的所有Sctivity
     */
    public void finishAllActivityExceptAppoint(Class<?> cls) {
        if (mStack == null || mStack.isEmpty()) {
            return;
        }
        Iterator<WeakReference<Activity>> iterator = mStack.iterator();
        while (iterator.hasNext()) {
            WeakReference<Activity> weakReference = iterator.next();
            Activity activity = weakReference.get();
            if (activity.getClass().equals(cls)) {
                continue;
            }
            if (!activity.isFinishing()) {
                activity.finish();
            }
            iterator.remove();
        }
    }


    /**
     * 结束所有的Activity
     */
    public void exit() {
        synchronized (AppManager.class) {
            for (WeakReference<Activity> weakReference : mStack) {
                if (weakReference != null && weakReference.get() != null) {
                    weakReference.get().finish();
                }
            }
            mStack.clear();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }

    public static class Holder {
        public static final AppManager INSTANCE = new AppManager();
    }
}
