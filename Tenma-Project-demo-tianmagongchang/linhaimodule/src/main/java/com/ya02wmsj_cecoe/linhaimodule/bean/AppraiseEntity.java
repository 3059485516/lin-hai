package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BenyChan on 2019-06-19.
 */
public class AppraiseEntity implements Serializable {

    /**
     * status : 已结束
     * need_sign : n
     * icon_path :
     * man_id : 4d92a971360243b0bab91e2ffc6740e3
     * ctime : 2019-06-15 19:47:33
     * node_id : 7
     * content : 1
     * id : 1
     * form_name : 征文
     * address :
     * end_time : 2019-06-15 00:00:00
     * name : 县级活动-超管发布
     * start_time : 2019-06-15 00:00:00
     * pstatus : STATUS003
     * form_id : 3
     * participate_total ：5
     */

    private String status;
    private String need_sign;
    private String icon_path;
    private String man_id;
    private String ctime;
    private String node_id;
    private String content;
    private String id;
    private String form_name;
    private String address;
    private String end_time;
    private String name;
    private String title;
    private String pic_url;
    private String start_time;
    private String pstatus;
    private String form_id;
    private boolean participate;        //是否参与
    private List<VoteEntity> voteInfo;
    private List<OptionEntity> optionInfo;
    private List<QuestionEntity> questionInfo;
    private List<ScoreInfo> scoreInfo;

    public List<ScoreInfo> getScoreInfo() {
        return scoreInfo;
    }

    public void setScoreInfo(List<ScoreInfo> scoreInfo) {
        this.scoreInfo = scoreInfo;
    }

    private String participate_total;

    public String getParticipate_total() {
        return participate_total;
    }

    public void setParticipate_total(String participate_total) {
        this.participate_total = participate_total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNeed_sign() {
        return need_sign;
    }

    public void setNeed_sign(String need_sign) {
        this.need_sign = need_sign;
    }

    public String getIcon_path() {
        return icon_path;
    }

    public void setIcon_path(String icon_path) {
        this.icon_path = icon_path;
    }

    public String getMan_id() {
        return man_id;
    }

    public void setMan_id(String man_id) {
        this.man_id = man_id;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

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

    public String getForm_name() {
        return form_name;
    }

    public void setForm_name(String form_name) {
        this.form_name = form_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public boolean getParticipate() {
        return participate;
    }

    public void setParticipate(boolean participate) {
        this.participate = participate;
    }

    public List<VoteEntity> getVoteInfo() {
        return voteInfo;
    }

    public void setVoteInfo(List<VoteEntity> voteInfo) {
        this.voteInfo = voteInfo;
    }

    public List<OptionEntity> getOptionInfo() {
        return optionInfo;
    }

    public void setOptionInfo(List<OptionEntity> optionInfo) {
        this.optionInfo = optionInfo;
    }

    public List<QuestionEntity> getQuestionInfo() {
        return questionInfo;
    }

    public void setQuestionInfo(List<QuestionEntity> questionInfo) {
        this.questionInfo = questionInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}
