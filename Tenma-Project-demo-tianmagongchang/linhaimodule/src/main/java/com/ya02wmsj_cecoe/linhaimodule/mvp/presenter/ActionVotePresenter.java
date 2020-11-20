package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.ActionWebContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.List;
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

    @Override
    public void getOnlineActivityDetail(String id) {
        mView.showDialog();
        addRx2Destroy(new RxSubscriber<List<AppraiseEntity>>(Api.getOnlineActivityDetail(id)) {
            @Override
            protected void _onNext(List<AppraiseEntity> list) {
                mView.dismissDialog();
                if (list != null && list.size() > 0) {
                    mView.updateInfo(list.get(0));
                }
            }

            @Override
            protected void _onError(String code) {
                mView.dismissDialog();
                super._onError(code);
            }
        });
    }
}
