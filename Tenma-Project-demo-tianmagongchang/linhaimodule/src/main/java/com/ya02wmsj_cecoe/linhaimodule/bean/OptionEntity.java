package com.ya02wmsj_cecoe.linhaimodule.bean;

import java.io.Serializable;

/**
 * Created by BenyChan on 2019-06-20.
 */
public class OptionEntity implements Serializable {

    /**
     * activity_id : 46
     * id : 3
     * vote_number : 0
     * option_desc : 凑合啦
     */

    private String activity_id;
    private String id;
    private String vote_number;
    private String option_desc;

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVote_number() {
        return vote_number;
    }

    public void setVote_number(String vote_number) {
        this.vote_number = vote_number;
    }

    public String getOption_desc() {
        return option_desc;
    }

    public void setOption_desc(String option_desc) {
        this.option_desc = option_desc;
    }
}
