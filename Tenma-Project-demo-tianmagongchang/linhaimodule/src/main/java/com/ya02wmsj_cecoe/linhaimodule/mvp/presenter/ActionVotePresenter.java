package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;



import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ActionWebContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.Map;

/**
 * Created by BenyChan on 2019-06-20.
 */
public class ActionVotePresenter extends ActionWebContract.Presenter {
    public ActionVotePresenter(ActionWebContract.View view) {
        super(view);
    }

    @Override
    public void vote(int position, Map<String, Object> map) {
        addRx2Destroy(new RxSubscriber<String>(Api.consultOrSelectVote(map)) {
            @Override
            protected void _onNext(String str) {
                toast("投票成功");
                mView.updateVoteCount(position, str);
            }
        });
    }
}
