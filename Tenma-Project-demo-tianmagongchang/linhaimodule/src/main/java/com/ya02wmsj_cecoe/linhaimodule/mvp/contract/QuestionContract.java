package com.ya02wmsj_cecoe.linhaimodule.mvp.contract;


import com.ya02wmsj_cecoe.linhaimodule.base.mvp.APresenter;
import com.ya02wmsj_cecoe.linhaimodule.base.mvp.IView;

import java.util.Map;

/**
 * Created by BenyChan on 2019-06-20.
 */
public interface QuestionContract {
    interface View extends IView {
        void showScore(String text);
    }

    abstract class Presenter extends APresenter<View> {
        public Presenter(View view) {
            super(view);
        }

        public abstract void answerQuestion(Map<String, Object> map);

        public abstract void getAnswerScores(String action_id);
    }
}
