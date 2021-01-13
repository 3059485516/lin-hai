package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;

import com.ya02wmsj_cecoe.linhaimodule.bean.AppraiseEntity;
import com.ya02wmsj_cecoe.linhaimodule.bean.HttpResult;
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
        mView.showDialog("正在投票...");
        addRx2Destroy(new RxSubscriber<HttpResult>(Api.consultNewOrSelectVote(map),mView) {
            @Override
            protected void _onNext(HttpResult httpResult) {
                if (httpResult != null) {
                    String desc = httpResult.getDesc();
                    if (50001 == httpResult.getResultCode()) {
                        toast(desc);
                    } else if (50000 == httpResult.getResultCode()) {
                        toast(desc);
                        mView.updateVoteCount(position);
                    }else {
                        toast(desc);
                    }
                } else {
                    toast("投票失败!");
                }
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
