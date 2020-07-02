package com.ya02wmsj_cecoe.linhaimodule.rx;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.socks.library.KLog;
import com.ya02wmsj_cecoe.linhaimodule.App;
import com.ya02wmsj_cecoe.linhaimodule.Constant;
import com.ya02wmsj_cecoe.linhaimodule.rx.gson.GsonConverterFactory;
import com.ya02wmsj_cecoe.linhaimodule.rx.porxy.IGlobalManager;
import com.ya02wmsj_cecoe.linhaimodule.rx.porxy.ProxyHandler;

import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

/**
 * @author Yang Shihao
 */
public class RetrofitUtils implements IGlobalManager {
    public static RetrofitUtils getInstance() {
        return Holder.INSTANCE;
    }

    private static Retrofit mRetrofit;
    private static OkHttpClient mClient;
    private PersistentCookieJar mPersistentCookieJar;

    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
            if (mClient == null) {
                mPersistentCookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.get().getApplication()));
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor(message -> KLog.d("json++++++++++", message));
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                mClient = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).addInterceptor(logging).cookieJar(mPersistentCookieJar).build();
            }

            mRetrofit = new Retrofit.Builder().baseUrl(Constant.getBaseInterfaceUrl()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(mClient).build();

        }
        return mRetrofit;
    }

    public void cookieClear() {
        if (mPersistentCookieJar != null) {
            mPersistentCookieJar.clear();
        }
    }


    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> cls) {
        T t = getRetrofit().create(cls);
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class<?>[]{cls}, new ProxyHandler(t, this));
    }

    @Override
    public void exitLogin() {
        //token失效,再次获取token失败,登录
//        new Handler(Looper.getMainLooper()).post(() -> {
//            cookieClear();
//            mClient.dispatcher().cancelAll();
//            Config.getInstance().exit();
//            Intent intent = new Intent(App.getInstance(), LoginActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.putExtra(Constant.KEY_BOOLEAN_1, true);
//            App.getInstance().startActivity(intent);
//        });
    }

    static class Holder {
        public static RetrofitUtils INSTANCE = new RetrofitUtils();
    }
}
