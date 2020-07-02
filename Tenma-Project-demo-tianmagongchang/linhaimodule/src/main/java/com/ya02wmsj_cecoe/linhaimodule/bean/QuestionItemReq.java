package com.ya02wmsj_cecoe.linhaimodule.bean;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-06-20.
 */
public class QuestionItemReq implements Serializable {

    /**
     * quesId : 9
     * quesOptNames : A
     */

    private String quesId;
    private String quesOptNames;

    public QuestionItemReq() {

    }

    public QuestionItemReq(String quesId, String quesOptNames) {
        this.quesId = quesId;
        this.quesOptNames = quesOptNames;
    }

    public String getQuesId() {
        return quesId;
    }

    public void setQuesId(String quesId) {
        this.quesId = quesId;
    }

    public String getQuesOptNames() {
        return quesOptNames;
    }

    public void setQuesOptNames(String quesOptNames) {
        this.quesOptNames = quesOptNames;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
