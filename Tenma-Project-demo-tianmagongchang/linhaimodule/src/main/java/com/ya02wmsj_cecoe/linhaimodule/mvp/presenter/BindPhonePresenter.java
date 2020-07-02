package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import android.text.TextUtils;

import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.BindPhoneContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by BenyChan on 2019-07-23.
 */
public class BindPhonePresenter extends BindPhoneContract.Presenter {
    private Disposable mTimer;

    public BindPhonePresenter(BindPhoneContract.View view) {
        super(view);
    }

    @Override
    public void getSMSCode(String phone) {
        if (TextUtils.isEmpty(phone)) {
            toast("请填写手机号码");
            return;
        }
        addRx2Destroy(new RxSubscriber<Object>(Api.getSMSCode(phone)) {
            @Override
            protected void _onNext(Object o) {
                mView.setCodeEnable(false);
                startTimer();
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
                closeTimer();
            }
        });
    }

    @Override
    public void bindPhone(String phone, String code) {
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(code)) {
            toast("手机号或者验证码不能为空");
            return;
        }
        addRx2Destroy(new RxSubscriber<Object>(Api.bindMobile(phone, code)) {
            @Override
            protected void _onNext(Object o) {
                toast("绑定成功！");
                mView.finishActivity();
            }
        });
    }

    private void startTimer() {
        mTimer = Flowable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mView.setCountDownText((60 - aLong) + "s");
                        if (60 - aLong <= 0) {
                            closeTimer();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        addRx2Destroy(mTimer);
    }

    private void closeTimer() {
        mView.setCodeEnable(true);
        mView.setCountDownText("获取验证码");
        if (mTimer != null && !mTimer.isDisposed()) {
            mTimer.dispose();
        }
    }
}
