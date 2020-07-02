package com.ya02wmsj_cecoe.linhaimodule.rx;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.socks.library.KLog;
import com.ya02wmsj_cecoe.linhaimodule.App;
import com.ya02wmsj_cecoe.linhaimodule.Config;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;
import com.ya02wmsj_cecoe.linhaimodule.rx.exception.ApiException;
import com.ya02wmsj_cecoe.linhaimodule.rx.exception.JsonException;
import com.ya02wmsj_cecoe.linhaimodule.utils.T;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;

import static com.ya02wmsj_cecoe.linhaimodule.rx.HttpCode.CODE_4030;

public abstract class RxSubscriber<D> {
    private static final String TAG = "RxSubscriber";
    private Disposable mSubscribe;

    public RxSubscriber(Observable<D> observable) {
        subscribe(observable, null);
    }

    public RxSubscriber(Observable<D> observable, IView view) {
        subscribe(observable, view);
    }

    private void subscribe(Observable<D> observable, final IView view) {
        mSubscribe = observable.switchIfEmpty(observer -> _onError(HttpCode.CODE_30001.getCode())).subscribe(d -> {
            if (view != null) {
                view.dismissDialog();
            }
            if (d != null) {
                _onNext(d);
            } else {
                _onError(HttpCode.CODE_30002.getCode());
            }
        }, throwable -> {
            if (view != null) {
                view.dismissDialog();
            }
            if (throwable instanceof HttpException || throwable instanceof CompositeException || throwable instanceof SocketTimeoutException || throwable instanceof NoRouteToHostException || throwable instanceof ConnectException || throwable instanceof UnknownHostException) {
                _onError(HttpCode.CODE_30001.getCode());
            } else if (throwable instanceof ApiException) {
                _onError(throwable.getMessage());
            } else if (throwable instanceof JsonException) {
                _onError(HttpCode.CODE_30003.getCode());
            } else {
                KLog.d(throwable.getMessage());
                _onError(HttpCode.CODE_30001.getCode());
            }
        });
    }

    public Disposable getSubscribe() {
        return mSubscribe;
    }

    protected abstract void _onNext(D d);

    protected void _onError(String code) {
        if (code.equals(CODE_4030.getCode())) {
            Config.getInstance().loginVolunteer();
        }
        T.showShort(App.get().getApplication(), HttpCode.getMsg(code));
    }
}
