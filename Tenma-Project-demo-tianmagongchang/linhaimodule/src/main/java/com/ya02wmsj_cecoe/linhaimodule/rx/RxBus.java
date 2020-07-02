package com.ya02wmsj_cecoe.linhaimodule.rx;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

/**
 * @author Yang Shihao
 */
public class RxBus {
    private static RxBus mRxBus;
    private FlowableProcessor<Object> mBus;

    private RxBus() {
        if (mBus == null) {
            mBus = PublishProcessor.create().toSerialized();
        }
    }

    public static RxBus getInstance() {
        RxBus rxBus = mRxBus;
        if (mRxBus == null) {
            synchronized (RxBus.class) {
                rxBus = mRxBus;
                if (mRxBus == null) {
                    rxBus = new RxBus();
                    mRxBus = rxBus;
                }
            }
        }
        return rxBus;
    }

    public void send(Object obj) {
        mBus.onNext(obj);
    }

    public <T> Flowable<T> register(Class<T> cls) {
        return mBus.ofType(cls);
    }

    public Flowable<Object> register() {
        return mBus;
    }
}
