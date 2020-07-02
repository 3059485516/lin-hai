package com.ya02wmsj_cecoe.linhaimodule.mvp.presenter;



import com.ya02wmsj_cecoe.linhaimodule.mvp.contract.QuestionContract;
import com.ya02wmsj_cecoe.linhaimodule.rx.Api;
import com.ya02wmsj_cecoe.linhaimodule.rx.RxSubscriber;

import java.util.Map;

/**
 * Created by BenyChan on 2019-06-20.
 */
public class QuestionPresenter extends QuestionContract.Presenter {
    private String mActionId;

    public QuestionPresenter(QuestionContract.View view, String action_id) {
        super(view);
        mActionId = action_id;
    }

    @Override
    public void answerQuestion(Map<String, Object> map) {
        addRx2Destroy(new RxSubscriber<Object>(Api.answerQuestion(map)) {
            @Override
            protected void _onNext(Object o) {
                toast("提交成功");
                getAnswerScores(mActionId);
            }

            @Override
            protected void _onError(String code) {
                super._onError(code);
                if (code.contains("20065")) {
                    getAnswerScores(mActionId);
                }
            }
        });
    }

    @Override
    public void getAnswerScores(String action_id) {
        addRx2Destroy(new RxSubscriber<String>(Api.getAnswerScores(action_id)) {
            @Override
            protected void _onNext(String score) {
                mView.showScore("你的分数是：" + score);
            }
        });
    }
}
