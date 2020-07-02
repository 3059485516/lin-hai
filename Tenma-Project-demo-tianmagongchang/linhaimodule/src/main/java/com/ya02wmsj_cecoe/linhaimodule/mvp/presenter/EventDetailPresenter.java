package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.EventDetail;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.EventDetailContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

/**
 * Created by BenyChan on 2019-07-30.
 */
public class EventDetailPresenter extends EventDetailContract.Presenter {
    private String mEventId;

    public EventDetailPresenter(EventDetailContract.View view, String event_id) {
        super(view);
        mEventId = event_id;
    }

    @Override
    public void getEventDetail() {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<EventDetail>(Api.getEventDetail(mEventId)) {
            @Override
            protected void _onNext(EventDetail o) {
                mView.dismissDialog();
                if (o != null) {
                    mView.updateDetail(o);
                }
            }
        });
    }
}
