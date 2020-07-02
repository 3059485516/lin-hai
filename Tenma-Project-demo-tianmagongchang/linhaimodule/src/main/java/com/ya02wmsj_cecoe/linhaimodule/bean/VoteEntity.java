package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-06-19.
 */
public class VoteEntity implements Serializable {

    /**
     * content : 内容1
     * id : 10
     * title : 标题1
     * vote_number : 0
     * pic : /uploads/pic/20190617/1560727459_306826162.jpg
     * activity_id : 30
     */

    private String content;
    private String id;
    private String title;
    private String vote_number;
    private String pic;
    private String activity_id;
    private int score_me;
    private float score_ave;

    private int my_score;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVote_number() {
        return vote_number;
    }

    public void setVote_number(String vote_number) {
        this.vote_number = vote_number;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public int getScore_me() {
        return score_me;
    }

    public void setScore_me(int score_me) {
        this.score_me = score_me;
    }

    public float getScore_ave() {
        return score_ave;
    }

    public void setScore_ave(float score_ave) {
        this.score_ave = score_ave;
    }

    public int getMy_score() {
        return my_score;
    }

    public void setMy_score(int my_score) {
        this.my_score = my_score;
    }
}
