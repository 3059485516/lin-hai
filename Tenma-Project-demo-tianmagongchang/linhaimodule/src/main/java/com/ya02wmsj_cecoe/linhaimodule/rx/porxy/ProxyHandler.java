package com.ya02wmsj_cecoe.linhaimodule.rx.porxy;

import android.text.TextUtils;

import com.socks.library.KLog;
import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.rx.HttpCode;
import com.ya02wmsj_cecoe.linhaimodule.rx.exception.ApiException;
import com.ya02wmsj_cecoe.linhaimodule.rx.exception.TokenInvalidException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

public class ProxyHandler implements InvocationHandler {
    private static final String TAG = "ProxyHandler";
    private final static int REFRESH_TOKEN_VALID_TIME = 60 * 1000;
    private volatile long mTokenChangedTime = 0;
    private boolean mIsTokenNeedRefresh;
    private int tryCount;
    private Object mProxyObject;
    private IGlobalManager mGlobalManager;

    public ProxyHandler(Object proxyObject, IGlobalManager globalManager) {
        mProxyObject = proxyObject;
        mGlobalManager = globalManager;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) {
        return Observable.just(true).flatMap((Function<Object, ObservableSource<?>>) o -> {
            try {
                if (mIsTokenNeedRefresh) {
                    KLog.d(TAG, "apply: 可以刷新");
//                    updateMethodToken(method, args);
                }
                return (Observable<?>) method.invoke(mProxyObject, args);
            } catch (InvocationTargetException e) {
                throw new ApiException(HttpCode.CODE_30001.getCode());
            } catch (IllegalAccessException e) {
                throw new ApiException(HttpCode.CODE_30001.getCode());
            }
        }).retryWhen(throwableObservable -> throwableObservable.flatMap((Function<Throwable, ObservableSource<?>>) throwable -> {
            if (throwable instanceof TokenInvalidException) {
                return refreshTokenWhenTokenInvalid(throwable);
            }
            return Observable.error(throwable);
        }));
    }

    private Disposable mDisposable;

    private synchronized Observable<?> refreshTokenWhenTokenInvalid(Throwable throwable) {
        synchronized (ProxyHandler.class) {
            if (!TextUtils.isEmpty(Config.getInstance().getTMUser().getToken())) {
                Config.getInstance().uploadUserInfo(Config.getInstance().getTMUser());
                return Observable.just(true);
            } else {
                Config.getInstance().gotoLoginAct();
                return Observable.error(throwable);
            }
//            final Config config = Config.getInstance();
//            final String phone = config.getPhone();
//            final String username = config.getLoginName();
//            final String pwd = config.getPassword();
//            KLog.d(TAG, "旧的TOKEN = " + Config.getInstance().getUser().getToken());
//            if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pwd)) {
//                exit();
//                return Observable.error(throwable);
//            } else if (System.currentTimeMillis() - mTokenChangedTime < REFRESH_TOKEN_VALID_TIME) {
//                tryCount++;
//                if (tryCount > 10) {
//                    mTokenChangedTime = 0;
//                    exit();
//                    return Observable.error(throwable);
//                }
//                mIsTokenNeedRefresh = true;
//                return Observable.just(true);
//            } else {
//                String imei = AppUtils.getIMEI(App.getInstance(), phone);
//                mDisposable = new RxSubscriber<User>(Api.loginByPassword(username, pwd, imei)) {
//                    @Override
//                    protected void _onNext(User user) {
//                        tryCount = 0;
//                        KLog.d(TAG, "获取新的TOKEN成功 = " + user.getToken());
//                        mTokenChangedTime = System.currentTimeMillis();
//                        Config.getInstance().setUser(user);
//                        Config.getInstance().saveLoginInfo(phone, username, pwd, user.getToken(), true);
//                        mIsTokenNeedRefresh = true;
//                    }
//
//                    @Override
//                    protected void _onError(String code) {
//                        KLog.d(TAG, "获取新的token失败: " + HttpCode.getMsg(code));
//                        //登陆失败,跳转其他页面,关闭其他所有Activity
//                        exit();
//                        dis();
//                    }
//                }.getSubscribe();
//            }
        }
    }

    private void exit() {
        tryCount = 0;
        mGlobalManager.exitLogin();
    }

    private void dis() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    private void updateMethodToken(Method method, Object[] args) {
        if (!mIsTokenNeedRefresh) {
            return;
        }
//        Annotation[][] annotationsArray = method.getParameterAnnotations();
//        Annotation[] annotations;
//        if (annotationsArray != null && annotationsArray.length > 0) {
//            for (int i = 0; i < annotationsArray.length; i++) {
//                annotations = annotationsArray[i];
//                for (Annotation annotation : annotations) {
//                    if (annotation instanceof Field) {
//                        if ("TOKEN".equals(((Field) annotation).value())) {
//                            args[i] = Config.getInstance().getUser().getToken();
//                        }
//                    } else if (annotation instanceof Part) {
//
//                    } else if (annotation instanceof FieldMap) {
//                        Map arg = (Map) args[i];
//                        if (arg.containsKey("TOKEN")) {
//                            ((Map) args[i]).put("TOKEN", Config.getInstance().getUser().getToken());
//                        }
//                    } else if (annotation instanceof PartMap) {
//                        Map arg = (Map) args[i];
//                        if (arg.containsKey("TOKEN")) {
//                            ((Map) args[i]).put("TOKEN", RequestBody.create(MediaType.parse("text/plain"), Config.getInstance().getUser().getToken()));
//                        }
//                    }
//                }
//            }
//        }
//        mIsTokenNeedRefresh = false;
    }
}
