package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;
import java.util.List;

public class LtEvaMainEntity implements Serializable {

    /**
     * final_audit_point : 0
     * first_auditor : null
     * final_audit_point_reason :
     * rule_title : null
     * pic : ["http://47.99.86.101:8040//uploads/ya02lhwhlt_wdhaw/pic/20200324/82521fd53d2572b52b07146ba61bdc39.jpg","http://47.99.86.101:8040//uploads/ya02lhwhlt_wdhaw/pic/20200324/289931d9ce8f8f211cf9a16653ad8e94.jpg"]
     * reject_reason :
     * title :
     * content :
     * ca_name : 大洋社区文化礼堂
     * report_time : 2020-03-24 10:26:51
     * ctime : 2021-03-17 23:42:22
     * final_audit_time : 1970-01-01 00:00:00
     * first_audit_time : 1970-01-01 00:00:00
     * final_auditor : null
     * status : 待审核
     */
    private String final_audit_point;
    private String first_auditor;
    private String final_audit_point_reason;
    private String rule_title;
    private List<String> pic;
    private String reject_reason;
    private String title;
    private String content;
    private String ca_name;
    private String report_time;
    private String ctime;
    private String final_audit_time;
    private String first_audit_time;
    private String final_auditor;
    private String status;

    public void setFinal_audit_point(String final_audit_point) {
        this.final_audit_point = final_audit_point;
    }

    public void setFirst_auditor(String first_auditor) {
        this.first_auditor = first_auditor;
    }

    public void setFinal_audit_point_reason(String final_audit_point_reason) {
        this.final_audit_point_reason = final_audit_point_reason;
    }

    public void setRule_title(String rule_title) {
        this.rule_title = rule_title;
    }

    public void setPic(List<String> pic) {
        this.pic = pic;
    }

    public void setReject_reason(String reject_reason) {
        this.reject_reason = reject_reason;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCa_name(String ca_name) {
        this.ca_name = ca_name;
    }

    public void setReport_time(String report_time) {
        this.report_time = report_time;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public void setFinal_audit_time(String final_audit_time) {
        this.final_audit_time = final_audit_time;
    }

    public void setFirst_audit_time(String first_audit_time) {
        this.first_audit_time = first_audit_time;
    }

    public void setFinal_auditor(String final_auditor) {
        this.final_auditor = final_auditor;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFinal_audit_point() {
        return final_audit_point;
    }

    public String getFirst_auditor() {
        return first_auditor;
    }

    public String getFinal_audit_point_reason() {
        return final_audit_point_reason;
    }

    public String getRule_title() {
        return rule_title;
    }

    public List<String> getPic() {
        return pic;
    }

    public String getReject_reason() {
        return reject_reason;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCa_name() {
        return ca_name;
    }

    public String getReport_time() {
        return report_time;
    }

    public String getCtime() {
        return ctime;
    }

    public String getFinal_audit_time() {
        return final_audit_time;
    }

    public String getFirst_audit_time() {
        return first_audit_time;
    }

    public String getFinal_auditor() {
        return final_auditor;
    }

    public String getStatus() {
        return status;
    }
}
