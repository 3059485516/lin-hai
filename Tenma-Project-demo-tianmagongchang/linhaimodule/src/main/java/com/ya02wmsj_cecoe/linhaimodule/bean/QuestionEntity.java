package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BenyChan on 2019-06-20.
 */
public class QuestionEntity implements Serializable {

    /**
     * id : 7
     * ques_title :
     * ques_content : 一年中的哪一月天数是不固定的?
     * activity_id : 45
     * ques_pic :
     * score : 10
     * type : 单选
     * standard_answer : B
     */

    private String id;
    private String ques_title;
    private String ques_content;
    private String activity_id;
    private String ques_pic;
    private String score;
    private String type;
    private String standard_answer;
    private List<QuestionItem> options;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQues_title() {
        return ques_title;
    }

    public void setQues_title(String ques_title) {
        this.ques_title = ques_title;
    }

    public String getQues_content() {
        return ques_content;
    }

    public void setQues_content(String ques_content) {
        this.ques_content = ques_content;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getQues_pic() {
        return ques_pic;
    }

    public void setQues_pic(String ques_pic) {
        this.ques_pic = ques_pic;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStandard_answer() {
        return standard_answer;
    }

    public void setStandard_answer(String standard_answer) {
        this.standard_answer = standard_answer;
    }

    public List<QuestionItem> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionItem> options) {
        this.options = options;
    }
}
